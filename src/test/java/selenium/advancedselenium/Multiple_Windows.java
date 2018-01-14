package selenium.advancedselenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by SeleniumGuru.com on 1/10/18.
 */
public class Multiple_Windows {
    @Test
    public void iframe_test() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("http://the-internet.herokuapp.com/windows");

        //maximize the window
        driver.manage().window().maximize();

        //Get the current window handle
        String parentWindow = driver.getWindowHandle();

        //Click the click me button
        driver.findElement(By.linkText("Click Here")).click();

        Thread.sleep(2000);
        //For loop for all the window handles
        for(String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            System.out.println(driver.getTitle());
        }

        //Switch back to parent window
        driver.switchTo().window(parentWindow);






    }

}
