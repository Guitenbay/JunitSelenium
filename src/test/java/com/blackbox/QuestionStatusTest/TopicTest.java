package com.blackbox.QuestionStatusTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TopicTest {

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
        Engine.getInstance().awaitElementClickable(By.xpath("//*[@id=\"navigation-panel\"]/ul/li[5]/a")).click();
        Engine.getInstance().getDriver().manage().window().maximize();
    }

    @Before
    public void refresh() {
        Engine.getInstance().refresh();
    }


    @Test
    public void ATopicAddTest() throws InterruptedException {
        driver.findElement(By.id("create-status-btn")).click();
        driver.findElement(By.id("qtype-name")).click();
        driver.findElement(By.id("qtype-name")).sendKeys("test1");
        driver.findElement(By.id("save-qtype-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void BTopicReviseTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"qtype-data-table\"]/tbody/tr[7]/td[5]/a[1]/i")).click();
        driver.findElement(By.id("qtype-name")).click();
        driver.findElement(By.id("qtype-name")).sendKeys("test2");
        driver.findElement(By.id("save-qtype-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void CTopicDeleteTest() throws InterruptedException {
        driver.findElement(By.cssSelector(".item-row:nth-child(7) .delete-item > .glyphicon")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void DTopicSearchTest() throws InterruptedException {
        driver.findElement(By.id("qtype-keyword")).click();
        driver.findElement(By.id("qtype-keyword")).sendKeys("情景题");
        driver.findElement(By.id("search-user-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("情景题", driver.findElement(By.cssSelector(".item-row:nth-child(2) > td:nth-child(2)")).getText());
    }

}
