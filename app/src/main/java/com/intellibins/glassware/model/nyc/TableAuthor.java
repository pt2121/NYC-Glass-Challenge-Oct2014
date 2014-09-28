package com.intellibins.glassware.model.nyc;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class TableAuthor {

    @Expose
    private String id;

    @Expose
    private String displayName;

    @Expose
    private String roleName;

    @Expose
    private String screenName;

    @Expose
    private List<String> rights = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

}
