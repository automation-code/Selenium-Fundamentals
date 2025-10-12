package JavaScripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsBrowsers {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Set item in localStorage
        js.executeScript("localStorage.setItem('username', 'testuser');");

        // Get item from localStorage
        String username = (String) js.executeScript("return localStorage.getItem('username');");
        System.out.println("Retrieved from localStorage: " + username);

        // Get all localStorage keys
        Long localStorageLength = (Long) js.executeScript("return localStorage.length;");
        System.out.println("localStorage items count: " + localStorageLength);

        // Remove item from localStorage
        js.executeScript("localStorage.removeItem('username');");

        // Clear all localStorage
        js.executeScript("localStorage.clear();");

        // Set item in sessionStorage
        js.executeScript("sessionStorage.setItem('sessionId', '123456');");

        // Get item from sessionStorage
        String sessionId = (String) js.executeScript("return sessionStorage.getItem('sessionId');");
        System.out.println("Retrieved from sessionStorage: " + sessionId);

        // Clear sessionStorage
        js.executeScript("sessionStorage.clear();");

        driver.quit();
    }

    // Set localStorage item
    public static void setLocalStorageItem(WebDriver driver, String key, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("localStorage.setItem('" + key + "', '" + value + "');");
    }

    // Get localStorage item
    public static String getLocalStorageItem(WebDriver driver, String key) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return localStorage.getItem('" + key + "');");
    }

    // Clear localStorage
    public static void clearLocalStorage(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("localStorage.clear();");
    }
}
