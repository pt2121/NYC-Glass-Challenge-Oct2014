/*
 * *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.intellibins.glassware

import android.location.Location
import com.intellibins.glassware.model.Loc
import rx.functions.Func2

class LocUtils {

    double myLatitude

    double myLongitude

    Func2<Loc, Loc, Integer> compare(double lat, double lng) {
        myLatitude = lat
        myLongitude = lng
        f
    }

    def f = new Func2<Loc, Loc, Integer>() {

        @Override
        public Integer call(Loc b1, Loc b2) {
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