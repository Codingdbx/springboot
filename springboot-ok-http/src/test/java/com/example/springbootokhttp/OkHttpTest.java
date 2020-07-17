package com.example.springbootokhttp;

import okhttp3.*;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 11:57
 * @since JDK1.8
 */
public class OkHttpTest {

    OkHttpClient client = new OkHttpClient();

    @Test
    public void get(){

        Request request = new Request.Builder()
                .url("https://www.w3.org/Protocols/?id=syde&u=i")
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "b0649422-cbbd-4c56-b81b-d73560fc6f53")
                .build();

        try (Response response = client.newCall(request).execute()){

            response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void post(){

        MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
        RequestBody body = RequestBody.create("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"j\"\r\n\r\n23123\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"r\"\r\n\r\n12312\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--",mediaType);

        Request request = new Request.Builder()
                .url("https://www.w3.org/Protocols/?id=syde&u=i")
                .post(body)
                .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "da424986-c431-4870-bd52-be45d46c8542")
                .build();

        try (Response response = client.newCall(request).execute()){

            response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
