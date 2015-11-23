package SamMaximishin;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.LoginPage;
import pageobjects.MainPage;

/**
 * Hello world!
 *
 */
public class App  extends BaseTest  {
    //protected static WebDriver driver;
    //protected static WebDriverWait wait;
    LoginPage loginPage;
    MainPage mainPage;

    static String url       = "http://localhost:11443/wp-login.php";
    static String name      = "root";
    static String password  = "1234";
    static String postTitle = "My First title " + System.currentTimeMillis();
    static String postBody  = "One upon a time lived Vadim who provided Selenium's course";


    @BeforeClass
    public void initPages(){
         driver = new FirefoxDriver();
         loginPage = PageFactory.initElements(driver,LoginPage.class);
         mainPage  = PageFactory.initElements(driver,MainPage.class);
    }

    @Test(priority = 21, enabled = true)
    public void loginForum(){
        loginPage.open(url);
        loginPage.login(name, password);
    }

    @Test(priority = 61, enabled = true)
    public void createPost(){
        mainPage.createPost(postTitle, postBody);
    }

    @Test(priority = 81, enabled = true)
    public void postExists(){
        mainPage.navigateToPostList();
        Assert.assertTrue(mainPage.postExists(postTitle));
    }

    @Test(priority = 101, enabled = true)
    public void deleteAllPosts(){
        mainPage.deleteAllPosts();
    }

    @Test(priority = 121, enabled = true)
    public void allPostsDeleted(){
        Assert.assertEquals(mainPage.getPostCount(), 0, "There are 0 posts.");
    }


    @AfterClass
    public void finish(){
        //driver.close();
    }

}
