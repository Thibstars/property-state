package com.github.thibstars.propertystate.providers;

import com.github.thibstars.propertystate.pages.Page;
import com.github.thibstars.propertystate.pages.PropertyStateFinder;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Thibault Helsmoortel
 */
public class ImmoWebPropertyPage extends Page implements PropertyStateFinder {

    @FindBy(className = "flag-list")
    private List<WebElement> flagListContainer;

    private final String url;

    public ImmoWebPropertyPage(WebDriver webDriver, String url) {
        super(webDriver);
        this.url = url;
    }

    @Override
    public Optional<String> findState() {
        getDriver().get(url);
        waitUntilDocumentLoaded();

        if (flagListContainer != null && !flagListContainer.isEmpty()) {
            return Optional.of(flagListContainer)
                    .map(container -> container.get(0))
                    .map(WebElement::getText);
        }

        return Optional.empty();
    }

    private void waitUntilDocumentLoaded() {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.of(5, ChronoUnit.SECONDS));
        webDriverWait.until(driver -> String
                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                .equals("complete"));
    }

}
