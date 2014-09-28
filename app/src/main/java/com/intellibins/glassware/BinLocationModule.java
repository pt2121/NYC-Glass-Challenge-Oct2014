package com.intellibins.glassware;

import android.app.Application;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prt2121 on 9/27/14.
 */
@Module(
        injects = MenuActivity.class,
        library = true
)
public class BinLocationModule {

    private final Application mApp;

    @Inject
    public BinLocationModule(Application app) {
        this.mApp = app;
    }

    @Provides
    public NycBinLocation provideNycBinLocation() {
        return new NycBinLocation(mApp);
    }

//    @Provides
//    public List<Bin> provideClosestBin(final double myLatitude, final double myLongitude) {
//        return null;
//    }
//
//    @Provides
//    public List<Bin> provideClosestBin(final double myLatitude, final double myLongitude, int num) {
//        return null;
//    }
}
