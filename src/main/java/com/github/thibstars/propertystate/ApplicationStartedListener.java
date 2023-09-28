package com.github.thibstars.propertystate;

import com.github.thibstars.propertystate.drivers.DriverType;
import com.github.thibstars.propertystate.pages.PropertyStateFinder;
import com.github.thibstars.propertystate.providers.ImmoWebPropertyPage;
import com.github.thibstars.propertystate.providers.Provider;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Thibault Helsmoortel
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOGGER = Logger.getLogger(ApplicationStartedListener.class.getName());

    private final WebDriverHolder webDriverHolder;

    @Value("${driver.type}")
    private String driverType;

    @Value("${provider}")
    private String provider;

    @Value("${url}")
    private String url;

    public ApplicationStartedListener(WebDriverHolder webDriverHolder) {
        this.webDriverHolder = webDriverHolder;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        switch (DriverType.parse(this.driverType)) {
            case CHROME -> {
                WebDriverManager.chromedriver()
                        .setup();

                this.webDriverHolder.setWebDriver(new ChromeDriver());
            }
            case EDGE -> {
                WebDriverManager.edgedriver()
                        .setup();

                this.webDriverHolder.setWebDriver(new EdgeDriver());
            }
            default -> throw new IllegalStateException("Illegal driver type.");
        }

        PropertyStateFinder propertyStateFinder;
        switch (Provider.valueOf(provider)) {
            case IMMO_WEB -> propertyStateFinder = new ImmoWebPropertyPage(webDriverHolder.getWebDriver(), url);
            default -> throw new IllegalStateException("Illegal provider.");
        }

        propertyStateFinder.findState()
                .ifPresentOrElse(
                        state -> LOGGER.info("Property state: " + state),
                        () -> LOGGER.warning("Unable to find property state.")
                );
    }
}
