package com.blackbox.ProjectManagementForHostTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class SearchBoxTest {

    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @After
    public void clear() {
        driver.findElement(By.cssSelector(".box1 > .filter")).clear();
    }

    @BeforeClass
    public static void setUp() {
        SpecialActionUtils.login(Engine.getInstance(), "MaoHaonan", "shellingford1234", "http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action");
        Engine.getInstance().getDriver().get("http://gc21131138.imwork.net:20430/test-maker/web/client/welcome.action#project.action");
        Engine.getInstance().getDriver().manage().window().maximize();
    }

    @Test
    public void matchedUsernameTest() {
        engine.implicitlyWait();
        driver.findElement(By.cssSelector(".box1 > .filter")).click();
        driver.findElement(By.cssSelector(".box1 > .filter")).sendKeys("test");
        {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"bootstrap-duallistbox-nonselected-list_\"]/option"));
            assert(elements.size() > 0);
        }

    }

    @Test
    public void notMatchedUsernameTest() {
        engine.implicitlyWait();
        driver.findElement(By.cssSelector(".box1 > .filter")).click();
        driver.findElement(By.cssSelector(".box1 > .filter")).sendKeys("123fadsfhk");
        Assert.assertEquals("0", driver.findElement(By.xpath("//span/span")).getText().substring(9, 10));
    }

    @Test
    public void SpaceUsernameTest() {
        engine.implicitlyWait();
        driver.findElement(By.cssSelector(".box1 > .filter")).click();
        driver.findElement(By.cssSelector(".box1 > .filter")).sendKeys("test");
        String var = driver.findElement(By.xpath("//span/span")).getText();
        clear();
        driver.findElement(By.cssSelector(".box1 > .filter")).sendKeys("          test");
        Assert.assertEquals(var, driver.findElement(By.xpath("//span/span")).getText());
        clear();
        driver.findElement(By.cssSelector(".box1 > .filter")).sendKeys("test              ");
        Assert.assertEquals(var, driver.findElement(By.xpath("//span/span")).getText());
        clear();
    }

}
