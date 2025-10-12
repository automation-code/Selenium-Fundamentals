package JavaScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsScrolling {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Scroll down by pixel
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);

        // Scroll to bottom of page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(2000);

        // Scroll to top
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(2000);

        // Scroll to specific element
        WebElement specificElement = driver.findElement(By.xpath("//a[@aria-label='Buy, iPhone 17 Pro']"));
        js.executeScript("arguments[0].scrollIntoView(true);", specificElement);
        Thread.sleep(2000);

        // Smooth scroll
        js.executeScript("window.scrollTo({top: 500, behavior: 'smooth'});");
        Thread.sleep(2000);

        // Scroll horizontally
        js.executeScript("window.scrollBy(500, 0);");
        Thread.sleep(2000);

        driver.quit();
    }

    // Scroll to bottom
    public static void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scroll to element
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Scroll by pixels
    public static void scrollByPixels(WebDriver driver, int x, int y) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + x + "," + y + ");");
    }
}
