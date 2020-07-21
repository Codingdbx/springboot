package com.weaver.rparecruitment.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 16:31
 * @since JDK1.8
 */
@RestController
public class TestController {


    @RequestMapping("/index")
    String index(HttpServletRequest request, HttpServletResponse response) {

        return "";
    }

    @RequestMapping("/x-www-form-urlencoded")
    String index(@RequestParam("fileName") String fileName) {

        System.out.println("fileName=" + fileName);

        return fileName;
    }


    @RequestMapping("/json")
    String index(@RequestBody Map<String, String> map) {
        String fileName = map.get("fileName");
        System.out.println("fileName=" + fileName);

        return fileName;
    }

    @RequestMapping("/form-data")
    String index(HttpServletRequest request,String fileName,@RequestParam("fieldNameHere") MultipartFile file) throws Exception {

        String originalFilename = file.getOriginalFilename();
        String extString = "";
        if (originalFilename != null && originalFilename.length() > 0) {
            extString = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        System.out.println("extString=" + extString);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();

            System.out.println("data=" + data);
            System.out.println("fileName=" + fileName);
            return fileName;
        }
    }

    @RequestMapping("/octet-stream")
    String index(HttpServletRequest request) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))){
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            System.out.println("data=" + data);
            return data;
        }

    }

}
