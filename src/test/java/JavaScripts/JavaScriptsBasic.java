package JavaScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsBasic {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Execute alert script
        js.executeScript("alert('Hello from Selenium!');");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        // Get page title
        String title = (String) js.executeScript("return document.title;");
        System.out.println("Page Title: " + title);

        // Get current URL
        String url = (String) js.executeScript("return document.URL;");
        System.out.println("Current URL: " + url);

        // Get domain
        String domain = (String) js.executeScript("return document.domain;");
        System.out.println("Domain: " + domain);

        // Get page height
        Long height = (Long) js.executeScript("return document.body.scrollHeight;");
        System.out.println("Page Height: " + height + "px");

        // Get inner text
        String innerText = (String) js.executeScript("return document.body.innerText;");
        System.out.println("Inner Text Length: " + innerText.length() + " characters");

        driver.quit();
    }

    // Reusable method - Execute any script
    public static Object executeJS(WebDriver driver, String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script);
    }

    // Get page title using JS
    public static String getPageTitle(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return document.title;");
    }
}
