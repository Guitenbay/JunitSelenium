package com.blackbox.QuestionManagemaneForHostTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RefreshListsTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }


    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.login(Engine.getInstance(), "MaoHaonan", "shellingford1234", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#item-management.action");
        Engine.getInstance().getDriver().manage().window().maximize();
    }

    @Test
    public void refreshLists() {
        driver.findElement(By.id("question-keyword")).click();
        driver.findElement(By.id("question-keyword")).sendKeys("123");
        driver.findElement(By.id("search-syllabus-btn")).click();
        List<WebElement> hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        driver.findElement(By.id("reload-question-btn")).click();
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr"));
        Assert.assertEquals(hiddenElementList.size(), elementList.size());
    }

}
