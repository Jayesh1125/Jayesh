package demo.wrappers;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrappers {
    private WebDriver driver;
    private WebDriverWait wait;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Increased wait time
    }

    // Wrapper method to fill in a text input
    public void enterText(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void radioButton(ChromeDriver driver, String radioButtonText) {
        try {
            WebElement element = driver.findElement(By.xpath("//span[contains(@class,'OvPDhc') and contains(text(),'" + radioButtonText + "')]/../../..//div[@class='vd3tt']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkBox(ChromeDriver driver, String checkboxText) {
        try {
            WebElement element = driver.findElement(By.xpath("//span[contains(@class,'n5vBHf') and contains(text(),'" + checkboxText + "')]/../../preceding-sibling::div[contains(@id,'i')]"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropDownClick(ChromeDriver driver, String dropDownText) {
        try {
            WebElement element = driver.findElement(By.xpath("//div[contains(@class,'QXL7Te')]//div[@data-value='" + dropDownText + "']"));
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickOnElement(ChromeDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDateSevenDaysAgo() {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateMinus7Days = currentDate.minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateMinus7Days.format(formatter);
    }

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return currentTime.format(formatter);
    }

    public static String getEpochTimeAsString() {
        long epochTime = System.currentTimeMillis() / 1000;
        return String.valueOf(epochTime);
    }
    // public static void dropDownClick(ChromeDriver driver, String optionText) {
    //     try {
    //         WebElement element = driver.findElement(By.xpath("//div[contains(@data-value,'" + optionText + "')]"));
    //         element.click();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // Wrapper method to check if an element is visible
    public boolean isElementVisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed(); // Returns true if the element is visible
        } catch (NoSuchElementException e) {
            return false; // Returns false if the element is not found
        }
    }
    

    // Wrapper method to wait for an element to be visible
    // public WebElement waitForElement(By locator) {
    //     try {
    //         System.out.println("Waiting for element: " + locator.toString());
    //         return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // or use ExpectedConditions.elementToBeClickable(locator)
    //     } catch (TimeoutException e) {
    //         System.err.println("Element not found within the time limit: " + locator.toString());
    //         throw new RuntimeException("Element not found: " + locator.toString(), e);
    //     } catch (NoSuchElementException e) {
    //         System.err.println("Element not found: " + locator.toString());
    //         throw new RuntimeException("Element not found: " + locator.toString(), e);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         throw new RuntimeException("An error occurred while waiting for the element: " + locator.toString(), e);
    //     }
    
    

    // Wrapper method to close the browser
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

   
    

