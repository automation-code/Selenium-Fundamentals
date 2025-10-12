package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class ActionsWithKeys {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

        driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']"))
                .sendKeys("Selenium", Keys.ENTER);


        // Perform copy and paste using keys
        WebElement textField1 = driver.findElement(By.xpath("//input[@id='field1']"));
        WebElement textField2 = driver.findElement(By.xpath("//input[@id='field2']"));

        Actions actions = new Actions(driver);
        // Select all text and copy from textField1 field
        actions.keyDown(textField1, Keys.CONTROL).sendKeys("ac").keyUp(Keys.CONTROL).build().perform();
        // Paste text in to textField2 field
        actions.keyDown(textField2, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();

        Thread.sleep(5000);

        driver.quit();

    }
}
