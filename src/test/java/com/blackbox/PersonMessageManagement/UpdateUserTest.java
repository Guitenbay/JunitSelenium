package com.blackbox.PersonMessageManagement;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateUserTest {
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
    public void updateName() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("更新信息")).click();
        driver.findElement(By.id("fullName")).clear();
        driver.findElement(By.id("fullName")).sendKeys("5th_YP");
        driver.findElement(By.id("submit-profile-form-btn")).click();
        Assert.assertEquals("信息更新成功！请重新登录查看修改内容。", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }
    @Test
    public void cancelUpdate() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("更新信息")).click();
        driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    }

    @Test
    public void updateEmail() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("更新信息")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("726979224@qq.com");
        driver.findElement(By.cssSelector(".glyphicon-save")).click();
        Assert.assertEquals("信息更新成功！请重新登录查看修改内容。", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }
    @Test
    public void updatePhone() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("更新信息")).click();
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("phone")).sendKeys("123456");
        driver.findElement(By.id("submit-profile-form-btn")).click();
        Assert.assertEquals("信息更新成功！请重新登录查看修改内容。", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }
}
