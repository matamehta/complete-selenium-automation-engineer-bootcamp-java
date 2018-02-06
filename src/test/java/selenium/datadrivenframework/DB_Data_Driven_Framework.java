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
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Map;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/15/18.
 */
public class DB_Data_Driven_Framework {
    //Create variables for database connection
    private final static String dbUrl = "jdbc:postgresql://localhost:5434/company";
    private final static String user = "seleniumguru";
    private final static String password = "test1234";

    @Test
    public void db_data_driven_test() throws InterruptedException, IOException, InvalidFormatException, ClassNotFoundException, SQLException {

        //Use class for postgres
        Class.forName("org.postgresql.Driver");
        //Create an object

        //Create a connection object
        Connection connection = DriverManager.getConnection(dbUrl, user, password);

        //Create a statement
        Statement stmt = connection.createStatement();

        //Create a result set
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM CUSTOMER INNER JOIN FLIGHT_DETAILS ON (CUSTOMER.ID = FLIGHT_DETAILS.ID);");


        WebDriver driver = null;
        String browserType = "firefox";

        while(resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            System.out.println(username);
            System.out.println(password);

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
            driver.findElement(By.name("userName")).sendKeys(username);

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

            String tripType = "oneway";
            WebElement trip_type = driver.findElement(By.xpath("//input[@name='tripType'][@value=\'" + tripType + "\']"));
            trip_type.click();
        }


    }
}
