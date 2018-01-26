package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/6/18.
 */


public class Grid_Test_Safari {
    @Test
    public void login_test() throws InterruptedException, MalformedURLException {
        //Initialize the hubURL with your ip address and port
        String hubURL = "http://192.168.1.163:5566/wd/hub";
        //Create safari browser object from Desired Capabilities object
        DesiredCapabilities capability = DesiredCapabilities.safari();
        //Accept Insecure Certificate
        capability.acceptInsecureCerts();
        //Set Platform such as Mac, Windows based on the OS you are running your test
        capability.setPlatform(Platform.MAC);
        //Create Remote Webdriver object using the hubUrl and capabilities object
        WebDriver driver = new RemoteWebDriver(new URL(hubURL), capability);
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
        //Set Explicit Wait for 10 seconds by creating object for WebDriverWait class
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Now wait until next element is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if (driver.getTitle().equals("Find a Flight: Mercury Tours:")){
            System.out.println("Test case passed.");
        } else {
            System.out.println("Test case failed.");
            fail("Test case failed.");
        }
        //Destroy the driver instance and close the browser
        driver.quit();
    }
}







