package com.intellibins.glassware.model.nyc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("custom_fields")
    @Expose
    private CustomFields customFields;

    @Expose
    private String rdfSubject;

    public CustomFields getCustomFields() {
        return customFields;
    }

    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

    public String getRdfSubject() {
        return rdfSubject;
    }

    public void setRdfSubject(String rdfSubject) {
        this.rdfSubject = rdfSubject;
    }

}
