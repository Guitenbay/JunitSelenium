package com.junit.selenium.utils;

import com.junit.selenium.config.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class ChromeDriverUtils {
    private static ChromeDriverService service;


    private static void createAndStartService() throws IOException {
        String driverPath = "";
        if(Constants.MACHINE.equals("mac_m1")){
            driverPath = "driver/chromedriver_mac_m1";
        }else{
            driverPath = "driver/chromedriver.exe";
        }
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverPath))
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
                new ChromeOptions());
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        driver.quit();
        stopService();
    }
}
