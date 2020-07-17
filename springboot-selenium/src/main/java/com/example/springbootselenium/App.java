package com.example.springbootselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    private static final String CHROME_EXE = "chromedriver.exe";

    public static void main(String[] args) throws InterruptedException {

        addWebDriverToPath();

        WebDriver driver = new ChromeDriver();

        driver.get("http://192.168.7.224:8081");

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement username = driver.findElement(By.id("loginid"));
        WebElement password = driver.findElement(By.id("userpassword"));

        username.sendKeys("sysadmin");
        password.sendKeys("1");

        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();


        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.get("http://192.168.7.224:8081/spa/workflow/static4form/index.html?_rdm=1573785117371#/main/workflow/req?sessionkey=281664_307338_1_1573785116818&requestid=11450886&belongTest=false&f_weaver_belongto_usertype=0&f_weaver_belongto_userid=1&isShowChart=0&needRemind=false&isaffirmance=&selectNextFlow=&_key=6hzu0c");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        List<WebElement> lists = driver.findElements(By.className("wf-input-field571883"));
        WebElement check = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[6]/td[2]/div/div/label/span"));
        WebElement filed_value = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[5]/td[2]/div/div/input"));

        WebElement a = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[5]/td[2]/div/div/div/div[1]/div/span/ul/li[1]/div/a"));

        WebDriver driver2 = new ChromeDriver();

        driver2.get("http://192.168.7.224:8081");
        driver2.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement username2 = driver2.findElement(By.id("loginid"));
        WebElement password2 = driver2.findElement(By.id("userpassword"));

        username2.sendKeys("sysadmin");
        password2.sendKeys("1");

        WebElement submit2 = driver2.findElement(By.name("submit"));
        submit2.click();

        driver2.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver2.get("http://192.168.7.224:8081/spa/workflow/static4form/index.html?_rdm=1573804121937#/main/workflow/req?iscreate=1&workflowid=209164&isagent=0&beagenter=0&f_weaver_belongto_userid=&f_weaver_belongto_usertype=0&menuIds=1,12&_key=73pvqw");
        driver2.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement addbutton1 = driver2.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[13]/td/div/div/div[2]/table/tbody/tr[2]/td[7]/div/div/i[1]"));
        WebElement addbutton3 = driver2.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[17]/td/div/div/div[2]/table/tbody/tr[2]/td[7]/div/div/i[1]"));

        for (int i = 0; i < lists.size(); i++) {
            addbutton1.click();
        }
        addbutton3.click();

        driver2.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        WebElement check2 = driver2.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[17]/td/div/div/div[2]/table/tbody/tr[4]/td[7]/div/div/label/span"));

        WebElement a2 = driver2.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[17]/td/div/div/div[2]/table/tbody/tr[4]/td[3]/div/div/div/div[1]/div/span/ul/li[1]/div/a"));

        WebElement filed_value2 = driver2.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[17]/td/div/div/div[2]/table/tbody/tr[4]/td[3]/div/div/input"));
        WebElement check2_value = driver2.findElement(By.xpath("/html/body/div[1]/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div/table/tbody/tr[17]/td/div/div/div[2]/table/tbody/tr[4]/td[7]/div/div/input"));

        JavascriptExecutor executor = (JavascriptExecutor) driver2;
        executor.executeScript("arguments[0].className = '" + check.getAttribute("class") + "';", check2);
        executor.executeScript("arguments[0].className = '" + check.getAttribute("class") + "';", check2);
        executor.executeScript("arguments[0].innerText = '" + a.getAttribute("innerText") + "';", a2);

        executor.executeScript("WfForm.changeFieldValue('field314681_0', {value:'1'}); ");
        executor.executeScript("WfForm.changeFieldValue('field314677_0', {value: '" + filed_value.getAttribute("value") + "',specialobj:[{id:'" + filed_value.getAttribute("value") + "',name:'" + a.getAttribute("innerText") + "'}]}); ");

        List<WebElement> lists2 = driver2.findElements(By.className("wf-input-field314668"));

        for (int i = 0; i < lists2.size(); i++) {
            lists2.get(i).sendKeys(lists.get(i).getAttribute("value"));
        }

    }

    private static void addWebDriverToPath() {
        //打成jar包后，此方式 windows 默认jar就是一个单文件
        //URL resource = App.class.getClassLoader().getResource("chromedriver.exe");

        String LOCATION;
        try {
            LOCATION = URLDecoder.decode(App.class.getProtectionDomain().getCodeSource().getLocation().getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOCATION = "";
        }
        System.out.println("LOCATION==========================" + LOCATION);

        File file = new File(LOCATION);
        String path = file.getParent() + File.separatorChar + "browser" + File.separatorChar + CHROME_EXE;
        System.setProperty("webdriver.chrome.driver", path);

    }
}
