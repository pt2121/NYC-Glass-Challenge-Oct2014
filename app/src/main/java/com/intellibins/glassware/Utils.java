package com.intellibins.glassware;

import com.intellibins.glassware.model.Loc;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by prt2121 on 9/28/14.
 */
public class Utils {

    public static List<Loc> sortBins(List<Loc> locs, final double myLatitude,
            final double myLongitude) {
        Comparator<Loc> comp = new Comparator<Loc>() {
            @Override
            public int compare(Loc b1, Loc b2) {
                float[] result1 = new float[3];
                android.location.Location
                        .distanceBetween(myLatitude, myLongitude, b1.latitude, b1.longitude,
                                result1);
                Float distance1 = result1[0];

                float[] result2 = new float[3];
                android.location.Location
                        .distanceBetween(myLatitude, myLongitude, b2.latitude, b2.longitude,
                                result2);
                Float distance2 = result2[0];

                return distance1.compareTo(distance2);
            }
        };
        Collections.sort(locs, comp);
        return locs;
    }

}
