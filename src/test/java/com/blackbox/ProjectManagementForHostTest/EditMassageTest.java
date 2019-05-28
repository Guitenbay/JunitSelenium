package com.blackbox.ProjectManagementForHostTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditMassageTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.login(Engine.getInstance(), "MaoHaonan", "shellingford1234", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#project.action");
        Engine.getInstance().getDriver().manage().window().maximize();
    }

    @Test
    public void stateTest() {
        engine.implicitlyWait();
        driver.findElement(By.xpath("//*[@id=\"project-status\"]/option[3]")).click();
        driver.findElement(By.id("save-project-btn")).click();
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[5]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void ValidTimeTest() throws InterruptedException {
        engine.implicitlyWait();
        Thread.sleep(1000);
//        driver.findElement(By.id("project-start")).click();
//        driver.findElement(By.linkText("5")).click();
        driver.findElement(By.id("project-start")).clear();
        driver.findElement(By.id("project-start")).sendKeys("2019-04-06");
        Thread.sleep(2000);
//        driver.findElement(By.id("project-finish")).click();
        driver.findElement(By.id("project-finish")).clear();
        driver.findElement(By.id("project-finish")).sendKeys("2019-06-30");
        driver.findElement(By.xpath("//*[@id=\"save-project-form\"]/div[6]/label")).click();
//        driver.findElement(By.cssSelector(".ui-icon-circle-triangle-e")).click();
//        driver.findElement(By.linkText("1")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("save-project-btn")).click();
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[5]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

}
