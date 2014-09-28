package com.intellibins.glassware.binlocation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.intellibins.glassware.R;
import com.intellibins.glassware.model.Bin;
import com.intellibins.glassware.model.nyc.BinData;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prt2121 on 9/27/14.
 */
public class NycBinLocation implements IBinLocation {

    private static final String TAG = NycBinLocation.class.getSimpleName();

    private static BinData mBinData;

    private List<Location> mSortedBinLocations;

    private Application mApp;

    public NycBinLocation(Application app) {
        mApp = app;
    }

    private static BinData parseJson(String jsonText) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(jsonText, BinData.class);
    }

    private static String getJsonText(Context context) {
        try {
            Resources res = context.getResources();
            InputStream inputStream = res.openRawResource(R.raw.json);
            byte[] b = new byte[inputStream.available()];
            int bytes = inputStream.read(b);
            return bytes == -1 ? null : new String(b);
        } catch (Exception e) {
            return null;
        }
    }

    private static List<Bin> makeBins(BinData binData) {
        List<List<String>> lists = binData.getData();
        List<Bin> bins = new ArrayList<>();
        for (List<String> strings : lists) {
            try {
                int len = strings.size();
                Bin bin = new Bin.Builder(strings.get(len - 4))
                        .address(strings.get(len - 3))
                        .latitude(Double.parseDouble(strings.get(len - 2)))
                        .longitude(Double.parseDouble(strings.get(len - 1)))
                        .build();
                bins.add(bin);
            } catch (Exception ex) {
                Log.e(TAG, "#makeBins " + strings.toString());
                Log.e(TAG, ex.toString());
            }
        }
        return bins;
    }

    @Override
    public List<Bin> getBins() {
        return makeBins(parseJson(getJsonText(mApp.getApplicationContext())));
    }

}