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

package com.intellibins.glassware.dropofflocation

import com.intellibins.glassware.model.Loc
import rx.Observable

/**
 *
 * http://www.nyc.gov/html/nycwasteless/html/stuff/harmful_hh_prod_special_waste.shtml
 */
class DropOffLocationHelper {

    def name = "Household Special Waste Drop-Off Site"

    Observable<Loc> getLocs() {
        def locs = [new Loc(name, "Hunts Point Ave New York, NY 10474", 40.820948, -73.890549),
                    new Loc(name, "Gravesend New York, NY 11214", 40.591017, -73.977126),
                    new Loc(name, "121st St New York, NY 11354", 40.771360, -73.847710),
                    new Loc(name, "Muldoon Ave New York, NY 10312", 40.569652, -74.194829)]
        Observable.from(locs)
    }
}