package com.grab.steps;

import com.grab.configuration.WebDriverConfiguration;
import com.grab.enums.EmployeeGroupItems;
import com.grab.enums.FrequencyItems;
import com.grab.enums.HomePageTabs;
import com.grab.enums.PaymentMethodItems;
import com.grab.pageObjects.HomePage;
import com.grab.pageObjects.LoginPage;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class HomeStepDefs extends WebDriverConfiguration{
    private WebDriver driver;
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Before("@HomePage")
    public void before(Scenario scenario) {
        webDriverSelection();
        this.driver = getDriver();
        loginPage.init(driver);
        homePage.init(driver);
        loginPage.login();
    }

    @After("@HomePage")
    public void after(Scenario scenario) {
        driver.quit();
    }

    @Given("^user select \"([^\"]*)\" from employee list$")
    public void userSelectFromEmployeeList(EmployeeGroupItems item) {
        homePage.selectEmployeeGroupMenuItem(item);
    }

    @Given("^user select \"([^\"]*)\" from Payment Method list$")
    public void userSelectFromPaymentMethodList(PaymentMethodItems item) {
        homePage.selectPaymentMethodMenuItem(item);
    }

    @Then("^Payment Method \"([^\"]*)\" is selected$")
    public void paymentMethodIsSelected(PaymentMethodItems item) {
        homePage.isPaymentMethodElementSelected(item);
    }

    @Given("^user select \"([^\"]*)\" tab$")
    public void userSelectTab(HomePageTabs tab){
        homePage.selectHomePageTab(tab);
    }

    @When("^user select \"([^\"]*)\" frequency$")
    public void userSelectFrequency(FrequencyItems item) {
        homePage.selectFrequencyItem(item);
    }

    @Then("^\"([^\"]*)\" frequency is selected$")
    public void frequencyIsSelected(FrequencyItems item) {
        homePage.isFrequencyItemSelected(item);
    }

    @And("^Employee Group \"([^\"]*)\" is selected$")
    public void employeeGroupIsSelected(EmployeeGroupItems item) {
        homePage.isEmployeeGroupElementSelected(item);
    }

    @And("^trips with Employee Group \"([^\"]*)\" are shown in trips table$")
    public void tripsWithEmployeeGroupAreShownInTripsTable(EmployeeGroupItems item) {
        homePage.checkEmployeeGroupInMyTripsTable(item);
    }

    @And("^trips with Payment Method \"([^\"]*)\" are shown in trips table$")
    public void tripsWithPaymentMethodAreShownInTripsTable(PaymentMethodItems item) {
        homePage.checkPaymentMethodInMyTripsTable(item);
    }
}
