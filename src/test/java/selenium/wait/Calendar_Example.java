package selenium.wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/8/18.
 */
public class Calendar_Example {
    @Test
    public void login_test() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("https://www.orbitz.com");

        //maximize the window
        driver.manage().window().maximize();


        //Click on departing textbox
        WebElement departTextbox = driver.findElement(By.xpath("//input[@id='package-departing']"));
        departTextbox.click();

        //Wait until calendar object is present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='datepicker-cal-weeks']")));

        WebElement calendarObject = driver.findElement(By.xpath("//table[@class='datepicker-cal-weeks']"));

        List<WebElement> departList = calendarObject.findElements(By.tagName("td"));
        String departDate = "15";
        //for each loop
        for (WebElement cell: departList) {
            if (cell.getText().equals(departDate)) {
                System.out.println("Cell found");
                cell.click();
                break;
            } else {
                System.out.println("Cell NOT found yet");
            }
        }

        Thread.sleep(4000);

        //Click on returning textbox
        WebElement returnTextbox = driver.findElement(By.xpath("//input[@id='package-returning']"));
        returnTextbox.click();


        //Wait until calendar object is present
        WebDriverWait wait2 = new WebDriverWait(driver, 10);
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='datepicker-cal-weeks']")));

        WebElement calendarObject2 = driver.findElement(By.xpath("//table[@class='datepicker-cal-weeks']"));

        List<WebElement> returnDateList = calendarObject2.findElements(By.tagName("td"));
        String returnDate = "29";
        //for each loop
        for (WebElement cell: returnDateList) {
            if (cell.getText().equals(returnDate)) {
                System.out.println("Cell found");
                cell.click();
                break;
            } else {
                System.out.println("Cell NOT found yet");
            }
        }




        //Destroy the driver instance and close the browser
        //driver.quit();


    }
}
