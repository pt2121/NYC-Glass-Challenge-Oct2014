package com.intellibins.glassware;

import com.intellibins.glassware.binlocation.BinLocationModule;
import com.intellibins.glassware.userlocation.UserLocationModule;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prt2121 on 9/28/14.
 */
@Module(
        injects = {
                BinLocationModule.class,
                UserLocationModule.class
        }
)
public class IntellibinsModule {

    private final IntellibinsApp mApp;

    public IntellibinsModule(IntellibinsApp app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApp;
    }
}