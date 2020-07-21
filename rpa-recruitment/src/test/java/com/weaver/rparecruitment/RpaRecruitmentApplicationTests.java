package com.weaver.rparecruitment;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.weaver.rparecruitment.entity.Resume;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RpaRecruitmentApplicationTests {

    @Test
    void contextLoads() {
    }



    @Test
    void testJson() {
        Resume resume = new Resume();

        resume.setTitle("[(51job.com)申请贵公司全盘账会计（上海）－t]投递时间[2020-03-18]");
        resume.setDeliveryTime("2020-03-18");
        resume.setName("t");
        resume.setPhone("15801797231");

        List<Resume> resumeList = new ArrayList<>();
        resumeList.add(resume);

        String jsonString = JSONObject.toJSONString(resumeList);
        System.out.println(jsonString);

        List<Resume> parse_resumeList = JSONObject.parseObject(jsonString, new TypeReference<List<Resume>>() {
        });

        System.out.println(parse_resumeList.size());

    }





}
