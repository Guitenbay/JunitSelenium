package com.blackbox.RecheckTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class RecheckTest {

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
        Engine.getInstance().getDriver().findElement(By.linkText("再审任务")).click();
        Engine.getInstance().implicitlyWait();
    }

    @After
    public void waitFor() throws InterruptedException {
        Thread.sleep(1000);
    }


    @Test
    public void recheckBreak() throws InterruptedException {
        Thread.sleep(1000);
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("zuofei");
        driver.findElement(By.cssSelector("#submit-comment > .ui-button-text")).click();
        Thread.sleep(1000);
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(3)")).click();
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
    public void recheckNullNotPass() throws InterruptedException {
        Thread.sleep(1000);
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(1)")).click();
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
    public void recheckNotPass() throws InterruptedException {
        Thread.sleep(1000);
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("tongguo");
        driver.findElement(By.cssSelector("#submit-comment > .ui-button-text")).click();
        Thread.sleep(1000);
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(1)")).click();
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
    public void recheckNullBreak() throws InterruptedException {
        Thread.sleep(1000);
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.cssSelector(".col-md-10 > .btn:nth-child(3)")).click();
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
    public void recheckNullPass() throws InterruptedException {
        Thread.sleep(1000);
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
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
    public void recheckPass() throws InterruptedException {
        Thread.sleep(1000);
        vars.put("x", driver.findElement(By.cssSelector("td:nth-child(2) > span")).getText());
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("tongguo");
        driver.findElement(By.cssSelector("#submit-comment > .ui-button-text")).click();
        driver.findElement(By.id("qa-form-modal-comment-dlg-btn")).click();
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
    public void recheckSave() throws InterruptedException {
        engine.awaitElementClickable(By.cssSelector(".glyphicon-edit")).click();
        engine.awaitElementClickable(By.id("qa-form-modal-comment-dlg-btn")).click();
        driver.findElement(By.id("qa-comment")).click();
        driver.findElement(By.id("qa-comment")).sendKeys("eisoangioudsapjmdisfon");
        driver.findElement(By.cssSelector(".ui-state-hover")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("qa-form-modal-comment-dlg-btn")).click();
        {
            WebElement element = driver.findElement(By.id("qa-comment"));
            if (!element.isSelected()) {
                element.click();
            }
        }
    }
    @Test
    public void recheckNullInput() throws InterruptedException {
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
    public void recheckInput() throws InterruptedException {
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
    public void recheckOverInput() throws InterruptedException {
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
    public void recheckOpen() throws InterruptedException{
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
