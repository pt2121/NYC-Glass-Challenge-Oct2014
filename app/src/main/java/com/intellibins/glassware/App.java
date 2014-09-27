package com.intellibins.glassware;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Created by prt2121 on 9/27/14.
 */
public class App extends Application {

    private ObjectGraph graph;

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

    }

    private void buildObjectGraphAndInject() {
        // TODO
        //graph = ObjectGraph.create();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public void inject(Object o) {
        graph.inject(o);
    }
}
