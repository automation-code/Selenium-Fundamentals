package WaitsTimeouts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ThreadSleep {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        Thread.sleep(3000);
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
        Thread.sleep(3000);
        driver.quit();
    }
}
