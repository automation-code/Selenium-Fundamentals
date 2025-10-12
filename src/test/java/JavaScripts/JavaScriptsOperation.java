package JavaScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsOperation {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement element = driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']"));
        WebElement textField = driver.findElement(By.xpath("//input[@id='field1']"));
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));

        // Enter text using JS
        js.executeScript("arguments[0].value='Selenium JavaScript';", element);
        Thread.sleep(2000);

        // Clear text
        js.executeScript("arguments[0].value='';", element);
        Thread.sleep(2000);

        // Set value
        js.executeScript("arguments[0].value='New Search Term';", textField);
        Thread.sleep(2000);

        // Focus on element
        js.executeScript("arguments[0].focus();", element);
        Thread.sleep(2000);

        // Blur element
        js.executeScript("arguments[0].blur();", textField);
        Thread.sleep(2000);

        // Disable element
        js.executeScript("arguments[0].disabled=true;", textField);
        Thread.sleep(2000);

        // Enable element
        js.executeScript("arguments[0].disabled=false;", textField);
        Thread.sleep(2000);

        driver.quit();
    }

    // Enter text in input field
    public static void enterText(WebDriver driver, WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    // Check checkbox
    public static void checkCheckbox(WebDriver driver, WebElement checkbox) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].checked=true;", checkbox);
    }

    // Select dropdown option
    public static void selectDropdownByValue(WebDriver driver, WebElement dropdown, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + value + "';", dropdown);
    }
}
