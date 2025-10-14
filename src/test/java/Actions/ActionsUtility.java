package Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionsUtility {

    private WebDriver driver;
    private Actions actions;

    // Constructor
    public ActionsUtility(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Mouse Hover Action
    public void hoverOverElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    // Click and Hold
    public void clickAndHold(WebElement element) {
        actions.clickAndHold(element).perform();
    }

    // Release Click
    public void releaseClick(WebElement element) {
        actions.release(element).perform();
    }

    // Right Click (Context Click)
    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    // Double Click
    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    // Drag and Drop
    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    // Drag and Drop by Offset
    public void dragAndDropByOffset(WebElement element, int xOffset, int yOffset) {
        actions.dragAndDropBy(element, xOffset, yOffset).perform();
    }

    // Move to Element with Offset
    public void moveToElementWithOffset(WebElement element, int xOffset, int yOffset) {
        actions.moveToElement(element, xOffset, yOffset).perform();
    }

    // Click at Current Location
    public void clickAtCurrentLocation() {
        actions.click().perform();
    }

    // Send Keys to Element
    public void sendKeysToElement(WebElement element, String keys) {
        actions.sendKeys(element, keys).perform();
    }

    // Send Keys with Key Down and Key Up
    public void sendKeysWithModifier(WebElement element, Keys modifier, String keys) {
        actions.keyDown(modifier)
                .sendKeys(element, keys)
                .keyUp(modifier)
                .perform();
    }

    // Press Key Down
    public void pressKeyDown(Keys key) {
        actions.keyDown(key).perform();
    }

    // Release Key Up
    public void releaseKeyUp(Keys key) {
        actions.keyUp(key).perform();
    }

    // Scroll to Element
    public void scrollToElement(WebElement element) {
        actions.scrollToElement(element).perform();
    }

    // Scroll by Amount
    public void scrollByAmount(int deltaX, int deltaY) {
        actions.scrollByAmount(deltaX, deltaY).perform();
    }

    // Composite Action - Hover and Click
    public void hoverAndClick(WebElement element) {
        actions.moveToElement(element)
                .click()
                .perform();
    }

    // Composite Action - Multiple Actions Chain
    public void complexActionChain(WebElement element1, WebElement element2) {
        actions.moveToElement(element1)
                .pause(Duration.ofSeconds(5))
                .clickAndHold()
                .moveToElement(element2)
                .release()
                .perform();
    }

    // Copy Text (Ctrl+C or Cmd+C)
    public void copyText(WebElement element) {
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            actions.keyDown(element, Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();
        } else {
            actions.keyDown(element, Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        }
    }

    // Copy Text (Ctrl+C or Cmd+C)
    public void selectCopyText(WebElement element) {
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            actions.keyDown(element, Keys.COMMAND).sendKeys("ac").keyUp(Keys.COMMAND).perform();
        } else {
            actions.keyDown(element, Keys.CONTROL).sendKeys("ac").keyUp(Keys.CONTROL).perform();
        }
    }

    // Paste Text (Ctrl+V or Cmd+V)
    public void pasteText(WebElement element) {
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            actions.keyDown(element, Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).perform();
        } else {
            actions.keyDown(element, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
        }
    }

    // Build Complex Action without Performing (for custom chains)
    public Actions getActionsBuilder() {
        return actions;
    }

    // Example Usage Method
    public static void main(String[] args) throws InterruptedException {
        // Example initialization (you would use your actual WebDriver setup)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
        ActionsUtility actionsUtil = new ActionsUtility(driver);

        // Example 1: Hover over element
        WebElement pointMeButton = driver.findElement(By.cssSelector(".dropbtn"));
        WebElement laptopButton = driver.findElement(By.cssSelector("div[id='HTML3'] a:nth-child(1)"));
        actionsUtil.hoverOverElement(pointMeButton);

        // Example 2: Drag and drop
        WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement target = driver.findElement(By.xpath("//div[@id='droppable']"));
        actionsUtil.dragAndDrop(source, target);

        // Example 3: Complex action chain
        actionsUtil.getActionsBuilder()
                .moveToElement(pointMeButton)
                .click()
                .pause(Duration.ofSeconds(5))
                .moveToElement(laptopButton)
                .click()
                .perform();

        // Example 4: Select copy and paste
        WebElement textField1 = driver.findElement(By.xpath("//input[@id='field1']"));
        WebElement textField2 = driver.findElement(By.xpath("//input[@id='field2']"));
        actionsUtil.scrollToElement(textField1);
        actionsUtil.selectCopyText(textField1);
        actionsUtil.pasteText(textField2);

        // Example 5: Right click
        WebElement element = driver.findElement(By.cssSelector("button[onclick='myFunction()']"));
        actionsUtil.rightClick(element);
        Thread.sleep(4000);

        driver.quit();
    }

}
