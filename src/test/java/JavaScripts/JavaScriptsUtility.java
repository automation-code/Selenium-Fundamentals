package JavaScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsUtility {

    private WebDriver driver;
    private JavascriptExecutor js;

    JavaScriptsUtility(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // ========== CLICKING ==========
    public void clickElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    // ========== SCROLLING ==========
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollByPixels(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    // ========== ELEMENT MANIPULATION ==========
    public void highlightElement(WebElement element) {
        js.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    public void changeBackgroundColor(WebElement element, String color) {
        js.executeScript("arguments[0].style.backgroundColor='" + color + "';", element);
    }

    public void changeText(WebElement element, String text) {
        js.executeScript("arguments[0].textContent='" + text + "';", element);
    }

    public void hideElement(WebElement element) {
        js.executeScript("arguments[0].style.display='none';", element);
    }

    public void showElement(WebElement element) {
        js.executeScript("arguments[0].style.display='block';", element);
    }

    // ========== FORM OPERATIONS ==========
    public void enterText(WebElement element, String text) {
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    public void clearText(WebElement element) {
        js.executeScript("arguments[0].value='';", element);
    }

    // ========== PAGE INFORMATION ==========
    public String getPageTitle() {
        return (String) js.executeScript("return document.title;");
    }

    public String getCurrentURL() {
        return (String) js.executeScript("return document.URL;");
    }

    public Long getPageHeight() {
        return (Long) js.executeScript("return document.body.scrollHeight;");
    }

    public boolean isPageLoaded() {
        String readyState = (String) js.executeScript("return document.readyState;");
        return readyState.equals("complete");
    }

    // ========== NAVIGATION ==========
    public void refreshPage() {
        js.executeScript("location.reload();");
    }

    public void navigateToURL(String url) {
        js.executeScript("window.location='" + url + "';");
    }

    // ========== STORAGE ==========
    public void setLocalStorage(String key, String value) {
        js.executeScript("localStorage.setItem('" + key + "', '" + value + "');");
    }

    public String getLocalStorage(String key) {
        return (String) js.executeScript("return localStorage.getItem('" + key + "');");
    }

    public void clearLocalStorage() {
        js.executeScript("localStorage.clear();");
    }

    // ========== GENERAL ==========
    public Object executeScript(String script, Object... args) {
        return js.executeScript(script, args);
    }

    public Object executeAsyncScript(String script, Object... args) {
        return js.executeAsyncScript(script, args);
    }

    // ========== ALERTS ==========
    public void generateAlert(String message) {
        js.executeScript("alert('" + message + "');");
    }

    public void generateConsoleLog(String message) {
        js.executeScript("console.log('" + message + "');");
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        JavaScriptsUtility jsUtil = new JavaScriptsUtility(driver);

        try {
            driver.get("https://testautomationpractice.blogspot.com/");
            Thread.sleep(2000);

            // Use utility methods
            System.out.println("Page Title: " + jsUtil.getPageTitle());
            System.out.println("Current URL: " + jsUtil.getCurrentURL());
            System.out.println("Page Height: " + jsUtil.getPageHeight() + "px");
            System.out.println("Page Loaded: " + jsUtil.isPageLoaded());

            // Scroll operations
            jsUtil.scrollToBottom();
            Thread.sleep(2000);
            jsUtil.scrollToTop();

            // Highlight element
            WebElement element = driver.findElement(By.tagName("h1"));
            jsUtil.highlightElement(element);

            // Storage operations
            jsUtil.setLocalStorage("testKey", "testValue");
            System.out.println("LocalStorage Value: " + jsUtil.getLocalStorage("testKey"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
