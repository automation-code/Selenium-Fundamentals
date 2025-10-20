package DevTools;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v141.log.Log;
import org.openqa.selenium.devtools.v141.network.Network;
import org.openqa.selenium.devtools.v141.performance.Performance;
import org.openqa.selenium.devtools.v141.performance.model.Metric;

import java.util.*;

public class CaptureDataSummery {

    private ChromeDriver driver;
    private DevTools devTools;
    private List<String> capturedRequests = new ArrayList<>();
    private List<String> capturedResponses = new ArrayList<>();
    private List<String> failedRequests = new ArrayList<>();
    private List<String> consoleLogs = new ArrayList<>();
    private Map<String, Integer> statusCodeCount = new HashMap<>();

    public CaptureDataSummery(ChromeDriver driver) {
        this.driver = driver;
        this.devTools = ((HasDevTools) driver).getDevTools();
    }

    public void initializeSession() {
        devTools.createSession();
    }

    public void enableNetworkMonitoring() {
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        // Request listener
        devTools.addListener(Network.requestWillBeSent(), requestEvent -> {
            String requestInfo = requestEvent.getRequest().getMethod() + " | " +
                    requestEvent.getRequest().getUrl();
            capturedRequests.add(requestInfo);
        });

        // Response listener
        devTools.addListener(Network.responseReceived(), responseEvent -> {
            int status = responseEvent.getResponse().getStatus();
            String url = responseEvent.getResponse().getUrl();
            String responseInfo = status + " | " + url;
            capturedResponses.add(responseInfo);

            // Count status codes
            statusCodeCount.put(String.valueOf(status),
                    statusCodeCount.getOrDefault(String.valueOf(status), 0) + 1);
        });

        // Failed request listener
        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            String failedInfo = loadingFailed.getType() + " | " + loadingFailed.getErrorText();
            failedRequests.add(failedInfo);
        });
    }

    public void enableConsoleLogMonitoring() {
        devTools.send(Log.enable());

        devTools.addListener(Log.entryAdded(), logEntry -> {
            String logInfo = logEntry.getLevel() + " | " + logEntry.getText();
            consoleLogs.add(logInfo);
        });
    }

    public List<Metric> getPerformanceMetrics() {
        devTools.send(Performance.enable(Optional.empty()));
        return devTools.send(Performance.getMetrics());
    }

    public void printCapturedData() {
        System.out.println("Total Requests: " + capturedRequests.size());
        System.out.println("Total Responses: " + capturedResponses.size());
        System.out.println("Failed Requests: " + failedRequests.size());
        System.out.println("Console Logs: " + consoleLogs.size());

        statusCodeCount.forEach((code, count) ->
                System.out.println("  " + code + ": " + count + " times")
        );

        if (!failedRequests.isEmpty()) {
            failedRequests.forEach(System.out::println);
        }
    }

    public List<String> getCapturedRequests() {
        return new ArrayList<>(capturedRequests);
    }

    public List<String> getCapturedResponses() {
        return new ArrayList<>(capturedResponses);
    }

    public List<String> getFailedRequests() {
        return new ArrayList<>(failedRequests);
    }

    public List<String> getConsoleLogs() {
        return new ArrayList<>(consoleLogs);
    }

    public Map<String, Integer> getStatusCodeCount() {
        return new HashMap<>(statusCodeCount);
    }

    public void clearAllData() {
        capturedRequests.clear();
        capturedResponses.clear();
        failedRequests.clear();
        consoleLogs.clear();
        statusCodeCount.clear();
    }

    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();

        try {
            CaptureDataSummery captureDataSummery = new CaptureDataSummery(driver);
            captureDataSummery.initializeSession();
            captureDataSummery.enableNetworkMonitoring();
            captureDataSummery.enableConsoleLogMonitoring();

            driver.get("https://www.yandex.com");

            // Generate some console logs
            driver.executeScript("console.log('Test log from utility');");
            driver.executeScript("console.error('Test error from utility');");

            Thread.sleep(5000);

            // Print summary
            captureDataSummery.printCapturedData();

            // Get performance metrics
            System.out.println("--- Performance Metrics ---");
            List<Metric> metrics = captureDataSummery.getPerformanceMetrics();
            System.out.println("Total Performance Metrics: " + metrics.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
}
