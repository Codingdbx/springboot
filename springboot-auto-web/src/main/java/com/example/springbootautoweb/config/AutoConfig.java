package com.example.springbootautoweb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/27 18:29
 * @since JDK1.8
 */

@ConfigurationProperties(prefix = "coding.dong")
@Component
public class AutoConfig {

    private boolean enabled = false;

    private String sql;

    private Init initializationMode = Init.ENABLED;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Init getInitializationMode() {
        return initializationMode;
    }

    public void setInitializationMode(Init initializationMode) {
        this.initializationMode = initializationMode;
    }
}
