package com.grab.pageObjects;

import com.google.common.collect.Multimap;
import com.grab.enums.EmployeeGroupItems;
import com.grab.enums.FrequencyItems;
import com.grab.enums.HomePageTabs;
import com.grab.enums.PaymentMethodItems;
import com.grab.helpers.ListTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends Page{
    private WebDriver driver;

    @FindBy(xpath = "//div[@role='tab'][text()='My Trips']")
    private WebElement myTripsTab;

    @FindBy(xpath = "//div[@role='tab'][text()='Statement Preferences']")
    private WebElement statementPreferencesTab;

    @FindBy(xpath = "//div[@class='ant-col-3']/descendant::div[contains(@class, 'ant-select-selection--single')]")
    private WebElement employeeGroupDropDown;

    @FindBy(xpath = "//div[@class='ant-col-5']/descendant::div[contains(@class, 'ant-select-selection--single')]")
    private WebElement paymentMethodDropDown;

    @FindBy(xpath = "//li[text()='All Groups']")
    private WebElement allGroupsMenuItem;

    @FindBy(xpath = "//li[text()='General']")
    private WebElement generalMenuItem;

    @FindBy(xpath = "//li[text()='Qa']")
    private WebElement qaMenuItem;

    @FindBy(xpath = "//li[text()='Cash']")
    private WebElement cashMenuItem;

    @FindBy(xpath = "//li[text()='Corporate Credit Card']")
    private WebElement corporateCreditCardMenuItem;

    @FindBy(xpath = "//li[text()='Corporate Billing']")
    private WebElement corporateBillingMenuItem;

    @FindBy(xpath = "//li[text()='GrabPay / Credit']")
    private WebElement grabPayCreditMenuItem;

    @FindBy(xpath = "//li[text()='All']")
    private WebElement allMenuItem;

    @FindBy(xpath = "//div[text()='Monthly']/../../../span[contains(@class, 'ant-radio')]")
    private WebElement monthlyFrequencyRadioButton;

    @FindBy(xpath = "//div[text()='None']/../../../span[contains(@class, 'ant-radio')]")
    private WebElement noneFrequencyRadioButton;

    @FindBy(xpath = "//div[@class='TripList__tripTable___2Jl_l']/descendant::table")
    private WebElement tripsTable;

    public void selectEmployeeGroupMenuItem(EmployeeGroupItems item) {
        employeeGroupDropDown.click();
        switch (item){
            case GENERAL:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(generalMenuItem)).click();
                break;
            case ALL_GROUPS:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(allGroupsMenuItem)).click();
                break;
            case QA:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(qaMenuItem)).click();
                break;
        }
    }

    public void selectPaymentMethodMenuItem(PaymentMethodItems item) {
        paymentMethodDropDown.click();
        switch (item){
            case ALL:
                allMenuItem.click();
                break;
            case CASH:
                cashMenuItem.click();
                break;
            case CORPORATE_CREDIT_CARD:
                corporateCreditCardMenuItem.click();
                break;
            case CORPORATE_BILLING:
                corporateBillingMenuItem.click();
                break;
            case GRAB_PAY_CREDIT:
                grabPayCreditMenuItem.click();
                break;
        }
    }

    public void isEmployeeGroupElementSelected(EmployeeGroupItems item) {
        switch (item){
            case GENERAL:
                Assert.assertEquals("General", employeeGroupDropDown.getAttribute("innerText"));
                break;
            case ALL_GROUPS:
                Assert.assertEquals("All Groups", employeeGroupDropDown.getAttribute("innerText"));
                break;
            case QA:
                Assert.assertEquals("Qa", employeeGroupDropDown.getAttribute("innerText"));
                break;
        }
    }

    public void isPaymentMethodElementSelected(PaymentMethodItems item) {
        switch (item){
            case CASH:
                Assert.assertEquals("Cash", paymentMethodDropDown.getAttribute("innerText"));
                break;
            case CORPORATE_CREDIT_CARD:
                Assert.assertEquals("Corporate Credit Card", paymentMethodDropDown.getAttribute("innerText"));
                break;
            case CORPORATE_BILLING:
                Assert.assertEquals("Corporate Billing", paymentMethodDropDown.getAttribute("innerText"));
                break;
            case GRAB_PAY_CREDIT:
                Assert.assertEquals("GrabPay / Credit", paymentMethodDropDown.getAttribute("innerText"));
                break;
            case ALL:
                Assert.assertEquals("All", paymentMethodDropDown.getAttribute("innerText"));
                break;
        }
    }

    public void selectHomePageTab(HomePageTabs tab){
        switch (tab) {
            case MY_TRIPS_TAB:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(myTripsTab)).click();
                break;
            case STATEMENT_PREFERENCES_TAB:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(statementPreferencesTab)).click();
                break;
        }
    }

    public void selectFrequencyItem(FrequencyItems item) {
        switch (item) {
            case MONTHLY:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(monthlyFrequencyRadioButton)).click();
                break;
            case NONE:
                waiter(driver).until(ExpectedConditions.elementToBeClickable(noneFrequencyRadioButton)).click();
                break;
        }
    }

    public void isFrequencyItemSelected(FrequencyItems item){
        switch (item) {
            case MONTHLY:
                Assert.assertTrue(waiter(driver).until(ExpectedConditions.attributeContains(monthlyFrequencyRadioButton, "className", "ant-radio-checked")));
                break;
            case NONE:
                 Assert.assertTrue(waiter(driver).until(ExpectedConditions.attributeContains(noneFrequencyRadioButton, "className", "ant-radio-checked")));
        }
    }

    private ListTable getListTable() {
        waitTableSpinnerLoaded();
        waiter(driver).until(ExpectedConditions.elementToBeClickable(tripsTable));
        return new ListTable(tripsTable);
    }

    private Multimap<String, String> getTableDataGroupedByColumn() {
        return getListTable().getDataGroupedByColumn();
    }

    public void checkEmployeeGroupInMyTripsTable(EmployeeGroupItems searchField) {
        Multimap<String, String> tableList = getTableDataGroupedByColumn();
        if (tableList.isEmpty()){
            Assert.assertTrue("Table list is empty", false);
        }
        if (searchField.equals(EmployeeGroupItems.QA)) {
            for (String value: tableList.get("Employee Group")) {
                Assert.assertEquals("Qa", value);
            }
         } else if (searchField.equals(EmployeeGroupItems.GENERAL)) {
            for (String value: tableList.get("Employee Group")) {
                Assert.assertEquals("General", value);
            }
        } else {
            Assert.assertTrue("Incorrect Employee Group inserted", false);
        }
    }

    public void checkPaymentMethodInMyTripsTable(PaymentMethodItems searchField) {
        Multimap<String, String> tableList = getTableDataGroupedByColumn();
        if (tableList.isEmpty()){
            Assert.assertTrue("Table list is empty", false);
        }
        if (searchField.equals(PaymentMethodItems.CASH)) {
            for (String value: tableList.get("Payment Method")) {
                Assert.assertEquals("Cash",value);
            }
        } else if (searchField.equals(PaymentMethodItems.CORPORATE_BILLING)) {
            for (String value: tableList.get("Payment Method")) {
                Assert.assertEquals("Corporate Billing",value);
            }
        } else if (searchField.equals(PaymentMethodItems.CORPORATE_CREDIT_CARD)) {
            for (String value: tableList.get("Payment Method")) {
                Assert.assertEquals("Corporate Credit Card",value);
            }
        } else if (searchField.equals(PaymentMethodItems.GRAB_PAY_CREDIT)) {
            for (String value : tableList.get("Payment Method")) {
                Assert.assertEquals("GrabPay / Credit",value);
            }
        }  else {
            Assert.assertTrue("Incorrect Payment Method inserted", false);
        }
    }

    public void isTripsTableEmpty(){
        Multimap<String, String> tableList = getTableDataGroupedByColumn();
        Assert.assertTrue("Table list is empty.", tableList.isEmpty());
    }

    private void waitTableSpinnerLoaded() {
        waiter(driver).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='ant-spin-container ant-spin-blur']")));
    }

    @Override
    public void init(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
