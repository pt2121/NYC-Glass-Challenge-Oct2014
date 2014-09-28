package com.intellibins.glassware;

import android.app.Application;
import android.content.Context;

import java.util.Arrays;

import dagger.ObjectGraph;

/**
 * Created by prt2121 on 9/27/14.
 */
public class IntellibinsApp extends Application {

    private static final String TAG = IntellibinsApp.class.getSimpleName();

    private ObjectGraph graph;

    public static IntellibinsApp get(Context context) {
        return (IntellibinsApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
    }

    private void buildObjectGraphAndInject() {
        graph = ObjectGraph.create(new IntellibinsModule(this), new BinLocationModule(this));
    }

    public void inject(Object o) {
        graph.inject(o);
    }
}