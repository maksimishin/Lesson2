package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by smaximishin on 11/15/2015.
 */
public class LoginPage extends AbstractPage {

    @FindBy(id = "user_login")
    static WebElement loginInput;

    @FindBy(id = "user_pass")
    static WebElement passwordInput;

    @FindBy(id = "wp-submit")
    static WebElement submitButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, int implicitTimeoutSeconds) {
        super(driver, implicitTimeoutSeconds);
    }
    public void open(String url){
        driver.navigate().to(url); }

    public void login(String username, String password){
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }


}




