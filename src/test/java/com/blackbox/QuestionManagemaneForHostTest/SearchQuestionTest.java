package com.blackbox.QuestionManagemaneForHostTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchQuestionTest {

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

    @After
    public void clear() {
        driver.findElement(By.id("reload-question-btn")).click();
        driver.findElement(By.id("question-keyword")).clear();
    }

    @Test
    public void matchedTest() {
        driver.findElement(By.id("question-keyword")).click();
        driver.findElement(By.id("question-keyword")).sendKeys("5");
        driver.findElement(By.id("search-syllabus-btn")).click();
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr"));
        List<WebElement> hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        Assert.assertTrue(elementList.size() - hiddenElementList.size() > 0);
    }

    @Test
    public void noMatchedTest() {
        driver.findElement(By.id("question-keyword")).click();
        driver.findElement(By.id("question-keyword")).sendKeys("fa");
        driver.findElement(By.id("search-syllabus-btn")).click();
        List<WebElement> elementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr"));
        List<WebElement> hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        Assert.assertEquals(0, elementList.size() - hiddenElementList.size());
    }

    @Test
    public void spaceTest() {
        driver.findElement(By.id("question-keyword")).click();
        driver.findElement(By.id("question-keyword")).sendKeys("   ");
        driver.findElement(By.id("search-syllabus-btn")).click();
        List<WebElement> hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        Assert.assertEquals(0, hiddenElementList.size());
    }

    /**
     * 测试  “597  ”
     * 和    “   597”
     */
    @Test
    public void addSpaceTest() {
        WebElement searchBox = driver.findElement(By.id("question-keyword"));
        searchBox.click();
        searchBox.sendKeys("597");
        driver.findElement(By.id("search-syllabus-btn")).click();
        List<WebElement> hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        int init = hiddenElementList.size();
        searchBox.clear();

        searchBox.click();
        searchBox.sendKeys("597    ");
        driver.findElement(By.id("search-syllabus-btn")).click();
        hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        Assert.assertEquals(init, hiddenElementList.size());
        searchBox.clear();

        searchBox.click();
        searchBox.sendKeys("    597");
        driver.findElement(By.id("search-syllabus-btn")).click();
        hiddenElementList = driver.findElements(By.xpath("//*[@id=\"question-mgmt-table\"]/tbody/tr[@class='hidden']"));
        Assert.assertEquals(init, hiddenElementList.size());
    }

}
