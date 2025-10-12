package JavaScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsClicks {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Click element by locating
        WebElement element = driver.findElement(By.cssSelector("div[id='broken-links'] a:nth-child(2)"));
        js.executeScript("arguments[0].click();", element);
        Thread.sleep(2000);

        // Click element by ID
        driver.navigate().back();
        Thread.sleep(2000);
        js.executeScript("document.getElementById('apple').click();");

        Thread.sleep(3000);

        driver.quit();
    }

    // Reusable method - Click element using JS
    public static void clickElementJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    // Click hidden element
    public static void clickHiddenElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
}
