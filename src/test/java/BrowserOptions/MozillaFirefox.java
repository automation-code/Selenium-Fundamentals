package BrowserOptions;

import org.openqa.selenium.firefox.*;

import java.util.HashMap;
import java.util.Map;


public class MozillaFirefox {

    static FirefoxOptions firefoxOptions = new FirefoxOptions();

    // ============================================
    // 1. WINDOW & DISPLAY SETTINGS
    // ============================================

    public static void windowAndDisplaySettings() {
        // Run in headless mode (no GUI window)
        firefoxOptions.addArguments("--headless");

        // Set window size
        firefoxOptions.addArguments("--width=1920");
        firefoxOptions.addArguments("--height=1080");

        // Start maximized
        firefoxOptions.addArguments("-maximize");

        // Run in private/incognito mode
        firefoxOptions.addArguments("-private");

        // Enable UI
        firefoxOptions.addArguments("--no-remote");
    }

    // ============================================
    // 2. PERFORMANCE OPTIMIZATION
    // ============================================

    public static void performanceOptimization() {
        // Disable JavaScript
        // firefoxOptions.setPageLoadStrategy("eager");

        // Set page load strategy
        // firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        // firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        // firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        // Disable plugins
        firefoxOptions.addArguments("--disable-plugins");

        // Disable image loading
        firefoxOptions.addPreference("permissions.default.image", 2);

        // Disable CSS loading
        firefoxOptions.addPreference("permissions.default.stylesheet", 2);

        // Disable Java
        firefoxOptions.addPreference("plugin.state.java", 0);
    }

    // ============================================
    // 3. SECURITY & PRIVACY
    // ============================================

    public static void securityAndPrivacy() {
        // Disable SSL verification
        firefoxOptions.setAcceptInsecureCerts(true);

        // Disable mixed content warning
        firefoxOptions.addPreference("security.mixed_content.block_active_content", false);
        firefoxOptions.addPreference("security.mixed_content.block_display_content", false);

        // Disable certificate warning
        firefoxOptions.addPreference("browser.startup.homepage_override.mstone", "ignore");

        // Disable auto-update
        firefoxOptions.addPreference("app.update.auto", false);

        // Disable tracking protection
        firefoxOptions.addPreference("privacy.trackingprotection.enabled", false);

        // Disable privacy mode
        firefoxOptions.addPreference("browser.privatebrowsing.autostart", false);
    }

    // ============================================
    // 4. USER AGENT & IDENTIFICATION
    // ============================================

    public static void userAgentSettings() {
        // Set custom user agent
        firefoxOptions.addPreference("general.useragent.override",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0");

        // Set language
        firefoxOptions.addPreference("intl.accept_languages", "en-US,en;q=0.9");
    }

    // ============================================
    // 5. DEVICE EMULATION
    // ============================================

    public static void deviceEmulation() {
        // Mobile device emulation - Firefox on Android
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 5");
        firefoxOptions.setCapability("mobileEmulation", mobileEmulation);

        // Custom mobile device
        Map<String, Object> customMobile = new HashMap<>();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 375);
        deviceMetrics.put("height", 667);
        deviceMetrics.put("pixelRatio", 2.0);
        customMobile.put("deviceMetrics", deviceMetrics);
        customMobile.put("userAgent", "Mozilla/5.0 (Android 11; Mobile; rv:91.0) Gecko/91.0 Firefox/91.0");
        firefoxOptions.setCapability("mobileEmulation", customMobile);
    }

    // ============================================
    // 6. PREFERENCES - NOTIFICATIONS & POPUPS
    // ============================================

    public static void notificationAndPopupSettings() {
        // Disable notifications
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);

        // Disable permission requests
        firefoxOptions.addPreference("permissions.default.desktop-notification", 2);

        // Disable geolocation requests
        firefoxOptions.addPreference("permissions.default.geo", 2);

        // Disable microphone requests
        firefoxOptions.addPreference("permissions.default.microphone", 2);

        // Disable camera requests
        firefoxOptions.addPreference("permissions.default.camera", 2);

        // Block popups
        firefoxOptions.addPreference("dom.popup_allowed_events", "");

