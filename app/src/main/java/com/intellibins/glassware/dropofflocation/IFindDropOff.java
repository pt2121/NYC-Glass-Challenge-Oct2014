package com.intellibins.glassware.dropofflocation;

import com.intellibins.glassware.model.Loc;

import rx.Observable;

/**
 * Created by prt2121 on 10/5/14.
 */
public interface IFindDropOff {

    public Observable<Loc> getLocs();

}
