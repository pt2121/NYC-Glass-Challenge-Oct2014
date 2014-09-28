package com.intellibins.glassware.model.nyc;

import com.google.gson.annotations.Expose;

public class Top {

    @Expose
    private Integer count;

    @Expose
    private String item;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
