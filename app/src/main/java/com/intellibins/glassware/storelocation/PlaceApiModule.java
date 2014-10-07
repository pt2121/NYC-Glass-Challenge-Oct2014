package com.intellibins.glassware.storelocation;

import com.intellibins.glassware.SplashScreenActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by prt2121 on 10/5/14.
 */
@Module(
        complete = false,
        injects = SplashScreenActivity.class,
        library = true
)
public final class PlaceApiModule {

    @Provides
    @Singleton
    RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder()
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://maps.googleapis.com")
                .build();
    }
}
