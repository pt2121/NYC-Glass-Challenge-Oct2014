package com.intellibins.glassware.userlocation;

import com.intellibins.glassware.DataService;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prt2121 on 9/30/14.
 */
@Module(
        complete = false,
        injects = DataService.class,
        library = true
)
public class UserLocationModule {

    private final Application mApp;

    @Inject
    public UserLocationModule(Application app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    public IUserLocation provideUserLocation() {
        return new UserLocation(mApp);
    }

}
