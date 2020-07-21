package com.weaver.rparecruitment;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.io.*;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/1 15:32
 * @since JDK1.8
 */
public class TestCookie {

    @Test
    void getCookie(){
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream("cookie.properties");
        try (FileInputStream is =new FileInputStream("D:/myproject/rpa-recruitment/src/main/resources/cookie.properties");
             BufferedReader bf = new BufferedReader(new InputStreamReader(is))) {
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = bf.readLine()) != null) {
                sb.append(str);
            }
            String cookie = sb.toString();
            //获取cookie值
            if (cookie.length() > 0) {
                cookie = cookie.substring(cookie.indexOf("=")+1);
            }

            System.out.println("cookie" + cookie);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setCookie(){
        String cookie = "0.30622425797757047; tvfe_boss_uuid=569c5da79a639def; pgv_pvid=3966690512; pac_uid=0_5ddcedec57ffd; RK=qmLMhQTcM6; ptcz=29cbd7343985969be3f94d313e5d30b2cd0b9e88339890ab21d6b503aa9d34c1; pgv_pvi=1669745664; _ga=GA1.2.469530291.1583720955; " +
                "_ga=GA1.3.469530291.1583720955; CCSHOW=0000; logout_page=dm_loginpage; _gid=GA1.3.318544267.1585636782; uin=o1404488036; skey=@pIjj1L6km; dm_login_weixin_rem=; qm_flag=3; qqmail_alias=yeye.li@weaver.com.cn; qm_username=717053192; biz_username=717053192; " +
                "username=717053192&717053192; qylevel=3; qm_sk=717053192&yKamb4b5; biz_referrer=www.baidu.com; qm_authimgs_id=1; Hm_lvt_bdfb0d7298c0c5a5a2475c291ac7aca2=1585636782,1585645195,1585719657,1585720029; " +
                "qm_verifyimagesession=h01979adb98511b017e837155e05fd384181c03200dbe804dc8d5cc53ca6737cf8504c785cd70e404ef; Hm_lpvt_bdfb0d7298c0c5a5a2475c291ac7aca2=1585720031; 0.45933900195055677; sid=717053192&fbce9051b32f42d3ee05ae9704970723,cQxcQFjHyCyc.; " +
                "qm_sid=fbce9051b32f42d3ee05ae9704970723,cQxcQFjHyCyc.; ssl_edition=sail.qq.com; qm_ssum=717053192&9580497cc137477e028da4848fc7d011; tinfo=EXPIRED; new_mail_num=717053192&18";
        String[] cookies = cookie.split(";");
        for (String str : cookies) {
            String[] strs = str.split("=");
            Cookie _cookie;
            if (strs.length == 1) {
                _cookie = new Cookie("", strs[0]);
            } else {
                _cookie = new Cookie(strs[0], strs[1]);
            }
            System.out.println("name=" + _cookie.getName() + ", value=" + _cookie.getValue());
        }

    }


}
