package com.weaver.rparecruitment;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/8 15:22
 * @since JDK1.8
 */
public class TestIpAddress {
    
    @Test
    void getIp(){
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();

            System.out.println("ip="+ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
