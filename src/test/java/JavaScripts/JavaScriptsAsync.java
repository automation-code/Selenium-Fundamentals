package JavaScripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsAsync {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Execute async script with callback
        Object result = js.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "setTimeout(function() {" +
                        "  callback('Async operation completed');" +
                        "}, 3000);"
        );
        System.out.println("Async result: " + result);

        // Fetch API call (async)
        String apiResult = (String) js.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "fetch('https://jsonplaceholder.typicode.com/posts/1')" +
                        ".then(response => response.json())" +
                        ".then(data => callback(JSON.stringify(data)))" +
                        ".catch(error => callback('Error: ' + error));"
        );
        System.out.println("API Response: " + apiResult.substring(0, 100) + "...");
    }

    // Execute async script with timeout
    public static Object executeAsyncWithTimeout(WebDriver driver, String script, int timeoutSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(java.time.Duration.ofSeconds(timeoutSeconds));
        return js.executeAsyncScript(script);
    }
}
