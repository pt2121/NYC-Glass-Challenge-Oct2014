package com.intellibins.glassware.binlocation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.intellibins.glassware.R;
import com.intellibins.glassware.model.Bin;
import com.intellibins.glassware.model.nyc.BinData;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by prt2121 on 9/27/14.
 */
public class NycBinLocation_java implements IBinLocation {

    private static final String TAG = NycBinLocation_java.class.getSimpleName();

    private Application mApp;

    public NycBinLocation_java(Application app) {
        mApp = app;
    }

    private BinData parseJson(String jsonText) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(jsonText, BinData.class);
    }

    private Observable<String> getJsonText(Context context) {
        try {
            Resources res = context.getResources();
            InputStream inputStream = res.openRawResource(R.raw.json);
            byte[] b = new byte[inputStream.available()];
            int bytes = inputStream.read(b);
            return bytes == -1 ? Observable.<String>empty()
                    : Observable.just(new String(b));
        } catch (Exception e) {
            return Observable.empty();
        }
    }

    private Observable<Bin> makeBins(final BinData binData) {
        return Observable.create(new Observable.OnSubscribe<Bin>() {
            @Override
            public void call(final Subscriber<? super Bin> subscriber) {
                final Thread t = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        List<List<String>> lists = binData.getData();
                        for (List<String> strings : lists) {
                            try {
                                int len = strings.size();
                                Bin bin = new Bin.Builder(strings.get(len - 4))
                                        .address(strings.get(len - 3))
                                        .latitude(Double.parseDouble(strings.get(len - 2)))
                                        .longitude(Double.parseDouble(strings.get(len - 1)))
                                        .build();
                                subscriber.onNext(bin);
                            } catch (Exception ex) {
                                Log.e(TAG, "#makeBins " + strings.toString());
                                Log.e(TAG, ex.toString());
                            }
                        }
                        subscriber.onCompleted();
                    }
                });
                t.start();
            }
        });
    }

    @Override
    public Observable<Bin> getBins() {
        return getJsonText(mApp.getApplicationContext())
                .map(new Func1<String, BinData>() {
                    @Override
                    public BinData call(String jsonString) {
                        return parseJson(jsonString);
                    }
                }).flatMap(new Func1<BinData, Observable<Bin>>() {
                    @Override
                    public Observable<Bin> call(BinData binData) {
                        return makeBins(binData);
                    }
                });
    }

}