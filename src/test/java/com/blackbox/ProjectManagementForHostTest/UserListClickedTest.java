package com.blackbox.ProjectManagementForHostTest;

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

public class UserListClickedTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.login(Engine.getInstance(), "MaoHaonan", "shellingford1234", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#project.action");
        Engine.getInstance().getDriver().manage().window().maximize();
    }

    @Test
    public void userListClickedTest() {
        driver.findElement(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option[@title=\"sa\"]")).click();
        List<WebElement> noSelectedElements = driver.findElements(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option[@title=\"sa\"]"));
        List<WebElement> selectedElements = driver.findElements(By.xpath("//*[@id=\"bootstrap-duallistbox-selected-list_\"]/option[@title=\"sa\"]"));
        Assert.assertEquals(0, noSelectedElements.size());
        Assert.assertEquals(1, selectedElements.size());
        driver.findElement(By.xpath("//*[@id=\"bootstrap-duallistbox-selected-list_\"]/option[@title=\"sa\"]")).click();
    }

}
