
package com.intellibins.glassware.model;

import com.google.gson.annotations.Expose;

public class Meta {

    @Expose
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

}
