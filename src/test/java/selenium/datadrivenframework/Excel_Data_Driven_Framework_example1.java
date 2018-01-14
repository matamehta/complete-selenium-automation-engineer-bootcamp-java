package selenium.datadrivenframework;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;

import static junit.framework.TestCase.fail;


/**
 * Created by SeleniumGuru.com on 1/6/18.
 */
public class Excel_Data_Driven_Framework_example1 {
    @Test
    public void login_test() throws InterruptedException, IOException, InvalidFormatException {

        //Create a data file object
        File dataFile = new File("/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/Data.xlsx");

        //Create an fileinput stream aobject
        FileInputStream inputStream = new FileInputStream(dataFile);


        //Create a Buffered Input stream object
        BufferedInputStream bufferStream = new BufferedInputStream(inputStream);

        //Create an object for the workbook
        Workbook workbook = WorkbookFactory.create(bufferStream);

        //Create an object for the sheet
        Sheet sh1 = workbook.getSheet("Sheet1");

        //Create variables
        String username = sh1.getRow(1).getCell(0).getStringCellValue();
        String password = sh1.getRow(1).getCell(1).getStringCellValue();

        //Declare the gecko driver path
        System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");

        //Initialize the selenium webdriver class and create object
        WebDriver driver = new FirefoxDriver();

        //Go to newtours website
        driver.get("http://www.newtours.demoaut.com");

        //maximize the window
        driver.manage().window().maximize();

        //type testing in username text box
        driver.findElement(By.name("userName")).sendKeys(username);

        //type testing in password text box
        driver.findElement(By.name("password")).sendKeys(password);

        //click on sign in button
        driver.findElement(By.name("login")).click();

        //Wait for 5 secs
        Thread.sleep(5000);

        //Print out the title
        System.out.println(driver.getTitle());

        //Validate the title
        if (driver.getTitle().equals("Find a Flight: Mercury Tours:")){
           System.out.println("Test case passed.");
        } else {
           System.out.println("Test case failed.");
           fail("Test case failed.");
        }

        String tripType = "oneway";
        WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripType + "\']"));
        trip_type.click();
        //Destroy the driver instance and close the browser
        driver.quit();


    }
}
