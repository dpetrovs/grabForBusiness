package com.grab.pageObjects;

import com.grab.configuration.WebDriverConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page  extends WebDriverConfiguration{

    WebDriverWait waiter(WebDriver webDriver) {
        return new WebDriverWait(webDriver, 10);
    }
    void init (WebDriver driver) {
    }
    public void waitPageLoaded(){
    }
}
