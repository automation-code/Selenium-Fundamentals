package WaitsTimeouts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitsTimeouts {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Waits for elements to appear before throwing NoSuchElementException
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Explicit wait object.
        WebElement element = driver.findElement(By.xpath(""));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Waits until the element is visible.
        wait.until(ExpectedConditions.visibilityOf(element));
        // Waits until the element is clickable.
        wait.until(ExpectedConditions.elementToBeClickable(element));
        // Waits for an alert to appear.
        wait.until(ExpectedConditions.alertIsPresent());

        // Custom wait with polling interval and ignored exceptions.
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .withMessage("We can pass the any message here")
                .ignoring(NoSuchElementException.class);

        WebElement btnTxt = wait.until(ExpectedConditions.visibilityOf(element));



    }
}
