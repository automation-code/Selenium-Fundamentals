package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v141.network.Network;

import java.util.Optional;

public class FinishedLoading {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();
            devTools.send(Network.enable(
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()));

            // Add listener for Loading Finished
            devTools.addListener(Network.loadingFinished(), loadingFinished -> {
                System.out.println("Request ID: " + loadingFinished.getRequestId());
                System.out.println("Timestamp: " + loadingFinished.getTimestamp());
                System.out.println("Encoded Data Length: " + loadingFinished.getEncodedDataLength() + " bytes");
            });

            driver.get("https://www.google.com");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Track total bytes loaded
    public static long getTotalBytesLoaded(ChromeDriver driver, String url) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));

        final long[] totalBytes = {0};

        devTools.addListener(Network.loadingFinished(), loadingFinished -> {
            totalBytes[0] += (long) loadingFinished.getEncodedDataLength();
        });

        driver.get(url);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return totalBytes[0];
    }
}
