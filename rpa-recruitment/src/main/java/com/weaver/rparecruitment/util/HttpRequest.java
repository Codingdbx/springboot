package com.weaver.rparecruitment.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Description:  http request</p>
 *
 * @author dbix
 * @date 2019/11/7
 * @since JDK1.8
 */
public final class HttpRequest {

    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";

    //传输数据格式
    private Charset output_charset = Charset.forName("UTF-8");
    //接受数据格式
    private Charset input_charset = Charset.forName("GBK");

    //连接主机超时时间
    private int connectTimeout;

    //从主机读取数据时间
    private int readTimeout;

    //可以接收数据
    private boolean doInput;
    //可以传输数据
    private boolean doOutput;

    //从本地缓存获取数据
    private boolean useCaches;

    {
        connectTimeout = 1000;
        readTimeout = 1000;
        doInput = true;
        doOutput = true;
        useCaches = false;
    }

    private String url;
    private URL _url;
    private HttpURLConnection connection;

    private String responseData;
    private int responseCode = 101;

    private Map<String, String> parameters = new HashMap<>();

    private Map<String, List<String>> headerFields = new HashMap<>();

    public HttpRequest(String url) {
        this.url = url;
    }

    /**
     * set parameter for request url
     * @param key key
     * @param value value
     */
    public void setParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void setOutput_charset(Charset output_charset) {
        this.output_charset = output_charset;
    }

    public void setInput_charset(Charset input_charset) {
        this.input_charset = input_charset;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    private void initConnection() throws IOException {
        initUrl();
        connection = (HttpURLConnection) _url.openConnection();
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        connection.setDoInput(doInput);
        connection.setDoOutput(doOutput);
        connection.setUseCaches(useCaches);
    }

    private void initUrl() throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        if (parameters.size() > 0) {
            parameters.forEach((key,value)->{
                if (sb.toString().contains("?")) {
                    sb.append("&")
                            .append(key)
                            .append("=")
                            .append(value);
                } else {
                    sb.append("?")
                            .append(key)
                            .append("=")
                            .append(value);
                }
            });
        }
        url = sb.toString();
        _url = new URL(url);
    }


    /**
     * http GET request
     *
     * @param properties request properties
     * @return response data.
     * @throws IOException throws exception
     */
    public void get(Map<String, String> properties) throws IOException {
        //初始化连接
        initConnection();
        //设置请求method
        connection.setRequestMethod(METHOD_GET);

        //设置属性
        properties.forEach(connection::setRequestProperty);
        //打开连接
        connection.connect();

        response();
    }

    /**
     * http POST request
     *
     * @param data       request data is put in request body
     * @param properties request properties
     * @throws IOException throws exception
     */
    public void post(byte[] data, Map<String, String> properties) throws IOException {
        //初始化连接
        initConnection();
        connection.setRequestMethod(METHOD_POST);

        properties.forEach(connection::setRequestProperty);
        connection.connect();

        // 向请求中写入数据
        // 即使connection.setRequestMethod(“GET”);
        // 只要用到了outputStream.write，这个请求就会变成POST请求。
        // GET 方式没有请求体
        try (OutputStream os = connection.getOutputStream()) {
            os.write(data);
        }

        response();
    }

    public void post(String data, Map<String, String> properties) throws IOException {
        this.post(data.getBytes(output_charset), properties);
    }

    private void response() throws IOException{
        StringBuilder sb = new StringBuilder();

        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream,input_charset);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
        }
        //获取返回状态码
        responseCode = connection.getResponseCode();
        headerFields = connection.getHeaderFields();
        responseData = sb.toString();

        connection.disconnect();
    }

    /**
     * Get response data
     *
     * @return response data
     */
    public String getResponseData() {
        return responseData;
    }

    /**
     * Get response code
     *
     * @return response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, List<String>> getResponseHeaders(){
        return headerFields;
    }
}
