package Dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Options {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://testautomationpractice.blogspot.com/");

        WebElement element = driver.findElement(By.xpath("//select[@id='country']"));

        Select select = new Select(element);
        // Returns all options in the dropdown.
        List<WebElement> options = select.getOptions();

        for (WebElement option : options) {
            if (option.getText().equals("India")) {
                option.click();
                System.out.println("Selected option: " + option.getText());
            }
        }

        int numberOfOptions = options.size();
        System.out.println("Number of options: " + numberOfOptions);

        driver.quit();
    }
}
