package com.intellibins.glassware.model;

/**
 * Created by prt2121 on 9/27/14.
 */
public class Loc {

    public final String name;

    public final String address;

    public final double latitude;

    public final double longitude;

    public String image;

    public Loc(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static class Builder {

        private String name;

        private String address;

        private double latitude;

        private double longitude;

        private String image;

        public Builder(String name) {
            this.name = name;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Loc build() {
            Loc l = new Loc(this.name, this.address, this.latitude, this.longitude);
            l.image = this.image;
            return l;
        }
    }
}

// NYC
// Borough	Site type	Park/Site Name	Address	Latitude	Longitude
// [ 1, "09BA982F-E777-4D8F-BCAB-3A04D56FB719", 1, 1341003073, "392904", 1341003073, "392904", "{\n}", "Bronx", "Subproperty", "227th St. Plgd", "E 227 St/Bronx River Pkway", "40.890848989", "-73.864223918" ]
