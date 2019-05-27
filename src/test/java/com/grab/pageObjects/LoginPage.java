package com.grab.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends Page {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='AppBar__headerLabel___2BK51']")
    private WebElement homePageTitle;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//h1[@innertext='Log In']")
    private WebElement loginHeader;

    public void login(){
        driverBaseSteps(driver);
        emailField.sendKeys(getLogin());
        passwordField.sendKeys(getPassword());
        loginButton.click();
        waitPageLoaded();
    }

    public void isHomePageOpened(){
        Assert.assertEquals("Trips", homePageTitle.getAttribute("innerText"));
    }

    @Override
    public void init(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void waitPageLoaded() {
        waiter(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='AppBar__headerLabel___2BK51']")));
    }
}
