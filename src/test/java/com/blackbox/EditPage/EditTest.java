package com.blackbox.EditPage;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditTest {

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
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#question-authoring.action");
        Engine.getInstance().getDriver().manage().window().maximize();
//        Engine.getInstance().implicitlyWait();
    }

    @Test
    public void freshEditTasks() {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        driver.findElement(By.id("author-q-keyword")).click();
        driver.findElement(By.id("author-q-keyword")).sendKeys("hjj");
        driver.findElement(By.id("search-syllabus-btn")).click();
        driver.findElement(By.id("reload-question-btn")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tbody:nth-child(2) td:nth-child(2) > span"));
            assert(elements.size() > 0);
        }
    }
    @Test
    public void allSearchEditTasks() throws InterruptedException {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
//        driver.findElement(By.cssSelector(".glyphicon-search")).click();
        Thread.sleep(6000);
        String expect = engine.awaitElementPresence(By.xpath("//*[@id=\"load-alert\"]/div/strong")).getText();

        String len = String.valueOf(driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr")).size());
        Assert.assertEquals(expect, len);
    }
    @Test
    public void cancelEditPopup() throws InterruptedException {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".glyphicon-edit")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("cancel-edit-form-btn")).click();
        Thread.sleep(1000);
        {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"authoring-question-modal\"]"));
            Assert.assertEquals("display: none;", element.getAttribute("style"));
        }
    }
    @Test
    public void closeEditPopup() throws InterruptedException{
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".glyphicon-edit")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".bg-primary > .close > span")).click();
        Thread.sleep(1000);
        {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"authoring-question-modal\"]"));
            Assert.assertEquals("display: none;", element.getAttribute("style"));
        }
    }

    @Test
    public void openDetails() {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        driver.findElement(By.cssSelector(".glyphicon-file")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("#question-details-modal .modal-content"));
            assert(elements.size() > 0);
        }
    }
    @Test
    public void openEditPopup() {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        driver.findElement(By.cssSelector(".glyphicon-edit")).click();
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("#authoring-question-modal .modal-content"));
            assert(elements.size() > 0);
        }
    }
    @Test
    public void searchEditTask() throws InterruptedException{
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        driver.findElement(By.id("author-q-keyword")).click();
        driver.findElement(By.id("author-q-keyword")).sendKeys("5");
        driver.findElement(By.id("search-syllabus-btn")).click();
        Thread.sleep(1000);
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("tbody:nth-child(2) td:nth-child(2) > span"));
            assert(elements.size() > 0);
        }
        driver.findElement(By.id("author-q-keyword")).click();
        driver.findElement(By.id("author-q-keyword")).sendKeys("588");
        driver.findElement(By.id("search-syllabus-btn")).click();
        Thread.sleep(1000);
        {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr"));
            for (WebElement element: elements) {
                Assert.assertEquals("hidden", element.getAttribute("class"));
            }
        }
    }

    @Test
    public void showInformation() throws InterruptedException {
        engine.refresh();
        Engine.getInstance().implicitlyWait();
        driver.findElement(By.cssSelector(".glyphicon-edit")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("显示题目属性")).click();
        Thread.sleep(1000);
        {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"question-info-section\"]/table"));
            assert(elements.size() > 0);
        }
    }
}
