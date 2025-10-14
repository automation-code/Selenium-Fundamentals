package Cookies;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class CookiesUtility {

    private WebDriver driver;

    // Constructor
    public CookiesUtility(WebDriver driver) {
        this.driver = driver;
    }

    // Add Cookie with Name and Value
    public void addCookie(String name, String value) {
        try {
            Cookie cookie = new Cookie(name, value);
            driver.manage().addCookie(cookie);
            System.out.println("Cookie added successfully: " + name + " = " + value);
        } catch (Exception e) {
            System.out.println("Failed to add cookie: " + e.getMessage());
        }
    }

    // Add Cookie with Name, Value, and Domain
    public void addCookie(String name, String value, String domain) {
        try {
            Cookie cookie = new Cookie(name, value, domain, "/", null);
            driver.manage().addCookie(cookie);
            System.out.println("Cookie added with domain: " + name + " = " + value);
        } catch (Exception e) {
            System.out.println("Failed to add cookie: " + e.getMessage());
        }
    }

    // Add Cookie with All Parameters
    public void addCookie(String name, String value, String domain, String path, Date expiry) {
        try {
            Cookie cookie = new Cookie(name, value, domain, path, expiry);
            driver.manage().addCookie(cookie);
            System.out.println("Cookie added with full parameters: " + name);
        } catch (Exception e) {
            System.out.println("Failed to add cookie: " + e.getMessage());
        }
    }

    // Add Cookie with Secure and HttpOnly flags
    public void addSecureCookie(String name, String value, String domain, String path, Date expiry, boolean isSecure, boolean isHttpOnly) {
        try {
            Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly);
            driver.manage().addCookie(cookie);
            System.out.println("Secure cookie added: " + name);
        } catch (Exception e) {
            System.out.println("Failed to add secure cookie: " + e.getMessage());
        }
    }

    // Add Cookie Object
    public void addCookie(Cookie cookie) {
        try {
            driver.manage().addCookie(cookie);
            System.out.println("Cookie object added: " + cookie.getName());
        } catch (Exception e) {
            System.out.println("Failed to add cookie object: " + e.getMessage());
        }
    }

    // Get Cookie by Name
    public Cookie getCookie(String name) {
        try {
            Cookie cookie = driver.manage().getCookieNamed(name);
            if (cookie != null) {
                System.out.println("Cookie retrieved: " + name + " = " + cookie.getValue());
                return cookie;
            } else {
                System.out.println("Cookie not found: " + name);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get cookie: " + e.getMessage());
            return null;
        }
    }

    // Get Cookie Value by Name
    public String getCookieValue(String name) {
        try {
            Cookie cookie = driver.manage().getCookieNamed(name);
            if (cookie != null) {
                return cookie.getValue();
            } else {
                System.out.println("Cookie not found: " + name);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get cookie value: " + e.getMessage());
            return null;
        }
    }

    // Get All Cookies
    public Set<Cookie> getAllCookies() {
        try {
            Set<Cookie> cookies = driver.manage().getCookies();
            System.out.println("Total cookies: " + cookies.size());
            return cookies;
        } catch (Exception e) {
            System.out.println("Failed to get all cookies: " + e.getMessage());
            return null;
        }
    }

    // Delete Cookie by Name
    public void deleteCookie(String name) {
        try {
            driver.manage().deleteCookieNamed(name);
            System.out.println("Cookie deleted: " + name);
        } catch (Exception e) {
            System.out.println("Failed to delete cookie: " + e.getMessage());
        }
    }

    // Delete Cookie Object
    public void deleteCookie(Cookie cookie) {
        try {
            driver.manage().deleteCookie(cookie);
            System.out.println("Cookie object deleted: " + cookie.getName());
        } catch (Exception e) {
            System.out.println("Failed to delete cookie object: " + e.getMessage());
        }
    }

    // Delete All Cookies
    public void deleteAllCookies() {
        try {
            driver.manage().deleteAllCookies();
            System.out.println("All cookies deleted successfully");
        } catch (Exception e) {
            System.out.println("Failed to delete all cookies: " + e.getMessage());
        }
    }

    // Check if Cookie Exists
    public boolean isCookiePresent(String name) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        return cookie != null;
    }

    // Get Cookie Domain
    public String getCookieDomain(String name) {
        Cookie cookie = getCookie(name);
        return (cookie != null) ? cookie.getDomain() : null;
    }

    // Get Cookie Path
    public String getCookiePath(String name) {
        Cookie cookie = getCookie(name);
        return (cookie != null) ? cookie.getPath() : null;
    }

    // Get Cookie Expiry Date
    public Date getCookieExpiry(String name) {
        Cookie cookie = getCookie(name);
        return (cookie != null) ? cookie.getExpiry() : null;
    }

    // Check if Cookie is Secure
    public boolean isCookieSecure(String name) {
        Cookie cookie = getCookie(name);
        return (cookie != null) && cookie.isSecure();
    }

    // Check if Cookie is HttpOnly
    public boolean isCookieHttpOnly(String name) {
        Cookie cookie = getCookie(name);
        return (cookie != null) && cookie.isHttpOnly();
    }

    // Update Cookie (Delete and Re-add with new value)
    public void updateCookie(String name, String newValue) {
        try {
            Cookie oldCookie = getCookie(name);
            if (oldCookie != null) {
                deleteCookie(name);
                Cookie newCookie = new Cookie(name, newValue, oldCookie.getDomain(),
                        oldCookie.getPath(), oldCookie.getExpiry(),
                        oldCookie.isSecure(), oldCookie.isHttpOnly());
                addCookie(newCookie);
                System.out.println("Cookie updated: " + name + " = " + newValue);
            } else {
                System.out.println("Cookie not found for update: " + name);
            }
        } catch (Exception e) {
            System.out.println("Failed to update cookie: " + e.getMessage());
        }
    }

    // Print All Cookies Details
    public void printAllCookies() {
        Set<Cookie> cookies = getAllCookies();
        if (cookies != null && !cookies.isEmpty()) {
            System.out.println("\n========== All Cookies ==========");
            for (Cookie cookie : cookies) {
                printCookieDetails(cookie);
            }
            System.out.println("==================================\n");
        } else {
            System.out.println("No cookies found");
        }
    }

    // Print Single Cookie Details
    public void printCookieDetails(Cookie cookie) {
        System.out.println("Name: " + cookie.getName());
        System.out.println("Value: " + cookie.getValue());
        System.out.println("Domain: " + cookie.getDomain());
        System.out.println("Path: " + cookie.getPath());
        System.out.println("Expiry: " + (cookie.getExpiry() != null ? cookie.getExpiry() : "Session"));
        System.out.println("Secure: " + cookie.isSecure());
        System.out.println("HttpOnly: " + cookie.isHttpOnly());
        System.out.println("---");
    }

    // Save Cookies to File
    public void saveCookiesToFile(String filepath) {
        try {
            File file = new File(filepath);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            Set<Cookie> cookies = getAllCookies();
            for (Cookie cookie : cookies) {
                bufferedWriter.write(cookie.getName() + ";" +
                        cookie.getValue() + ";" +
                        cookie.getDomain() + ";" +
                        cookie.getPath() + ";" +
                        cookie.getExpiry() + ";" +
                        cookie.isSecure() + ";" +
                        cookie.isHttpOnly());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Cookies saved to file: " + filepath);
        } catch (Exception e) {
            System.out.println("Failed to save cookies to file: " + e.getMessage());
        }
    }

    // Load Cookies from File
    public void loadCookiesFromFile(String filepath) {
        try {
            File file = new File(filepath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] token = line.split(";");

                String name = token[0];
                String value = token[1];
                String domain = token[2];
                String path = token[3];
                Date expiry = null;

                // Parse expiry date if not null
                if (!token[4].equals("null")) {
                    expiry = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(token[4]);
                }

                boolean isSecure = Boolean.parseBoolean(token[5]);
                boolean isHttpOnly = Boolean.parseBoolean(token[6]);

                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure, isHttpOnly);
                driver.manage().addCookie(cookie);
            }

            bufferedReader.close();
            fileReader.close();
            System.out.println("Cookies loaded from file: " + filepath);
        } catch (Exception e) {
            System.out.println("Failed to load cookies from file: " + e.getMessage());
        }
    }

    // Verify Cookie Value
    public boolean verifyCookieValue(String name, String expectedValue) {
        String actualValue = getCookieValue(name);
        if (actualValue != null && actualValue.equals(expectedValue)) {
            System.out.println("Cookie value verification passed for: " + name);
            return true;
        } else {
            System.out.println("Cookie value verification failed. Expected: " + expectedValue + ", Actual: " + actualValue);
            return false;
        }
    }

    // Get Cookies Count
    public int getCookiesCount() {
        Set<Cookie> cookies = getAllCookies();
        return (cookies != null) ? cookies.size() : 0;
    }

    // Example Usage Method
    public static void main(String[] args) {
         // Example initialization (you would use your actual WebDriver setup)
         WebDriver driver = new ChromeDriver();
        CookiesUtility cookieUtil = new CookiesUtility(driver);
        driver.get("https://www.google.com");

         // Example 1: Add simple cookie
         cookieUtil.addCookie("username", "testuser");

         // Example 2: Get cookie value
         String value = cookieUtil.getCookieValue("username");

         // Example 3: Check if cookie exists
         if (cookieUtil.isCookiePresent("username")) {
             System.out.println("Cookie exists");
         }

         // Example 4: Update cookie
         cookieUtil.updateCookie("username", "newuser");

         // Example 5: Print all cookies
         cookieUtil.printAllCookies();

         // Example 6: Save cookies to file (for session management)
         cookieUtil.saveCookiesToFile("cookies.data");

         // Example 7: Load cookies from file (restore session)
         cookieUtil.loadCookiesFromFile("cookies.data");
         driver.navigate().refresh();

         // Example 8: Delete specific cookie
         cookieUtil.deleteCookie("username");

         // Example 9: Delete all cookies
         cookieUtil.deleteAllCookies();

         // Example 10: Verify cookie value
         boolean isValid = cookieUtil.verifyCookieValue("session", "abc123");

         driver.quit();
    }
}
