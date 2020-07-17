package com.example.springbootarbitrage.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Fund implements Serializable {
    /**
     * 主键ID
     * IDENTITY 主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    /**
     * 基金代码（场外）
     */
    private String code;

    private String symbol;//场内代码
    private String w_symbol;//场外代码

    /**
     * 基金类型
     */
    private String type;

    /**
     * 交易所名称
     */
    @Column(name = "exchange_name")
    private String exchangeName;

    /**
     * 证券公司名称
     */
    @Column(name = "securities_name")
    private String securitiesName;

    /**
     * 官网地址
     */
    private String url;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 获取基金类型
     *
     * @return type - 基金类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置基金类型
     *
     * @param type 基金类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取交易所名称
     *
     * @return exchange_name - 交易所名称
     */
    public String getExchangeName() {
        return exchangeName;
    }

    /**
     * 设置交易所名称
     *
     * @param exchangeName 交易所名称
     */
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName == null ? null : exchangeName.trim();
    }

    /**
     * 获取证券公司名称
     *
     * @return securities_name - 证券公司名称
     */
    public String getSecuritiesName() {
        return securitiesName;
    }

    /**
     * 设置证券公司名称
     *
     * @param securitiesName 证券公司名称
     */
    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName == null ? null : securitiesName.trim();
    }

    /**
     * 获取官网地址
     *
     * @return url - 官网地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置官网地址
     *
     * @param url 官网地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}