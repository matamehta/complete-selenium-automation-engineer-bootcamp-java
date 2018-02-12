package selenium.dockerswarm;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.fail;

/**
 * Created by SeleniumGuru.com on 2/9/18.
 */
public class grid_test_firefox3 {
    //Initialize webdriver object
    WebDriver driver = null;
    @BeforeSuite(description = "Runs before your test", groups = { "Smoke Test", "Integration Test"})
    public void beforeSuite() throws MalformedURLException {
        //Configured the hub url
        String hubURL = "http://192.168.99.100:4444/wd/hub";

        //Create a Desired Capabilities object
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        capabilities.setPlatform(Platform.ANY);

        capabilities.setBrowserName("firefox");

        capabilities.acceptInsecureCerts();

        //Create a webdriver object
        driver = new RemoteWebDriver(new URL(hubURL), capabilities);

        //Go to newtours website
        driver.get("https://newtours.herokuapp.com/");

        //maximize the window
        driver.manage().window().maximize();
    }


    @Test(description = "Login user", groups = { "Smoke Test", "Integration Test"})
    public void login_test() throws InterruptedException {

        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");

        //type testing in password text box
        driver.findElement(By.name("password")).sendKeys("testing");

        //click on sign in button
        driver.findElement(By.name("login")).click();

        //Wait for 5 secs
        Thread.sleep(5000);

    }

    @Test(description = "Validate page title", groups = { "Integration Test"})
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

    }

    @AfterSuite(description = "Close browser", groups = { "Smoke Test", "Integration Test"})
    public void tearDown(){
        //Destroy the driver instance and close the browser
        driver.quit();
    }
}
