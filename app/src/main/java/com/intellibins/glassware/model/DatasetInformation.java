
package com.intellibins.glassware.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatasetInformation {

    @SerializedName("Agency")
    @Expose
    private String agency;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

}
