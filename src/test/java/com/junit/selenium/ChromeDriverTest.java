package com.junit.selenium;

import com.junit.selenium.utils.ChromeDriverUtils;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

@RunWith(JUnit4.class)
public class ChromeDriverTest extends TestCase {

    private WebDriver driver;

//    @BeforeClass
//    public static void createAndStartService() throws IOException {
//        service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File("driver/chromedriver_mac_m1.exe"))
//                .usingAnyFreePort()
//                .build();
//        service.start();
//    }
//
//    @AfterClass
//    public static void createAndStopService() {
//        service.stop();
//    }

    @Before
    public void createDriver() throws IOException {
        driver = ChromeDriverUtils.createDriver();
    }

    @After
    public void quitDriver() {
        ChromeDriverUtils.quitDriver(driver);
    }

    @Test
    public void testBingSearch() {
        driver.get("https://cn.bing.com/");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("webdriver");

        assertEquals("必应", driver.getTitle());
    }
}
