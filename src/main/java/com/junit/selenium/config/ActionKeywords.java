package com.junit.selenium.config;

import com.junit.selenium.script.DriverScript;
import com.junit.selenium.utils.ChromeDriverUtils;
import com.junit.selenium.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static com.junit.selenium.script.DriverScript.OR;

public class ActionKeywords {
    private static WebDriver driver;

    public static void openBrowser(String object) {
        try {
            driver = ChromeDriverUtils.createDriver();
            Log.info("启动浏览器。");

        }catch(Exception e) {
            Log.info("无法正常启动浏览器---"+e.getMessage());
            DriverScript.bResult = false;
        }

    }

    public static void openUrl(String object) {
        try {
            Log.info("打开测试环境地址：" + object);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(object);

        }catch(Exception e){
            Log.info("无法打开测试环境地址："+e.getMessage());
            DriverScript.bResult = false;
        }
    }


    public static void click(String object) {
        try {
            Log.info("点击元素："+object);
            driver.findElement(By.xpath(OR.getProperty(object))).click();

        }catch(Exception e) {
            Log.info("无法点击元素---"+e.getMessage());
            DriverScript.bResult = false;
        }
    }


    public static void input(String object, String variable) {
        try {
            Log.info("在 "+object+"输入: " + variable);
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(variable);

        }catch(Exception e) {
            Log.info("无法在 " + object + "输入: " + variable + "---" + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void select(String object, String project) {
        try {
            Log.info("在 "+object+"选择: " + project);
            Select sel = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
            sel.selectByVisibleText(project);

        }catch(Exception e) {
            Log.info("无法在"+object+"选择: "+project+"---"+e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void waitFor() throws Exception{
        Thread.sleep(3000);
    }


//    public static void inputUsername(String object){
//        try{
//            Log.info("在用户名输入框输入文字");
//            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.UserName);
//        }catch(Exception e){
//            Log.error("无法输入用户名 --- " + e.getMessage());
//            DriverScript.bResult = false;
//        }
//    }



//    public static void inputPassword(String object){
//
//        try{
//            Log.info("密码框输入:");
//            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
//        }catch(Exception e){
//            Log.error("密码框无法输入--- " + e.getMessage());
//            DriverScript.bResult = false;
//        }
//
//    }


    public static void closeBrowser() {
        try {
            Log.info("关闭并退出浏览器。");
            ChromeDriverUtils.quitDriver(driver);
        }catch(Exception e) {
            Log.info("无法关闭并退出浏览器---"+e.getMessage());
            DriverScript.bResult = false;
        }
    }
}