        // Disable auto-opening downloads
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
    }

    // ============================================
    // 7. DOWNLOAD SETTINGS
    // ============================================

    public static void downloadSettings() {
        // Set download directory
        firefoxOptions.addPreference("browser.download.folderList", 2);
        firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
        firefoxOptions.addPreference("browser.download.dir", "/path/to/downloads");

        // Auto-download file types
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf,application/x-msexcel,application/excel,application/x-excel," +
                        "application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword," +
                        "application/xml");

        // Disable download manager
        firefoxOptions.addPreference("browser.download.manager.showAlertOnComplete", false);
    }

    // ============================================
    // 8. PROXY SETTINGS
    // ============================================

    public static void proxySettings() {
        // Set HTTP proxy
        firefoxOptions.addPreference("network.proxy.type", 1);
        firefoxOptions.addPreference("network.proxy.http", "proxy.example.com");
        firefoxOptions.addPreference("network.proxy.http_port", 8080);

        // Set HTTPS proxy
        firefoxOptions.addPreference("network.proxy.ssl", "proxy.example.com");
        firefoxOptions.addPreference("network.proxy.ssl_port", 8080);

        // Set SOCKS proxy
        // firefoxOptions.addPreference("network.proxy.type", 1);
        // firefoxOptions.addPreference("network.proxy.socks", "proxy.example.com");
        // firefoxOptions.addPreference("network.proxy.socks_port", 1080);

        // Set no proxy for hosts
        // firefoxOptions.addPreference("network.proxy.no_proxies_on", "localhost,127.0.0.1");
    }

    // ============================================
    // 9. BROWSER BEHAVIOR
    // ============================================

    public static void browserBehavior() {
        // Disable homepage
        firefoxOptions.addPreference("browser.startup.homepage_override.mstone", "ignore");
        firefoxOptions.addPreference("startup.homepage_welcome_url", "");
        firefoxOptions.addPreference("startup.homepage_welcome_url.additional", "");

        // Disable pocket
        firefoxOptions.addPreference("extensions.pocket.enabled", false);

        // Disable telemetry
        firefoxOptions.addPreference("datareporting.policy.dataSubmissionPolicyAcceptedVersion", 2);
        firefoxOptions.addPreference("datareporting.policy.dataSubmissionPolicyNotifiedTime", "1");

        // Disable crash reports
        firefoxOptions.addPreference("breakpad.reportURL", "");

        // Disable health report
        firefoxOptions.addPreference("datareporting.healthreport.uploadEnabled", false);
    }

    // ============================================
    // 10. PERFORMANCE - CACHING & STORAGE
    // ============================================

    public static void cachingAndStorage() {
        // Disable cache
        firefoxOptions.addPreference("browser.cache.disk.enable", false);
        firefoxOptions.addPreference("browser.cache.memory.enable", false);

        // Clear cookies on exit
        firefoxOptions.addPreference("network.cookie.lifetimePolicy", 2);

        // Clear history on exit
        firefoxOptions.addPreference("privacy.history.custom", true);
        firefoxOptions.addPreference("privacy.clearOnShutdown.cache", true);
        firefoxOptions.addPreference("privacy.clearOnShutdown.cookies", true);
        firefoxOptions.addPreference("privacy.clearOnShutdown.downloads", true);
        firefoxOptions.addPreference("privacy.clearOnShutdown.formdata", true);
        firefoxOptions.addPreference("privacy.clearOnShutdown.history", true);

        // Disable offline storage
        firefoxOptions.addPreference("dom.storage.enabled", false);
    }

    // ============================================
    // 11. BINARY PATH & LOGGING
    // ============================================

    public static void binaryPathAndLogging() {
        firefoxOptions.setBinary("/usr/bin/firefox");
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.DEBUG);
        // firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);
        // firefoxOptions.setLogLevel(FirefoxDriverLogLevel.INFO);
        // firefoxOptions.setLogLevel(FirefoxDriverLogLevel.WARN);
        // firefoxOptions.setLogLevel(FirefoxDriverLogLevel.ERROR);
    }

    // ============================================
    // 12. FIREFOX PROFILE
    // ============================================

    public static void usingFirefoxProfile() {
        // Create a custom Firefox profile
        FirefoxProfile profile = new FirefoxProfile();

        // Add preferences to profile
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", "/path/to/downloads");

        // Add extensions to profile
        try {
            // profile.addExtension(new File("/path/to/extension.xpi"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set profile to options
        firefoxOptions.setProfile(profile);
    }

    // ============================================
    // 13. ARGUMENTS
    // ============================================

    public static void additionalArguments() {
        // Safe mode
        firefoxOptions.addArguments("-safe-mode");

        // Allow downgrade
        firefoxOptions.addArguments("-allow-downgrade");

        // Ignore private browsing mode
        firefoxOptions.addArguments("-new-tab");

        // Create new profile
        // firefoxOptions.addArguments("-profile", "/path/to/profile");

        // Use existing profile
        // firefoxOptions.addArguments("-profile", "default");
    }

    // ============================================
    // 14. CAPABILITIES
    // ============================================

    public static void capabilities() {
        // Set browser name
        firefoxOptions.setCapability("browserName", "firefox");

        // Set accept insecure certificates
        firefoxOptions.setCapability("acceptInsecureCerts", true);

        // Set page load strategy
        // firefoxOptions.setCapability("pageLoadStrategy", "eager");
        // firefoxOptions.setCapability("pageLoadStrategy", "normal");
        // firefoxOptions.setCapability("pageLoadStrategy", "none");

        // Set platform
        // firefoxOptions.setCapability("platformName", "WINDOWS");
        // firefoxOptions.setCapability("platformName", "MAC");
        // firefoxOptions.setCapability("platformName", "LINUX");
    }

    // ============================================
    // FIREFOX DRIVER INITIALIZATION
    // ============================================

    public static FirefoxDriver createBasicDriver() {
        return new FirefoxDriver(firefoxOptions);
    }

    // ============================================
    // PRACTICAL EXAMPLES
    // ============================================

    // Example 1: Headless mode for testing
    public static FirefoxDriver createHeadlessDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        return new FirefoxDriver(options);
    }

    // Example 2: Mobile testing
    public static FirefoxDriver createMobileDriver() {
        FirefoxOptions options = new FirefoxOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 5");
        options.setCapability("mobileEmulation", mobileEmulation);

        return new FirefoxDriver(options);
    }

    // Example 3: Production-safe driver
    public static FirefoxDriver createProductionDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.addPreference("permissions.default.desktop-notification", 2);
        options.addPreference("permissions.default.geo", 2);
        options.addPreference("permissions.default.microphone", 2);
        options.addPreference("permissions.default.camera", 2);
        options.addPreference("dom.popup_allowed_events", "");

        return new FirefoxDriver(options);
    }

    // Example 4: Web scraping with optimization
    public static FirefoxDriver createScrapingDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addPreference("permissions.default.image", 2);
        options.addPreference("browser.cache.disk.enable", false);
        options.addPreference("browser.cache.memory.enable", false);
        options.addPreference("general.useragent.override",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:91.0) Gecko/20100101 Firefox/91.0");

        return new FirefoxDriver(options);
    }

    // Example 5: Private browsing mode
    public static FirefoxDriver createPrivateDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        return new FirefoxDriver(options);
    }

    // Example 6: Custom window size
    public static FirefoxDriver createFixedSizeDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1280");
        options.addArguments("--height=720");

        return new FirefoxDriver(options);
    }

    // Example 7: Disable all notifications and popups
    public static FirefoxDriver createQuietDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");

        // Disable all notifications
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("permissions.default.desktop-notification", 2);
        options.addPreference("permissions.default.geo", 2);
        options.addPreference("permissions.default.microphone", 2);
        options.addPreference("permissions.default.camera", 2);

        // Disable popups
        options.addPreference("dom.popup_allowed_events", "");

        // Disable telemetry
        options.addPreference("datareporting.policy.dataSubmissionPolicyAcceptedVersion", 2);

        return new FirefoxDriver(options);
    }

    // Example 8: Custom profile with downloads
    public static FirefoxDriver createDownloadDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.download.dir", System.getProperty("user.home") + "/Downloads");
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");

        return new FirefoxDriver(options);
    }

    public static void main(String[] args) {
        FirefoxDriver driver = createHeadlessDriver();

        try {
            driver.get("https://www.google.com");
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);
        } finally {
            driver.quit();
        }
    }
}
