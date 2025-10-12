package DevTools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v140.performance.Performance;
import org.openqa.selenium.devtools.v140.performance.model.Metric;

import java.util.List;
import java.util.Optional;

public class PerformanceMatrics {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            DevTools devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();

            // Enable Performance
            devTools.send(Performance.enable(Optional.empty()));

            // Navigate to website
            driver.get("https://www.google.com");
            Thread.sleep(3000);

            // Get performance metrics
            List<Metric> metrics = devTools.send(Performance.getMetrics());

            for (Metric metric : metrics) {
                System.out.printf("%-40s: %.2f%n", metric.getName(), metric.getValue().doubleValue());
            }

            System.out.println("Total Metrics: " + metrics.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // Get specific performance metric
    public static Double getMetricValue(ChromeDriver driver, String metricName) {
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));

        List<Metric> metrics = devTools.send(Performance.getMetrics());

        for (Metric metric : metrics) {
            if (metric.getName().equalsIgnoreCase(metricName)) {
                return metric.getValue().doubleValue();
            }
        }

        return null;
    }
}
