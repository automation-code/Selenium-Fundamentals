package WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElements {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Opens the specified URL in the current browser window.
        driver.get("https://testautomationpractice.blogspot.com/");

        // Clicks the element.
        driver.findElement(By.xpath("//div[@id='PageList1'] //a[contains(text(),'Home')]")).click();

        // Sends keystrokes to an input or editable element.
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Selenium");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("selenium@automation.com");
        driver.findElement(By.xpath("//input[@id='phone']")).sendKeys("86432154632");
        driver.findElement(By.xpath("//textarea[@id='textarea']")).sendKeys("Selenium Automation Testing");

        // Returns the value of a specified attribute.
        String attributeText = driver.findElement(By.xpath("//input[@id='field1']")).getAttribute("value");
        System.out.println("getAttribute: " + attributeText);


        // Clears the value of a text field or text area.
        driver.findElement(By.xpath("//input[@id='field1']")).clear();

        // Returns the visible inner text of an element.
        String buttonText = driver.findElement(By.xpath("//h2[contains(text(),'Dynamic Button')]")).getText();
        System.out.println("getText: " + buttonText);

        // Returns the tag name of the element.
        String tagName = driver.findElement(By.xpath("//h2[contains(text(),'Dynamic Button')]")).getTagName();
        System.out.println("getTagName: " + tagName);

        // getCssValue(String propertyName)
        String cssValue = driver.findElement(By.cssSelector("a[type='application/atom+xml']")).getCssValue("font-size");
        System.out.println("getCssValue: " + cssValue);

        // Checks if the element is visible to the user.
        boolean isDisplayFlag = driver.findElement(By.cssSelector("form[id='singleFileForm'] button[type='submit']")).isDisplayed();
        System.out.println("isDisplayed: " + isDisplayFlag);

        // Checks if the element is enabled.
        boolean isEnableFlag = driver.findElement(By.xpath("//input[@id='sunday']")).isEnabled();
        System.out.println("isEnabled: " + isEnableFlag);

        // Checks if the element is selected (for checkboxes, options, etc.).
        boolean isSelectedFlag = driver.findElement(By.xpath("//input[@id='sunday']")).isSelected();
        System.out.println("isSelected: " + isSelectedFlag);

        // Submits a form (if the element is inside a <form>).
        driver.findElement(By.xpath("//input[@id='Wikipedia1_wikipedia-search-input']")).sendKeys("Hello");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();

        // Closes all browser windows and ends the WebDriver session.
        driver.quit();
    }
}
