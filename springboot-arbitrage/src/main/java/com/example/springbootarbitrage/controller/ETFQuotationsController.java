package com.example.springbootarbitrage.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description: 网页爬取 ETF拯救世界 所有微博语录</p>
 *
 * @author dbx
 * @date 2020/7/21 8:59
 * @since JDK1.8
 */
@RestController
public class ETFQuotationsController {


    /**
     * 入口
     */
    @RequestMapping("/getAllQuotations")
    public void getAllQuotations() {

        WebDriver driver = getWebDriver("E:/chrome/chrome79/chromedriver_win32/chromedriver.exe");

        JavascriptExecutor executor = (JavascriptExecutor) driver;

        //https://weibo.com/chinaetfs?is_all=1
        driver.get("http://weibo.com/login.php");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //https://weibo.com/chinaetfs?is_search=0&visible=0&is_all=1&is_tag=0&profile_ftype=1&page=199#feedtop
        try {
            WebDriverWait wait = new WebDriverWait(driver, 3000);
            wait.until(ExpectedConditions.urlToBe("https://weibo.com/chinaetfs"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取 内容主 div
        WebElement main_div = By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[2]").findElement(driver);

        StringBuilder sb = new StringBuilder();

        int loop = 1;

        while (loop <=200) {

            try {
                //#下拉到页面底部
                executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                Thread.sleep(1 * 1000);

                executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                Thread.sleep(2 * 1000);

                executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                Thread.sleep(2 * 1000);

                executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                Thread.sleep(1 * 1000);

                executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                // 下一页 /html/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[47]/div/a[2]
                WebElement element = By.linkText("下一页").findElement(driver);

                List<WebElement> divs0 = main_div.findElements(By.className("WB_cardwrap"));
                sb.append("第 ").append(loop).append(" 页:").append("\r\n\r\n");

                for (WebElement div0 : divs0) {
                    String date = "";
                    try {
                        WebElement time = div0.findElement(By.className("WB_from"));
                        date = time.getText();
                        List<WebElement> divs1 = div0.findElements(By.className("W_f14"));
                        for (WebElement div1 : divs1) {
                            String text = div1.getText();
                            String href = "";
                            if (text.contains("展开全文")) {
                                WebElement a = div1.findElement(By.tagName("a"));
                                href = a.getAttribute("href");
                            } else if (text.contains("ETF拯救世界")) {
                                continue;
                            }


                            sb.append(text.replaceAll("\r", "").replaceAll("\n", ""));
                            if (href.length() > 0) {
                                sb.append("-------")
                                        .append(href);
                            }

                            sb.append("-------")
                                    .append(date)
                                    .append("\r\n\r\n");
                        }
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }

                }
                System.err.println("第 " + loop + " 页结束");

                //点击下一页
                element.click();

                Thread.sleep(3 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
                write(sb.toString());
            }
            ++loop;
        }
        //写入文件
        write(sb.toString());
    }


    /**
     * set web driver path
     *
     * @param driverPath google drive path
     * @return WebDriver
     */
    private WebDriver getWebDriver(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        return new ChromeDriver();
    }

    private void write(String str) {
        try (FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\etf.txt"))) {
            out.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
