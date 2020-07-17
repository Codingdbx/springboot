package com.example.springbootautoweb.controller;


import com.example.springbootautoweb.entity.DataSet;
import com.example.springbootautoweb.entity.ElementSet;
import com.example.springbootautoweb.entity.IframeSet;
import com.example.springbootautoweb.entity.PageSet;
import com.example.springbootautoweb.service.DataSetService;
import com.example.springbootautoweb.service.ElementSetService;
import com.example.springbootautoweb.service.IframeSetService;
import com.example.springbootautoweb.service.PageSetService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: </p>
 *
 * @author dbx
 * @date 2020/3/9 11:13
 * @since JDK1.8
 */
@RestController
public class WriteDataController {

    private final PageSetService pageSetService;
    private final DataSetService dataSetService;
    private final ElementSetService elementSetService;
    private final IframeSetService iframeSetService;

    @Value("${driver.path}")
    private String driverPath;

    @Autowired
    public WriteDataController(PageSetService pageSetService, DataSetService dataSetService,
                               ElementSetService elementSetService, IframeSetService iframeSetService) {
        this.pageSetService = pageSetService;
        this.dataSetService = dataSetService;
        this.elementSetService = elementSetService;
        this.iframeSetService = iframeSetService;

    }

    @RequestMapping("/execute")
    public String execute(String pageId) {

//        String pageId = "C3QA3V0B2D14T9738C2954356452371M";

        PageSet pageSet = pageSetService.selectByPrimaryKey(pageId);

        WebDriver driver = getWebDriver(driverPath);
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(pageSet.getWaitTime()), TimeUnit.SECONDS);

        //打开页面
        driver.get(pageSet.getPageUrl());

        //OA需要用户手动登录
        try {
            Thread.sleep(20000);
            //打开页面
            driver.get(pageSet.getPageUrl());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //查出页面所有的元素
        List<ElementSet> elementSetList = elementSetService.selectListByPageId(pageSet.getPageId());

        for (ElementSet elementSet : elementSetList) {
            String locationType = elementSet.getLocationType();
            String locationValue = elementSet.getLocationValue();
            String iframeIds = elementSet.getIframeId();
            if (iframeIds != null && iframeIds.length() > 0) {
                String[] split = iframeIds.split(",");
                for (String id : split) {
                    IframeSet iframeSet = iframeSetService.selectByPrimaryKey(id);
                    WebElement iframe = getSelector(iframeSet.getLocationType(), iframeSet.getLocationValue()).findElement(driver);
                    //切换到iFrame里面
                    driver.switchTo().frame(iframe);
                }
            }

            WebElement element = getSelector(locationType, locationValue).findElement(driver);

            DataSet dataSet = dataSetService.selectByPrimaryKey(elementSet.getDataId());

            String elementType = elementSet.getElementType();

            switch (elementType) {
                case "p":
                case "input":
                    element.sendKeys(dataSet.getDataValue());
                    break;
                case "div":
                    executor.executeScript("arguments[0].title = '" + dataSet.getDataValue() + "';", element);
                    executor.executeScript("arguments[0].innerHTML= '" + dataSet.getDataValue() + "';", element);
                    break;
                case "hidden_input":
                    //改变隐藏的input值
                    executor.executeScript("WfForm.changeFieldValue('" + locationValue + "',{value:'" + dataSet.getDataValue() + "'});");
                    break;
                default:
                    break;
            }

            // 回到主iframe
            driver.switchTo().defaultContent();
        }

        return "executing success.";
    }

    private WebDriver getWebDriver(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }

    private By getSelector(String locationType, String locationValue) {

        switch (locationType) {
            case "id":
                return By.id(locationValue);
            case "xpath":
                return By.xpath(locationValue);
            case "css":
                return By.cssSelector(locationValue);
            case "name":
                return By.name(locationValue);
            case "className":
                return By.className(locationValue);
            case "tagName":
                return By.tagName(locationValue);
            case "linkText":
                return By.linkText(locationValue);
            case "partialLinkText":
                return By.partialLinkText(locationValue);
            default:
               throw new IllegalArgumentException("locationType not is the illegal.");
        }
    }

}
