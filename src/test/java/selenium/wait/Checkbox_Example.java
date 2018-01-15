package selenium.wait;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/8/18.
 */
public class Checkbox_Example {
    @Test
    public void checkbox_example() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("https://www.w3.org/TR/2017/NOTE-wai-aria-practices-1.1-20171214/examples/checkbox/checkbox-1/checkbox-1.html");

        //maximize the window
        driver.manage().window().maximize();

        //Select lettuce checkbox
        driver.findElement(By.xpath("//div[@role='checkbox'][contains(text(),'Lettuce')]")).click();

        //Uncheck tomato checkbox
        driver.findElement(By.xpath("//div[@role='checkbox'][contains(text(),'Tomato')]")).click();
        //Check tomato checkbox
        driver.findElement(By.xpath("//div[@role='checkbox'][contains(text(),'Tomato')]")).click();

        //Check sprouts checkbox
        driver.findElement(By.xpath("//div[@role='checkbox'][contains(text(),'Sprouts')]")).click();

    }
}
