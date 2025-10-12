package JavaScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptsManipulation {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();
        driver.get("https://www.apple.com/");

        WebElement element = driver.findElement(By.xpath("//a[@aria-label='Buy, iPhone Air']"));

        // Change text content
        WebElement textContent = driver.findElement(By.xpath("//a[@aria-label='Buy, iPhone Air']"));
        js.executeScript("arguments[0].textContent='Apple';", textContent);
        Thread.sleep(2000);

        // Change style/CSS
        js.executeScript("arguments[0].style.border='3px solid red';", element);
        js.executeScript("arguments[0].style.backgroundColor='yellow';", element);
        Thread.sleep(2000);

        // Get attribute value
        String href = (String) js.executeScript(
                "return arguments[0].getAttribute('href');",
                driver.findElement(By.tagName("a"))
        );
        System.out.println("Attribute value: " + href);

        // Set attribute
        js.executeScript("arguments[0].setAttribute('title', 'Apple');", element);

        // Remove attribute
        js.executeScript("arguments[0].removeAttribute('class');", element);

        // Get inner HTML
        String innerHTML = (String) js.executeScript("return arguments[0].innerHTML;", element);
        System.out.println("Inner HTML: " + innerHTML);

        // Hide element
        js.executeScript("arguments[0].style.display='none';", element);
        Thread.sleep(2000);

        // Show element
        js.executeScript("arguments[0].style.display='block';", element);
        Thread.sleep(2000);

        driver.quit();
    }

    // Highlight element
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    // Change element text
    public static void changeText(WebDriver driver, WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent='" + text + "';", element);
    }

    // Get element attribute
    public static String getAttribute(WebDriver driver, WebElement element, String attribute) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].getAttribute('" + attribute + "');", element);
    }
}
