package com.blackbox.PersonMessageManagement;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelpTest {
    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @BeforeClass
    public static void login() throws InterruptedException {
        SpecialActionUtils.login(Engine.getInstance(), "MoonBird", "1159174835", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().manage().window().maximize();
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
    }

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @Test
    public void openHelp() throws InterruptedException {
        engine.refresh();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li/a")).click();
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("about-dialog"));
        Assert.assertEquals("false", element.getAttribute("aria-hidden"));
    }
    @Test
    public void closeHelp() throws InterruptedException {
        engine.refresh();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"about-dialog\"]/div/div/div[1]/button")).click();
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("about-dialog"));
        Assert.assertEquals("true", element.getAttribute("aria-hidden"));
    }
    @Test
    public void cancelHelp() throws InterruptedException {
        engine.refresh();
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[3]/ul/li/a")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"about-dialog\"]/div/div/div[3]/button")).click();
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.id("about-dialog"));
        Assert.assertEquals("true", element.getAttribute("aria-hidden"));
    }
}
