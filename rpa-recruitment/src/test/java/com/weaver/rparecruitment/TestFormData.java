package com.weaver.rparecruitment;

import com.weaver.rparecruitment.util.HttpRequest;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 20:41
 * @since JDK1.8
 */
public class TestFormData {

    @Test
    void testFormData(){
        String url = "http://localhost:8087/form-data";


        HttpRequest request = new HttpRequest(url);
        request.setReadTimeout(5000);

        Map<String, String> properties = new HashMap<>();

        properties.put("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

        StringBuilder sb = new StringBuilder();

        sb.append("------WebKitFormBoundary7MA4YWxkTrZu0gW")
                .append("\r\n")
                .append("Content-Disposition: form-data; name=\"fileName\"")
                .append("\r\n\r\n").append("123123123").append("\r\n")
                .append("------WebKitFormBoundary7MA4YWxkTrZu0gW").append("\r\n")
                .append("Content-Disposition: form-data; name=\"fieldNameHere\";filename=\"Resin启动2.png\"")
                .append("\r\n").append("Content-Type: image/png\r\n\r\n").append("我说得对不对噢\r\n")
                .append("------WebKitFormBoundary7MA4YWxkTrZu0gW--");

        try {
            request.post(sb.toString(), properties);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int responseCode = request.getResponseCode();
        String responseData = request.getResponseData();

    }

    @Test
    void postManTest() throws Exception{
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"fileName\"\r\n\r\n123123123\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"fieldNameHere\"; filename=\"C:\\Users\\Administrator\\Desktop\\e9学习\\Resin启动2.png\"\r\nContent-Type: image/png\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
        Request request = new Request.Builder()
                .url("http://localhost:8087/form-data")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "ddc74498-d1b5-4689-ab61-dc411d7a9bff")
                .build();

        Response response = client.newCall(request).execute();
    }
}
