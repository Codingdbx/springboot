package com.weaver.rparecruitment;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/2 15:47
 * @since JDK1.8
 */
public class TestProperties {

    @Test
    void readData(){
        Properties pro = new Properties();
        try (FileInputStream is = new FileInputStream("D:/myproject/rpa-recruitment/src/main/resources/cookie.properties")) {
            pro.load(is);
            String cookie = pro.getProperty("cookie");
            String indexUrl = pro.getProperty("indexUrl");

            System.out.println("cookie=" + cookie);
            System.out.println("indexUrl=" + indexUrl);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Test
    void writeData(){
        String cookie = "qylevel=3;0.4123365258212359;qm_username=717053192;qm_authimgs_id=0;";
        String indexUrl = "https://exmail.qq.com/cgi-bin/frame_html?sid=1t7_sewEAr88HjRq,7&sign_type=";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D:/myproject/rpa-recruitment/src/main/resources/cookie.properties"))) {
            writer.write("cookie=" + cookie+"\n" + "indexUrl="+indexUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
