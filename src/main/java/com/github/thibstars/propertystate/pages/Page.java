package com.github.thibstars.propertystate.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Thibault Helsmoortel
 */
public abstract class Page {

    private WebDriver driver;

    protected Page(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
