
package com.intellibins.glassware.model;

import com.google.gson.annotations.Expose;

public class Column {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private String dataTypeName;
    @Expose
    private String fieldName;
    @Expose
    private Integer position;
    @Expose
    private String renderTypeName;
    @Expose
    private Format format;
    @Expose
    private Integer tableColumnId;
    @Expose
    private Integer width;
    @Expose
    private CachedContents cachedContents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getRenderTypeName() {
        return renderTypeName;
    }

    public void setRenderTypeName(String renderTypeName) {
        this.renderTypeName = renderTypeName;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Integer getTableColumnId() {
        return tableColumnId;
    }

    public void setTableColumnId(Integer tableColumnId) {
        this.tableColumnId = tableColumnId;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public CachedContents getCachedContents() {
        return cachedContents;
    }

    public void setCachedContents(CachedContents cachedContents) {
        this.cachedContents = cachedContents;
    }

}
