package com.blackbox.ProjectManagementForHostTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ExportTest {
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
    public void cancelExportTest() {
        Engine.getInstance().implicitlyWait();
        Engine.getInstance().awaitElementPresence(By.id("project-status")).click();
        Engine.getInstance().awaitElementPresence(By.id("export-current-project")).click();
        Engine.getInstance().awaitElementClickable(By.xpath("//div[3]/div/div/button")).click();
    }

    @Test
    public void exportTest() throws InterruptedException {
        Engine.getInstance().implicitlyWait();
        Thread.sleep(1000);
        Engine.getInstance().awaitElementClickable(By.id("export-current-project")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("导出成功", driver.findElement(By.xpath("//div[5]/div/div/div[2]/div/div")).getText().substring(0, 4));
    }

}
