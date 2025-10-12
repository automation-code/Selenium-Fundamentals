package Screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Screenshots {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

            // Approach1 - Save as FILE
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File("./src/test/java/Screenshots/screensDir/ApproachFile1.png");
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved as File: " + destFile.getAbsolutePath());

            // Approach2: Save as Bytes
            byte[] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            FileOutputStream fos = new FileOutputStream("./src/test/java/Screenshots/screensDir/ApproachFile2.png");
            fos.write(screenshotBytes);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Reusable method
    public static void captureFullPageScreenshot(WebDriver driver, String fileName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(fileName);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
