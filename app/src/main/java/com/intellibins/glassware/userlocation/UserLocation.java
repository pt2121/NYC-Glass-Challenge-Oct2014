/*
 * Copyright (c) 2014 Intellibins authors
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of The Intern nor the names of its contributors may
 *       be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE LISTED COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
public class UserLocation implements IUserLocation {

    private static final int MAX_TIME = 10 * 60 * 1000; // an old location expires in 10 mins

    protected BehaviorSubject<Location> mSubject;

    private final MyLocationListener listener = new MyLocationListener();

    private final Criteria criteria = new Criteria();

    private LocationManager mLocationManager;

    public UserLocation(Application app) {
        mLocationManager = (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void start() {
        mSubject = BehaviorSubject.create(getLastBestLocation(MAX_TIME));
        //mSubject.subscribeOn(Schedulers.io());
        mSubject.subscribeOn(Schedulers.newThread());
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        final Looper looper = Looper.myLooper();
        mLocationManager.requestLocationUpdates(60 * 1000, 0, criteria, listener, looper);
    }

    @Override
    public void stop() {
        mLocationManager.removeUpdates(listener);
        mSubject.onCompleted();
    }

    @Override
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
