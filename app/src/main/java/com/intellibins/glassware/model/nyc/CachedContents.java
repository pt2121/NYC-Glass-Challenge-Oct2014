/*
 * Copyright (c) 2014 Intellibins authors
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of The Intern nor the names of its contributors may
 *       be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE LISTED COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.intellibins.glassware.model.nyc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
