
package com.intellibins.glassware.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Grant {

    @Expose
    private Boolean inherited;
    @Expose
    private String type;
    @Expose
    private List<String> flags = new ArrayList<String>();

    public Boolean getInherited() {
        return inherited;
    }

    public void setInherited(Boolean inherited) {
        this.inherited = inherited;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

}
