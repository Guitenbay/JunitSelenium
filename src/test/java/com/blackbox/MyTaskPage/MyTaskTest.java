package com.blackbox.MyTaskPage;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class MyTaskTest {
    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();
    private Map<String, Object> vars = new HashMap<String, Object>();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }


    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.login(Engine.getInstance(), "luhaoyang", "lhy980513", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#dashboard.action");
        Engine.getInstance().getDriver().manage().window().maximize();
//        Engine.getInstance().implicitlyWait();
    }

    @Test
    public void CheckEditTask() throws InterruptedException {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
        String expect = engine.awaitElementPresence(By.xpath("//*[@id=\"author-pending\"]")).getText();
        driver.findElement(By.linkText("编辑任务")).click();
        //Thread.sleep(1000);
        String len = String.valueOf(driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr")).size());
        Assert.assertEquals(expect, len);
    }
    @Test
    public void CheckJudgeAgain() throws InterruptedException {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
        String expect = engine.awaitElementPresence(By.xpath("//*[@id=\"qa-pending\"]")).getText();
        driver.findElement(By.linkText("再审任务")).click();
        //Thread.sleep(2000);
        String len = String.valueOf(driver.findElements(By.xpath("//*[@id=\"qa-table\"]/tbody/tr")).size());
        Assert.assertEquals(expect, len);
    }
    @Test
    public void CheckJudgeTask() throws InterruptedException {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
        String expect = engine.awaitElementPresence(By.xpath("//*[@id=\"review-pending\"]")).getText();
        driver.findElement(By.linkText("评审任务")).click();
        //Thread.sleep(1000);
        String len = String.valueOf(driver.findElements(By.xpath("//*[@id=\"q-review-table\"]/tbody/tr")).size());
        Assert.assertEquals(expect, len);
    }

}
