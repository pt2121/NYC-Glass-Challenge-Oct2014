package com.intellibins.glassware;

import com.intellibins.glassware.model.Loc;

import java.util.List;

/**
 * Created by prt2121 on 10/7/14.
 */
public class DataEvent {

    public final List<Loc> locs;

    public DataEvent(List<Loc> locs) {
        this.locs = locs;
    }
}
