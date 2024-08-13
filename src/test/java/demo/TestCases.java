
package demo;

import org.openqa.selenium.By;
//import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrappers;
    @Test
    public void testCase01() throws InterruptedException {
        // Navigate to the Google Form
        //String formUrl = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        System.out.println("Navigated to Google Form.");

        Thread.sleep(3000);
        

        // Fill in "Crio Learner" in the first text box
        WebElement nameinputbox = driver.findElement(By.xpath("//div[@class='Xb9hP']/input[@type='text']"));
        wrappers.enterText(nameinputbox, "Crio Learner");
        Thread.sleep(3000);
        WebElement practicingAutomation = driver.findElement(By.xpath("//textarea[contains(@class,'tL9Q4c')]"));
        String practicingAutomationText = "I want to be the best QA Engineer!";
        String epochTimeString = Wrappers.getEpochTimeAsString();
        wrappers.enterText(practicingAutomation, practicingAutomationText+" "+epochTimeString);
        System.out.println("I want to be the best QA Engineer!");
        Thread.sleep(3000);
        Wrappers.radioButton(driver,"6 - 10");
        //Thread.sleep(3000);
        Wrappers.checkBox(driver, "Java");
        Wrappers.checkBox(driver, "Selenium");
        Wrappers.checkBox(driver, "TestNG");

        WebElement dropDoWebElement = driver.findElement(By.xpath("//div[@jsname='LgbsSe']"));
        Wrappers.clickOnElement(driver, dropDoWebElement);
        Thread.sleep(2000);
        Wrappers.dropDownClick(driver,"Ms");
        Thread.sleep(3000);

        WebElement dateInputBox = new WebDriverWait(driver, Duration.ofSeconds(50))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='date']")));
    String sevenDaysAgoDate = Wrappers.getDateSevenDaysAgo();
    wrappers.enterText(dateInputBox, sevenDaysAgoDate);

   // Ensure the time input fields are targeted correctly
WebElement hourInputBox = new WebDriverWait(driver, Duration.ofSeconds(50))
.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Xb9hP']/input[@aria-label='Hour']")));
wrappers.enterText(hourInputBox, "07");

WebElement minuteInputBox = new WebDriverWait(driver, Duration.ofSeconds(50))
.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Xb9hP']/input[@aria-label='Minute']")));
wrappers.enterText(minuteInputBox, "30");

        // wrappers.enterText(dropdownelement, HH);
        // wrappers.enterText(dropdownelement, MM);
    
    // Click submit button
    
    WebElement submitButton = driver.findElement(By.xpath("//span[contains(text(),'Submit')]"));
    Wrappers.clickOnElement(driver, submitButton);
    //Thread.sleep(3000);
    
// WebElement successMessageElement = wrappers.waitForElement(By.xpath("//div[contains(@class, 'vHW8K') and contains(text(), 'Thanks for your response, Automation Wizard!')]"));

// // If found, retrieve and print the message
// String messageText = successMessageElement.getText();
// System.out.println("Submission Message: " + messageText);

WebElement successMessageElement = new WebDriverWait(driver, Duration.ofSeconds(50))
.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'vHW8K') and contains(text(), 'Thanks for your response, Automation Wizard!')]")));
String messageText = successMessageElement.getText();
System.out.println("Submission Message: " + messageText);






}






    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow testCase01 testCase02... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        //Object WebDriverManager;
        // NOT NEEDED FOR SELENIUM MANAGER
        //WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        wrappers = new Wrappers(driver);
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}