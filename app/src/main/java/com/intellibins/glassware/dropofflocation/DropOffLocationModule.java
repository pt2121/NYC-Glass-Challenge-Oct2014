package com.intellibins.glassware.dropofflocation;

import com.intellibins.glassware.DataService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prt2121 on 10/5/14.
 */
@Module(
        complete = false,
        injects = DataService.class,
        library = true
)
public class DropOffLocationModule {

    @Provides
    public IFindDropOff provideNycDropOffLocation() {
        return new DropOffLocation();
    }
}
