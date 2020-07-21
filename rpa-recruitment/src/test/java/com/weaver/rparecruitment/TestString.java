package com.weaver.rparecruitment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 13:56
 * @since JDK1.8
 */
@SpringBootTest
class TestString {

    @Test
    void trim() {
        String resource = "[(51jo b.com)申 请贵公司全盘账会计（上海）－t]投递时间[2020 -03-18]";
        char[] value = resource.toCharArray();

        int len = value.length;
        int st = 0;
        char[] val = value;    /* avoid getfield opcode */

        //ASCII编码
        //char为16位的数据，为无符号数，其范围为：0 至 2 ^ 32 -1，即 0 - 65535，用十六进制码来看，则为：’\u0000’ - ‘\uffff’。
        while ((st < len) && (val[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        String result = ((st > 0) || (len < value.length)) ? resource.substring(st, len) : resource;

    }

    @Test
    void testChar() {

        int a = ' ';
        System.out.println("a=" + a);

        int b = 31;
        System.out.println("b=" + (char) b);

        int c = '0';
        System.out.println("c=" + c);

        //ascii码  '0'=48  '1'=49
        // '1' -> 1
        char c9 = '1';
        int num9 = c9 - '0';

        // 1 -> '1'
        int num10 = 1;
        char c10 = (char) (num10 + '0');

        //用1 - 26来表示字母'a' 到 'z'  'a' = 97

        char a2 = 1 + '`';
        System.out.println("a2=" + a2);


    }

    @Test
    void testRegex() {
        String str = "徐斌 | 男 | 38 岁(1981/12/23) | 19年工作经验";

        //根据|分割成字符串数组
        String[] splits = str.split("\\|");

        char[] chars = str.toCharArray();
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        System.out.println(chars.length);
        System.out.println(bytes.length);

        byte a = (byte) 'a';
        char b = '徐';

        System.out.println("a=" + a);
        System.out.println("b=" + b);

        byte c = (byte) b;
        long d = b;
        System.out.println("c=" + c);
        System.out.println("d=" + d);

        String e = "徐";
        byte[] eBytes = e.getBytes(StandardCharsets.UTF_8);
        String str_eBytes = new String(eBytes, StandardCharsets.UTF_8);

        byte f = 112;
        char g = (char) f;
        System.out.println("g=" + g);
    }

}
