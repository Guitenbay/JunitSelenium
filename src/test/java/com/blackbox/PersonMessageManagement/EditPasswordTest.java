package com.blackbox.PersonMessageManagement;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPasswordTest {
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
    public void mismatchPassword() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("修改密码")).click();
        driver.findElement(By.id("oldPassword")).sendKeys("old-password");
        driver.findElement(By.id("newPassword")).sendKeys("123456");
        driver.findElement(By.id("retypePassword")).sendKeys("654321");
        driver.findElement(By.id("change-passwd-submit")).click();
        Assert.assertEquals("你的输入不相同", driver.findElement(By.id("retypePassword-error")).getText());
    }
    @Test
    public void newPasswordEqualsOldOne() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("修改密码")).click();
        driver.findElement(By.id("oldPassword")).sendKeys("old-password");
        driver.findElement(By.id("newPassword")).sendKeys("old-password");
        driver.findElement(By.id("retypePassword")).sendKeys("old-password");
        driver.findElement(By.id("change-passwd-submit")).click();
        Assert.assertEquals("新密码与旧密码一致", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }
    @Test
    public void tooShortNewPassword() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("修改密码")).click();
        driver.findElement(By.id("oldPassword")).sendKeys("old-password");
        driver.findElement(By.id("newPassword")).sendKeys("123");
        driver.findElement(By.id("retypePassword")).sendKeys("123");
        Assert.assertEquals("最少 4 个字", driver.findElement(By.id("newPassword-error")).getText());
    }

    @Test
    public void wrongOldPassword() {
        engine.refresh();
        driver.findElement(By.linkText("个人信息")).click();
        driver.findElement(By.linkText("修改密码")).click();
        driver.findElement(By.id("oldPassword")).sendKeys("old-password");
        driver.findElement(By.id("newPassword")).sendKeys("654321");
        driver.findElement(By.id("retypePassword")).sendKeys("654321");
        driver.findElement(By.id("change-passwd-submit")).click();
        Assert.assertEquals("旧密码填写错误", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }
}
