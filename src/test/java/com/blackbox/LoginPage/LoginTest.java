package com.blackbox.LoginPage;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }


    @BeforeClass
    public static void setUp() {

    }

    @Test
    public void successLoginForUser() {
        SpecialActionUtils.login(engine, "Moonbird", "1159174835", "http://gc21131138.imwork.net:20430/test-maker/web/client/index.action");
        engine.implicitlyWait();
        Assert.assertEquals("我的项目", driver.findElement(By.xpath("//*[@id=\"content-panel\"]/div[1]/h1")).getText());
    }

    @Test
    public void successLoginForAdmin() {

    }

    @Test
    public void nonexistentUser() {
        driver.get("http://gc21131138.imwork.net:20430/test-maker/web/admin/index.action");
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys("mao");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();
    }
    @Test
    public void wrongPassword() {
        driver.get("http://gc21131138.imwork.net:20430/test-maker/web/admin/index.action");
        driver.findElement(By.id("username")).sendKeys("testadmin1");
        driver.findElement(By.id("password")).sendKeys("1111");
        driver.findElement(By.id("login-button")).click();
    }

}
