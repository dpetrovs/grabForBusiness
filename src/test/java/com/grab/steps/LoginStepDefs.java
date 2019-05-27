package com.grab.steps;

import com.grab.configuration.WebDriverConfiguration;
import com.grab.pageObjects.HomePage;
import com.grab.pageObjects.LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs extends WebDriverConfiguration{
    private WebDriver driver;
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Before("@LogIn")
    public void before(Scenario scenario) {
        webDriverSelection();
        this.driver = getDriver();
        loginPage.init(driver);
        homePage.init(driver);
    }

    @Then("^home page is opened$")
    public void homePageIsOpened() {
        loginPage.isHomePageOpened();
    }

    @After("@LogIn")
    public void after(Scenario scenario) {
       driver.quit();
    }
    @Given("^user is navigated to Grab for Business site$")
    public void userIsNavigatedToGrabForBusinessSite() throws Throwable {
        loginPage.login();
    }
}
