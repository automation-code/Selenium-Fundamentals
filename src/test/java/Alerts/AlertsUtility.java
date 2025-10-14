package Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsUtility {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public AlertsUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Constructor with custom timeout
    public AlertsUtility(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Switch to Alert
    public Alert switchToAlert() {
        try {
            return driver.switchTo().alert();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present: " + e.getMessage());
            return null;
        }
    }

    // Wait for Alert to be Present
    public Alert waitForAlert() {
        try {
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            System.out.println("Alert not present within timeout: " + e.getMessage());
            return null;
        }
    }

    // Check if Alert is Present
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // Accept Alert (Click OK)
    public void acceptAlert() {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                alert.accept();
                System.out.println("Alert accepted successfully");
            }
        } catch (Exception e) {
            System.out.println("Failed to accept alert: " + e.getMessage());
        }
    }

    // Dismiss Alert (Click Cancel)
    public void dismissAlert() {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                alert.dismiss();
                System.out.println("Alert dismissed successfully");
            }
        } catch (Exception e) {
            System.out.println("Failed to dismiss alert: " + e.getMessage());
        }
    }

    // Get Alert Text
    public String getAlertText() {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                String alertText = alert.getText();
                System.out.println("Alert text: " + alertText);
                return alertText;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Failed to get alert text: " + e.getMessage());
            return null;
        }
    }

    // Get Alert Text and Accept
    public String getAlertTextAndAccept() {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                String alertText = alert.getText();
                alert.accept();
                System.out.println("Alert text retrieved and accepted: " + alertText);
                return alertText;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Failed to get alert text and accept: " + e.getMessage());
            return null;
        }
    }

    // Get Alert Text and Dismiss
    public String getAlertTextAndDismiss() {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                String alertText = alert.getText();
                alert.dismiss();
                System.out.println("Alert text retrieved and dismissed: " + alertText);
                return alertText;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Failed to get alert text and dismiss: " + e.getMessage());
            return null;
        }
    }

    // Send Keys to Alert (for Prompt)
    public void sendKeysToAlert(String text) {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                alert.sendKeys(text);
                System.out.println("Text sent to alert: " + text);
            }
        } catch (Exception e) {
            System.out.println("Failed to send keys to alert: " + e.getMessage());
        }
    }

    // Send Keys and Accept Alert
    public void sendKeysAndAcceptAlert(String text) {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                alert.sendKeys(text);
                alert.accept();
                System.out.println("Text sent and alert accepted: " + text);
            }
        } catch (Exception e) {
            System.out.println("Failed to send keys and accept alert: " + e.getMessage());
        }
    }

    // Handle Simple Alert (Accept)
    public void handleSimpleAlert() {
        acceptAlert();
    }

    // Handle Confirmation Alert (Accept or Dismiss based on parameter)
    public void handleConfirmationAlert(boolean accept) {
        if (accept) {
            acceptAlert();
        } else {
            dismissAlert();
        }
    }

    // Handle Prompt Alert (Send text and Accept/Dismiss)
    public void handlePromptAlert(String text, boolean accept) {
        try {
            Alert alert = waitForAlert();
            if (alert != null) {
                alert.sendKeys(text);
                if (accept) {
                    alert.accept();
                    System.out.println("Prompt handled - Text: " + text + ", Accepted");
                } else {
                    alert.dismiss();
                    System.out.println("Prompt handled - Text: " + text + ", Dismissed");
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to handle prompt alert: " + e.getMessage());
        }
    }

    // Verify Alert Text Contains Expected Text
    public boolean verifyAlertTextContains(String expectedText) {
        try {
            String actualText = getAlertText();
            if (actualText != null && actualText.contains(expectedText)) {
                System.out.println("Alert text verification passed");
                return true;
            } else {
                System.out.println("Alert text verification failed. Expected: " + expectedText + ", Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to verify alert text: " + e.getMessage());
            return false;
        }
    }

    // Verify Alert Text Equals Expected Text
    public boolean verifyAlertTextEquals(String expectedText) {
        try {
            String actualText = getAlertText();
            if (actualText != null && actualText.equals(expectedText)) {
                System.out.println("Alert text verification passed");
                return true;
            } else {
                System.out.println("Alert text verification failed. Expected: " + expectedText + ", Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to verify alert text: " + e.getMessage());
            return false;
        }
    }

    // Accept Alert if Present (without throwing exception)
    public void acceptAlertIfPresent() {
        if (isAlertPresent()) {
            acceptAlert();
        } else {
            System.out.println("No alert present to accept");
        }
    }

    // Dismiss Alert if Present (without throwing exception)
    public void dismissAlertIfPresent() {
        if (isAlertPresent()) {
            dismissAlert();
        } else {
            System.out.println("No alert present to dismiss");
        }
    }

    // Example Usage Method
    public static void main(String[] args) {
        // Example initialization (you would use your actual WebDriver setup)
        WebDriver driver = new ChromeDriver();
        AlertsUtility alertUtil = new AlertsUtility(driver);
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

        // Example 1: Simple Alert
        driver.findElement(By.xpath("//button[@id='alertBtn']")).click();
        alertUtil.handleSimpleAlert();

        // Example 2: Confirmation Alert
        driver.findElement(By.xpath("//button[@id='confirmBtn']")).click();
        alertUtil.handleConfirmationAlert(true);

        // Example 3: Prompt Alert
        driver.findElement(By.xpath("//button[@id='promptBtn']")).click();
        alertUtil.handlePromptAlert("John Doe", true);

        // Example 4: Get alert text before accepting
        String text = alertUtil.getAlertTextAndAccept();
        System.out.println("Alert message was: " + text);

        driver.quit();
    }
}
