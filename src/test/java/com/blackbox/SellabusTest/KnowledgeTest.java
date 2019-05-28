package com.blackbox.SellabusTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class KnowledgeTest {

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

    @Before
    public void refresh() {
        Engine.getInstance().refresh();
    }


    @Test
    public void KnowledgeAddTest() throws InterruptedException {
        driver.findElement(By.id("kp-tab-title")).click();
        driver.findElement(By.id("create-kp-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("select2-syllabus-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-syllabus-list-results\"]/li[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("select2-chapter-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-chapter-list-results\"]/li[2]")).click();
        driver.findElement(By.cssSelector(".col-md-4 > #kp-number")).click();
        driver.findElement(By.cssSelector(".col-md-4 > #kp-number")).sendKeys("1.1.1");
        driver.findElement(By.xpath("//*[@id=\"create-kp-form\"]/div[3]/div[2]/select")).click();
        {
            Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"create-kp-form\"]/div[3]/div[2]/select")));
            dropdown.selectByValue("K1");
        }
        driver.findElement(By.cssSelector(".col-md-4:nth-child(4) > .form-control")).click();
        driver.findElement(By.cssSelector(".col-md-4 > #kp-score")).click();
        driver.findElement(By.cssSelector(".col-md-4 > #kp-score")).sendKeys("2");
        driver.findElement(By.id("kp-title")).click();
        driver.findElement(By.id("kp-title")).sendKeys("test1");
        driver.findElement(By.id("submit-newKP-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void KnowledgeReviseTest() throws InterruptedException {
        driver.findElement(By.id("kp-tab-title")).click();
        driver.findElement(By.id("select2-syllabus-filter-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-syllabus-filter-results\"]/li[1]")).click();
        driver.findElement(By.cssSelector(".item-row:nth-child(1) > td:nth-child(10) > .edit-item > .glyphicon")).click();
        driver.findElement(By.id("kp-name")).click();
        driver.findElement(By.id("kp-name")).sendKeys("test2");
        driver.findElement(By.id("submit-kp-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void KnowledgeDeleteTest() throws InterruptedException {
        driver.findElement(By.id("kp-tab-title")).click();
        driver.findElement(By.id("select2-syllabus-filter-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-syllabus-filter-results\"]/li[1]")).click();
        driver.findElement(By.cssSelector(".item-row:nth-child(1) > td:nth-child(10) > .delete-item > .glyphicon")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void KnowledgeSearchTest() throws InterruptedException {
        driver.findElement(By.id("kp-tab-title")).click();
        driver.findElement(By.id("select2-syllabus-filter-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-syllabus-filter-results\"]/li[2]")).click();
        driver.findElement(By.id("kp-filter")).click();
        driver.findElement(By.id("kp-filter")).sendKeys("认识测试的总体目标");
        driver.findElement(By.id("search-kp-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("认识测试的总体目标", driver.findElement(By.cssSelector("#kp-list-table-body > .item-row:nth-child(7) > td:nth-child(3)")).getText());
    }

}
