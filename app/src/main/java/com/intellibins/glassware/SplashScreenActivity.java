package com.intellibins.glassware;

import com.intellibins.glassware.binlocation.IFindBin;
import com.intellibins.glassware.dropofflocation.IFindDropOff;
import com.intellibins.glassware.model.Loc;
import com.intellibins.glassware.storelocation.GooglePlaceService;
import com.intellibins.glassware.storelocation.model.Photo;
import com.intellibins.glassware.storelocation.model.Place;
import com.intellibins.glassware.storelocation.model.Result;
import com.intellibins.glassware.userlocation.IUserLocation;
import com.intellibins.glassware.view.TuggableView;
import com.prt2121.glass.widget.SliderView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SplashScreenActivity extends BaseGlassActivity {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();

    @Inject
    IFindBin mBinLocation;

    @Inject
    IUserLocation mUserLocation;

    @Inject
    IFindDropOff mDropOffLocation;

    @Inject
    RestAdapter mRestAdapter;

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

        userLocation.flatMap(new Func1<Location, Observable<Place>>() {
            @Override
            public Observable<Place> call(Location location) {
                return mRestAdapter.create(GooglePlaceService.class)
                        .getPlaces(location.getLatitude() + "," + location.getLongitude(),
                                getResources().getString(R.string.place_api_key));
            }
        }).flatMap(new Func1<Place, Observable<Result>>() {
            @Override
            public Observable<Result> call(Place place) {
                return Observable.from(place.getResults());
            }
        }).first()
                .map(new Func1<Result, Loc>() {
                    @Override
                    public Loc call(Result result) {
                        List<Photo> photo = result.getPhotos();
                        String ref = (photo != null && !photo.isEmpty()) ? photo.get(0)
                                .getPhotoReference() : null;
                        getImage(ref);
                        com.intellibins.glassware.storelocation.model.Location location = result
                                .getGeometry().getLocation();
                        return new Loc.Builder(result.getName())
                                .address(result.getVicinity())
                                .latitude(location.getLat())
                                .longitude(location.getLng())
                                .build();
                    }
                }).subscribe(new Action1<Loc>() {
            @Override
            public void call(Loc loc) {
                Log.v(TAG, "store " + loc.name);
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

    private void getImage(String ref) {
        if (!TextUtils.isEmpty(ref)) {
            Bitmap bitmap = null;
            InputStream iStream = null;
            try {
                URL url = new URL(
                        "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&key="
                                + getResources().getString(R.string.place_api_key)
                                + "&photoreference="
                                + ref);
                HttpURLConnection urlConnection = (HttpURLConnection) url
                        .openConnection();
                urlConnection.connect();
                iStream = urlConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(iStream);
                saveBitmap(SplashScreenActivity.this, bitmap);
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            } finally {
                try {
                    if (iStream != null) {
                        iStream.close();
                    }
                } catch (Exception e) {
                    Log.d(TAG, e.toString());
                }
            }
        }
    }

    private String saveBitmap(Context context, Bitmap bmp) {
        FileOutputStream out = null;
        try {
            File file = File.createTempFile("store_image", ".jpg", context.getCacheDir());
            Log.v(TAG, "file " + file.getAbsolutePath());
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, out);
            return file.getName();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Throwable ignore) {
            }
        }
        return null;
    }
}
