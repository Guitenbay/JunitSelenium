package com.blackbox.QuestionStatusTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LanguageTest {

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
    public void ALanguageAddTest() throws InterruptedException {
        driver.findElement(By.id("language-tab-title")).click();
        driver.findElement(By.id("create-qlanguage-btn")).click();
        driver.findElement(By.id("qlanguage-name")).click();
        driver.findElement(By.id("qlanguage-name")).sendKeys("test1");
        driver.findElement(By.id("save-qlanguage-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void BLanguageReviseTest() throws InterruptedException {
        driver.findElement(By.id("language-tab-title")).click();
        driver.findElement(By.cssSelector("#qlanguage-data-table .item-row:nth-child(4) .edit-item > .glyphicon")).click();
        driver.findElement(By.id("qlanguage-name")).click();
        driver.findElement(By.id("qlanguage-name")).sendKeys("test2");
        driver.findElement(By.id("save-qlanguage-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void CLanguageDeleteTest() throws InterruptedException {
        driver.findElement(By.id("language-tab-title")).click();
        driver.findElement(By.cssSelector("#qlanguage-data-table .item-row:nth-child(4) .delete-item > .glyphicon")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/div")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

}
