
package com.intellibins.glassware.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CachedContents {

    @SerializedName("non_null")
    @Expose
    private Integer nonNull;
    @Expose
    private String smallest;
    @Expose
    private String sum;
    @SerializedName("null")
    @Expose
    private Integer _null;
    @Expose
    private String average;
    @Expose
    private String largest;
    @Expose
    private List<Top> top = new ArrayList<Top>();

    public Integer getNonNull() {
        return nonNull;
    }

    public void setNonNull(Integer nonNull) {
        this.nonNull = nonNull;
    }

    public String getSmallest() {
        return smallest;
    }

    public void setSmallest(String smallest) {
        this.smallest = smallest;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Integer getNull() {
        return _null;
    }

    public void setNull(Integer _null) {
        this._null = _null;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getLargest() {
        return largest;
    }

    public void setLargest(String largest) {
        this.largest = largest;
    }

    public List<Top> getTop() {
        return top;
    }

    public void setTop(List<Top> top) {
        this.top = top;
    }

}
