package selenium.testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;


/**
 * Created by SeleniumGuru.com on 1/6/18.
 */
public class LoginTest_TestNG_Dependency {
    //Initialize webdriver object
    WebDriver driver = null;
    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"})
    public void login_test() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("https://newtours.herokuapp.com/");

        //maximize the window
        driver.manage().window().maximize();

        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");

        //type testing in password text box
        driver.findElement(By.name("password")).sendKeys("testing");

        //click on sign in button
        driver.findElement(By.name("login")).click();

        //Wait for 5 secs
        Thread.sleep(5000);

    }

    @Test(description = "Validate page title", groups = { "Integration Test"}, dependsOnMethods = { "login_test"})
    public void validateTitle() {
        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if (driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test case passed.");
        } else {
            System.out.println("Test case failed.");
            fail("Test case failed.");
        }

        driver.quit();

    }

}
