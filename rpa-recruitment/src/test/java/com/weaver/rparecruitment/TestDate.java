package com.weaver.rparecruitment;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/7 17:18
 * @since JDK1.8
 */
public class TestDate {

    @Test
    void testDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println("time"+format);

    }

    @Test
    void testDate2(){
        SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = _sdf.parse("2020-4-7");
            long time = date.getTime();

            String format = _sdf.format(new Date());
            long today = _sdf.parse(format).getTime();

            long len = (today - time)/(1000*60*60*24);

            System.out.println("长度："+len);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
