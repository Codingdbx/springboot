package com.example.springbootautoweb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "ElementSet")
public class ElementSet implements Serializable {
    @Id
    @Column(name = "ELEMENT_ID")
    private String elementId;

    @Column(name = "ELEMENT_NAME")
    private String elementName;

    @Column(name = "ELEMENT_TYPE")
    private String elementType;

    @Column(name = "PAGE_ID")
    private String pageId;

    @Column(name = "IFRAME_ID")
    private String iframeId;

    @Column(name = "LOCATION_TYPE")
    private String locationType;

    @Column(name = "LOCATION_VALUE")
    private String locationValue;

    @Column(name = "DATA_ID")
    private String dataId;

    @Column(name = "SORT")
    private String sort;

    private static final long serialVersionUID = 1L;

    /**
     * @return ELEMENT_ID
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * @param elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
    }

    /**
     * @return ELEMENT_NAME
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * @param elementName
     */
    public void setElementName(String elementName) {
        this.elementName = elementName == null ? null : elementName.trim();
    }

    /**
     * @return ELEMENT_TYPE
     */
    public String getElementType() {
        return elementType;
    }

    /**
     * @param elementType
     */
    public void setElementType(String elementType) {
        this.elementType = elementType == null ? null : elementType.trim();
    }

    /**
     * @return PAGE_ID
     */
    public String getPageId() {
        return pageId;
    }

    /**
     * @param pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId == null ? null : pageId.trim();
    }

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

    /**
     * @return DATA_ID
     */
    public String getDataId() {
        return dataId;
    }

    /**
     * @param dataId
     */
    public void setDataId(String dataId) {
        this.dataId = dataId == null ? null : dataId.trim();
    }

    /**
     * @return SORT
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }
}