package com.intellibins.glassware.storelocation;

import com.intellibins.glassware.storelocation.model.Place;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by prt2121 on 10/5/14.
 * https://developers.google.com/places/documentation/search
 * https://maps.googleapis.com/maps/api/place/nearbysearch/output?parameters
 * eg https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=cruise&key=AddYourOwnKeyHere
 */
public interface GooglePlaceService {

    // TODO add API KEY
    @GET("/maps/api/place/nearbysearch/json?keyword=convenience%20store&rankby=distance")
    Observable<Place> getPlaces(@Query("location") String location,
            @Query("key") String key);

}
