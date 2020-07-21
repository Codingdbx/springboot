package com.weaver.rparecruitment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/1 16:05
 * @since JDK1.8
 */
@Component
@ConfigurationProperties(prefix = "weaver.rpa.email")
public class EmailConfig {

    private String loginUrl;

    private Integer refreshTime;

    private String cookiePath;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public Integer getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Integer refreshTime) {
        this.refreshTime = refreshTime;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }
}
