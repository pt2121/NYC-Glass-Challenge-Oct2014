package com.intellibins.glassware.dropofflocation;

import com.intellibins.glassware.model.Loc;

import rx.Observable;

/**
 * Created by prt2121 on 10/7/14.
 */
public class DropOffLocation implements IFindDropOff {

    @Override
    public Observable<Loc> getLocs() {
        return new DropOffLocationHelper().getLocs();
    }
}
