package selenium.wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/8/18.
 */
public class Header_Automation {
    @Test
    public void homepage_validation() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        final WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("https://www.virginia.gov/");

        //maximize the window
        driver.manage().window().maximize();

        //Validate the header
        WebElement header = driver.findElement(By.xpath("//h2[contains(text(), 'How Can Virginia Government Help You?')]"));


        if (header.isDisplayed()) {
            System.out.println("Header text is present");
        } else {
            fail("Header text is not present.");
        }

        //Validate text
        WebElement bodyText = driver.findElement(By.xpath("//p[contains(text(), 'View cabin rental rates, availability or make an online reservation for a cozy, climate-controlled cabin across the state.')]"));

        if (bodyText.isDisplayed()) {
            System.out.println("Body text is present");
        } else {
            fail("Body text is not present.");
        }


        //Destroy the driver instance and close the browser
        driver.quit();


    }
}
