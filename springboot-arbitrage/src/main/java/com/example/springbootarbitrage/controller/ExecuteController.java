package com.example.springbootarbitrage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.springbootarbitrage.config.AppConfig;
import com.example.springbootarbitrage.entity.Announcement;
import com.example.springbootarbitrage.entity.Fund;
import com.example.springbootarbitrage.entity.Worth;
import com.example.springbootarbitrage.service.FundService;
import com.example.springbootarbitrage.util.HttpRequest;
import com.example.springbootarbitrage.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/4/3 9:18
 * @since JDK1.8
 */
@RestController
public class ExecuteController {

    protected final static Logger LOGGER = LoggerFactory.getLogger(ExecuteController.class);
    
    private FundService fundService;
    private AppConfig appConfig;
    
    @Autowired
    private ExecuteController(FundService fundService,AppConfig appConfig){
        this.fundService = fundService;
        this.appConfig = appConfig;
    }
    
    @RequestMapping("/execute")
    void execute(){
        List<Fund> fundList = fundService.selectAll();

        for (Fund fund : fundList) {
            //获取该基金场内价格

            //获取该基金场外净值
            Worth worth = getWorth(fund.getCode());
            //获取该基金前10个公告

            List<Announcement> announcement = getAnnouncement(fund.getCode());
            
            
        }
        

    }

    /**
     * 获取公告信息
     * @param symbol 场内基金代码
     * @return return list
     */
    private List<Announcement> getAnnouncement(String symbol){
        String url = appConfig.getAnnouncementUrl() + symbol+"SZ160416&count=10&source=%E5%85%AC%E5%91%8A&page=1";

        HttpRequest request = new HttpRequest(url);
        Map<String, String> properties = new HashMap<>();
        properties.put("Content-Type", "application/json");
        properties.put("Cookie", appConfig.getCookie());
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
        }

        return announcementList
                .stream()
                .sorted((a1, a2) -> Integer.compare(Integer.parseInt(a2.getId()), Integer.parseInt(a1.getId())))
                .collect(Collectors.toList());

    }
    
    private Worth getWorth(String code){
        String url = appConfig.getOutWorthUrl() + code;

        HttpRequest request = new HttpRequest(url);
        Map<String, String> properties = new HashMap<>();
        properties.put("Content-Type", "application/json");
        properties.put("Cookie", appConfig.getCookie());
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
        Worth worth = new Worth();
        worth.setId(StringUtils.UUID());
        worth.setCode(code);
        worth.setDate(end_date);
        worth.setGrowthRate(nav_grtd);
        worth.setValue(unit_nav);

        return worth;
    }
}
