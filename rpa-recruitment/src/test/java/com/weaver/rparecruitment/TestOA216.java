package com.weaver.rparecruitment;

import com.alibaba.fastjson.JSONObject;
import com.weaver.rparecruitment.entity.Resume;
import com.weaver.rparecruitment.util.HttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 14:44
 * @since JDK1.8
 */
@SpringBootTest
public class TestOA216 {

    @Test
    void receiveResumeServlet216() {
        String url = "http://localhost:8080/weaver/receiveResumeServlet216";

        Resume resume = new Resume();

        resume.setTitle("[(51job.com)申请贵公司全盘账会计（上海）－光头强]投递时间[2020-03-18]");
        resume.setDeliveryTime("2020-03-18");
        resume.setName("光头强");
        resume.setEmail("sdadad@163.com");
        resume.setPhone("15801797231");
        resume.setSex("男");
        resume.setAge("28");
        resume.setSource("resume@quickmail.51job.com");
        resume.setPosition("java开发工程师");

        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(resume);

        String jsonString = JSONObject.toJSONString(resumeList);

        String json = "[{\"age\":\"27\",\"deliveryTime\":\"2020-03-20\",\"email\":\"18202791071@163.com\",\"name\":\"吕超凡\",\"phone\":\"18202791071\",\"position\":\"电商支持专员\",\"sex\":\"女\",\"source\":\"resume@quickmail.51job.com\",\"title\":\"(51job.com)申请贵公司大客户销售（襄阳）（襄阳）－吕超凡\"}]";
        Map<String, String> properties = new HashMap<>();

        //发送json数据
        properties.put("Content-Type", "application/json;charset=UTF-8");

        HttpRequest request = new HttpRequest(url);
        request.setReadTimeout(5000);

        try {
            request.post(json, properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int responseCode = request.getResponseCode();
        String responseData = request.getResponseData();

    }
}
