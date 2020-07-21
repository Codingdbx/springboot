package com.weaver.rparecruitment;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/1 11:22
 * @since JDK1.8
 */
public class TestAppendUrl {

    @Test
    void testAppend(){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("id", "999");
        parameters.put("set", "es");
        parameters.put("account", "dhs");


//        String url = "https://www.e-cology.com.cn/wui/index.html#/main/portal/portal-195-1?_key=7ota2p";
        String url = "http://localhost.fiddler:8080/weaver/upLoadFileServlet";

        if (parameters.size() > 0) {
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                StringBuilder sb = new StringBuilder();
                sb.append(url);
                if (url.contains("?")) {
                    sb.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                } else {
                    sb.append("?")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                }
                url = sb.toString();
            }
        }

        System.out.println("new url=============" + url);
    }
}
