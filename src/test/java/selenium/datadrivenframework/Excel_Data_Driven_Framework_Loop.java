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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Integer.*;
import static junit.framework.TestCase.fail;


/**
 * Created by SeleniumGuru.com on 1/6/18.
 */
public class Excel_Data_Driven_Framework_Loop {
    @Test
    public void excel_data_driven_test() throws InterruptedException, IOException, InvalidFormatException {

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

        //Count number of rows in excel sheet
        int firstRow = sh1.getFirstRowNum();
        System.out.println(firstRow);

        int lastRow = sh1.getLastRowNum();
        System.out.println(lastRow);


        //for loop to automate through all the test scenarios
        for (int i = 1; i <= lastRow; i++) {

            //cell variable
            int j = 0;

            //Create variables
            String username = sh1.getRow(i).getCell(j).getStringCellValue();
            String password = sh1.getRow(i).getCell(j+1).getStringCellValue();
            String tripType =sh1.getRow(i).getCell(j+2).getStringCellValue();
            int noOfPassengers = (int) sh1.getRow(i).getCell(j+3).getNumericCellValue();
            String from = sh1.getRow(i).getCell(j+4).getStringCellValue();
            String departMonth = sh1.getRow(i).getCell(j+5).getStringCellValue();
            int departDate = (int) sh1.getRow(i).getCell(j+6).getNumericCellValue();
            String to = sh1.getRow(i).getCell(j+7).getStringCellValue();
            String returnMonth = sh1.getRow(i).getCell(j+8).getStringCellValue();
            int returnDate = (int) sh1.getRow(i).getCell(j+9).getNumericCellValue();
            String serviceType = sh1.getRow(i).getCell(j+10).getStringCellValue();
            String airline = sh1.getRow(i).getCell(j+11).getStringCellValue();

            //Declare the gecko driver path
            System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver1");

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

            //Use explicit wait
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='findFlights']")));


            //Print out the title
            System.out.println(driver.getTitle());

            //Validate the title
            if (driver.getTitle().equals("Find a Flight: Mercury Tours:")){
                System.out.println("Test case passed.");
            } else {
                System.out.println("Test case failed.");
                fail("Test case failed.");
            }



            //selecting the trip type
            System.out.println(tripType);
            WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'"+ tripType + "\']"));
            trip_type.click();

            //Select passenger dropdown
            WebElement passengerDropdown = driver.findElement(By.xpath("//select[@name='passCount']"));

            //Create an object for select dropdown
            Select passengerSelectDropdown = new Select(passengerDropdown);

            //Selecting number of passengers
            passengerSelectDropdown.selectByVisibleText(String.valueOf(noOfPassengers));


            //Select depart from dropdown
            WebElement fromDropdown = driver.findElement(By.xpath("//select[@name='fromPort']"));

            //Create an object for select dropdown
            Select fromSelectDropdown = new Select(fromDropdown);

            //Selecting depart from
            fromSelectDropdown.selectByVisibleText(from);


            //Select depart month dropdown
            WebElement departureMonthDropdown = driver.findElement(By.xpath("//select[@name='fromMonth']"));

            //Create an object for select dropdown
            Select departureMonthSelectDropdown = new Select(departureMonthDropdown);

            //Selecting depart month
            departureMonthSelectDropdown.selectByVisibleText(departMonth);



            //Select departDate dropdown
            WebElement departureDateDropdown = driver.findElement(By.xpath("//select[@name='fromDay']"));

            //Create an object for select dropdown
            Select departureDateSelectDropdown = new Select(departureDateDropdown);

            //Selecting depart date
            departureDateSelectDropdown.selectByVisibleText(String.valueOf(departDate));



            //Select to destination dropdown
            WebElement toDropdown = driver.findElement(By.xpath("//select[@name='toPort']"));

            //Create an object for select dropdown
            Select toSelectDropdown = new Select(toDropdown);

            //Selecting to destination
            toSelectDropdown.selectByVisibleText(to);



            //Select return month dropdown
            WebElement returnMonthDropdown = driver.findElement(By.xpath("//select[@name='toMonth']"));

            //Create an object for select dropdown
            Select returnMonthSelectDropdown = new Select(returnMonthDropdown);

            //Selecting return month
            returnMonthSelectDropdown.selectByVisibleText(returnMonth);


            //Select return date dropdown
            WebElement returnDateDropdown = driver.findElement(By.xpath("//select[@name='toDay']"));

            //Create an object for select dropdown
            Select returnDateSelectDropdown = new Select(returnDateDropdown);

            //Selecting return date
            returnDateSelectDropdown.selectByVisibleText(String.valueOf(returnDate));



            //Select service type
            WebElement service = driver.findElement(By.xpath("//input[@name='servClass'][@value=\'" + serviceType + "\']"));
            service.click();


            //Select airline dropdown
            WebElement airlineDropdown = driver.findElement(By.xpath("//select[@name='airline']"));

            //Create an object for select dropdown
            Select airlineSelectDropdown = new Select(airlineDropdown);

            //Selecting airline value
            airlineSelectDropdown.selectByVisibleText(airline);


            //Destroy the driver instance and close the browser
            driver.quit();



        }




    }

}
