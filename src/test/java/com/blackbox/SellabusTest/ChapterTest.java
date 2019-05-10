package com.blackbox.SellabusTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChapterTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.adminLogin(Engine.getInstance(), "testadmin1", "123456", "http://gc21131138.imwork.net:20430/test-maker/web/admin/welcome.action");
        Engine.getInstance().implicitlyWait();
        Engine.getInstance().awaitElementClickable(By.xpath("//*[@id=\"navigation-panel\"]/ul/li[4]/a")).click();
        Engine.getInstance().getDriver().manage().window().maximize();
    }




    @Test
    public void ChapterAddTest() throws InterruptedException {

        driver.findElement(By.id("edit-syllabus-form-btn")).click();
        driver.findElement(By.id("syllabus-level")).click();
        driver.findElement(By.id("syllabus-level")).click();
        driver.findElement(By.id("syllabus-level")).sendKeys("AL002");
        driver.findElement(By.id("syllabus-version")).click();
        driver.findElement(By.id("syllabus-version")).sendKeys("1.0.0");
        driver.findElement(By.id("update-syllabus-btn")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void ChapterReviseTest() throws InterruptedException {
//        engine.implicitlyWait();
        driver.findElement(By.cssSelector(".item-row:nth-child(9) .edit-item > .glyphicon")).click();
        driver.findElement(By.id("syllabus-version")).click();
        driver.findElement(By.id("syllabus-version")).sendKeys("1.0.1");
        driver.findElement(By.id("update-syllabus-btn")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void ChapterDeleteTest() throws InterruptedException {
//        engine.implicitlyWait();
        driver.findElement(By.cssSelector(".item-row:nth-child(9) .delete-item > .glyphicon")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

}
