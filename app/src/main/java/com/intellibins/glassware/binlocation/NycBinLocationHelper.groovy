/*
 * Copyright (c) 2014 Intellibins authors
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of The Intern nor the names of its contributors may
 *       be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE LISTED COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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

class NycBinLocationHelper {

    private String TAG = NycBinLocationHelper.class.getSimpleName()
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
            Log.e(TAG, e.toString())
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
                            Log.e(TAG, ex.toString())
                        }
                    }
                    subscriber.onCompleted()
                })
        } as Observable.OnSubscribe<Loc>)
    }

    Observable<Loc> getLocs() {
        getJsonText(mApp.getApplicationContext())
                .flatMap({ String jsonText -> parseJson(jsonText) })
                .flatMap({ BinData binData -> makeBins(binData) })
    }
}