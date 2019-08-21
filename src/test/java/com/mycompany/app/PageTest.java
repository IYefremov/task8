package com.mycompany.app;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageTest {
    WebDriver driver;

    @Before
    public void tune() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("http://automationpractice.com");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void userCanSearch() {

        // Click to the "Login" button
        WebElement loginElement = driver.findElement(By.cssSelector(" a[class='login']"));
        loginElement.click();
        // Enter unique email into the email_create field
        WebElement inputEmailField = driver.findElement(By.xpath("//input[@id='email_create']"));
        inputEmailField.clear();
        inputEmailField.sendKeys(generateUniqueEmail());
        // Click on the "Create an account button"
        WebElement createAnAccountButton = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
        createAnAccountButton.click();
        // fill in "YOUR PERSONAL INFORMATION" form
        // Fill in "First Name" field
        WebElement firstNameField = driver.findElement(By.xpath("//*[@id='customer_firstname']"));
        firstNameField.clear();
        firstNameField.sendKeys("First Name");
        // Fill in "Last name" field
        WebElement lastNameField = driver.findElement(By.xpath("//input[@id='customer_lastname']"));
        lastNameField.clear();
        lastNameField.sendKeys("Last Name");
        // Fill in "Password" field
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='passwd']"));
        passwordField.clear();
        passwordField.sendKeys("MyPassword");
        // Fill in "Address" field
        WebElement addressField = driver.findElement(By.xpath("//input[@id='address1']"));
        addressField.clear();
        addressField.sendKeys("My Address");
        // Fill in "Phone" field
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='phone']"));
        phoneField.clear();
        phoneField.sendKeys("0961111111");
        // Fill in "Post Code" field
        WebElement cityField = driver.findElement(By.xpath("//input[@id='city']"));
        cityField.clear();
        cityField.sendKeys("Vinnitsia");
        // Fill in "Post Code" field
        WebElement postCodeField = driver.findElement(By.xpath("//input[@id='postcode']"));
        postCodeField.clear();
        postCodeField.sendKeys("01234");
        // Click "Register" button
        WebElement registerButton = driver.findElement(By.xpath("//button[@id='submitAccount']"));
        registerButton.click();
        // Read alert message
        WebElement alertPopUp = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        String alertText = alertPopUp.getText();
        String strExcepted = "There is 1 error";

        Assert.assertTrue("Alert message is incorrect and doesn't contain \\\"There is 1 errors\\\" string", StringUtils.containsIgnoreCase(alertText, strExcepted));
    }
    @After
    public void ClearAll() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
    static private String generateUniqueEmail() {
        int length = 10;
        return "mail" + RandomStringUtils.randomNumeric(length) + "@gmail.com";
    }
}
