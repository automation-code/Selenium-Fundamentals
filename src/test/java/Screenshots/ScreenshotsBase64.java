package Screenshots;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotsBase64 {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        try{
            TakesScreenshot takesScreenshotBase64 = (TakesScreenshot) driver;
            String base64String = takesScreenshotBase64.getScreenshotAs(OutputType.BASE64);
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            FileOutputStream fos = new FileOutputStream("./src/test/java/Screenshots/screensDir/ScreenshotBase64.png");
            fos.write(decodedBytes);
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }


    // Reusable method - Returns Base64 string
    public static String getBase64Screenshot(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Failed to capture Base64 screenshot: " + e.getMessage());
            return null;
        }
    }

    // Reusable method - Save Base64 screenshot to file
    public static void saveBase64Screenshot(WebDriver driver, String fileName) {
        try {
            String base64String = getBase64Screenshot(driver);
            if (base64String != null) {
                byte[] decodedBytes = Base64.getDecoder().decode(base64String);
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(decodedBytes);
                fos.close();
                System.out.println("Base64 screenshot saved: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Failed to save Base64 screenshot: " + e.getMessage());
        }
    }

    // Reusable method - Get HTML embed code
    public static String getBase64AsHtmlImage(WebDriver driver) {
        String base64String = getBase64Screenshot(driver);
        if (base64String != null) {
            return "<img src='data:image/png;base64," + base64String + "' />";
        }
        return null;
    }
}
