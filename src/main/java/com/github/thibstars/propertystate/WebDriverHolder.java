package com.github.thibstars.propertystate;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

/**
 * @author Thibault Helsmoortel
 */
@Component
public class WebDriverHolder {

    private WebDriver webDriver;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
