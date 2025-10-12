package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MoveToElement {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement pointMeButton = driver.findElement(By.cssSelector(".dropbtn"));
        WebElement laptopButton = driver.findElement(By.cssSelector("div[id='HTML3'] a:nth-child(1)"));

        // Moves mouse to the element.
        actions.moveToElement(pointMeButton).build().perform();
        laptopButton.click();

        // Right-clicks on element.
        actions.contextClick(pointMeButton).build().perform();


        Thread.sleep(5000);

        driver.quit();
    }
}
