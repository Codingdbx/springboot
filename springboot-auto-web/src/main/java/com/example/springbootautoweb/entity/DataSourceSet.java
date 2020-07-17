package com.example.springbootautoweb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "DataSourceSet")
public class DataSourceSet implements Serializable {
    @Id
    @Column(name = "SOURCE_ID")
    private String sourceId;

    @Column(name = "SOURCE_URL")
    private String sourceUrl;

    @Column(name = "SOURCE_TYPE")
    private String sourceType;

    private static final long serialVersionUID = 1L;

    /**
     * @return SOURCE_ID
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * @param sourceId
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    /**
     * @return SOURCE_URL
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * @param sourceUrl
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    /**
     * @return SOURCE_TYPE
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }
}