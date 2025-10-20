package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v141.network.Network;
import org.openqa.selenium.devtools.v141.network.model.Response;

import java.util.Optional;

public class ReceivedResponse {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Get DevTools instance
            DevTools devTools = ((HasDevTools) driver).getDevTools();

            // Create DevTools session
            devTools.createSession();

            // Enable Network domain
            devTools.send(Network.enable(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));

            // Add listener for Network Response Received
            devTools.addListener(Network.responseReceived(), responseReceived -> {
                Response response = responseReceived.getResponse();

                System.out.println("URL: " + response.getUrl());
                System.out.println("Status Code: " + response.getStatus());
                System.out.println("Status Text: " + response.getStatusText());
                System.out.println("MIME Type: " + response.getMimeType());
                System.out.println("Protocol: " + response.getProtocol());
                System.out.println("Security State: " + response.getSecurityState());

                // Response Headers
                response.getHeaders().toJson().forEach((key, value) -> {
                    System.out.println("  " + key + ": " + value);
                });
            });

            driver.get("https://www.google.com/");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }

    // Reusable method to monitor network responses
    public static void monitorNetworkResponses(ChromeDriver driver) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            System.out.println("Response: " + response.getUrl() + " | Status: " + response.getStatus());
        });
    }

    // Filter responses by status code
    public static void monitorResponsesByStatus(ChromeDriver driver, int statusCode) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            if (response.getStatus() == statusCode) {
                System.out.println("Status " + statusCode + " Found: " + response.getUrl());
            }
        });
    }
}
