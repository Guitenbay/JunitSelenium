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

public class EditExtraTest {
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
    public void closeEditFreshPopup() throws InterruptedException {
        engine.refresh();

        Thread.sleep(6000);
        driver.findElement(By.xpath("//*[@id=\"load-alert\"]/button")).click();
        Thread.sleep(1000);
        {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"q-workspace-container\"]/div/*"));

//            List<WebElement> elements = parent.findElements(By.xpath("./*"));
            Assert.assertEquals(0, elements.size());
        }
    }
}
