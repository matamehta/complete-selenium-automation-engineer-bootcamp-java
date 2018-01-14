package selenium.advancedselenium;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by SeleniumGuru.com on 1/8/18.
 */
public class Alert_Example {
    @Test
    public void login_test() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("http://www.ksrtc.in/");

        //maximize the window
        driver.manage().window().maximize();

        //click on search button
        driver.findElement(By.id("searchBtn")).click();

        //create an alert object for the pop up
        Alert alert = driver.switchTo().alert();

        //Get text from alert box
        System.out.println(alert.getText());

        //hard code sleep
        Thread.sleep(4000);

        //accept the alert
        alert.accept();






    }
}
