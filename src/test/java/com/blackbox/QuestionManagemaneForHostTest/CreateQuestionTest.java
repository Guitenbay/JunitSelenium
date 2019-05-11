package com.blackbox.QuestionManagemaneForHostTest;

import com.blackbox.Engine;
import com.blackbox.SpecialActionUtils;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateQuestionTest {

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
    public void waitFor() throws InterruptedException {
        Thread.sleep(1000);
    }

    /**
     * 全空
     * @throws InterruptedException
     */
    @Test
    public void createQuestionWithNothing() throws InterruptedException {
        engine.implicitlyWait();
        driver.findElement(By.cssSelector(".glyphicon-plus-sign")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("save-question-btn")).click();
        Thread.sleep(1000);
        Assert.assertEquals("必须填写", driver.findElement(By.id("authorStartDate-error")).getText());
        driver.findElement(By.xpath("//*[@id=\"new-question-modal\"]/div/div/div[3]/button[1]")).click();
    }

    private void createQuestion(String questionBegin, String questionFinish, String reviewBegin, String reviewFinish) throws InterruptedException {
        engine.implicitlyWait();
        driver.findElement(By.id("show-edit-question-form-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("select2-chapter-select-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-chapter-select-results\"]/li[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("select2-knowledge-point-select-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-knowledge-point-select-results\"]/li[2]")).click();
        driver.findElement(By.id("select2-author-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-author-list-results\"]/li[1]")).click();
        driver.findElement(By.id("select2-reviewer-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\"select2-reviewer-list-results\"]/li[5]")).click();
        driver.findElement(By.id("select2-question-qa-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\'select2-question-qa-list-results\']/li[2]")).click();
        driver.findElement(By.id("select2-question-type-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\'select2-question-type-list-results\']/li[4]")).click();
        driver.findElement(By.id("authorStartDate")).click();
        driver.findElement(By.id("authorStartDate")).sendKeys(questionBegin);
        driver.findElement(By.id("authorFinishDate")).click();
        driver.findElement(By.id("authorFinishDate")).sendKeys(questionFinish);
        driver.findElement(By.id("reviewStartDate")).click();
        driver.findElement(By.id("reviewStartDate")).sendKeys(reviewBegin);
        driver.findElement(By.id("reviewFinishDate")).click();
        driver.findElement(By.id("reviewFinishDate")).sendKeys(reviewFinish);
        driver.findElement(By.id("select2-question-language-list-container")).click();
        driver.findElement(By.xpath("//*[@id=\'select2-question-language-list-results\']/li[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("save-question-btn")).click();
    }

    private void failAssert() throws InterruptedException {
        Thread.sleep(1000);
        Assert.assertEquals("错误", driver.findElement(By.xpath("//div[@class='modal bootstrap-dialog type-danger fade size-wide in']/div/div/div/div/div[2]")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"new-question-modal\"]/div/div/div[3]/button[1]")).click();
        Thread.sleep(1000);
    }

    /**
     * 出题评审时间都在一天
     * Success
     * @throws InterruptedException
     */
    @Test
    public void createQuestionWithOneDay() throws InterruptedException {
        createQuestion("2019-04-06", "2019-04-06", "2019-04-06", "2019-04-06");
        Assert.assertEquals("操作成功！", driver.findElement(By.cssSelector(".bootstrap-dialog-message")).getText());
        driver.findElement(By.xpath("//div[3]/div/div/button")).click();
    }

    /**
     * 创建评审结束日期在出题结束日期之前的题目
     * Fail
     */
    @Test
    public void createQuestionWithReviewFinishBeforeQuestionFinish() throws InterruptedException {
        createQuestion("2019-04-06", "2019-04-07", "2019-04-06", "2019-04-06");
        failAssert();
    }

    /**
     * 创建评审开始时间早于出题开始时间的题目
     * Fail
     */
    @Test
    public void createQuestionWithReviewStartBeforeQuestionStart() throws InterruptedException {
        createQuestion("2019-04-07", "2019-04-07", "2019-04-06", "2019-04-07");
        failAssert();
    }

    /**
     * 创建出题评审时间超过项目结束时间的题目
     * Fail
     */
    @Test
    public void createQuestionWithReviewOverProjectTime() throws InterruptedException {
        createQuestion("2019-06-30", "2019-07-01", "2019-06-30", "2019-07-01");
        failAssert();
    }

    /**
     * 创建出题评审时间早于项目开始时间的题目
     * Fail
     */
    @Test
    public void createQuestionWithReviewBeforeProjectTime() throws InterruptedException {
        createQuestion("2019-04-05", "2019-04-06", "2019-04-05", "2019-04-06");
        failAssert();
    }

}
