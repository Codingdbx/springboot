package com.example.springbootarbitrage.entity;

import java.io.Serializable;

public class Announcement implements Serializable {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 基金代码
     */
    private String code;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告日期
     */
    private String date;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取基金代码
     *
     * @return code - 基金代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置基金代码
     *
     * @param code 基金代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取公告标题
     *
     * @return title - 公告标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置公告标题
     *
     * @param title 公告标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取公告日期
     *
     * @return date - 公告日期
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置公告日期
     *
     * @param date 公告日期
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}