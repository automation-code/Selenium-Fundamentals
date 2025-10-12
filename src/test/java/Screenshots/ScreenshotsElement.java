package Screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotsElement {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.bing.com/images?FORM=Z9LH");

        try {
            WebElement element = driver.findElement(By.xpath("//a[@class='b_logoArea']"));

            // Method 1: Element screenshot as File
            File elementFile = element.getScreenshotAs(OutputType.FILE);
            File destFile = new File("./src/test/java/Screenshots/screensDir/ElementScreenshotAsFile1.png");
            FileUtils.copyFile(elementFile, destFile);

            // Method 2: Element screenshot as Bytes
            byte[] elementBytes = element.getScreenshotAs(OutputType.BYTES);
            FileOutputStream fos = new FileOutputStream("./src/test/java/Screenshots/screensDir/ElementScreenshotAsBytes1.png");
            fos.write(elementBytes);
            fos.close();

            // Method 3: Element screenshot as Base64
            String elementBase64 = element.getScreenshotAs(OutputType.BASE64);
            byte[] decodedBytes = Base64.getDecoder().decode(elementBase64);
            FileOutputStream fosBase64 = new FileOutputStream("./src/test/java/Screenshots/screensDir/ElementScreenshotAsBase641.png");
            fosBase64.write(decodedBytes);
            fosBase64.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Reusable method - Capture element screenshot
    public static void captureElementScreenshot(WebElement element, String fileName) {
        try {
            File elementFile = element.getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(elementFile, destFile);
        } catch (IOException e) {
            System.err.println("Failed to capture element screenshot: " + e.getMessage());
        }
    }

    // Reusable method - Get element screenshot as Base64
    public static String getElementBase64Screenshot(WebElement element) {
        try {
            return element.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Failed to get element Base64 screenshot: " + e.getMessage());
            return null;
        }
    }

    // Reusable method - Capture element screenshot as bytes
    public static byte[] getElementScreenshotAsBytes(WebElement element) {
        try {
            return element.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            System.err.println("Failed to get element screenshot as bytes: " + e.getMessage());
            return null;
        }
    }
}
