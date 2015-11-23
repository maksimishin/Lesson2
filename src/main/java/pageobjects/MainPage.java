package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Created by smaximishin on 11/15/2015.
 */
public class MainPage extends AbstractPage {

    protected static By welcomePanel = By.id("welcome-panel");

    @FindBy(xpath="//div[@class=\"wp-menu-name\" and text()=\"Posts\"]")
    static  WebElement posts;

    @FindBy (xpath="//a[@class=\"wp-first-item current\" and contains(text(),\"All Posts\")]")
    static WebElement allPosts;

    @FindBy (xpath="//a[@class=\"page-title-action\" and contains(text(),\"Add New\")]")
    static WebElement addNew;

    @FindBy (xpath="//input[@id=\"title\"]")
    static WebElement titleInput;

    @FindBy (xpath="//body[@id=\"tinymce\"]")
    static WebElement bodyInput;

    static final String submitButtonXpath = "//input[@id=\"publish\" and @value=\"Publish\" and @class=\"button button-primary button-large\"]";

    @FindBy (xpath=submitButtonXpath)
    static WebElement submit;

    @FindBy (id ="cb-select-all-2")
    static WebElement allItemsCheckbox;

    @FindBy (id ="bulk-action-selector-bottom")
    static WebElement actionsMenu;

    @FindBy (xpath ="//option[@value=\"trash\"]")
    static WebElement actionMoveToTrash;

    @FindBy (xpath ="//span[@class=\"count\"]")
    static WebElement postNumber;


    public MainPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
    }

    public void waitWelcomePanel(){

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(welcomePanel));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(welcomePanel)));
    }

    public void createPost(String title, String body) {
        waitWelcomePanel();

        // Click Posts
        actions.moveToElement(posts).click().perform();
        //posts.click();

        //click All posts
        wait.until(ExpectedConditions.visibilityOf(allPosts));
        actions.moveToElement(allPosts).click().perform();
        //allPosts.click();

        //Click Add news
        addNew.click();

        //Enter title
        titleInput.sendKeys(title);

        //Enter Message
        driver.switchTo().frame("content_ifr");

        bodyInput.click();
        bodyInput.sendKeys(body);
        driver.switchTo().defaultContent();


        // Click 'Publish'
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(submitButtonXpath)));
        submit.click();
    }

    public void navigateToPostList(){
        //click 'All posts' to return to posts list
        allPosts.click();
    }

    public void  deleteAllPosts(){
        allItemsCheckbox.click();
        actionsMenu.click();
        wait.until(ExpectedConditions.elementToBeClickable(actionMoveToTrash));
//        try { Thread.sleep(2000);} catch (Exception e){};
        new Select(actionsMenu).selectByValue("trash");
        actionsMenu.submit();
    }

    public  boolean postExists(String title){
        //Find new message in the message list
        String expression = "//a[@class=\"row-title\" and contains(text(),\""+title+"\")]";
        return driver.findElements(By.xpath(expression)).size()  >0;

    }

    public int getPostCount(){
        //Find new message in the message list
        String allPosts = postNumber.getText();
        Pattern numberPattern = Pattern.compile("\\((\\d+)\\)");
        System.out.println("Pattern: "+"\\((\\d+)\\)");
        Matcher m = numberPattern.matcher(allPosts);
        if (m.matches()) return Integer.parseInt(m.group(1));
        else return -1;

    }


}
