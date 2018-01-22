package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static junit.framework.TestCase.fail;


/**
 * Created by SeleniumGuru.com on 1/6/18.
 */
public class LoginTest_TestNG {
    WebDriver driver = null;
    @BeforeSuite(groups = {"Smoke Test", "Integration Test"})
    public void beforeSuite(){
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");
        //Initialize the selenium webdriver class and create object
        driver = new FirefoxDriver();
        //Go to newtours website
        driver.get("https://newtours.herokuapp.com/");
        //maximize the window
        driver.manage().window().maximize();
    }

    @Test(description="Login to newtours website", groups = { "Smoke Test", "Integration Test" })
    public void loginTest() throws InterruptedException {
        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");
        //type testing in password text box
        driver.findElement(By.name("password")).sendKeys("testing");
        //click on sign in button
        driver.findElement(By.name("login")).click();
        //Set Explicit Wait for 10 seconds by creating object for WebDriverWait class
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Now wait until next element is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
    }

    @Test(description="Validate Finds Flights Page Title", groups = { "Integration Test" })
    public void validateTitle() throws InterruptedException {
        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if (driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
            System.out.println("Test case passed.");
        } else {
            System.out.println("Test case failed.");
            fail("Test case failed.");
        }
    }

    @AfterSuite(groups = {"Smoke Test", "Integration Test"})
    public void closeBrowser() {
        //Destroy the driver instance and close the browser
        driver.quit();
    }
}
