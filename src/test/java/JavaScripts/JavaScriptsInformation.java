package JavaScripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsInformation {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://www.apple.com/");

        // Get page title
        String title = (String) js.executeScript("return document.title;");
        System.out.println("Title: " + title);

        // Get current URL
        String url = (String) js.executeScript("return document.URL;");
        System.out.println("URL: " + url);

        // Get domain
        String domain = (String) js.executeScript("return document.domain;");
        System.out.println("Domain: " + domain);

        // Get ready state
        String readyState = (String) js.executeScript("return document.readyState;");
        System.out.println("Ready State: " + readyState);

        // Get page height
        Long scrollHeight = (Long) js.executeScript("return document.body.scrollHeight;");
        System.out.println("Scroll Height: " + scrollHeight + "px");

        // Get page width
        Long scrollWidth = (Long) js.executeScript("return document.body.scrollWidth;");
        System.out.println("Scroll Width: " + scrollWidth + "px");

        // Get viewport height
        Long viewportHeight = (Long) js.executeScript("return window.innerHeight;");
        System.out.println("Viewport Height: " + viewportHeight + "px");

        // Get viewport width
        Long viewportWidth = (Long) js.executeScript("return window.innerWidth;");
        System.out.println("Viewport Width: " + viewportWidth + "px");

        // Get user agent
        String userAgent = (String) js.executeScript("return navigator.userAgent;");
        System.out.println("User Agent: " + userAgent);

        // Get cookie
        String cookies = (String) js.executeScript("return document.cookie;");
        System.out.println("Cookies: " + cookies);

        driver.quit();
    }

    // Get page load status
    public static boolean isPageLoaded(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String readyState = (String) js.executeScript("return document.readyState;");
        return readyState.equals("complete");
    }
}
