package com.example.springbootarbitrage.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Worth implements Serializable {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 基金代码
     */
    private String code;

    /**
     * 当前净值
     */
    private String value;

    /**
     * 日增长率
     */
    @Column(name = "growth_rate")
    private String growthRate;

    /**
     * 净值日期
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
     * 获取当前净值
     *
     * @return value - 当前净值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置当前净值
     *
     * @param value 当前净值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取日增长率
     *
     * @return growth_rate - 日增长率
     */
    public String getGrowthRate() {
        return growthRate;
    }

    /**
     * 设置日增长率
     *
     * @param growthRate 日增长率
     */
    public void setGrowthRate(String growthRate) {
        this.growthRate = growthRate == null ? null : growthRate.trim();
    }

    /**
     * 获取净值日期
     *
     * @return date - 净值日期
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置净值日期
     *
     * @param date 净值日期
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}