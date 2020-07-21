package com.weaver.rparecruitment.controller;

import com.weaver.rparecruitment.config.AppConfig;
import com.weaver.rparecruitment.entity.SearchCondition;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 发布简历信息</p>
 *
 * @author dbx
 * @date 2020/4/9 15:40
 * @since JDK1.8
 */

@RestController
public class PublishResumeTo51Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(PublishResumeTo51Controller.class);

    private AppConfig appConfig;

    private WebDriver driver;

    @Autowired
    public PublishResumeTo51Controller(AppConfig appConfig){
        this.appConfig = appConfig;
    }

    /**
     * 发布简历信息
     */
    @RequestMapping("/publishResumeMessage")
    void publishResumeMessage(@RequestBody(required = false) SearchCondition message){

        driver = getWebDriver(appConfig.getDriverPath());

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        //打开前程无忧登录页面
        driver.get("https://login.51job.com/login.php");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //是否采用自动登录的方式
        boolean auto_login = false;
        if (auto_login) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.urlToBe("https://www.51job.com"));
                LOGGER.info("cookie登录成功!");
            } catch (Exception e) {
                LOGGER.error("cookie登录失败,cookie已经失效!");
                LOGGER.error(e.getMessage(), e);
            }
        }


        driver.manage().deleteAllCookies();
        LOGGER.info("等待用户手动登录...");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3000);
            wait.until(ExpectedConditions.urlToBe("https://www.51job.com"));
            LOGGER.info("用户登录成功!");
            //解析cookie值
            //获取首页地址
            String currentUrl = driver.getCurrentUrl();

            //写入数据到配置文件
        } catch (Exception e) {
            LOGGER.error("用户登录失败!");
            LOGGER.error(e.getMessage(), e);
        }




    }

    /**
     * set web driver path
     * @param driverPath google drive path
     * @return WebDriver
     */
    private WebDriver getWebDriver(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }
}
