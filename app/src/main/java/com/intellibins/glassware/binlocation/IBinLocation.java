package com.intellibins.glassware.binlocation;

import com.intellibins.glassware.model.Bin;

import rx.Observable;

/**
 * Created by prt2121 on 9/28/14.
 */
public interface IBinLocation {

    public Observable<Bin> getBins();

}
