package com.intellibins.glassware.binlocation

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.intellibins.glassware.R
import com.intellibins.glassware.model.Bin
import com.intellibins.glassware.model.nyc.BinData
import rx.Observable
import rx.Subscriber

class NycBinLocation implements IBinLocation {

    private Application mApp

    NycBinLocation(Application app) {
        mApp = app
    }

    private BinData parseJson(String jsonText) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create()
        gson.fromJson(jsonText, BinData.class)
    }

    private Observable<String> getJsonText(Context context) {
        try {
            Resources res = context.getResources()
            InputStream inputStream = res.openRawResource(R.raw.json)
            byte[] b = new byte[inputStream.available()]
            int bytes = inputStream.read(b)
            bytes == -1 ? Observable.<String> empty()
                    : Observable.just(new String(b))
        } catch (Exception e) {
            Observable.empty()
        }
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
                .map({ String jsonText -> parseJson(jsonText) })
                .flatMap({ BinData binData -> makeBins(binData) })
    }
}