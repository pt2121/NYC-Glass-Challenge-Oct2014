package com.intellibins.glassware.binlocation

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.google.gson.GsonBuilder
import com.intellibins.glassware.R
import com.intellibins.glassware.model.Loc
import com.intellibins.glassware.model.nyc.BinData
import rx.Observable
import rx.Subscriber
import rx.observables.StringObservable

class NycBinLocationHelper implements IFindBin {

    private Application mApp

    NycBinLocationHelper(Application app) {
        mApp = app
    }

    private Observable<BinData> parseJson(String jsonText) {
        Observable.just(
                new GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                        .fromJson(jsonText, BinData.class))
    }

    private Observable<String> getJsonText(Context context) {
        try {
            Resources res = context.getResources()
            InputStream inputStream = res.openRawResource(R.raw.json)
            StringObservable.stringConcat(
                    StringObservable.from(inputStream)
                            .map({ byte[] bytes -> new String(bytes)
                    }))
        } catch (Exception e) {
            Observable.empty()
        }
    }

    private Observable<Loc> makeBins(final BinData binData) {
        Observable.create({
            Subscriber<Loc> subscriber ->
                Thread.start({
                    List<List<String>> lists = binData.getData()
                    lists.each { ls ->
                        try {
                            int len = ls.size()
                            Loc bin = new Loc.Builder(ls.get(len - 4))
                                    .address(ls.get(len - 3))
                                    .latitude(Double.parseDouble(ls.get(len - 2)))
                                    .longitude(Double.parseDouble(ls.get(len - 1)))
                                    .build()
                            subscriber.onNext(bin)
                        } catch (Exception ex) {
                            Log.e(NycBinLocationHelper.getSimpleName(), ex.toString())
                        }
                    }
                    subscriber.onCompleted()
                })
        } as Observable.OnSubscribe<Loc>)
    }

    @Override
    Observable<Loc> getLocs() {
        getJsonText(mApp.getApplicationContext())
                .flatMap({ String jsonText -> parseJson(jsonText) })
                .flatMap({ BinData binData -> makeBins(binData) })
    }
}