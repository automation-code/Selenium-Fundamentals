package Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Alerts {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");

        // Accept the alert
        driver.findElement(By.xpath("//button[@id='alertBtn']")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // getText from the alert
        driver.findElement(By.xpath("//button[@id='confirmBtn']")).click();
        String msgText = alert.getText();
        System.out.println(msgText);
        alert.accept();

        // SendKeys in the alert box
        driver.findElement(By.xpath("//button[@id='promptBtn']")).click();
        alert.sendKeys("Hello, How are you?");
        Thread.sleep(5000);
        alert.accept();

        driver.quit();
    }
}
