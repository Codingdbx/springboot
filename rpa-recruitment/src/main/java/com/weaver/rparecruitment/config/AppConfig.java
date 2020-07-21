package com.weaver.rparecruitment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/1 14:02
 * @since JDK1.8
 */
@Component
@ConfigurationProperties(prefix = "weaver.rpa.app")
public class AppConfig {

    private String loginId;

    private String uploadUrl;

    private String driverPath;

    private String receiveUrl;

    private boolean enableCookie = false;//是否启用cookie方式登录

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }

    public String getReceiveUrl() {
        return receiveUrl;
    }

    public void setReceiveUrl(String receiveUrl) {
        this.receiveUrl = receiveUrl;
    }

    public boolean isEnableCookie() {
        return enableCookie;
    }

    public void setEnableCookie(boolean enableCookie) {
        this.enableCookie = enableCookie;
    }
}
