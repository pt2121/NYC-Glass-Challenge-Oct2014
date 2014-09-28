package com.intellibins.glassware;

import com.intellibins.glassware.model.Bin;

import java.util.List;

/**
 * Created by prt2121 on 9/28/14.
 */
public interface IBinLocation {

    public List<Bin> getBins();

    public List<Bin> getClosestBin(double myLatitude, double myLongitude);

    public List<Bin> getClosestBin(double myLatitude, double myLongitude, int num);

}
