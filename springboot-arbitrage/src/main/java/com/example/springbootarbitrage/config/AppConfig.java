package com.example.springbootarbitrage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/3 9:20
 * @since JDK1.8
 */
@Component
@ConfigurationProperties(prefix = "arbitrage.xueqiu.fund")
public class AppConfig {

    private String inWorthUrl;//场内价格实时查询地址
    private String outWorthUrl;//场外净值查询地址
    private String announcementUrl;//基金公告查询地址
    private String cookie;

    public String getInWorthUrl() {
        return inWorthUrl;
    }

    public void setInWorthUrl(String inWorthUrl) {
        this.inWorthUrl = inWorthUrl;
    }

    public String getOutWorthUrl() {
        return outWorthUrl;
    }

    public void setOutWorthUrl(String outWorthUrl) {
        this.outWorthUrl = outWorthUrl;
    }

    public String getAnnouncementUrl() {
        return announcementUrl;
    }

    public void setAnnouncementUrl(String announcementUrl) {
        this.announcementUrl = announcementUrl;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
