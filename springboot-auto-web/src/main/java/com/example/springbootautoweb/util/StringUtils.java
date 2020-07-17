package com.example.springbootautoweb.util;

import java.util.UUID;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 10:24
 * @since JDK1.8
 */
public final class StringUtils {

    public static String UUID(){
        return  UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }
}
