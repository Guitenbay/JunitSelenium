package com.blackbox;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpecialActionUtils {

    public static void login(Engine engine, String name, String pwd, String url) {
        WebDriver driver = engine.getDriver();
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pwd);
        driver.findElement(By.id("auth-button")).click();
        engine.awaitElementClickable(By.id("project-list")).click();
        {
            WebElement dropdown = engine.awaitElementPresence(By.id("project-list"));
            dropdown.findElement(By.xpath("//option[. = '5th_test']")).click();
        }
        driver.findElement(By.id("project-list")).click();
        driver.findElement(By.id("login-button")).click();
    }

    public static void adminLogin(Engine engine, String name, String pwd, String url) {
        WebDriver driver = engine.getDriver();
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pwd);
        driver.findElement(By.id("login-button")).click();
    }

}
