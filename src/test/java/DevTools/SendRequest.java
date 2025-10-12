package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v140.network.Network;
import org.openqa.selenium.devtools.v140.network.model.Request;

import java.util.Optional;

public class SendRequest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            // Get DevTools and create session
            DevTools devTools = ((HasDevTools) driver).getDevTools();

            // Create DevTools session
            devTools.createSession();

            // Enable Network
            devTools.send(Network.enable(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));

            // Add listener for Request Will Be Sent
            devTools.addListener(Network.requestWillBeSent(), requestEvent -> {
                Request request = requestEvent.getRequest();

                System.out.println("URL: " + request.getUrl());
                System.out.println("Method: " + request.getMethod());
                System.out.println("Request ID: " + requestEvent.getRequestId());

                // Display request headers
                request.getHeaders().toJson().forEach((key, value) -> {
                    System.out.println("  " + key + ": " + value);
                });

                // Display POST data if present
                if (request.getPostData().isPresent()) {
                    System.out.println(request.getPostData().get());
                }
            });

            driver.get("https://www.google.com");

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }

    // Monitor specific API endpoints
    public static void monitorAPIEndpoints(ChromeDriver driver, String apiPattern) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestEvent -> {
            String url = requestEvent.getRequest().getUrl();
            if (url.contains(apiPattern)) {
                System.out.println("API Call Detected:");
                System.out.println("  URL: " + url);
                System.out.println("  Method: " + requestEvent.getRequest().getMethod());
            }
        });
    }

    // Monitor POST requests only
    public static void monitorPOSTRequests(ChromeDriver driver) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestEvent -> {
            if (requestEvent.getRequest().getMethod().equals("POST")) {
                System.out.println("POST Request: " + requestEvent.getRequest().getUrl());
                requestEvent.getRequest().getPostData().ifPresent(data ->
                        System.out.println("  Data: " + data)
                );
            }
        });
    }
}
