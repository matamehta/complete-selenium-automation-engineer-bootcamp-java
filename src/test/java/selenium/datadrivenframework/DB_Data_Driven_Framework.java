package selenium.datadrivenframework;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.sql.*;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com.
 */
public class DB_Data_Driven_Framework {
    private final static String DB_URL = "jdbc:postgresql://localhost:5434/company";
    private final static String USER = "ashok";
    private final static String PASS = "test1234";

    @Test
    public void database_data_driven_test() throws InterruptedException, IOException, InvalidFormatException, SQLException, ClassNotFoundException {
        //Initialize webdriver
        WebDriver driver = null;
        //Use postgres driver
        Class.forName("org.postgresql.Driver");
        System.out.println("Connecting to database...");
        //Create connection object using the database url along with username and password
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        //Create statement object from connection
        Statement stmt = conn.createStatement();
        //Create ressult set executing the query
        ResultSet rs = stmt.executeQuery( "SELECT * FROM CUSTOMER INNER JOIN FLIGHT_DETAILS ON (CUSTOMER.NAME = FLIGHT_DETAILS.NAME);");
        while (rs.next() ) {
            //Get username, password, tripType, Firefox
            String  userName = rs.getString("username");
            String password  = rs.getString("password");
            String tripType = rs.getString("trip_type");
            String browserType = "Firefox";
            //Initialize the selenium webdriver class and create object
            if (browserType.equalsIgnoreCase("firefox")) {
                //Declare the gecko driver path
                System.setProperty("webdriver.gecko.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/geckodriver");
                driver = new FirefoxDriver();
            } else if (browserType.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/chromedriver");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.safari.driver", "/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/safaridriver");
                driver = new SafariDriver();
            }
            //Go to newtours website
            driver.get("https://newtours.herokuapp.com/");
            //maximize the window
            driver.manage().window().maximize();
            //type testing in username text box
            driver.findElement(By.name("userName")).sendKeys(userName);
            //type testing in password text box
            driver.findElement(By.name("password")).sendKeys(password);
            //click on sign in button
            driver.findElement(By.name("login")).click();
            // Explicit wait
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
            //Select trip type
            WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripType + "\']"));
            trip_type.click();

            //Destroy the driver instance and close the browser
            driver.quit();
        }
        rs.close();
        stmt.close();
        conn.close();
  }
}




