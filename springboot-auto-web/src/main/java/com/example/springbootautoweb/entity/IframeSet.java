package com.example.springbootautoweb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "IframeSet")
public class IframeSet implements Serializable {
    @Id
    @Column(name = "IFRAME_ID")
    private String iframeId;

    @Column(name = "LOCATION_TYPE")
    private String locationType;

    @Column(name = "LOCATION_VALUE")
    private String locationValue;

    private static final long serialVersionUID = 1L;

    /**
     * @return IFRAME_ID
     */
    public String getIframeId() {
        return iframeId;
    }

    /**
     * @param iframeId
     */
    public void setIframeId(String iframeId) {
        this.iframeId = iframeId == null ? null : iframeId.trim();
    }

    /**
     * @return LOCATION_TYPE
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * @param locationType
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType == null ? null : locationType.trim();
    }

    /**
     * @return LOCATION_VALUE
     */
    public String getLocationValue() {
        return locationValue;
    }

    /**
     * @param locationValue
     */
    public void setLocationValue(String locationValue) {
        this.locationValue = locationValue == null ? null : locationValue.trim();
    }
}