package com.intellibins.glassware.dropofflocation;

import com.intellibins.glassware.SplashScreenActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prt2121 on 10/5/14.
 */
@Module(
        complete = false,
        injects = SplashScreenActivity.class,
        library = true
)
public class DropOffLocationModule {

    @Provides
    public IFindDropOff provideNycDropOffLocation() {
        return new DropOffLocation();
    }
}
