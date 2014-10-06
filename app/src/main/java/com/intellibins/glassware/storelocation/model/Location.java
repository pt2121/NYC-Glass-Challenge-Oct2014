package com.intellibins.glassware.storelocation.model;

import com.google.gson.annotations.Expose;

public class Location {

    @Expose
    private Double lat;

    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}
