package com.intellibins.glassware.binlocation;

import com.intellibins.glassware.model.Loc;

import rx.Observable;

/**
 * Created by prt2121 on 9/28/14.
 */
public interface IFindBin {

    public Observable<Loc> getLocs();

}
