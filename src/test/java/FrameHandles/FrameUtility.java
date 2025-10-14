package FrameHandles;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FrameUtility {

    private WebDriver driver;
    private WebDriverWait wait;
    private String parentWindow;

    // Constructor
    public FrameUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.parentWindow = driver.getWindowHandle();
    }

    // Constructor with custom timeout
    public FrameUtility(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        this.parentWindow = driver.getWindowHandle();
    }

    // Get Current Window Handle
    public String getCurrentWindowHandle() {
        try {
            String handle = driver.getWindowHandle();
            System.out.println("Current window handle: " + handle);
            return handle;
        } catch (Exception e) {
            System.out.println("Failed to get current window handle: " + e.getMessage());
            return null;
        }
    }

    // Get All Window Handles
    public Set<String> getAllWindowHandles() {
        try {
            Set<String> handles = driver.getWindowHandles();
            System.out.println("Total windows: " + handles.size());
            return handles;
        } catch (Exception e) {
            System.out.println("Failed to get all window handles: " + e.getMessage());
            return null;
        }
    }

    // Get Windows Count
    public int getWindowsCount() {
        Set<String> handles = getAllWindowHandles();
        return (handles != null) ? handles.size() : 0;
    }

    // Switch to Window by Handle
    public void switchToWindowByHandle(String windowHandle) {
        try {
            driver.switchTo().window(windowHandle);
            System.out.println("Switched to window: " + windowHandle);
        } catch (NoSuchWindowException e) {
            System.out.println("Window not found: " + windowHandle);
        } catch (Exception e) {
            System.out.println("Failed to switch to window: " + e.getMessage());
        }
    }

    // Switch to Window by Title
    public void switchToWindowByTitle(String title) {
        try {
            Set<String> handles = getAllWindowHandles();
            for (String handle : handles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().equals(title)) {
                    System.out.println("Switched to window with title: " + title);
                    return;
                }
            }
            System.out.println("No window found with title: " + title);
        } catch (Exception e) {
            System.out.println("Failed to switch to window by title: " + e.getMessage());
        }
    }

    // Switch to Window by Partial Title
    public void switchToWindowByPartialTitle(String partialTitle) {
        try {
            Set<String> handles = getAllWindowHandles();
            for (String handle : handles) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains(partialTitle)) {
                    System.out.println("Switched to window containing title: " + partialTitle);
                    return;
                }
            }
            System.out.println("No window found containing title: " + partialTitle);
        } catch (Exception e) {
            System.out.println("Failed to switch to window by partial title: " + e.getMessage());
        }
    }

    // Switch to Window by URL
    public void switchToWindowByURL(String url) {
        try {
            Set<String> handles = getAllWindowHandles();
            for (String handle : handles) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().equals(url)) {
                    System.out.println("Switched to window with URL: " + url);
                    return;
                }
            }
            System.out.println("No window found with URL: " + url);
        } catch (Exception e) {
            System.out.println("Failed to switch to window by URL: " + e.getMessage());
        }
    }

    // Switch to Window by Partial URL
    public void switchToWindowByPartialURL(String partialURL) {
        try {
            Set<String> handles = getAllWindowHandles();
            for (String handle : handles) {
                driver.switchTo().window(handle);
                if (driver.getCurrentUrl().contains(partialURL)) {
                    System.out.println("Switched to window containing URL: " + partialURL);
                    return;
                }
            }
            System.out.println("No window found containing URL: " + partialURL);
        } catch (Exception e) {
            System.out.println("Failed to switch to window by partial URL: " + e.getMessage());
        }
    }

    // Switch to Parent Window
    public void switchToParentWindow() {
        try {
            driver.switchTo().window(parentWindow);
        } catch (Exception e) {
            System.out.println("Failed to switch to parent window: " + e.getMessage());
        }
    }

    // Switch to New Window (Latest opened)
    public void switchToNewWindow() {
        try {
            Set<String> handles = getAllWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(parentWindow)) {
                    driver.switchTo().window(handle);
                    System.out.println("Switched to new window");
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to switch to new window: " + e.getMessage());
        }
    }

    // Switch to Window by Index (0-based)
    public void switchToWindowByIndex(int index) {
        try {
            List<String> handles = new ArrayList<>(getAllWindowHandles());
            if (index >= 0 && index < handles.size()) {
                driver.switchTo().window(handles.get(index));
                System.out.println("Switched to window at index: " + index);
            } else {
                System.out.println("Invalid window index: " + index);
            }
        } catch (Exception e) {
            System.out.println("Failed to switch to window by index: " + e.getMessage());
        }
    }

    // Wait for New Window to Open
    public void waitForNewWindow(int expectedWindows) {
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindows));
            System.out.println("New window opened. Total windows: " + expectedWindows);
        } catch (Exception e) {
            System.out.println("Failed to wait for new window: " + e.getMessage());
        }
    }

    // Close Current Window
    public void closeCurrentWindow() {
        try {
            driver.close();
            System.out.println("Current window closed");
        } catch (Exception e) {
            System.out.println("Failed to close current window: " + e.getMessage());
        }
    }

    // Close All Windows Except Parent
    public void closeAllWindowsExceptParent() {
        try {
            Set<String> handles = getAllWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(parentWindow)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
            System.out.println("Closed all windows except parent");
        } catch (Exception e) {
            System.out.println("Failed to close windows: " + e.getMessage());
        }
    }

    // Get Current Window Title
    public String getCurrentWindowTitle() {
        try {
            String title = driver.getTitle();
            System.out.println("Current window title: " + title);
            return title;
        } catch (Exception e) {
            System.out.println("Failed to get window title: " + e.getMessage());
            return null;
        }
    }

    // Get Current Window URL
    public String getCurrentWindowURL() {
        try {
            String url = driver.getCurrentUrl();
            System.out.println("Current window URL: " + url);
            return url;
        } catch (Exception e) {
            System.out.println("Failed to get window URL: " + e.getMessage());
            return null;
        }
    }

    // Print All Window Titles
    public void printAllWindowTitles() {
        try {
            Set<String> handles = getAllWindowHandles();
            int index = 1;
            for (String handle : handles) {
                driver.switchTo().window(handle);
                System.out.println(index + ". " + driver.getTitle());
                index++;
            }
        } catch (Exception e) {
            System.out.println("Failed to print window titles: " + e.getMessage());
        }
    }

    // Switch to Frame by Index
    public void switchToFrameByIndex(int index) {
        try {
            driver.switchTo().frame(index);
            System.out.println("Switched to frame at index: " + index);
        } catch (NoSuchFrameException e) {
            System.out.println("Frame not found at index: " + index);
        } catch (Exception e) {
            System.out.println("Failed to switch to frame by index: " + e.getMessage());
        }
    }

    // Switch to Frame by Name or ID
    public void switchToFrameByNameOrId(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
            System.out.println("Switched to frame: " + nameOrId);
        } catch (NoSuchFrameException e) {
            System.out.println("Frame not found: " + nameOrId);
        } catch (Exception e) {
            System.out.println("Failed to switch to frame by name/id: " + e.getMessage());
        }
    }

    // Switch to Frame by WebElement
    public void switchToFrameByElement(WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
        } catch (NoSuchFrameException e) {
            System.out.println("Frame not found");
        } catch (Exception e) {
            System.out.println("Failed to switch to frame by element: " + e.getMessage());
        }
    }

    // Switch to Frame by Locator
    public void switchToFrameByLocator(By locator) {
        try {
            WebElement frameElement = driver.findElement(locator);
            driver.switchTo().frame(frameElement);
        } catch (Exception e) {
            System.out.println("Failed to switch to frame by locator: " + e.getMessage());
        }
    }

    // Wait and Switch to Frame by Locator
    public void waitAndSwitchToFrame(By locator) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        } catch (Exception e) {
            System.out.println("Failed to wait and switch to frame: " + e.getMessage());
        }
    }

    // Wait and Switch to Frame by Index
    public void waitAndSwitchToFrame(int index) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
            System.out.println("Waited and switched to frame at index: " + index);
        } catch (Exception e) {
            System.out.println("Failed to wait and switch to frame: " + e.getMessage());
        }
    }

    // Wait and Switch to Frame by Name or ID
    public void waitAndSwitchToFrame(String nameOrId) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
            System.out.println("Waited and switched to frame: " + nameOrId);
        } catch (Exception e) {
            System.out.println("Failed to wait and switch to frame: " + e.getMessage());
        }
    }

    // Switch to Parent Frame
    public void switchToParentFrame() {
        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            System.out.println("Failed to switch to parent frame: " + e.getMessage());
        }
    }

    // Switch to Default Content (Exit all frames)
    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Failed to switch to default content: " + e.getMessage());
        }
    }

    // Get Frames Count
    public int getFramesCount() {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            int count = frames.size();
            System.out.println("Total frames: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("Failed to get frames count: " + e.getMessage());
            return 0;
        }
    }

    // Check if Frame Exists by Name or ID
    public boolean isFrameExistsByNameOrId(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
            switchToDefaultContent(); // Switch back
            System.out.println("Frame exists: " + nameOrId);
            return true;
        } catch (NoSuchFrameException e) {
            System.out.println("Frame does not exist: " + nameOrId);
            return false;
        }
    }

    // Check if Frame Exists by Index
    public boolean isFrameExistsByIndex(int index) {
        try {
            driver.switchTo().frame(index);
            switchToDefaultContent(); // Switch back
            System.out.println("Frame exists at index: " + index);
            return true;
        } catch (NoSuchFrameException e) {
            System.out.println("Frame does not exist at index: " + index);
            return false;
        }
    }

    // Print All Frame Names and IDs
    public void printAllFrames() {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            System.out.println("\n========== All Frames ==========");
            for (int i = 0; i < frames.size(); i++) {
                String name = frames.get(i).getAttribute("name");
                String id = frames.get(i).getAttribute("id");
                System.out.println("Frame " + i + " - Name: " + name + ", ID: " + id);
            }
            System.out.println("================================\n");
        } catch (Exception e) {
            System.out.println("Failed to print frames: " + e.getMessage());
        }
    }

    // Switch to Nested Frame (Frame within Frame)
    public void switchToNestedFrame(String parentFrame, String childFrame) {
        try {
            switchToFrameByNameOrId(parentFrame);
            switchToFrameByNameOrId(childFrame);
            System.out.println("Switched to nested frame: " + parentFrame + " -> " + childFrame);
        } catch (Exception e) {
            System.out.println("Failed to switch to nested frame: " + e.getMessage());
        }
    }

    // Example Usage Method
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        FrameUtility winFrameUtil = new FrameUtility(driver);

        driver.manage().window().maximize();
        driver.get("https://ui.vision/demo/webtest/frames/");

        WebElement frame1 = driver.findElement(By.cssSelector("frame[src='frame_1.html']"));
        WebElement frame2 = driver.findElement(By.cssSelector("frame[src='frame_2.html']"));
        WebElement frame3 = driver.findElement(By.cssSelector("frame[src='frame_3.html']"));
        WebElement frame4 = driver.findElement(By.cssSelector("frame[src='frame_4.html']"));
        WebElement frame5 = driver.findElement(By.cssSelector("frame[src='frame_5.html']"));

        winFrameUtil.switchToFrameByElement(frame3);
        driver.findElement(By.xpath("//input[@name='mytext3']")).sendKeys("Frame Text 3");
        winFrameUtil.switchToDefaultContent();

        winFrameUtil.switchToFrameByElement(frame5);
        driver.findElement(By.xpath("//input[@name='mytext5']")).sendKeys("Frame Text 5");
        winFrameUtil.switchToDefaultContent();

        winFrameUtil.switchToFrameByElement(frame1);
        driver.findElement(By.xpath("//input[@name='mytext1']")).sendKeys("Frame Text 1");
        winFrameUtil.switchToDefaultContent();

        winFrameUtil.switchToFrameByElement(frame4);
        driver.findElement(By.xpath("//input[@name='mytext4']")).sendKeys("Frame Text 4");
        winFrameUtil.switchToDefaultContent();

        winFrameUtil.switchToFrameByElement(frame2);
        driver.findElement(By.xpath("//input[@name='mytext2']")).sendKeys("Frame Text 2");
        winFrameUtil.switchToDefaultContent();

        Thread.sleep(5000);
        driver.quit();
    }
}
