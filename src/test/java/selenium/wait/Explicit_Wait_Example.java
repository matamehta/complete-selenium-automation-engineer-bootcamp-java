package selenium.wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/8/18.
 */
public class Explicit_Wait_Example {
    @Test
    public void login_test() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver1");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("http://www.newtours.demoaut.com");

        //maximize the window
        driver.manage().window().maximize();


        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys("testing");

        //type testing in password text box
        driver.findElement(By.name("password")).sendKeys("testing");

        //click on sign in button
        driver.findElement(By.name("login")).click();

        //Set Explicit Wait until find Flights button is present

        WebDriverWait wait = new WebDriverWait(driver, 5);
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

        //Validate the logo
        WebElement mercuryToursLogo = driver.findElement(By.xpath("//img[@src='/images/nav/logo.gif'][@alt='Mercury Tours']"));

        if (mercuryToursLogo.isDisplayed()) {
            System.out.println("Logo is present");
        } else {
            fail("Logo is not present.");
        }

        //Validate rent a car image
        WebElement rentACarImage = driver.findElement(By.xpath("//img[@src='/images/nav/boxad1.gif']"));

        if (rentACarImage.isDisplayed()) {
            System.out.println("Rent a car image is present");
        } else {
            fail("Rent a car image is not present.");
        }

        //Find the one way radio button
        WebElement tripType  = driver.findElement(By.xpath("//input[@name='tripType'][@value='oneway']"));
        tripType.click();

        //Find the dropdown
        WebElement passengerDropdown  = driver.findElement(By.xpath("//select[@name='passCount']"));

        //Create an object for the select
        Select passengerDropdownSelect = new Select(passengerDropdown);

        //Select any element from select dropdown
        passengerDropdownSelect.selectByValue("4");


        //fromPort

        //Find the dropdown
        WebElement fromDropdown  = driver.findElement(By.xpath("//select[@name='fromPort']"));

        //Create an object for the select
        Select fromDropdownSelect = new Select(fromDropdown);

        //Select any element from select dropdown
        fromDropdownSelect.selectByVisibleText("London");

        //Destroy the driver instance and close the browser
        //driver.quit();


    }
}
