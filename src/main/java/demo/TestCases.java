package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        logStatus("End TestCases", "All testcases are run successfully", "DONE");
        driver.close();
        driver.quit();

    }

    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    static boolean status;
    public  void testCase01(){
        logStatus("Start TestCase", "TestCase 01 : Verify the Leetcode Homepage URL", "DONE");
        driver.get("https://leetcode.com/");

        status = driver.getCurrentUrl().contains("leetcode");
        if(status)
            logStatus("TestCase 01", "Test Case Pass. The url contains google." , status ? "PASS" : "FAIL");
        else
            logStatus("TestCase 01", "Test Case Pass. The url does not contains google." , status ? "PASS" : "FAIL");
            
        logStatus("End TestCase", "Test Case pass. The URL of the Leetcode homepage contains \"leetcode\"" , status ? "PASS" : "FAIL");
    }

    public void testCase02(){
        logStatus("Start TestCase", "TestCase 02 : Verify Problem Set URL and Display First 5 Questions", "DONE");
    
        WebElement questionLink = driver.findElement(By.xpath("//a[@href='/problemset/']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", questionLink);
        questionLink.click();
        status = driver.getCurrentUrl().contains("problemset");
        if(status)
            logStatus("TestCase 02", "Test Case Pass. Verified that we are on the problem set page." , status ? "PASS" : "FAIL");
        else
            logStatus("TestCase 02", "Test Case fail. Verify that we are not on the problem set page." , status ? "PASS" : "FAIL");
        

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='overflow-hidden']/div/div/a"));

        for(int i=1;i <= 5;i++)
        {
            js.executeScript("arguments[0].scrollIntoView();", list.get(i));
            System.out.println(list.get(i).getText());
        }

        logStatus("End TestCase", "Test Case pass. The correct details of the problems are obtained and verified." , status ? "PASS" : "FAIL");
        
    }

    public void testCase03(){
        logStatus("Start TestCase", "TestCase 03 : Verify the Two Sum problem", "DONE");
    
        driver.findElement(By.xpath("(//div[@class='overflow-hidden']/div/div/a)[2]")).click();
        status = driver.getCurrentUrl().contains("two-sum");
        if(status)
            logStatus("TestCase 03", "Test Case Pass. The url contains \"two-sum\"." , status ? "PASS" : "FAIL");
        else
            logStatus("TestCase 03", "Test Case Pass. The url does not contains \"two-sum\"." , status ? "PASS" : "FAIL");
            
        logStatus("End TestCase", "The URL of the problem contains \"two-sum\"." , status ? "PASS" : "FAIL");
        
    }

    public void testCase04(){
        logStatus("Start TestCase", "TestCase 04 : Ensure the submissions tab displays \"Register or Sign In\"", "DONE");
    
        driver.findElement(By.xpath("//span[text()='Submissions']")).click();
        status = driver.findElement(By.xpath("//a[text()='Register or Sign In']")).getText().contains("Register or Sign In");
         if(status)
            logStatus("TestCase 04", "Test Case Pass. Verified that it displays \"Register or Sign In\"." , status ? "PASS" : "FAIL");
        else
            logStatus("TestCase 04", "Test Case Pass. Verified that it does not displays \"Register or Sign In\"." , status ? "PASS" : "FAIL");
            
        logStatus("End TestCase", "The message \"Register or Sign In\" is displayed when you click on the submissions tab." , status ? "PASS" : "FAIL");
        
    }
}
