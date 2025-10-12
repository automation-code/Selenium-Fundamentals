import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v140.network.Network;
import org.openqa.selenium.devtools.v140.log.Log;
import org.openqa.selenium.devtools.v140.performance.Performance;
import org.openqa.selenium.devtools.v140.performance.model.Metric;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.Base64;

/**
 * Complete Selenium Library - All-in-One Utility Class
 * Contains all essential Selenium methods for test automation
 *
 * @author Your Name
 * @version 1.0
 */
public class SeleniumLibrary {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Actions actions;
    private DevTools devTools;

    // ========================================
    // 1. CONSTRUCTOR & INITIALIZATION
    // ========================================

    public SeleniumLibrary(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    public SeleniumLibrary(WebDriver driver, int waitTimeInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        this.js = (JavascriptExecutor) driver;
        this.actions = new Actions(driver);
    }

    // ========================================
    // 2. BROWSER OPERATIONS
    // ========================================

    /**
     * Initialize Chrome browser
     */
    public static WebDriver initChrome() {
        return new ChromeDriver();
    }

    /**
     * Initialize Firefox browser
     */
    public static WebDriver initFirefox() {
        return new FirefoxDriver();
    }

    /**
     * Initialize Edge browser
     */
    public static WebDriver initEdge() {
        return new EdgeDriver();
    }

    /**
     * Initialize Safari browser
     */
    public static WebDriver initSafari() {
        return new SafariDriver();
    }

    /**
     * Open URL
     */
    public void openURL(String url) {
        driver.get(url);
    }

    /**
     * Navigate to URL
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    /**
     * Navigate back
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * Navigate forward
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * Refresh page
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**
     * Maximize window
     */
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    /**
     * Minimize window
     */
    public void minimizeWindow() {
        driver.manage().window().minimize();
    }

    /**
     * Set window size
     */
    public void setWindowSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    /**
     * Get current URL
     */
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get page source
     */
    public String getPageSource() {
        return driver.getPageSource();
    }

    /**
     * Close current window
     */
    public void closeWindow() {
        driver.close();
    }

    /**
     * Quit browser
     */
    public void quitBrowser() {
        driver.quit();
    }

    // ========================================
    // 3. ELEMENT OPERATIONS
    // ========================================

    /**
     * Find element by locator
     */
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Find elements by locator
     */
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Click element
     */
    public void click(By locator) {
        findElement(locator).click();
    }

    /**
     * Click element with wait
     */
    public void clickWithWait(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Click using JavaScript
     */
    public void clickJS(By locator) {
        WebElement element = findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Send keys to element
     */
    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    /**
     * Send keys with clear
     */
    public void sendKeysWithClear(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Send keys using JavaScript
     */
    public void sendKeysJS(By locator, String text) {
        WebElement element = findElement(locator);
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

    /**
     * Clear element
     */
    public void clear(By locator) {
        findElement(locator).clear();
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Get attribute value
     */
    public String getAttribute(By locator, String attribute) {
        return findElement(locator).getAttribute(attribute);
    }

    /**
     * Get CSS value
     */
    public String getCSSValue(By locator, String property) {
        return findElement(locator).getCssValue(property);
    }

    /**
     * Check if element is displayed
     */
    public boolean isDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public boolean isEnabled(By locator) {
        return findElement(locator).isEnabled();
    }

    /**
     * Check if element is selected
     */
    public boolean isSelected(By locator) {
        return findElement(locator).isSelected();
    }

    /**
     * Submit form
     */
    public void submit(By locator) {
        findElement(locator).submit();
    }

    // ========================================
    // 4. WAIT OPERATIONS
    // ========================================

    /**
     * Wait for element to be visible
     */
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be present
     */
    public WebElement waitForPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be invisible
     */
    public boolean waitForInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element
     */
    public boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait for alert to be present
     */
    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait for URL to contain
     */
    public boolean waitForURLContains(String urlPart) {
        return wait.until(ExpectedConditions.urlContains(urlPart));
    }

    /**
     * Wait for title to be
     */
    public boolean waitForTitle(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Implicit wait
     */
    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Hard wait (Thread.sleep)
     */
    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Wait for page load
     */
    public void waitForPageLoad() {
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
    }

    // ========================================
    // 5. DROPDOWN OPERATIONS
    // ========================================

    /**
     * Select dropdown by visible text
     */
    public void selectByVisibleText(By locator, String text) {
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
    }

    /**
     * Select dropdown by value
     */
    public void selectByValue(By locator, String value) {
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    /**
     * Select dropdown by index
     */
    public void selectByIndex(By locator, int index) {
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);
    }

    /**
     * Get all dropdown options
     */
    public List<WebElement> getAllDropdownOptions(By locator) {
        Select select = new Select(findElement(locator));
        return select.getOptions();
    }

    /**
     * Get selected dropdown option
     */
    public String getSelectedOption(By locator) {
        Select select = new Select(findElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Deselect all options (multi-select)
     */
    public void deselectAll(By locator) {
        Select select = new Select(findElement(locator));
        select.deselectAll();
    }

    // ========================================
    // 6. CHECKBOX & RADIO OPERATIONS
    // ========================================

    /**
     * Check checkbox if not already checked
     */
    public void checkCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Uncheck checkbox if checked
     */
    public void uncheckCheckbox(By locator) {
        WebElement checkbox = findElement(locator);
        if (checkbox.isSelected()) {
            checkbox.click();
        }
    }

    /**
     * Select radio button
     */
    public void selectRadio(By locator) {
        WebElement radio = findElement(locator);
        if (!radio.isSelected()) {
            radio.click();
        }
    }

    /**
     * Check if checkbox/radio is checked
     */
    public boolean isChecked(By locator) {
        return findElement(locator).isSelected();
    }

    // ========================================
    // 7. ALERT OPERATIONS
    // ========================================

    /**
     * Accept alert
     */
    public void acceptAlert() {
        waitForAlert().accept();
    }

    /**
     * Dismiss alert
     */
    public void dismissAlert() {
        waitForAlert().dismiss();
    }

    /**
     * Get alert text
     */
    public String getAlertText() {
        return waitForAlert().getText();
    }

    /**
     * Send keys to alert
     */
    public void sendKeysToAlert(String text) {
        waitForAlert().sendKeys(text);
    }

    /**
     * Check if alert is present
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // ========================================
    // 8. FRAME OPERATIONS
    // ========================================

    /**
     * Switch to frame by index
     */
    public void switchToFrameByIndex(int index) {
        driver.switchTo().frame(index);
    }

    /**
     * Switch to frame by name or ID
     */
    public void switchToFrameByNameOrId(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    /**
     * Switch to frame by WebElement
     */
    public void switchToFrame(By locator) {
        WebElement frame = findElement(locator);
        driver.switchTo().frame(frame);
    }

    /**
     * Switch to parent frame
     */
    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    /**
     * Switch to default content
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // ========================================
    // 9. WINDOW OPERATIONS
    // ========================================

    /**
     * Get current window handle
     */
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    /**
     * Get all window handles
     */
    public Set<String> getAllWindowHandles() {
        return driver.getWindowHandles();
    }

    /**
     * Switch to window by title
     */
    public void switchToWindowByTitle(String title) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(title)) {
                break;
            }
        }
    }

    /**
     * Switch to window by index
     */
    public void switchToWindowByIndex(int index) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(index));
    }

    /**
     * Switch to new window
     */
    public void switchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    /**
     * Close current window and switch to previous
     */
    public void closeCurrentWindowAndSwitch() {
        String originalWindow = driver.getWindowHandle();
        driver.close();
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // ========================================
    // 10. MOUSE ACTIONS
    // ========================================

    /**
     * Mouse hover on element
     */
    public void hover(By locator) {
        WebElement element = findElement(locator);
        actions.moveToElement(element).perform();
    }

    /**
     * Double click element
     */
    public void doubleClick(By locator) {
        WebElement element = findElement(locator);
        actions.doubleClick(element).perform();
    }

    /**
     * Right click element
     */
    public void rightClick(By locator) {
        WebElement element = findElement(locator);
        actions.contextClick(element).perform();
    }

    /**
     * Drag and drop
     */
    public void dragAndDrop(By source, By target) {
        WebElement sourceElement = findElement(source);
        WebElement targetElement = findElement(target);
        actions.dragAndDrop(sourceElement, targetElement).perform();
    }

    /**
     * Click and hold
     */
    public void clickAndHold(By locator) {
        WebElement element = findElement(locator);
        actions.clickAndHold(element).perform();
    }

    /**
     * Release element
     */
    public void release(By locator) {
        WebElement element = findElement(locator);
        actions.release(element).perform();
    }

    // ========================================
    // 11. KEYBOARD ACTIONS
    // ========================================

    /**
     * Press key
     */
    public void pressKey(Keys key) {
        actions.sendKeys(key).perform();
    }

    /**
     * Press key on element
     */
    public void pressKeyOnElement(By locator, Keys key) {
        WebElement element = findElement(locator);
        actions.sendKeys(element, key).perform();
    }

    /**
     * Press multiple keys
     */
    public void pressKeys(Keys... keys) {
        actions.sendKeys(keys).perform();
    }

    /**
     * Key down
     */
    public void keyDown(Keys key) {
        actions.keyDown(key).perform();
    }

    /**
     * Key up
     */
    public void keyUp(Keys key) {
        actions.keyUp(key).perform();
    }

    /**
     * Send keys with CTRL+A
     */
    public void selectAll(By locator) {
        WebElement element = findElement(locator);
        actions.keyDown(Keys.CONTROL).sendKeys(element, "a").keyUp(Keys.CONTROL).perform();
    }

    /**
     * Copy text (CTRL+C)
     */
    public void copy() {
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
    }

    /**
     * Paste text (CTRL+V)
     */
    public void paste(By locator) {
        WebElement element = findElement(locator);
        actions.keyDown(Keys.CONTROL).sendKeys(element, "v").keyUp(Keys.CONTROL).perform();
    }

    // ========================================
    // 12. JAVASCRIPT EXECUTOR
    // ========================================

    /**
     * Execute JavaScript
     */
    public Object executeScript(String script, Object... args) {
        return js.executeScript(script, args);
    }

    /**
     * Scroll to element
     */
    public void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scroll to top
     */
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scroll to bottom
     */
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scroll by pixels
     */
    public void scrollBy(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ");");
    }

    /**
     * Highlight element
     */
    public void highlightElement(By locator) {
        WebElement element = findElement(locator);
        js.executeScript("arguments[0].style.border='3px solid red';", element);
    }

    /**
     * Change element background color
     */
    public void changeBackgroundColor(By locator, String color) {
        WebElement element = findElement(locator);
        js.executeScript("arguments[0].style.backgroundColor='" + color + "';", element);
    }

    /**
     * Get page height
     */
    public Long getPageHeight() {
        return (Long) js.executeScript("return document.body.scrollHeight;");
    }

    /**
     * Get page width
     */
    public Long getPageWidth() {
        return (Long) js.executeScript("return document.body.scrollWidth;");
    }

    /**
     * Refresh page using JS
     */
    public void refreshJS() {
        js.executeScript("location.reload();");
    }

    /**
     * Generate alert using JS
     */
    public void generateAlert(String message) {
        js.executeScript("alert('" + message + "');");
    }

    // ========================================
    // 13. SCREENSHOT OPERATIONS
    // ========================================

    /**
     * Take full page screenshot
     */
    public void takeScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File(fileName);
            FileUtils.copyFile(source, destination);
            System.out.println("Screenshot saved: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }

    /**
     * Take screenshot as Base64
     */
    public String takeScreenshotBase64() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BASE64);
    }

    /**
     * Take element screenshot
     */
    public void takeElementScreenshot(By locator, String fileName) {
        try {
            WebElement element = findElement(locator);
            File source = element.getScreenshotAs(OutputType.FILE);
            File destination = new File(fileName);
            FileUtils.copyFile(source, destination);
            System.out.println("Element screenshot saved: " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save element screenshot: " + e.getMessage());
        }
    }

    /**
     * Take screenshot with timestamp
     */
    public String takeScreenshotWithTimestamp(String prefix) {
        String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = prefix + "_" + timestamp + ".png";
        takeScreenshot(fileName);
        return fileName;
    }

    // ========================================
    // 14. COOKIE OPERATIONS
    // ========================================

    /**
     * Add cookie
     */
    public void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        driver.manage().addCookie(cookie);
    }

    /**
     * Get cookie by name
     */
    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }

    /**
     * Get all cookies
     */
    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    /**
     * Delete cookie by name
     */
    public void deleteCookie(String name) {
        driver.manage().deleteCookieNamed(name);
    }

    /**
     * Delete all cookies
     */
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    // ========================================
    // 15. WEB TABLE OPERATIONS
    // ========================================

    /**
     * Get table row count
     */
    public int getTableRowCount(By tableLocator) {
        WebElement table = findElement(tableLocator);
        return table.findElements(By.tagName("tr")).size();
    }

    /**
     * Get table column count
     */
    public int getTableColumnCount(By tableLocator) {
        WebElement table = findElement(tableLocator);
        return table.findElements(By.tagName("th")).size();
    }

    /**
     * Get cell value from table
     */
    public String getTableCellValue(By tableLocator, int row, int col) {
        WebElement table = findElement(tableLocator);
        WebElement cell = table.findElement(
                By.xpath(".//tr[" + row + "]/td[" + col + "]")
        );
        return cell.getText();
    }

    /**
     * Get row data from table
     */
    public List<String> getTableRowData(By tableLocator, int rowNumber) {
        List<String> rowData = new ArrayList<>();
        WebElement table = findElement(tableLocator);
        List<WebElement> cells = table.findElements(
                By.xpath(".//tr[" + rowNumber + "]/td")
        );
        for (WebElement cell : cells) {
            rowData.add(cell.getText());
        }
        return rowData;
    }

    /**
     * Get column data from table
     */
    public List<String> getTableColumnData(By tableLocator, int colNumber) {
        List<String> columnData = new ArrayList<>();
        WebElement table = findElement(tableLocator);
        List<WebElement> cells = table.findElements(
                By.xpath(".//tr/td[" + colNumber + "]")
        );
        for (WebElement cell : cells) {
            columnData.add(cell.getText());
        }
        return columnData;
    }

    /**
     * Search text in table
     */
    public boolean searchInTable(By tableLocator, String searchText) {
        WebElement table = findElement(tableLocator);
        return table.getText().contains(searchText);
    }

    /**
     * Get all table data
     */
    public List<List<String>> getAllTableData(By tableLocator) {
        List<List<String>> tableData = new ArrayList<>();
        WebElement table = findElement(tableLocator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (int i = 1; i < rows.size(); i++) { // Skip header
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            tableData.add(rowData);
        }
        return tableData;
    }

    // ========================================
    // 16. FILE UPLOAD
    // ========================================

    /**
     * Upload file
     */
    public void uploadFile(By locator, String filePath) {
        WebElement fileInput = findElement(locator);
        fileInput.sendKeys(filePath);
    }

    // ========================================
    // 17. BROWSER STORAGE (LocalStorage/SessionStorage)
    // ========================================

    /**
     * Set LocalStorage item
     */
    public void setLocalStorage(String key, String value) {
        js.executeScript("localStorage.setItem('" + key + "', '" + value + "');");
    }

    /**
     * Get LocalStorage item
     */
    public String getLocalStorage(String key) {
        return (String) js.executeScript("return localStorage.getItem('" + key + "');");
    }

    /**
     * Remove LocalStorage item
     */
    public void removeLocalStorage(String key) {
        js.executeScript("localStorage.removeItem('" + key + "');");
    }

    /**
     * Clear LocalStorage
     */
    public void clearLocalStorage() {
        js.executeScript("localStorage.clear();");
    }

    /**
     * Set SessionStorage item
     */
    public void setSessionStorage(String key, String value) {
        js.executeScript("sessionStorage.setItem('" + key + "', '" + value + "');");
    }

    /**
     * Get SessionStorage item
     */
    public String getSessionStorage(String key) {
        return (String) js.executeScript("return sessionStorage.getItem('" + key + "');");
    }

    /**
     * Clear SessionStorage
     */
    public void clearSessionStorage() {
        js.executeScript("sessionStorage.clear();");
    }

    // ========================================
    // 18. DEVTOOLS OPERATIONS (V139)
    // ========================================

    /**
     * Initialize DevTools
     */
    public void initializeDevTools() {
        if (driver instanceof HasDevTools) {
            devTools = ((HasDevTools) driver).getDevTools();
            devTools.createSession();
        }
    }

    /**
     * Enable network monitoring
     */
    public void enableNetworkMonitoring() {
        if (devTools != null) {
            devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
        }
    }

    /**
     * Enable console log monitoring
     */
    public void enableConsoleLogMonitoring() {
        if (devTools != null) {
            devTools.send(Log.enable());
        }
    }

    /**
     * Get performance metrics
     */
    public List<Metric> getPerformanceMetrics() {
        if (devTools != null) {
            devTools.send(Performance.enable(Optional.empty()));
            return devTools.send(Performance.getMetrics());
        }
        return new ArrayList<>();
    }

    /**
     * Add network response listener
     */
    public void addNetworkResponseListener() {
        if (devTools != null) {
            devTools.addListener(Network.responseReceived(), response -> {
                System.out.println("Response: " + response.getResponse().getUrl() +
                        " | Status: " + response.getResponse().getStatus());
            });
        }
    }

    // ========================================
    // 19. UTILITY METHODS
    // ========================================

    /**
     * Get element count
     */
    public int getElementCount(By locator) {
        return findElements(locator).size();
    }

    /**
     * Check if element exists
     */
    public boolean isElementPresent(By locator) {
        try {
            findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Wait for element to disappear
     */
    public boolean waitForElementToDisappear(By locator, int timeoutInSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            return customWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get element location
     */
    public Point getElementLocation(By locator) {
        return findElement(locator).getLocation();
    }

    /**
     * Get element size
     */
    public Dimension getElementSize(By locator) {
        return findElement(locator).getSize();
    }

    /**
     * Get page load time
     */
    public long getPageLoadTime() {
        return (Long) js.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
    }

    /**
     * Verify page title
     */
    public boolean verifyPageTitle(String expectedTitle) {
        return driver.getTitle().equals(expectedTitle);
    }

    /**
     * Verify page URL contains
     */
    public boolean verifyURLContains(String urlPart) {
        return driver.getCurrentUrl().contains(urlPart);
    }

    /**
     * Print all links on page
     */
    public void printAllLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links: " + links.size());
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            String text = link.getText();
            System.out.println("Text: " + text + " | URL: " + url);
        }
    }

    /**
     * Get broken links count
     */
    public int getBrokenLinksCount() {
        int brokenLinks = 0;
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                try {
                    java.net.HttpURLConnection connection =
                            (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();
                    int responseCode = connection.getResponseCode();

                    if (responseCode >= 400) {
                        System.out.println("Broken link: " + url + " | Status: " + responseCode);
                        brokenLinks++;
                    }
                } catch (Exception e) {
                    brokenLinks++;
                }
            }
        }
        return brokenLinks;
    }

    /**
     * Get WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Get WebDriverWait instance
     */
    public WebDriverWait getWait() {
        return wait;
    }

    /**
     * Get JavascriptExecutor instance
     */
    public JavascriptExecutor getJavascriptExecutor() {
        return js;
    }

    /**
     * Get Actions instance
     */
    public Actions getActions() {
        return actions;
    }

    // ========================================
    // 20. EXAMPLE USAGE
    // ========================================

    public static void main(String[] args) {
        // Initialize browser
        WebDriver driver = SeleniumLibrary.initChrome();

        // Create library instance
        SeleniumLibrary selenium = new SeleniumLibrary(driver, 15);

        try {
            System.out.println("=== SELENIUM LIBRARY DEMO ===\n");

            // Browser operations
            selenium.openURL("https://www.google.com");
            selenium.maximizeWindow();
            System.out.println("Page Title: " + selenium.getPageTitle());

            // Element operations
            By searchBox = By.name("q");
            selenium.sendKeys(searchBox, "Selenium WebDriver");
            selenium.pressKeyOnElement(searchBox, Keys.ENTER);

            // Wait operations
            selenium.waitForPageLoad();
            selenium.sleep(2000);

            // Screenshot
            selenium.takeScreenshotWithTimestamp("google_search");

            // JavaScript operations
            selenium.scrollToBottom();
            selenium.sleep(1000);
            selenium.scrollToTop();

            // Get page info
            System.out.println("Current URL: " + selenium.getCurrentURL());
            System.out.println("Page Height: " + selenium.getPageHeight());

            System.out.println("\n✓ Demo completed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            selenium.quitBrowser();
        }
    }
}