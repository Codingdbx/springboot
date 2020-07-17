package com.example.springbootarbitrage;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springbootarbitrage.config.AppConfig;
import com.example.springbootarbitrage.entity.Announcement;
import com.example.springbootarbitrage.util.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/3 10:02
 * @since JDK1.8
 */
@SpringBootTest
public class TestBase {

    @Autowired
    private AppConfig appConfig;

    @Test
    void testOutWorthUrl() {
        String url = appConfig.getOutWorthUrl() + "160416";

        HttpRequest request = new HttpRequest(url);
        Map<String, String> properties = new HashMap<>();
        properties.put("Content-Type", "application/json");
        properties.put("Cookie", "device_id=24700f9f1986800ab4fcc880530dd0ed; xq_a_token=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xqat=2ee68b782d6ac072e2a24d81406dd950aacaebe3; " +
                "xq_r_token=f9a2c4e43ce1340d624c8b28e3634941c48f1052; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTU4NzUyMjY2MSwiY3RtIjoxNTg1NTYxOTY1MDEwLCJjaWQiOiJkOWQwbjRBWnVwIn0." +
                "A4gYARkYumFWKtm-b6nV3U4JazaGNPwkOm6ADu1NEzVjlcu7C34OM3Q6a6H2leSdg553e6XXxQ_BJR0QN23KLvyI45Bc1CJVX3m_qqrGMmxt-yXENiWU6cy2LSVmU3iEUzzE-zkJxxESSq2c990kLidUvFmu5_rx958V0lyheXVZHJ35v288wuCJ_" +
                "YGXZN0v2D3UvnyszIBsJi7apewQhnE14bcwmu-FmDISQyGTiCoLkZ_Y1BTJC-QVCM4t_s6fZwOPif7pnz_IZMRkSy8MnHVmXUbwWTJTiMTKcBLNwasCbKXDz3B1WzFJTqc2SE3t1CEpxsxNaQMcnaQgoEyHoA; u=431585562011986; Hm_lvt_" +
                "1db88642e346389874251b5a1eded6e3=1585562013,1585878220; is_overseas=0; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1585878255");
        try {
            request.get(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseData = request.getResponseData();

        JSONObject jsonData = JSONObject.parseObject(responseData);

        String fd_code = "";//基金代码
        String fd_name = "";//基金名称
        String fd_type = "";//基金类型
        String unit_nav = "";//场外净值
        String end_date = "";//净值日期
        String unit_acc_nav = "";
        String nav_grtd = "";//涨跌幅
        JSONArray data = jsonData.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject json = data.getJSONObject(i);
            if (json.containsKey("fd_code")) {
                fd_code = (String) json.get("fd_code");
            }
            if (json.containsKey("fd_name")) {
                fd_name = (String) json.get("fd_name");
            }
            if (json.containsKey("fd_type")) {
                fd_type = (String) json.get("fd_type");
            }
            if (json.containsKey("unit_nav")) {
                unit_nav = (String) json.get("unit_nav");
            }
            if (json.containsKey("end_date")) {
                end_date = (String) json.get("end_date");
            }
            if (json.containsKey("nav_grtd")) {
                nav_grtd = (String) json.get("nav_grtd");
            }
            if (json.containsKey("unit_acc_nav")) {
                unit_acc_nav = (String) json.get("unit_acc_nav");
            }

        }
        System.out.println("基金名称：" + fd_name + "\t基金代码：" + fd_code + "\t基金类型：" + fd_type +
                "\t场外净值：" + unit_nav + "\t净值日期：" + end_date + "\t涨跌幅：" + nav_grtd + "%");


    }

    @Test
    void testInWorth() {
        String url = appConfig.getInWorthUrl() + "SZ160416";

        HttpRequest request = new HttpRequest(url);
        Map<String, String> properties = new HashMap<>();
        properties.put("Content-Type", "application/json");
        properties.put("Cookie", "device_id=24700f9f1986800ab4fcc880530dd0ed; xq_a_token=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xqat=2ee68b782d6ac072e2a24d81406dd950aacaebe3; " +
                "xq_r_token=f9a2c4e43ce1340d624c8b28e3634941c48f1052; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTU4NzUyMjY2MSwiY3RtIjoxNTg1NTYxOTY1MDEwLCJjaWQiOiJkOWQwbjRBWnVwIn0." +
                "A4gYARkYumFWKtm-b6nV3U4JazaGNPwkOm6ADu1NEzVjlcu7C34OM3Q6a6H2leSdg553e6XXxQ_BJR0QN23KLvyI45Bc1CJVX3m_qqrGMmxt-yXENiWU6cy2LSVmU3iEUzzE-zkJxxESSq2c990kLidUvFmu5_rx958V0lyheXVZHJ35v288wuCJ_" +
                "YGXZN0v2D3UvnyszIBsJi7apewQhnE14bcwmu-FmDISQyGTiCoLkZ_Y1BTJC-QVCM4t_s6fZwOPif7pnz_IZMRkSy8MnHVmXUbwWTJTiMTKcBLNwasCbKXDz3B1WzFJTqc2SE3t1CEpxsxNaQMcnaQgoEyHoA; u=431585562011986; Hm_lvt_" +
                "1db88642e346389874251b5a1eded6e3=1585562013,1585878220; is_overseas=0; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1585878255");
        try {
            request.get(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseData = request.getResponseData();

        JSONObject jsonData = JSONObject.parseObject(responseData);

        String symbol = "";//场内代码
        String code = "";//基金代码
        String name = "";//基金名称
        Integer type = 0;//基金类型
        String exchange = "";//交易所
        BigDecimal current = new BigDecimal(0.000);//当前交易价格

        BigDecimal percent = new BigDecimal(0.000);//涨跌幅

        if (jsonData.containsKey("data")) {
            JSONObject data = (JSONObject) jsonData.get("data");
            JSONArray items = data.getJSONArray("items");
            for (int i = 0; i < items.size(); i++) {
                JSONObject item = items.getJSONObject(i);
                if (item.containsKey("quote")) {
                    JSONObject quote = (JSONObject) item.get("quote");
                    if (quote.containsKey("symbol")) {
                        symbol = (String) quote.get("symbol");
                    }
                    if (quote.containsKey("code")) {
                        code = (String) quote.get("code");
                    }
                    if (quote.containsKey("name")) {
                        name = (String) quote.get("name");
                    }
                    if (quote.containsKey("type")) {
                        type = (Integer) quote.get("type");
                    }
                    if (quote.containsKey("exchange")) {
                        exchange = (String) quote.get("exchange");
                    }
                    if (quote.containsKey("current")) {
                        current = (BigDecimal) quote.get("current");
                    }
                    if (quote.containsKey("percent")) {
                        percent = (BigDecimal) quote.get("percent");
                    }
                }
            }
        }

        System.out.println("基金名称：" + name + "\t场内代码：" + symbol + "\t当前交易价格：" + current.toString() + "\t涨跌幅：" + percent.toString() + "%");

    }

    @Test
    void testAnnouncementUrl() {
        String url = appConfig.getAnnouncementUrl() + "SZ160416&count=10&source=%E5%85%AC%E5%91%8A&page=1";

        HttpRequest request = new HttpRequest(url);
        Map<String, String> properties = new HashMap<>();
        properties.put("Content-Type", "application/json");
        properties.put("Cookie", "device_id=24700f9f1986800ab4fcc880530dd0ed; xq_a_token=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xqat=2ee68b782d6ac072e2a24d81406dd950aacaebe3; " +
                "xq_r_token=f9a2c4e43ce1340d624c8b28e3634941c48f1052; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTU4NzUyMjY2MSwiY3RtIjoxNTg1NTYxOTY1MDEwLCJjaWQiOiJkOWQwbjRBWnVwIn0." +
                "A4gYARkYumFWKtm-b6nV3U4JazaGNPwkOm6ADu1NEzVjlcu7C34OM3Q6a6H2leSdg553e6XXxQ_BJR0QN23KLvyI45Bc1CJVX3m_qqrGMmxt-yXENiWU6cy2LSVmU3iEUzzE-zkJxxESSq2c990kLidUvFmu5_rx958V0lyheXVZHJ35v288wuCJ_" +
                "YGXZN0v2D3UvnyszIBsJi7apewQhnE14bcwmu-FmDISQyGTiCoLkZ_Y1BTJC-QVCM4t_s6fZwOPif7pnz_IZMRkSy8MnHVmXUbwWTJTiMTKcBLNwasCbKXDz3B1WzFJTqc2SE3t1CEpxsxNaQMcnaQgoEyHoA; u=431585562011986; Hm_lvt_" +
                "1db88642e346389874251b5a1eded6e3=1585562013,1585878220; is_overseas=0; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1585878255");
        try {
            request.get(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseData = request.getResponseData();

        JSONObject jsonData = JSONObject.parseObject(responseData);

        List<Announcement> announcementList = new ArrayList<>();
        String source = "";//公告
        String text = "";//标题
        String timeBefore = "";//发布时间
        Integer id = 0;//id
        JSONArray list = jsonData.getJSONArray("list");
        for (int i = 0; i < list.size(); i++) {
            Announcement announcement = new Announcement();
            JSONObject json = list.getJSONObject(i);
            if (json.containsKey("id")) {
                id = (Integer) json.get("id");
            }
            if (json.containsKey("source")) {
                source = (String) json.get("source");
            }
            if (json.containsKey("text")) {
                text = (String) json.get("text");
            }
            if (json.containsKey("timeBefore")) {
                timeBefore = (String) json.get("timeBefore");
            }

            announcement.setId(id + "");
            announcement.setCode("SZ160416");
            announcement.setTitle(text);
            announcement.setDate(timeBefore);
            announcementList.add(announcement);
//            System.out.println("公告：" + source + "\t公告标题：" + text + "\t发布时间：" + timeBefore);
        }

        List<Announcement> new_list = announcementList
                                    .stream()
                                    .sorted((a1, a2) -> Integer.compare(Integer.parseInt(a2.getId()), Integer.parseInt(a1.getId())))
                                    .collect(Collectors.toList());

        for (Announcement a : new_list) {
            System.out.println("日期：" + a.getDate());

        }
    }
}
