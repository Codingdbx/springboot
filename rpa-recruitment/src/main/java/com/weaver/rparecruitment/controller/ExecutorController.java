package com.weaver.rparecruitment.controller;

import com.alibaba.fastjson.JSONObject;
import com.weaver.rparecruitment.config.AppConfig;
import com.weaver.rparecruitment.config.EmailConfig;
import com.weaver.rparecruitment.entity.Resume;
import com.weaver.rparecruitment.util.HttpRequest;
import com.weaver.rparecruitment.util.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 从邮箱中下载简历信息</p>
 *
 * @author dbx
 * @date 2020/3/16 10:53
 * @since JDK1.8
 */
@RestController
public class ExecutorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorController.class);

    private AppConfig appConfig;
    private EmailConfig emailConfig;

    private WebDriver driver;

    @Autowired
    public ExecutorController(AppConfig appConfig, EmailConfig emailConfig) {
        this.appConfig = appConfig;
        this.emailConfig = emailConfig;
    }

    //@RequestMapping("/downLoadResumeForExMail")
    public void downLoadResumeForExMail() {

        driver = getWebDriver(appConfig.getDriverPath());

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        //打开腾讯企业微信登录页面
        driver.get(emailConfig.getLoginUrl());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Map<String, String> map;
        String cookie = "";
        String indexUrl = "";

        //是否采用自动登录的方式
        boolean auto_login = true;

        try {
            map = readCookie(emailConfig.getCookiePath());
            cookie = map.get("cookie");
            indexUrl = map.get("indexUrl");
            if (cookie == null || cookie.length() == 0) {
                auto_login = false;
            }
        } catch (Exception e) {
            LOGGER.error("读取cookie配置文件出错!");
            LOGGER.error(e.getMessage(), e);
        }

        boolean loop = false;

        //cookie登录的方式
        if (appConfig.isEnableCookie() && auto_login) {
            LOGGER.info("启用cookie登录...");
            //清除所有当前域的cookie
            driver.manage().deleteAllCookies();
            //设置浏览器cookie
            this.setCookie(cookie);
            //跳到首页
            driver.get(indexUrl);

            try {
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.urlContains("https://exmail.qq.com/cgi-bin/frame_html"));
                LOGGER.info("cookie登录成功!");
                loop = true;
            } catch (Exception e) {
                LOGGER.error("cookie登录失败,cookie已经失效!");
//                LOGGER.error(e.getMessage(), e);
                auto_login = false;
            }

        } else {
            auto_login = false;
        }

        // 用户手动登录
        if (!auto_login) {
            driver.get(emailConfig.getLoginUrl());
            driver.manage().deleteAllCookies();
            LOGGER.info("等待用户手动登录...");
            try {
                WebDriverWait wait = new WebDriverWait(driver, 3000);
                wait.until(ExpectedConditions.urlContains("https://exmail.qq.com/cgi-bin/frame_html"));
                LOGGER.info("用户登录成功!");
                loop = true;
                //解析cookie值
                String save_cookie = getCookie();
                //获取首页地址
                String currentUrl = driver.getCurrentUrl();

                //写入数据到配置文件
                writeCookie(emailConfig.getCookiePath(), save_cookie, currentUrl);
            } catch (Exception e) {
                LOGGER.error("用户登录失败!");
                LOGGER.error(e.getMessage(), e);

            }
        }


        while (loop) {
            try {
                //刷新页面
                driver.navigate().refresh();

                //收件箱
                WebElement folder_1 = By.id("folder_1").findElement(driver);
                folder_1.click();

                //进入mainFrame
                WebElement mainFrame = By.id("mainFrame").findElement(driver);
                driver.switchTo().frame(mainFrame);

                //当天的邮件列表div /html/body/form[3]/div[2]
                // /html/body/form[3]/div[3] 前一天的邮件列表
                WebElement email_div = By.xpath("/html/body/form[3]/div[2]").findElement(driver);

                List<String> scripts = new ArrayList<>();

                List<WebElement> tables = email_div.findElements(By.tagName("table"));
                for (WebElement table : tables) {
                    String aClass = table.getAttribute("class");
                    //判断是否已读
                    if ("iF".equals(StringUtils.trimEnhance(aClass))) {//未读
                        //获取onclick事件
                        List<WebElement> tds0 = table.findElements(By.tagName("td"));
                        String script = tds0.get(2).getAttribute("onclick");

                        try {
                            WebElement ta = table.findElement(By.tagName("table"));
                            List<WebElement> tds = ta.findElements(By.tagName("td"));
                            WebElement td = tds.get(0);
                            String title = td.getAttribute("title");
                            //前程无忧
                            if ("resume@quickmail.51job.com".equals(title)) {
                                scripts.add(script);
                            }
                            //拉勾招聘
                            if ("service@portal.lagoujobs.com".equals(title)) {
                                //需要登录
                            }

                            //智联招聘
                            if ("b3.service@zhaopinmail.com".equals(title)) {
                                //查看手机等隐私信息，需要登录
                            }

                            //BOSS直聘
                            if ("cv@service.bosszhipin.com".equals(title)) {
                                //不需要登录，但简历格式不统一
                            }
                            //猎聘
                            if ("service@mail7.lietou-edm.com".equals(title)) {
                                //查看手机等隐私信息，需要登录
                            }

                        } catch (NoSuchElementException e) {
                            LOGGER.error("获取简历列表失败!");
                            LOGGER.error(e.getMessage(), e);
                        }
                    }
                }

                LOGGER.info("当前简历列表数量[{}]\n\n", scripts.size());

                List<Resume> resumeList = new ArrayList<>();
                for (int i = 0; i < scripts.size(); i++) {
                    executor.executeScript(scripts.get(i));
                    Thread.sleep(3000);

                    LOGGER.info("获取简历信息开始...");

                    //简历标题
                    WebElement title = driver.findElement(By.id("subject"));
                    String title_text = title.getText();
                    LOGGER.info("当前列表剩余数量[{}],简历标题:" + title_text, scripts.size() - i);

                    //投递时间
                    WebElement time = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[1]/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[2]/span"));
                    String time_text = time.getText();

                    //姓名
                    WebElement name = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[1]/tbody/tr/td[2]/table[1]/tbody/tr/td[1]/strong"));
                    String name_text = name.getText();

                    //年龄与性别组
                    WebElement age_sex = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[1]/tbody/tr/td[2]/table[1]/tbody/tr/td[1]"));
                    String age_sex_text = age_sex.getText();

                    String[] splits = age_sex_text.split("\\|");

                    //性别
                    String sex = splits[1];

                    //年龄
                    String age = splits[2];

                    //手机号
                    WebElement phone = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[1]/tbody/tr/td[2]/table[2]/tbody/tr[1]/td[1]/table/tbody/tr/td[2]"));
                    String phone_text = phone.getText();

                    //邮箱
                    WebElement email = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[1]/tbody/tr/td[2]/table[2]/tbody/tr[1]/td[2]/table/tbody/tr/td[2]/a"));
                    String email_text = email.getText();

                    //应聘职位
                    WebElement position = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]"));
                    String position_text = position.getText();

                    //居住地
                    WebElement address = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[1]/tbody/tr/td[2]/table[2]/tbody/tr[2]/td[1]/table/tbody/tr/td[2]"));
                    String address_text = address.getText();

                    //最高学历
                    WebElement education = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/table[2]/tbody/tr/td/table[2]/tbody/tr/td/table/tbody/tr/td[2]/table/tbody/tr[4]/td[2]"));
                    String education_text = education.getText();

                    //简历附件
                    WebElement annex = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[2]/div[2]/div[2]/div/a[1]"));
                    String href = annex.getAttribute("href");

                    //下载并上传简历信息http://localhost.fiddler:8080/weaver/upLoadFileServlet
                    String fileName = name_text + "-" + position_text;
                    String imageFileId = downLoadAndUpLoadResume(href, appConfig.getUploadUrl(), fileName, appConfig.getLoginId());

                    if (!StringUtils.isBlank(imageFileId)) {
                        LOGGER.info("下载简历并上传到OA系统成功!");
                    }

                    LOGGER.info("读取简历成功，简历标题[{}]投递时间[{}]", title_text, time_text);

                    Resume resume = new Resume();

                    resume.setTitle(title_text);
                    resume.setDeliveryTime(time_text);
                    resume.setPhone(phone_text);
                    resume.setName(name_text);
                    resume.setAge(StringUtils.findNumber(age).substring(0, 2));
                    resume.setSex(StringUtils.trimEnhance(sex));
                    resume.setEmail(email_text);
                    resume.setPosition(position_text);
                    resume.setSource("前程无忧");

                    resume.setAddress(address_text);
                    resume.setEducation(education_text);
                    resume.setImageFileId(imageFileId);
                    resume.setCreatorId(appConfig.getLoginId());
                    //获取本机的ip地址
                    String ip = InetAddress.getLocalHost().getHostAddress();
                    resume.setCreatorIp(ip);

                    resumeList.add(resume);
                }

                //请求OA接口入人才库 http://localhost:8080/weaver/receiveResumeServlet
                if (resumeList.size() > 0) {
                    String jsonString = JSONObject.toJSONString(resumeList);
                    LOGGER.info("发送简历到人才库信息:" + jsonString);

                    //发送json数据
                    Map<String, String> properties = new HashMap<>();
                    properties.put("Content-Type", "application/json;charset=UTF-8");
                    HttpRequest request = new HttpRequest(appConfig.getReceiveUrl());
                    try {
                        request.post(jsonString, properties);
                        int responseCode = request.getResponseCode();
                        String id = request.getResponseData();
                        if (responseCode == 200 && !StringUtils.isBlank(id)) {
                            LOGGER.info("发送简历到人才库成功\n\n");
                        }

                    } catch (Exception e) {
                        LOGGER.error("发送简历到人才库失败\n\n");
                        LOGGER.error(e.getMessage(), e);
                    }
                }

                //腾讯企业邮箱有限制刷新频率
                Thread.sleep(1000 * emailConfig.getRefreshTime());
            } catch (Exception e) {
                LOGGER.error("发生系统异常!");
                LOGGER.error(e.getMessage(), e);
//                loop = false;

            }
        }

    }

    /**
     * set web driver path
     * @param driverPath google drive path
     * @return WebDriver
     */
    private WebDriver getWebDriver(String driverPath) {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver(options);
    }

    /**
     * 从邮箱下载简历并上传OA系统
     * @param downLoadUrl downLoadUrl
     * @param upLoadUrl upLoadUrl
     * @param fileName fileName
     * @param userId userId 上传用户ID
     * @return imageFileId 附件ID
     * @throws IOException throws IOException
     */
    private String downLoadAndUpLoadResume(String downLoadUrl, String upLoadUrl, String fileName, String userId) throws IOException {

        HttpRequest down_request = new HttpRequest(downLoadUrl);
        Map<String, String> properties = new HashMap<>();
        properties.put("Cookie", getCookie());
        down_request.get(properties);

        //下载数据
        String responseData = down_request.getResponseData();

        //上传简历接口
        HttpRequest up_request = new HttpRequest(upLoadUrl);
        up_request.setReadTimeout(10 * 1000);
        //设置请求参数
        up_request.setParameter("fileName", URLEncoder.encode(fileName,"UTF-8"));
        up_request.setParameter("userId", userId);

        Map<String, String> up_properties = new HashMap<>();
        up_properties.put("Content-Type", "application/octet-stream");
        up_properties.put("Content-Length", "" + responseData.length());

        //上传简历
        up_request.post(responseData, up_properties);

        String json = up_request.getResponseData();
        JSONObject jsonObject = JSONObject.parseObject(json);

        return (String) jsonObject.get("imageFileId");
    }

    /**
     * 读取cookie
     *
     * @param path cookie file path
     * @return cookie
     * @throws Exception throws exception
     */
    private Map<String, String> readCookie(String path) throws Exception {
        Map<String, String> map = new HashMap<>();
        //使用ClassLoader加载配置文件，在打包之后读取classpath下的文件不会失效
        //InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
        try (FileInputStream is = new FileInputStream(path)) {
            Properties pro = new Properties();
            pro.load(is);
            String cookie = pro.getProperty("cookie");
            String indexUrl = pro.getProperty("indexUrl");
            map.put("cookie", cookie);
            map.put("indexUrl", indexUrl);
        }
        return map;
    }

    /**
     * write cookie to file
     *
     * @param path     file path
     * @param cookie   cookie value
     * @param indexUrl indexUrl value
     * @throws Exception throws exception
     */
    private void writeCookie(String path, String cookie, String indexUrl) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write("cookie=" + cookie + "\n" + "indexUrl=" + indexUrl);
        }
    }

    /**
     * 设置cookie
     *
     * @param cookie cookie value
     */
    private void setCookie(String cookie) {
//        Set<Cookie> cookieSet = driver.manage().getCookies();
//        String domain = "";
//        String path = "";
//        for (Cookie c :cookieSet) {
//            domain = c.getDomain();
//            path = c.getPath();
//        }

        String[] cookies = cookie.split(";");
        for (String str : cookies) {
            String[] _str = str.split("=");

            //(name, value, domain, path, expiry)
            Cookie _cookie;
            if (_str.length > 1) {
                //cookie不能有前导空格
                _cookie = new Cookie(_str[0].trim(), _str[1].trim());
                driver.manage().addCookie(_cookie);
            }
        }
    }

    /**
     * get cookies String from browser
     * @return cookies String
     */
    private String getCookie() {
        Set<Cookie> cookies = driver.manage().getCookies();

        StringBuilder sb = new StringBuilder();

        for (Cookie cookie : cookies) {
            if (StringUtils.isBlank(cookie.getName())) {
                sb.append(cookie.getValue()).append(";");
            } else {
                sb.append(cookie.getName())
                        .append("=")
                        .append(cookie.getValue())
                        .append(";");
            }
        }

        return sb.toString();
    }

}
