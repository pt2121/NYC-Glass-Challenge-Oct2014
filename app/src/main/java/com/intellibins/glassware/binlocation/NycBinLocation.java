package com.intellibins.glassware.binlocation;

import com.intellibins.glassware.model.Loc;

import android.app.Application;

import rx.Observable;

/**
 * Created by prt2121 on 10/7/14.
 */
public class NycBinLocation implements IFindBin {

    private Application mApp;

    public NycBinLocation(Application app) {
        mApp = app;
    }

    @Override
    public Observable<Loc> getLocs() {
        return new NycBinLocationHelper(mApp).getLocs();
    }
}
