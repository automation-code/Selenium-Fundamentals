package BrowserOptions;


import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriverService;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class MicrosoftEdge {

    static EdgeOptions edgeOptions = new EdgeOptions();

    // ============================================
    // 1. WINDOW & DISPLAY SETTINGS
    // ============================================

    public static void windowAndDisplaySettings() {
        // Run in headless mode (no GUI window)
        edgeOptions.addArguments("--headless");

        // Set window size
        edgeOptions.addArguments("--window-size=1920,1080");

        // Start maximized
        edgeOptions.addArguments("--start-maximized");

        // Fullscreen mode
        edgeOptions.addArguments("--kiosk");

        // Run in incognito mode
        edgeOptions.addArguments("--inprivate");

        // Disable fullscreen
        edgeOptions.addArguments("--disable-fullscreen");
    }

    // ============================================
    // 2. PERFORMANCE OPTIMIZATION
    // ============================================

    public static void performanceOptimization() {
        // Disable GPU acceleration
        edgeOptions.addArguments("--disable-gpu");

        // Disable extensions
        edgeOptions.addArguments("--disable-extensions");

        // Disable plugins
        edgeOptions.addArguments("--disable-plugins");

        // Disable images loading
        edgeOptions.addArguments("--blink-settings=imagesEnabled=false");

        // Disable Java
        edgeOptions.addArguments("--disable-java");

        // Single process mode (not recommended for production)
        edgeOptions.addArguments("--single-process");

        // Disable default apps
        edgeOptions.addArguments("--disable-default-apps");

        // Disable background timers
        edgeOptions.addArguments("--disable-backgrounding-occluded-windows");

        // Disable dev-shm-usage (important for Linux)
        edgeOptions.addArguments("--disable-dev-shm-usage");

        // Disable foreground tab timer
        edgeOptions.addArguments("--disable-backgroundtimer-throttling");
    }

    // ============================================
    // 3. SECURITY & PRIVACY
    // ============================================

    public static void securityAndPrivacy() {
        // Disable sandbox mode
        edgeOptions.addArguments("--no-sandbox");

        // Allow running insecure content
        edgeOptions.addArguments("--allow-running-insecure-content");

        // Ignore certificate errors
        edgeOptions.addArguments("--ignore-certificate-errors");

        // Disable CORS checks
        edgeOptions.addArguments("--disable-blink-features=AutomationControlled");

        // Allow insecure localhost
        edgeOptions.addArguments("--allow-insecure-localhost");

        // Disable web resources check
        edgeOptions.addArguments("--disable-web-resources");
    }

    // ============================================
    // 4. USER AGENT & IDENTIFICATION
    // ============================================

    public static void userAgentSettings() {
        // Set custom user agent
        edgeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Edge/120.0.0.0");

        // Set accept languages
        edgeOptions.addArguments("--accept-lang=en-US,en;q=0.9");
    }

    // ============================================
    // 5. DEVICE EMULATION
    // ============================================

    public static void deviceEmulation() {
        // Mobile device emulation - iPhone 12
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 12");
        edgeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Alternative: Pixel 5
        // mobileEmulation.put("deviceName", "Pixel 5");
        // edgeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Custom mobile device
        Map<String, Object> customMobile = new HashMap<>();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 375);
        deviceMetrics.put("height", 667);
        deviceMetrics.put("pixelRatio", 2.0);
        customMobile.put("deviceMetrics", deviceMetrics);
        customMobile.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_0 like Mac OS X)");
        edgeOptions.setExperimentalOption("mobileEmulation", customMobile);
    }

    // ============================================
    // 6. PREFERENCES
    // ============================================

    public static void preferenceSettings() {
        Map<String, Object> prefs = new HashMap<>();

        // Disable notifications
        prefs.put("profile.default_content_setting_values.notifications", 2);

        // Disable popups
        prefs.put("profile.default_content_settings.popups", 0);

        // Set download directory
        prefs.put("download.default_directory", "/path/to/downloads");

        // Disable password manager
        prefs.put("credentials_enable_service", false);

        // Disable plugins
        prefs.put("plugins.always_open_pdf_externally", false);

        // Disable autofill
        prefs.put("autofill.server_api.enabled", false);

        // Disable translate
        prefs.put("translate.enabled", false);

        // Disable bing chat sidebar
        prefs.put("edge.edge_sidebar_enabled", false);

        edgeOptions.setExperimentalOption("prefs", prefs);
    }

    // ============================================
    // 7. PROXY SETTINGS
    // ============================================

    public static void proxySettings() {
        // Set HTTP proxy
        edgeOptions.addArguments("--proxy-server=http://proxy.example.com:8080");

        // Set SOCKS proxy
        // edgeOptions.addArguments("--proxy-server=socks5://proxy.example.com:1080");

        // No proxy for specific hosts
        // edgeOptions.addArguments("--no-proxy-server");
    }

    // ============================================
    // 8. BINARY PATH & LANGUAGE
    // ============================================

    public static void binaryAndLanguage() {
        // Set Edge binary path (if not in standard location)
        edgeOptions.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
        // For Mac: "/Applications/Microsoft Edge.app/Contents/MacOS/Microsoft Edge"
        // For Linux: "/usr/bin/microsoft-edge"

        // Set language
        edgeOptions.addArguments("--lang=en-US");
    }

    // ============================================
    // 9. LOGGING & DEBUGGING
    // ============================================

    public static void loggingSettings() {
        // Enable logging
        edgeOptions.addArguments("--enable-logging");

        // Verbose logging
        edgeOptions.addArguments("--v=1");

        // Disable logging
        // edgeOptions.addArguments("--disable-logging");
    }

    // ============================================
    // 10. NETWORK & CONNECTIVITY
    // ============================================

    public static void networkSettings() {
        // Simulate offline mode
        edgeOptions.addArguments("--offline");

        // Disable background networking
        edgeOptions.addArguments("--disable-background-networking");

        // Disable device discovery notifications
        edgeOptions.addArguments("--disable-device-discovery-notifications");

        // Disable preconnect
        edgeOptions.addArguments("--disable-preconnect");

        // Disable predictions
        edgeOptions.addArguments("--disable-predictions");
    }

    // ============================================
    // 11. SYNC & SERVICES
    // ============================================

    public static void syncAndServices() {
        // Disable sync
        edgeOptions.addArguments("--disable-sync");

        // Disable cloud print
        edgeOptions.addArguments("--disable-cloud-print");

        // Disable metrics reporting
        edgeOptions.addArguments("--disable-metrics-repo-service");

        // Disable search suggestions
        edgeOptions.addArguments("--disable-search-suggestions");
    }

    // ============================================
    // 12. EDGE-SPECIFIC OPTIONS
    // ============================================

    public static void edgeSpecificOptions() {
        // Disable Bing search integration
        edgeOptions.addArguments("--disable-bing-search");

        // Disable collections feature
        edgeOptions.addArguments("--disable-collections");

        // Disable shopping features
        edgeOptions.addArguments("--disable-shopping");

        // Disable price comparison
        edgeOptions.addArguments("--disable-price-comparison");

        // Disable sidebar
        edgeOptions.addArguments("--disable-sidebar");

        // Disable edge features
        edgeOptions.addArguments("--disable-features=TranslateUI");
    }

    // ============================================
    // 13. ADDITIONAL OPTIONS
    // ============================================

    public static void additionalOptions() {
        // Disable first-run UI
        edgeOptions.addArguments("--no-first-run");

        // Disable default browser check
        edgeOptions.addArguments("--no-default-browser-check");

        // Disable component update
        edgeOptions.addArguments("--disable-component-update");

        // Allow expired certificates
        edgeOptions.addArguments("--allow-expired-certs");

        // Disable background timer throttling
        edgeOptions.addArguments("--disable-backgroundtimer-throttling");
    }

    // ============================================
    // 14. CAPABILITIES
    // ============================================

    public static void capabilities() {
        // Set browser name
        edgeOptions.setCapability("browserName", "msedge");

        // Set accept insecure certificates
        edgeOptions.setCapability("acceptInsecureCerts", true);

        // Set page load strategy
        // edgeOptions.setCapability("pageLoadStrategy", "eager");
        // edgeOptions.setCapability("pageLoadStrategy", "normal");
        // edgeOptions.setCapability("pageLoadStrategy", "none");

        // Set platform
        // edgeOptions.setCapability("platformName", "WINDOWS");
        // edgeOptions.setCapability("platformName", "MAC");
        // edgeOptions.setCapability("platformName", "LINUX");
    }

    // ============================================
    // EDGE DRIVER INITIALIZATION
    // ============================================

    public static EdgeDriver createBasicDriver() {
        return new EdgeDriver(edgeOptions);
    }

    public static EdgeDriver createDriverWithService(String msedgedriverPath) {
        EdgeDriverService service = new EdgeDriverService.Builder()
                .usingDriverExecutable(new File(msedgedriverPath))
                .usingAnyFreePort()
                .build();

        return new EdgeDriver(service, edgeOptions);
    }

    // ============================================
    // PRACTICAL EXAMPLES
    // ============================================

    // Example 1: Headless mode for testing
    public static EdgeDriver createHeadlessDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");

        return new EdgeDriver(options);
    }

    // Example 2: Mobile testing
    public static EdgeDriver createMobileDriver() {
        EdgeOptions options = new EdgeOptions();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 5");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        options.addArguments("--headless");

        return new EdgeDriver(options);
    }

    // Example 3: Production-safe driver
    public static EdgeDriver createProductionDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--window-size=1920,1080");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);

        return new EdgeDriver(options);
    }

    // Example 4: Web scraping with optimization
    public static EdgeDriver createScrapingDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new EdgeDriver(options);
    }

    // Example 5: InPrivate (Incognito) mode
    public static EdgeDriver createPrivateDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        options.addArguments("--disable-extensions");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        return new EdgeDriver(options);
    }

    // Example 6: Testing with specific window size
    public static EdgeDriver createFixedSizeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=1280,720");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        return new EdgeDriver(options);
    }

    // Example 7: Disable all notifications and popups
    public static EdgeDriver createQuietDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);

        return new EdgeDriver(options);
    }

    // Example 8: Testing with proxy
    public static EdgeDriver createProxyDriver(String proxyUrl) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--proxy-server=" + proxyUrl);
        options.addArguments("--no-sandbox");

        return new EdgeDriver(options);
    }

    // Example 9: CI/CD pipeline optimized driver
    public static EdgeDriver createCICDDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--window-size=1920,1080");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);

        return new EdgeDriver(options);
    }

    // Example 10: Disable all Edge services and features
    public static EdgeDriver createMinimalDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-sync");
        options.addArguments("--disable-cloud-print");
        options.addArguments("--disable-metrics-repo-service");
        options.addArguments("--disable-bing-search");
        options.addArguments("--disable-sidebar");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);

        return new EdgeDriver(options);
    }

    public static void main(String[] args) {
        EdgeDriver driver = createFixedSizeDriver();

        try {
            driver.get("https://www.google.com");
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);

        } finally {
            driver.quit();
        }
    }
}
