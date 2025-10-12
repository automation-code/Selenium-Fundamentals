package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragDrop {

    public static void main(String[] args) throws InterruptedException {
        WebElement draggable;
        WebElement droppable;
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.get("https://testautomationpractice.blogspot.com/");

        // Performs drag and drop.
        draggable = driver.findElement(By.xpath("//div[@id='draggable']"));
        droppable = driver.findElement(By.xpath("//div[@id='droppable']"));
        actions.dragAndDrop(draggable, droppable).build().perform();

        Thread.sleep(4000);

        driver.quit();
    }
}
