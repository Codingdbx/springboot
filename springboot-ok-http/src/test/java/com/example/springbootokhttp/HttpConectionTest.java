package com.example.springbootokhttp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/31 11:57
 * @since JDK1.8
 */
public class HttpConectionTest {
    String path = "";
    public void get() throws Exception{
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(5000);
        if (urlConnection.getResponseCode() == 200){
            InputStream inputStream = urlConnection.getInputStream();
        }
    }
    public void post() throws Exception{
        String name = "";
        String value = "";

        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000);
        String content = "name="+ URLEncoder.encode(name)+"&pass="+URLEncoder.encode(value);//数据编解码
        urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//设置请求头
        urlConnection.setRequestProperty("Content-Length",content.length()+"");
        urlConnection.setDoOutput(true);//以后就可以使用conn.getOutputStream().write()
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(content.getBytes());
        if (urlConnection.getResponseCode() == 200){
            InputStream inputStream = urlConnection.getInputStream();
        }
    }
}
