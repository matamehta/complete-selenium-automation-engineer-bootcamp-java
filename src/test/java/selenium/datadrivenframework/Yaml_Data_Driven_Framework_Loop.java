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
import java.util.Map;

import static junit.framework.TestCase.fail;

/**
 * Created by SeleniumGuru.com on 1/15/18.
 */
public class Yaml_Data_Driven_Framework_Loop {
    @Test
    public void yaml_data_driven_test() throws InterruptedException, IOException, InvalidFormatException {

        WebDriver driver = null;

        //Create a yaml object
        Yaml yaml = new Yaml();

        //File object
        File file = new File("/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/data.yaml");

        File configFile = new File("/Users/tulachanashok/Documents/Udemy-Selenium-Java/udemyseleniumjava/config.yaml");

        //Create a input stream
        FileInputStream inputStream = new FileInputStream(file);

        //Create a config input stream
        FileInputStream configStream = new FileInputStream(configFile);


        //Parse the yaml file and create a series of map of lists
        Map<String, Map<String, Object>> data = (Map<String, Map<String, Object>>) yaml.load(inputStream);
        System.out.println(data);

        Map<String, Object> config = (Map<String, Object>) yaml.load(configStream);
        System.out.println(config);

        //Create variables for config
        String browserType = config.get("browser").toString();

        //Create an array containing all the records
        String[] recordList = config.get("record").toString().split(",");
        System.out.println(recordList);

        for (String record : recordList) {
            System.out.println(record);
        }


        System.out.println(recordList.length);

        for (int i = 0; i < recordList.length; i++) {
            //Create variables
            String username = data.get(recordList[i]).get("username").toString();
            String password = data.get(recordList[i]).get("password").toString();

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
            driver.get("http://www.newtours.demoaut.com");

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
            //Destroy the driver instance and close the browser
            //driver.quit();
        }

    }
}
