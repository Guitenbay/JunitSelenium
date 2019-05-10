package com.blackbox;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.UUID;


public class ProjectManagement {
    private Engine engine = Engine.getInstance();
    private WebDriver driver = engine.getDriver();

    @BeforeClass
    public static void Login() throws InterruptedException {
        WebDriver driver = Engine.getInstance().getDriver();
        driver.get("http://gc21131138.imwork.net:20430/test-maker/web/admin/index.action");
        driver.findElement(By.id("username")).sendKeys("testadmin1");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click();
        driver.manage().window().maximize();
        Engine.getInstance().implicitlyWait();
        driver.findElement(By.linkText("项目管理")).click();
        Thread.sleep(1000);
    }

    @AfterClass
    public static void tearDown(){
        Engine.getInstance().stop();
    }

    @Test
    public void createProject() throws InterruptedException {
        String projectName = UUID.randomUUID().toString().substring(0, 8);

        engine.refresh();
        driver.findElement(By.id("create-project-btn")).click();
        driver.findElement(By.id("project-name")).sendKeys(projectName);
        driver.findElement(By.id("select2-project-syllabus-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\'select2-project-syllabus-list-results\']/li[3]")).click();
        driver.findElement(By.id("select2-facilitator-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\'select2-facilitator-list-results\']/li[3]")).click();
        driver.findElement(By.id("startDate")).sendKeys("2019-05-10");
        driver.findElement(By.id("finishDate")).sendKeys("2019-05-31");
        driver.findElement(By.id("save-project-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }
    @Test
    public void editProject() throws InterruptedException {
        engine.refresh();
        driver.findElement(By.cssSelector(".item-row:nth-child(1) .edit-item > .glyphicon")).click();
        driver.findElement(By.id("project-name")).sendKeys("0211111");
        driver.findElement(By.id("save-project-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("操作成功！", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
    }

    @Test
    public void exportProject() throws InterruptedException {
        engine.refresh();
        driver.findElement(By.cssSelector(".item-row:nth-child(1) .export-item > .glyphicon")).click();
        driver.findElement(By.xpath("//div[3]/div/div/button[2]")).click();
        Thread.sleep(1000);
        Assert.assertEquals("导出成功", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText().substring(0, 4));
    }

    @Test
    public void resetEdit() {
        engine.refresh();
        driver.findElement(By.cssSelector(".item-row:nth-child(1) .edit-item > .glyphicon")).click();
        WebElement projectName = driver.findElement(By.id("project-name"));
        projectName.sendKeys("test_project");
        driver.findElement(By.cssSelector(".col-md-2 > .btn-default")).click();
        Assert.assertTrue(projectName.getText().isEmpty());
    }

    @Test
    public void inspectProject() throws InterruptedException {
        engine.refresh();
        String expectedProjectName = driver.findElement(By.xpath("//tbody[@id='project-table-body']/tr/td[2]")).getText();

        driver.findElement(By.cssSelector(".item-row:nth-child(1) .view-item > .glyphicon")).click();
        Thread.sleep(1000);
        String projectName = driver.findElement(By.cssSelector(".in #myModalLabel")).getText();

        Assert.assertEquals(expectedProjectName, projectName);
    }
}
