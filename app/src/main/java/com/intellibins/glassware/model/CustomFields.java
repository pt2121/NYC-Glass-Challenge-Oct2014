
package com.intellibins.glassware.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomFields {

    @SerializedName("Update")
    @Expose
    private Update update;
    @SerializedName("Dataset Information")
    @Expose
    private DatasetInformation datasetInformation;

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public DatasetInformation getDatasetInformation() {
        return datasetInformation;
    }

    public void setDatasetInformation(DatasetInformation datasetInformation) {
        this.datasetInformation = datasetInformation;
    }

}
