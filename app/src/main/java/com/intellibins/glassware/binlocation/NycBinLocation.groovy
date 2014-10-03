package com.intellibins.glassware.binlocation

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.google.gson.GsonBuilder
import com.intellibins.glassware.R
import com.intellibins.glassware.model.Bin
import com.intellibins.glassware.model.nyc.BinData
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NycBinLocation implements IBinLocation {

    private Application mApp

    NycBinLocation(Application app) {
        mApp = app
    }

    private Observable<BinData> parseJson(String jsonText) {
        Observable.create({
            Subscriber<BinData> subscriber ->
                Thread.start({
                    subscriber.onNext(
                            new GsonBuilder()
                                    .setPrettyPrinting()
                                    .create()
                                    .fromJson(jsonText, BinData.class))
                    subscriber.onCompleted()
                })
        } as Observable.OnSubscribe<BinData>)
    }

    private Observable<String> getJsonText(Context context) {
        Observable.create({
            Subscriber<String> subscriber ->
                Thread.start({
                    try {
                        Resources res = context.getResources()
                        InputStream inputStream = res.openRawResource(R.raw.json)
                        byte[] b = new byte[inputStream.available()]
                        inputStream.read(b)
                        subscriber.onNext(new String(b))
                        subscriber.onCompleted()
                        inputStream.close()
                        System.gc()
                    } catch (Exception e) {
                    }
                })
        } as Observable.OnSubscribe<String>)
    }

    private Observable<Bin> makeBins(final BinData binData) {
        Observable.create({
            Subscriber<Bin> subscriber ->
                Thread.start({
                    List<List<String>> lists = binData.getData()
                    lists.each { ls ->
                        try {
                            int len = ls.size()
                            Bin bin = new Bin.Builder(ls.get(len - 4))
                                    .address(ls.get(len - 3))
                                    .latitude(Double.parseDouble(ls.get(len - 2)))
                                    .longitude(Double.parseDouble(ls.get(len - 1)))
                                    .build()
                            subscriber.onNext(bin)
                        } catch (Exception ex) {
                            Log.e(NycBinLocation.getSimpleName(), ex.toString())
                        }
                    }
                    subscriber.onCompleted()
                })
        } as Observable.OnSubscribe<Bin>)
    }

    @Override
    Observable<Bin> getBins() {
        getJsonText(mApp.getApplicationContext())
                .flatMap({ String jsonText -> parseJson(jsonText) })
                .flatMap({ BinData binData -> makeBins(binData) })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}