package SamMaximishin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Hello world!
 *
 */
public class App    {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    static String url       = "http://localhost:11443/wp-login.php";
    static String name      = "root";
    static String password  = "1234";
    static String postTitle = "My First title" + System.currentTimeMillis();
    static String postBody  = "One upon a time lived Vadim who provided Selenium's course";
    public static void main( String[] args ) {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.navigate().to(url);

        String expression;
        WebElement loginWE    = driver.findElement(By.id("user_login"));
        WebElement passwordWE = driver.findElement(By.id("user_pass"));
        WebElement submit     = driver.findElement(By.id("wp-submit"));

        loginWE.sendKeys(name);
        passwordWE.sendKeys(password);
        submit.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("welcome-panel")));

        // Click Posts
        WebElement temp = driver.findElement(By.xpath("//div[@class=\"wp-menu-name\" and text()=\"Posts\"]"));
        temp.click();

        //click All posts
        expression = "//a[@class=\"wp-first-item current\" and contains(text(),\"All Posts\")]";
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(expression)));
        temp = driver.findElement(By.xpath(expression));
        temp.click();

        //Click Add new
        expression = "//a[@class=\"page-title-action\" and contains(text(),\"Add New\")]";
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(expression)));
        //Add new
        temp = driver.findElement(By.xpath(expression));
        temp.click();

        //Enter title
        expression = "//input[@id=\"title\"]";
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(expression)));
        temp = driver.findElement(By.xpath(expression));
        temp.sendKeys(postTitle);

        //Enter Message
        driver.switchTo().frame("content_ifr");
        expression = "//body[@id=\"tinymce\"]";
        temp = driver.findElement(By.xpath(expression));
        temp.click();
        temp.sendKeys(postBody);
        driver.switchTo().defaultContent();


        // Click publish
        expression = "//input[@id=\"publish\" and @value=\"Publish\" and @class=\"button button-primary button-large\"]";
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(expression)));
        temp = driver.findElement(By.xpath(expression));
        temp.click();

        //click 'All posts' to return to posts list
        expression = "//a[@class=\"wp-first-item current\" and contains(text(),\"All Posts\")]";
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(expression)));
        temp = driver.findElement(By.xpath(expression));
        temp.click();

        //
        expression = "//a[@class=\"row-title\" and contains(text(),\""+postTitle+"\")]";
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(expression)));
        driver.findElement(By.xpath(expression));


    }
}
