package com.blackbox.LoginPage;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @Test
    public void successLoginForUser() {
        SpecialActionUtils.login(engine, "Moonbird", "1159174835", "http://gc21131138.imwork.net:20430/test-maker/web/client/index.action");
        engine.implicitlyWait();
        Assert.assertEquals("我的项目 5th_test", driver.findElement(By.xpath("//*[@id=\"content-panel\"]/div[1]/h1")).getText());
    }

    @Test
    public void successLoginForAdmin() {
        SpecialActionUtils.adminLogin(engine, "testadmin1", "123456", "http://gc21131138.imwork.net:20430/test-maker/web/admin/index.action");
        engine.implicitlyWait();
        Assert.assertEquals("欢迎", driver.findElement(By.linkText("欢迎")).getText());
    }

    @Test
    public void successLogoutForUser() {
        SpecialActionUtils.login(engine, "Moonbird", "1159174835", "http://gc21131138.imwork.net:20430/test-maker/web/client/index.action");
        engine.implicitlyWait();
        driver.findElement(By.id("logout-btn")).click();
        engine.implicitlyWait();
        WebElement element = driver.findElement(By.id("login-button"));
        Assert.assertEquals("登录", element.getText());
    }

    @Test
    public void nonexistentUser() throws InterruptedException {
        driver.get("http://gc21131138.imwork.net:20430/test-maker/web/admin/index.action");
        driver.findElement(By.id("username")).sendKeys("mao");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(1000);
        Assert.assertEquals("错误", driver.findElement(By.xpath("//div[@class='modal bootstrap-dialog type-danger fade size-wide in']/div/div/div/div/div[2]")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        Thread.sleep(1000);
    }
    @Test
    public void wrongPassword() throws InterruptedException {
        driver.get("http://gc21131138.imwork.net:20430/test-maker/web/admin/index.action");
        driver.findElement(By.id("username")).sendKeys("testadmin1");
        driver.findElement(By.id("password")).sendKeys("1111");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(1000);
        Assert.assertEquals("错误", driver.findElement(By.xpath("//div[@class='modal bootstrap-dialog type-danger fade size-wide in']/div/div/div/div/div[2]")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        Thread.sleep(1000);
    }

}
