package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by smaximishin on 11/15/2015.
 */
public class AbstractPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public AbstractPage(WebDriver driver) {
        this(driver, 10);
    }

    public AbstractPage(WebDriver driver, int implicitWaitSeconds) {
        this(driver,new WebDriverWait(driver, 10), implicitWaitSeconds);
    }

    public AbstractPage(WebDriver driver, WebDriverWait wait,  int implicitWaitSeconds) {
        this.driver = driver;
        this.wait = wait;
        if (implicitWaitSeconds>0) driver.manage().timeouts().implicitlyWait(implicitWaitSeconds, TimeUnit.SECONDS);
    }
}
