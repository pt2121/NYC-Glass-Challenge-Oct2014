package com.intellibins.glassware.binlocation

import android.location.Location
import com.intellibins.glassware.model.Bin
import rx.functions.Func2

class BinLocationUtils {

    double myLatitude

    double myLongitude

    Func2<Bin, Bin, Integer> compare(double lat, double lng) {
        myLatitude = lat
        myLongitude = lng
        f
    }

    def f = new Func2<Bin, Bin, Integer>() {

        @Override
        public Integer call(Bin b1, Bin b2) {
            float[] result1 = new float[3]
            Location.distanceBetween(myLatitude, myLongitude, b1.latitude, b1.longitude, result1)
            Float distance1 = result1[0]
            float[] result2 = new float[3]
            Location.distanceBetween(myLatitude, myLongitude, b2.latitude, b2.longitude, result2)
            Float distance2 = result2[0]
            distance1.compareTo(distance2)
        }
    }

}