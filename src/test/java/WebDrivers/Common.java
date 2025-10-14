package WebDrivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Common {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        // Opens the specified URL in the current browser window.
        driver.get("https://testautomationpractice.blogspot.com/");

        // Returns an instance of Options to manage timeouts, cookies, and windows.
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        // Returns the current page URL.
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // Returns the page title.
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        // Returns the HTML source of the current page.
        String pageSource = driver.getPageSource();
        System.out.println("Page Source: " + pageSource);

        /*
        Returns the current window handle (unique ID).
        String currentWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("button[onclick='myFunction()']")).click();
        driver.switchTo().window(currentWindow);
         */

        // Returns all open window handles.
        // Approach - 1
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("button[onclick='myFunction()']")).click();
        Set<String> allWindow = driver.getWindowHandles();
        Iterator<String> itr = allWindow.iterator();

        while (itr.hasNext()) {
            String childWindow = itr.next();
            driver.switchTo().window(childWindow);
            System.out.println(driver.getTitle());
        }

        driver.switchTo().window(parentWindow);
        System.out.println(driver.getTitle());

        /*
        Approach - 2
        driver.findElement(By.cssSelector("button[onclick='myFunction()']")).click();
        Set<String> getWindows = driver.getWindowHandles();
        List<String> listWindow = new ArrayList<>(getWindows);
        String win1 = listWindow.get(0);
        String win2 = listWindow.get(1);

        driver.switchTo().window(win2);
        System.out.println(driver.getTitle());
        driver.switchTo().window(win1);
        System.out.println(driver.getTitle());
        */

        // Navigates to a specific URL.
        driver.navigate().to("https://www.google.com");
        // Moves back in the browser history.
        driver.navigate().back();
        // Moves forward in the browser history.
        driver.navigate().forward();
        // Refreshes the current page.
        driver.navigate().refresh();

        // Closes the current browser window.
        driver.close();

        // Closes all browser windows and ends the WebDriver session.
        driver.quit();
    }
}
