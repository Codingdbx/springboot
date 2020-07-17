package com.example.springbootautoweb.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "PageSet")
public class PageSet implements Serializable {
    @Id
    @Column(name = "PAGE_ID")
    private String pageId;

    @Column(name = "PAGE_NAME")
    private String pageName;

    @Column(name = "PAGE_URL")
    private String pageUrl;

    @Column(name = "WAIT_TIME")
    private String waitTime;

    @Column(name = "SORT")
    private String sort;

    private static final long serialVersionUID = 1L;

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
     * @return PAGE_NAME
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * @param pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName == null ? null : pageName.trim();
    }

    /**
     * @return PAGE_URL
     */
    public String getPageUrl() {
        return pageUrl;
    }

    /**
     * @param pageUrl
     */
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    /**
     * @return WAIT_TIME
     */
    public String getWaitTime() {
        return waitTime;
    }

    /**
     * @param waitTime
     */
    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime == null ? null : waitTime.trim();
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