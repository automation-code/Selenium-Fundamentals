package BrowserOptions;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.HashMap;
import java.util.Map;


public class GoogleChrome {

    static ChromeOptions chromeOptions = new ChromeOptions();

    // ============================================
    // 1. WINDOW & DISPLAY SETTINGS
    // ============================================

    public static void windowAndDisplaySettings() {
        // Run in headless mode (no GUI window)
        chromeOptions.addArguments("--headless");

        // Set window size
        chromeOptions.addArguments("--window-size=1920,1080");

        // Start maximized
        chromeOptions.addArguments("--start-maximized");

        // Fullscreen mode
        chromeOptions.addArguments("--kiosk");

        // Run in incognito mode
        chromeOptions.addArguments("--incognito");

        // Disable fullscreen
        chromeOptions.addArguments("--disable-fullscreen");
    }

    // ============================================
    // 2. PERFORMANCE OPTIMIZATION
    // ============================================

    public static void performanceOptimization() {
        // Disable GPU acceleration
        chromeOptions.addArguments("--disable-gpu");

        // Disable extensions
        chromeOptions.addArguments("--disable-extensions");

        // Disable plugins
        chromeOptions.addArguments("--disable-plugins");

        // Disable images loading
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");

        // Disable Java
        chromeOptions.addArguments("--disable-java");

        // Single process mode (not recommended for production)
        chromeOptions.addArguments("--single-process");

        // Disable default apps
        chromeOptions.addArguments("--disable-default-apps");

        // Disable background timers
        chromeOptions.addArguments("--disable-backgrounding-occluded-windows");

        // Disable dev-shm-usage (important for Linux)
        chromeOptions.addArguments("--disable-dev-shm-usage");
    }

    // ============================================
    // 3. SECURITY & PRIVACY
    // ============================================

    public static void securityAndPrivacy() {
        // Disable sandbox mode
        chromeOptions.addArguments("--no-sandbox");

        // Allow running insecure content
        chromeOptions.addArguments("--allow-running-insecure-content");

        // Ignore certificate errors
        chromeOptions.addArguments("--ignore-certificate-errors");

        // Disable CORS checks
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

        // Disable web resources check
        chromeOptions.addArguments("--disable-web-resources");

        // Allow insecure localhost
        chromeOptions.addArguments("--allow-insecure-localhost");
    }

    // ============================================
    // 4. USER AGENT & IDENTIFICATION
    // ============================================

    public static void userAgentSettings() {
        // Set custom user agent
        chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        // Set accept languages
        chromeOptions.addArguments("--accept-lang=en-US,en;q=0.9");
    }

    // ============================================
    // 5. DEVICE EMULATION
    // ============================================

    public static void deviceEmulation() {
        // Mobile device emulation - iPhone 12
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 12");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Alternative: Pixel 5
        // mobileEmulation.put("deviceName", "Pixel 5");
        // chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Custom mobile device
        Map<String, Object> customMobile = new HashMap<>();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 375);
        deviceMetrics.put("height", 667);
        deviceMetrics.put("pixelRatio", 2.0);
        customMobile.put("deviceMetrics", deviceMetrics);
        customMobile.put("userAgent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_0 like Mac OS X)");
        chromeOptions.setExperimentalOption("mobileEmulation", customMobile);
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

        chromeOptions.setExperimentalOption("prefs", prefs);
    }

    // ============================================
    // 7. PROXY SETTINGS
    // ============================================

    public static void proxySettings() {
        // Set HTTP proxy
        chromeOptions.addArguments("--proxy-server=http://proxy.example.com:8080");

        // Set SOCKS proxy
        // chromeOptions.addArguments("--proxy-server=socks5://proxy.example.com:1080");

        // No proxy for specific hosts
        // chromeOptions.addArguments("--no-proxy-server");
    }

    // ============================================
    // 8. BINARY PATH & LANGUAGE
    // ============================================

    public static void binaryAndLanguage() {
        // Set Chrome binary path (if not in standard location)
        chromeOptions.setBinary("/usr/bin/google-chrome");
        // For Windows: "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"
        // For Mac: "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"

        // Set language
        chromeOptions.addArguments("--lang=en-US");
    }

    // ============================================
    // 9. LOGGING & DEBUGGING
    // ============================================

    public static void loggingSettings() {
        // Enable logging
        chromeOptions.addArguments("--enable-logging");

        // Verbose logging
        chromeOptions.addArguments("--v=1");

        // Disable logging
        // chromeOptions.addArguments("--disable-logging");
    }

    // ============================================
    // 10. NETWORK & CONNECTIVITY
    // ============================================

    public static void networkSettings() {
        // Simulate offline mode
        chromeOptions.addArguments("--offline");

        // Disable background networking
        chromeOptions.addArguments("--disable-background-networking");

        // Disable device discovery notifications
        chromeOptions.addArguments("--disable-device-discovery-notifications");

        // Disable preconnect
        chromeOptions.addArguments("--disable-preconnect");

        // Disable predictions
        chromeOptions.addArguments("--disable-predictions");
    }

    // ============================================
    // 11. SYNC & SERVICES
    // ============================================

    public static void syncAndServices() {
        // Disable sync
        chromeOptions.addArguments("--disable-sync");

        // Disable cloud print
        chromeOptions.addArguments("--disable-cloud-print");

        // Disable metrics reporting
        chromeOptions.addArguments("--disable-metrics-repo-service");
    }

    // ============================================
    // 12. ADDITIONAL OPTIONS
    // ============================================

    public static void additionalOptions() {
        // Disable first-run UI
        chromeOptions.addArguments("--no-first-run");

        // Disable default browser check
        chromeOptions.addArguments("--no-default-browser-check");

        // Disable component update
        chromeOptions.addArguments("--disable-component-update");

        // Disable background timer throttling
        chromeOptions.addArguments("--disable-backgroundtimer-throttling");
    }

    // ============================================
    // CHROME DRIVER INITIALIZATION
    // ============================================

    public static ChromeDriver createBasicDriver() {
        return new ChromeDriver(chromeOptions);
    }

    public static ChromeDriver createDriverWithService(String chromeDriverPath) {
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new java.io.File(chromeDriverPath))
                .usingAnyFreePort()
                .build();

        return new ChromeDriver(service, chromeOptions);
    }

    // ============================================
    // PRACTICAL EXAMPLES
    // ============================================

    // Example 1: Headless mode for testing
    public static ChromeDriver createHeadlessDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    // Example 2: Mobile testing
    public static ChromeDriver createMobileDriver() {
        ChromeOptions options = new ChromeOptions();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 5");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        options.addArguments("--headless");

        return new ChromeDriver(options);
    }

    // Example 3: Production-safe driver
    public static ChromeDriver createProductionDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--window-size=1920,1080");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("credentials_enable_service", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    // Example 4: Web scraping with optimization
    public static ChromeDriver createScrapingDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    // Example 5: Testing with specific window size
    public static ChromeDriver createFixedSizeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1280,720");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }

    // Example 6: Incognito mode for privacy testing
    public static ChromeDriver createIncognitoDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    public static void main(String[] args) {
        ChromeDriver driver = createHeadlessDriver();
        networkSettings();
        try {
            driver.get("https://www.google.com");
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);
        } finally {
            driver.quit();
        }
    }
}

