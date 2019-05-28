package com.blackbox.CheckTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();
    private Map<String, Object> vars = new HashMap<String, Object>();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }


    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.login(Engine.getInstance(), "Chenzb", "pZ626i23", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#question-reviewing.action");
        Engine.getInstance().getDriver().manage().window().maximize();
        Engine.getInstance().implicitlyWait();
    }

    @After
    public void waitFor() throws InterruptedException {
        Thread.sleep(1000);
    }


    @Test
    public void checkNotPass() throws InterruptedException {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("不通过");
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(1)")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("td:nth-child(2) > span"));
            if (element.isSelected()) {
                element.click();
            }
        }
    }
    @Test
    public void checkNullNotPass() throws InterruptedException {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(1)")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("td:nth-child(2) > span"));
            if (element.isSelected()) {
                element.click();
            }
        }
    }
    @Test
    public void checkNullPass() throws InterruptedException  {
        engine.refresh();

        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(2)")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/div[3]/div/div/button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("td:nth-child(2) > span"));
            if (element.isSelected()) {
                element.click();
            }
        }
    }
    @Test
    public void checkPass() throws InterruptedException {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("通过");
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(2)")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div/div[3]/div/div/button[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("td:nth-child(2) > span"));
            if (element.isSelected()) {
                element.click();
            }
        }
    }
    @Test
    public void checkSave() {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        driver.findElement(By.cssSelector(".glyphicon-edit")).click();
        driver.findElement(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("joawneigsad");
        driver.findElement(By.cssSelector(".ui-state-hover > .ui-button-text")).click();
        driver.findElement(By.id("q-review-modal-comment-dlg-btn")).click();
        {
            WebElement element = driver.findElement(By.id("qa-comment"));
            if (!element.isSelected()) {
                element.click();
            }
        }
    }
    @Test
    public void checkNullInput() throws InterruptedException {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        Thread.sleep(1000);
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        WebElement element = driver.findElement(By.xpath("//*[@id=\"submit-comment\"]"));
        Assert.assertEquals("true", element.getAttribute("disabled"));
    }

    @Test
    public void checkInput() throws InterruptedException {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        Thread.sleep(1000);
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("joawneigsad");
        driver.findElement(By.xpath("//*[@id=\"submit-comment\"]")).click();
    }

    @Test
    public void checkOverInput() throws InterruptedException {
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        Thread.sleep(1000);
        engine.awaitElementClickable(By.id("q-review-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("joawneigsadddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" +
                "dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        driver.findElement(By.xpath("//*[@id=\"submit-comment\"]")).click();
    }

    @Test
    public void checkOpen() throws InterruptedException{
        engine.refresh();
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='q-review-table']/tbody/tr[1]/td[8]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".bg-primary span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='q-review-table']/tbody/tr[3]/td[8]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".bg-primary span")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//table[@id='q-review-table']/tbody/tr[2]/td[8]/a/i")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".bg-primary span")).click();
    }
}
