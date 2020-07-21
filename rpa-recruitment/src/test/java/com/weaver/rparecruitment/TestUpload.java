package com.weaver.rparecruitment;

import com.weaver.rparecruitment.util.HttpRequest;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 13:57
 * @since JDK1.8
 */
class TestUpload {

    @Test
    void testUpLoad() throws Exception {

        String file_path = "C:\\Users\\Administrator\\Downloads\\resume.html";

//        String url = "http://localhost.fiddler:8080/weaver/upLoadFileServlet?id=898";
        String url = "http://localhost:8087/x-www-form-urlencoded";

        HttpRequest request = new HttpRequest(url);
        request.setParameter("fileName", "12312312");
        request.setParameter("data", "uuueerrr");
        Map<String, String> properties = new HashMap<>();

        FileInputStream is = new FileInputStream(file_path);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        is.close();
        isr.close();
        br.close();

        String content = sb.toString();

        request.setReadTimeout(10000);
        properties.put("Content-Type", "application/octet-stream");
        properties.put("Content-Length", "" + content.length());

        request.post(content, properties);
        int responseCode = request.getResponseCode();
        String responseData = request.getResponseData();

    }

    @Test
    void resume() throws Exception {
        String downLoad = "https://exmail.qq.com/cgi-bin/download?mailid=ZC0501-tLxWqfSZgD2~UHokoqXgHag&filename=resume.html&sid=1t7_sewEAr88HjRq,7&detect_qmail=1";
        HttpRequest request = new HttpRequest(downLoad);
        Map<String, String> properties = new HashMap<>();

        properties.put("Cookie", "0.30622425797757047; tvfe_boss_uuid=569c5da79a639def; pgv_pvid=3966690512; pac_uid=0_5ddcedec57ffd; RK=qmLMhQTcM6; ptcz=29cbd7343985969be3f94d313e5d30b2cd0b9e88339890ab21d6b503aa9d34c1; " +
                "pgv_pvi=1669745664; _ga=GA1.2.469530291.1583720955; _ga=GA1.3.469530291.1583720955; CCSHOW=0000; logout_page=dm_loginpage; _gid=GA1.3.318544267.1585636782; uin=o1404488036; skey=@pIjj1L6km; dm_login_weixin_rem=; " +
                "qm_flag=3; qqmail_alias=yeye.li@weaver.com.cn; qm_username=717053192; biz_username=717053192; username=717053192&717053192; qylevel=3; qm_sk=717053192&yKamb4b5; biz_referrer=www.baidu.com; qm_authimgs_id=1; " +
                "Hm_lvt_bdfb0d7298c0c5a5a2475c291ac7aca2=1585636782,1585645195,1585719657,1585720029; qm_verifyimagesession=h01979adb98511b017e837155e05fd384181c03200dbe804dc8d5cc53ca6737cf8504c785cd70e404ef; Hm_lpvt_bdfb0d7298c0c5a5a2475c291ac7aca2=1585720031; " +
                "0.45933900195055677; sid=717053192&fbce9051b32f42d3ee05ae9704970723,cQxcQFjHyCyc.; qm_sid=fbce9051b32f42d3ee05ae9704970723,cQxcQFjHyCyc.; ssl_edition=sail.qq.com; qm_ssum=717053192&9580497cc137477e028da4848fc7d011; " +
                "tinfo=EXPIRED; new_mail_num=717053192&18");
        request.get(properties);

        int responseCode = request.getResponseCode();
        String responseData = request.getResponseData();

        //响应Header信息
        Map<String, List<String>> responseHeaders = request.getResponseHeaders();

        System.out.println("显示响应Header信息...");

        for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
            System.out.println("Key : " + entry.getKey() +
                    " ,Value : " + entry.getValue());
        }

        System.out.println("responseData=================" + responseData);

    }

    @Test
    void testCookie() throws Exception {
        String downLoad = "https://shsnnccms.com.cn/nsheng/new.jsp";
        HttpRequest request = new HttpRequest(downLoad);
        Map<String, String> properties = new HashMap<>();

        properties.put("Cookie", "JSESSIONID=ED962A1E435EF6D8B36801B6E8CBAD23; userTrue=565827F62711D01558BF2DA8A48F5CC150BDE46AFD20D54F5B92DBBA3F92900D");
        request.get(properties);

        int responseCode = request.getResponseCode();
        String responseData = request.getResponseData();

        //响应Header信息
        Map<String, List<String>> responseHeaders = request.getResponseHeaders();

        System.out.println("显示响应Header信息...");

        for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
            System.out.println("Key : " + entry.getKey() +
                    " ,Value : " + entry.getValue());
        }

        System.out.println("responseData=================" + responseData);

    }
}
