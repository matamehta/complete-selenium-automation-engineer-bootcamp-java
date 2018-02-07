package selenium.testng;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static org.testng.Assert.fail;


/**
 * Created by SeleniumGuru.com on 1/6/18.
 */
public class LoginTest_TestNG_DataProvider {
    //Initialize webdriver object
    WebDriver driver = null;
    @BeforeMethod(description = "Runs before your test", groups = { "Smoke Test", "Integration Test"})
    public void beforeMethod(){
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("https://newtours.herokuapp.com/");

        //maximize the window
        driver.manage().window().maximize();
    }

    @DataProvider(name = "User Credentials")
    public static Object [][] getCredentials() {
        return new Object [][] {
                {"seleniumguru", "test1234"},
                {"testing", "testing"},
                {"selenium", "selenium"}
        };
    }


    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"}, dataProvider = "User Credentials")
    public void login_test(String username, String password) throws InterruptedException {
        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys(username);

        //type testing in password text box
        driver.findElement(By.name("password")).sendKeys(password);

        //click on sign in button
        driver.findElement(By.name("login")).click();

        //Wait for 5 secs
        Thread.sleep(5000);
        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if (driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test case passed.");
        } else {
            System.out.println("Test case failed.");
            fail("Test case failed.");
        }


    }


    @AfterMethod(description = "Close browser", groups = { "Smoke Test", "Integration Test"})
    public void tearDown(){
        //Destroy the driver instance and close the browser
        driver.quit();
    }
}
