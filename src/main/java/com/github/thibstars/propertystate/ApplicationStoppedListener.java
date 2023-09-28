package com.github.thibstars.propertystate;

import java.util.Optional;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Thibault Helsmoortel
 */
@Component
public class ApplicationStoppedListener implements ApplicationListener<ContextClosedEvent> {

    private final WebDriverHolder webDriverHolder;

    public ApplicationStoppedListener(WebDriverHolder webDriverHolder) {
        this.webDriverHolder = webDriverHolder;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Optional.ofNullable(webDriverHolder.getWebDriver())
                .ifPresent(WebDriver::quit);
    }
}
