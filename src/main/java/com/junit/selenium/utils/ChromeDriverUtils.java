package com.junit.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class ChromeDriverUtils {
    private static ChromeDriverService service;


    private static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("driver/chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    private static void stopService() {
        service.stop();
    }

    public static WebDriver createDriver() throws IOException {
        createAndStartService();
        WebDriver driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        driver.quit();
        stopService();
    }
}
