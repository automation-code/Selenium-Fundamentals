package Cookies;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Cookies {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        // Returns all cookies.
        Set<Cookie> getAllCookies = driver.manage().getCookies();

        for (Cookie cookie : getAllCookies) {
            System.out.println("Displayed all cookies from the browser");
            System.out.println(cookie.getName() + " = " + cookie.getValue());
        }

        // Returns cookie by name.
        Cookie getCookieByName = driver.manage().getCookieNamed("NID");
        System.out.println(getCookieByName);

        // Adds a new cookie.
        Cookie addCookie = new Cookie("Google", "Search Engine");
        driver.manage().addCookie(addCookie);
        System.out.println(addCookie);

        // Deletes cookie by name.
        driver.manage().deleteCookieNamed("Google");

        // Deletes specific cookie.
        driver.manage().deleteCookie(addCookie);

        // Deletes all cookies.
        driver.manage().deleteAllCookies();

        driver.quit();
    }
}
