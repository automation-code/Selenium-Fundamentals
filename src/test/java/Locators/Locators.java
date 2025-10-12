package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Opens the specified URL in the current browser window.
        driver.get("https://");

        // Locates element by ID.
        driver.findElement(By.id("id-locator")).sendKeys("id");

        // Locates element by name attribute.
        driver.findElement(By.name("name-locator")).sendKeys("name");

        // Locates element by class name.
        driver.findElement(By.className("class-name-locator")).sendKeys("class-name");

        // Locates element by tag name.
        driver.findElement(By.tagName("tag-name")).sendKeys("tag-name");

        // Locates link by exact text.
        driver.findElement(By.linkText("link-text")).sendKeys("link-text");

        // Locates link by partial text.
        driver.findElement(By.partialLinkText("partial-link-text")).sendKeys("partial-link-text");

        // Locates element using CSS selector.
        driver.findElement(By.cssSelector("css-selector")).sendKeys("css-selector");

        // Locates element using XPath expression.
        driver.findElement(By.xpath("xpath")).sendKeys("xpath");

        // Closes all browser windows and ends the WebDriver session.
        driver.quit();
    }
}
