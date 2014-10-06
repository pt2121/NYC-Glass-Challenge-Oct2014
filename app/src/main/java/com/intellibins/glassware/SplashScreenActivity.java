package com.intellibins.glassware;

import com.intellibins.glassware.binlocation.IFindBin;
import com.intellibins.glassware.dropofflocation.IFindDropOff;
import com.intellibins.glassware.model.Loc;
import com.intellibins.glassware.userlocation.IUserLocation;
import com.intellibins.glassware.view.TuggableView;
import com.prt2121.glass.widget.SliderView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class SplashScreenActivity extends BaseGlassActivity {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();

    @Inject
    IFindBin mBinLocation;

    @Inject
    IUserLocation mUserLocation;

    @Inject
    IFindDropOff mDropOffLocation;

    // wish Java supports currying :)
    Func1<Location, Observable<List<Loc>>> findClosestBins
            = new Func1<Location, Observable<List<Loc>>>() {
        @Override
        public Observable<List<Loc>> call(Location location) {
            return mBinLocation.getLocs()
                    .toSortedList(
                            new LocUtils()
                                    .compare(location.getLatitude(), location.getLongitude()));
        }
    };

    Func1<Location, Observable<List<Loc>>> findClosestDropOffs
            = new Func1<Location, Observable<List<Loc>>>() {
        @Override
        public Observable<List<Loc>> call(Location location) {
            return mDropOffLocation.getLocs()
                    .toSortedList(
                            new LocUtils()
                                    .compare(location.getLatitude(), location.getLongitude()));
        }
    };


    private TuggableView mTuggableView;

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntellibinsApp app = IntellibinsApp.get(this);
        app.inject(this);

        mTuggableView = new TuggableView(this, R.layout.activity_splash_screen);
        setContentView(mTuggableView);
        SliderView slider = (SliderView) mTuggableView.findViewById(R.id.progressBar);
        slider.startIndeterminate();

        mUserLocation.start();
        getNearestBinThenFinish();
    }

    private void getNearestBinThenFinish() {
        Observable<Location> userLocation = mUserLocation.observe()
                .take(1);

        userLocation.flatMap(findClosestDropOffs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Loc>>() {
                    @Override
                    public void call(List<Loc> locs) {
                        for (Loc loc : locs) {
                            Log.d(TAG, "drop-off " + loc.address);
                        }
                    }
                });

        mSubscription = userLocation
                .flatMap(findClosestBins)
                .flatMap(new Func1<List<Loc>, Observable<Loc>>() {
                    @Override
                    public Observable<Loc> call(List<Loc> locs) {
                        return Observable.from(locs);
                    }
                })
                .take(5)
                .toList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Loc>>() {
                    @Override
                    public void call(List<Loc> locs) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(
                                        new Intent(SplashScreenActivity.this, MenuActivity.class));
                            }
                        });
                        SplashScreenActivity.this.finish();
                        for (Loc loc : locs) {
                            Log.d(TAG, "loc " + loc.name);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTuggableView.activate();
    }

    @Override
    protected void onPause() {
        mTuggableView.deactivate();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserLocation.stop();
        mSubscription.unsubscribe();
    }

}
