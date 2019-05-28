package com.blackbox.SellabusTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionTest {

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
    public void SectionAddTest() throws InterruptedException {
        driver.findElement(By.id("chapter-tab-title")).click();
        driver.findElement(By.id("create-chapter-btn")).click();
        driver.findElement(By.id("select2-syllabus-select-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-syllabus-select-list-results\"]/li[2]")).click();
        driver.findElement(By.id("chapter-number")).sendKeys("第一章");
        driver.findElement(By.id("chapter-name")).click();
        driver.findElement(By.id("chapter-name")).sendKeys("test1");
        driver.findElement(By.id("submit-chapter-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void SectionReviseTest() throws InterruptedException {
        driver.findElement(By.id("chapter-tab-title")).click();
        driver.findElement(By.cssSelector("#chapter-list-table-body > .item-row:nth-child(1) .edit-item > .glyphicon")).click();
        driver.findElement(By.id("chapter-number")).click();
        driver.findElement(By.id("chapter-number")).sendKeys("第二章节");
        driver.findElement(By.id("submit-chapter-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void SectionDeleteTest() throws InterruptedException {
        driver.findElement(By.id("chapter-tab-title")).click();
        driver.findElement(By.cssSelector("#chapter-list-table-body > .item-row:nth-child(1) .edit-item > .glyphicon")).click();
        driver.findElement(By.id("chapter-number")).click();
        driver.findElement(By.id("chapter-number")).sendKeys("第二章节");
        driver.findElement(By.id("submit-chapter-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void SectionSearchTest() throws InterruptedException {
        driver.findElement(By.id("chapter-tab-title")).click();
        driver.findElement(By.id("chapter-keyword")).click();
        driver.findElement(By.id("chapter-keyword")).sendKeys("敏捷测试术语");
        driver.findElement(By.cssSelector("#search-chapter-btn > .glyphicon")).click();
        Thread.sleep(1000);
        Assert.assertEquals("敏捷测试术语", driver.findElement(By.cssSelector("#chapter-list-table-body > .item-row:nth-child(10) > td:nth-child(3)")).getText());
    }

}
