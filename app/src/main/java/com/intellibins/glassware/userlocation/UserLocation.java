package com.intellibins.glassware.userlocation;

import android.app.Application;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by prt2121 on 9/30/14.
 */
public class UserLocation {

    private static final int MAX_TIME = 10 * 60 * 1000; // an old location expires in 10 mins

    protected final BehaviorSubject<Location> mSubject;

    private final MyLocationListener listener = new MyLocationListener();

    private final Criteria criteria = new Criteria();

    private LocationManager mLocationManager;

    public UserLocation(Application app) {
        mLocationManager = (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
        mSubject = BehaviorSubject.create(getLastBestLocation(MAX_TIME));
        mSubject.subscribeOn(Schedulers.io());
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
    }

    public void start() {
        final Looper looper = Looper.myLooper();
        mLocationManager.requestLocationUpdates(60 * 1000, 0, criteria, listener, looper);
    }

    public void stop() {
        mLocationManager.removeUpdates(listener);
        mSubject.onCompleted();
    }

    public Observable<Location> observe() {
        return mSubject;
    }

    /**
     * Returns the most accurate and timely previously detected location.
     *
     * @param maxTime Maximum time before the location is outdated.
     * @return The most accurate and / or timely previously detected location within the {@code
     * maxTime} period.
     */
    public Location getLastBestLocation(long maxTime) {
        long minTime = System.currentTimeMillis() - maxTime;
        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;
        long bestTime = Long.MIN_VALUE;
        List<String> matchingProviders = mLocationManager.getAllProviders();
        for (String provider : matchingProviders) {
            Location location = mLocationManager.getLastKnownLocation(provider);
            if (location != null) {
                float accuracy = location.getAccuracy();
                long time = location.getTime();
                if ((time > minTime && accuracy < bestAccuracy)) {
                    bestResult = location;
                    bestAccuracy = accuracy;
                    bestTime = time;
                } else if (time < minTime && bestAccuracy == Float.MAX_VALUE && time > bestTime) {
                    bestResult = location;
                    bestTime = time;
                }
            }
        }
        return bestResult;
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            mSubject.onNext(location);
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

}
