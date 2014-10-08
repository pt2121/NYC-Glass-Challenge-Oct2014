package com.intellibins.glassware.binlocation;

import com.intellibins.glassware.DataService;

import android.app.Application;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prt2121 on 9/27/14.
 */
@Module(
        complete = false,
        injects = DataService.class,
        library = true
)
public class BinLocationModule {

    private final Application mApp;

    @Inject
    public BinLocationModule(Application app) {
        this.mApp = app;
    }

    @Provides
    public IFindBin provideNycBinLocation() {
        return new NycBinLocation(mApp);
    }

}
