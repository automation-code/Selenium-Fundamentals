package JavaScripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsNavigation {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Navigate to new URL
        js.executeScript("window.location='https://www.wikipedia.org';");
        Thread.sleep(2000);

        // Refresh page
        js.executeScript("location.reload();");
        Thread.sleep(2000);

        // Navigate back
        js.executeScript("window.history.back();");
        Thread.sleep(2000);

        // Navigate forward
        js.executeScript("window.history.forward();");
        Thread.sleep(2000);

        // Open new window
        js.executeScript("window.open('https://www.google.com');");
        Thread.sleep(2000);

        driver.quit();
    }

    // Refresh page using JS
    public static void refreshPage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("location.reload();");
    }

    // Navigate to URL
    public static void navigateToURL(WebDriver driver, String url) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.location='" + url + "';");
    }
}
