package com.intellibins.glassware.dropofflocation

import com.intellibins.glassware.model.Loc
import rx.Observable

/**
 *
 * http://www.nyc.gov/html/nycwasteless/html/stuff/harmful_hh_prod_special_waste.shtml
 */
class DropOffLocationHelper implements IFindDropOff {

    def name = "Household Special Waste Drop-Off Site"

    @Override
    Observable<Loc> getLocs() {
        def locs = [new Loc(name, "Hunts Point Ave New York, NY 10474", 40.820948, -73.890549),
                    new Loc(name, "Gravesend New York, NY 11214", 40.591017, -73.977126),
                    new Loc(name, "121st St New York, NY 11354", 40.771360, -73.847710),
                    new Loc(name, "Muldoon Ave New York, NY 10312", 40.569652, -74.194829)]
        Observable.from(locs)
    }
}