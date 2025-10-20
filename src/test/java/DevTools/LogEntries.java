package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v141.log.Log;

import java.util.ArrayList;
import java.util.List;

public class LogEntries {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();

            // Enable Log
            devTools.send(Log.enable());

            List<String> consoleLogs = new ArrayList<>();

            // Add listener for console logs
            devTools.addListener(Log.entryAdded(), logEntry -> {
                System.out.println("Level: " + logEntry.getLevel());
                System.out.println("Message: " + logEntry.getText());
                System.out.println("Source: " + logEntry.getSource());
                System.out.println("URL: " + logEntry.getUrl().orElse("N/A"));
                System.out.println("Line Number: " + logEntry.getLineNumber().orElse(0));
                System.out.println("Timestamp: " + logEntry.getTimestamp());
                consoleLogs.add(logEntry.getLevel() + ": " + logEntry.getText());
            });

            // Navigate to page
            driver.get("https://www.google.com");

            Thread.sleep(3000);
            System.out.println("Total Console Logs Captured: " + consoleLogs.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Capture only console errors
    public static List<String> captureConsoleErrors(ChromeDriver driver, String url) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());

        List<String> errors = new ArrayList<>();

        devTools.addListener(Log.entryAdded(), logEntry -> {
            if (logEntry.getLevel().toString().equals("ERROR") ||
                    logEntry.getLevel().toString().equals("SEVERE")) {
                errors.add(logEntry.getText());
            }
        });

        driver.get(url);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return errors;
    }
}


