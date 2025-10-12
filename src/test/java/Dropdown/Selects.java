package Dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Selects {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement element = driver.findElement(By.xpath("//select[@id='country']"));
        Select select = new Select(element);
        // Selects by index (0-based).
        select.selectByIndex(3);
        // Selects by option value attribute.
        select.selectByValue("australia");
        // Selects by visible option text.
        select.selectByVisibleText("India");
        // Selects option contains visible text.
        select.selectByContainsVisibleText("China");
        // Deselects option by visible text.
        select.deselectByVisibleText("China");
        // Returns the currently selected option.
        select.getFirstSelectedOption();
    }
}
