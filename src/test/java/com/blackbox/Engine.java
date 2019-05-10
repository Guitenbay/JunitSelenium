package com.blackbox;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Engine {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private ChromeDriverService service;

    private Engine() throws IOException {
        service = new ChromeDriverService.Builder().
                usingDriverExecutable(new File("bin/chromedriver.exe")).
                usingAnyFreePort().build();
        service.start();
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
        wait = new WebDriverWait(driver, 10);
        js = (JavascriptExecutor)driver;
    }

    public WebElement awaitElementClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));

    }

    public WebElement awaitElementPresence(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    void stop(){
        driver.quit();
        service.stop();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public JavascriptExecutor getJs() {
        return js;
    }

    public void refresh(){
        js.executeScript("location.reload();");
    }

    public void implicitlyWait(){
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    }

    private static class SingletonFactory{

        static Engine instance;

        static {
            try {
                instance = new Engine();

            } catch (IOException e) {

                System.err.println("无法打开 WebDriver 程序");
            }
        }
    }

    public static Engine getInstance(){
        return SingletonFactory.instance;
    }
}
