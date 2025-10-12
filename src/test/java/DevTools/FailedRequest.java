package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v140.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FailedRequest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();
            devTools.send(Network.enable(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));

            List<String> failedRequests = new ArrayList<>();

            // Add listener for Loading Failed
            devTools.addListener(Network.loadingFailed(), loadingFailed -> {
                System.out.println("Request ID: " + loadingFailed.getRequestId());
                System.out.println("Type: " + loadingFailed.getType());
                System.out.println("Error Text: " + loadingFailed.getErrorText());
                System.out.println("Canceled: " + loadingFailed.getCanceled());
                System.out.println("Blocked Reason: " + loadingFailed.getBlockedReason());
                failedRequests.add(loadingFailed.getErrorText());
            });

            driver.get("https://www.google.com");
            Thread.sleep(5000);

            System.out.println("Total Failed Requests: " + failedRequests.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Capture and return list of failed requests
    public static List<String> captureFailedRequests(ChromeDriver driver, String url) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        List<String> failedRequests = new ArrayList<>();

        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            failedRequests.add("Error: " + loadingFailed.getErrorText() + " | Type: " + loadingFailed.getType());
        });

        driver.get(url);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return failedRequests;
    }
}



