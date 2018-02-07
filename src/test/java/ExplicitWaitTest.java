import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.fail;

public class ExplicitWaitTest {
    @Test
    public void login_test() throws InterruptedException {
        //Initialize WebDriver class and create object
        WebDriver driver = new FirefoxDriver();
        //Go to newtours website
        driver.get("http://www.newtours.demoaut.com");
        //Type selenium in username textfield
        driver.findElement(By.name("userName")).sendKeys("selenium");
        //Type selenium in password textfield
        driver.findElement(By.name("password")).sendKeys("selenium");
        //Click on Login element
        driver.findElement(By.name("login")).click();
        //Set Explicit Wait for 10 seconds by creating object for WebDriverWait class
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Now wait until next element is present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
        System.out.println(driver.getTitle());  //Get Title
        if (driver.getTitle().equals("Find a Flight: Mercury Tours:")) {
            System.out.println("Test Passed"); //Print "Test Passed" if test passes
        } else {
            System.out.println("Test Failed"); //Print "Test Failed" if test failed
            fail("Test Failed");
        }
        driver.close();
    }
}
