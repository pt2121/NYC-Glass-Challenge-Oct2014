
package com.intellibins.glassware.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class View {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String attribution;
    @Expose
    private Integer averageRating;
    @Expose
    private String category;
    @Expose
    private Integer createdAt;
    @Expose
    private String description;
    @Expose
    private String displayType;
    @Expose
    private Integer downloadCount;
    @Expose
    private Integer indexUpdatedAt;
    @Expose
    private Boolean newBackend;
    @Expose
    private Integer numberOfComments;
    @Expose
    private Integer oid;
    @Expose
    private Boolean publicationAppendEnabled;
    @Expose
    private Integer publicationDate;
    @Expose
    private Integer publicationGroup;
    @Expose
    private String publicationStage;
    @Expose
    private Integer rowsUpdatedAt;
    @Expose
    private String rowsUpdatedBy;
    @Expose
    private Integer tableId;
    @Expose
    private Integer totalTimesRated;
    @Expose
    private Integer viewCount;
    @Expose
    private Integer viewLastModified;
    @Expose
    private String viewType;
    @Expose
    private List<Column> columns = new ArrayList<Column>();
    @Expose
    private List<Grant> grants = new ArrayList<Grant>();
    @Expose
    private Metadata metadata;
    @Expose
    private Owner owner;
    @Expose
    private Query query;
    @Expose
    private List<String> rights = new ArrayList<String>();
    @Expose
    private TableAuthor tableAuthor;
    @Expose
    private List<String> tags = new ArrayList<String>();
    @Expose
    private List<String> flags = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribution() {
        return attribution;
    }

    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    public Integer getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Integer averageRating) {
        this.averageRating = averageRating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Integer getIndexUpdatedAt() {
        return indexUpdatedAt;
    }

    public void setIndexUpdatedAt(Integer indexUpdatedAt) {
        this.indexUpdatedAt = indexUpdatedAt;
    }

    public Boolean getNewBackend() {
        return newBackend;
    }

    public void setNewBackend(Boolean newBackend) {
        this.newBackend = newBackend;
    }

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Boolean getPublicationAppendEnabled() {
        return publicationAppendEnabled;
    }

    public void setPublicationAppendEnabled(Boolean publicationAppendEnabled) {
        this.publicationAppendEnabled = publicationAppendEnabled;
    }

    public Integer getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Integer publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getPublicationGroup() {
        return publicationGroup;
    }

    public void setPublicationGroup(Integer publicationGroup) {
        this.publicationGroup = publicationGroup;
    }

    public String getPublicationStage() {
        return publicationStage;
    }

    public void setPublicationStage(String publicationStage) {
        this.publicationStage = publicationStage;
    }

    public Integer getRowsUpdatedAt() {
        return rowsUpdatedAt;
    }

    public void setRowsUpdatedAt(Integer rowsUpdatedAt) {
        this.rowsUpdatedAt = rowsUpdatedAt;
    }

    public String getRowsUpdatedBy() {
        return rowsUpdatedBy;
    }

    public void setRowsUpdatedBy(String rowsUpdatedBy) {
        this.rowsUpdatedBy = rowsUpdatedBy;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getTotalTimesRated() {
        return totalTimesRated;
    }

    public void setTotalTimesRated(Integer totalTimesRated) {
        this.totalTimesRated = totalTimesRated;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getViewLastModified() {
        return viewLastModified;
    }

    public void setViewLastModified(Integer viewLastModified) {
        this.viewLastModified = viewLastModified;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Grant> getGrants() {
        return grants;
    }

    public void setGrants(List<Grant> grants) {
        this.grants = grants;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

    public TableAuthor getTableAuthor() {
        return tableAuthor;
    }

    public void setTableAuthor(TableAuthor tableAuthor) {
        this.tableAuthor = tableAuthor;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

}
