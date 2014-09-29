package com.intellibins.glassware.model.nyc;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prt2121 on 9/27/14.
 */
public class BinData {

    @Expose
    private Meta meta;

    @Expose
    private List<List<String>> data = new ArrayList<List<String>>();

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

}