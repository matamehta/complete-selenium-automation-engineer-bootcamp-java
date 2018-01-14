package selenium.advancedselenium;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by SeleniumGuru.com on 1/8/18.
 */
public class IFrame_Example {
    @Test
    public void iframe_test() throws InterruptedException {
        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("file:///Users/tulachanashok/Desktop/selenium-java/frame.html");

        //maximize the window
        driver.manage().window().maximize();

       //Switch to default content
        driver.switchTo().defaultContent();

        //Count number of iframes
        int numberOfFrames = driver.findElements(By.tagName("iframe")).size();
        System.out.println(numberOfFrames);

        //Switch to 1st iFrame
        driver.switchTo().frame(0);
        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();

//        //Switch to default content
//        driver.switchTo().defaultContent();
//
//        //Switch to 2nd iFrame
//        driver.switchTo().frame("frame2");
//        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();
//
//        //Switch to default content
//        driver.switchTo().defaultContent();
//
//
//        //Switch to 3rd iFrame
//        driver.switchTo().frame(2);
//        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button']")).click();





    }
}
