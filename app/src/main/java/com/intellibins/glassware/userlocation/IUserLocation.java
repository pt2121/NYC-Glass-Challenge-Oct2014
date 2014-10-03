package com.intellibins.glassware.userlocation;

import android.location.Location;

import rx.Observable;

/**
 * Created by prt2121 on 10/1/14.
 */
public interface IUserLocation {

    public void start();

    public void stop();

    public Observable<Location> observe();

}
