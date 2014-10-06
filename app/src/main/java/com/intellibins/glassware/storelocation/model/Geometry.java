package com.intellibins.glassware.storelocation.model;

import com.google.gson.annotations.Expose;

public class Geometry {

    @Expose
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
