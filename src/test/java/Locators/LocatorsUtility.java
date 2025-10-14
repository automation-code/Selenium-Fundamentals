package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LocatorsUtility {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LocatorsUtility(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Constructor with custom timeout
    public LocatorsUtility(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // Find Element by ID
    public WebElement findById(String id) {
        try {
            WebElement element = driver.findElement(By.id(id));
            System.out.println("Element found by ID: " + id);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by ID: " + id);
            return null;
        }
    }

    // Find Element by Name
    public WebElement findByName(String name) {
        try {
            WebElement element = driver.findElement(By.name(name));
            System.out.println("Element found by Name: " + name);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by Name: " + name);
            return null;
        }
    }

    // Find Element by Class Name
    public WebElement findByClassName(String className) {
        try {
            WebElement element = driver.findElement(By.className(className));
            System.out.println("Element found by Class Name: " + className);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by Class Name: " + className);
            return null;
        }
    }

    // Find Element by Tag Name
    public WebElement findByTagName(String tagName) {
        try {
            WebElement element = driver.findElement(By.tagName(tagName));
            System.out.println("Element found by Tag Name: " + tagName);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by Tag Name: " + tagName);
            return null;
        }
    }

    // Find Element by Link Text (Exact Match)
    public WebElement findByLinkText(String linkText) {
        try {
            WebElement element = driver.findElement(By.linkText(linkText));
            System.out.println("Element found by Link Text: " + linkText);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by Link Text: " + linkText);
            return null;
        }
    }

    // Find Element by Partial Link Text
    public WebElement findByPartialLinkText(String partialLinkText) {
        try {
            WebElement element = driver.findElement(By.partialLinkText(partialLinkText));
            System.out.println("Element found by Partial Link Text: " + partialLinkText);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by Partial Link Text: " + partialLinkText);
            return null;
        }
    }

    // Find Element by CSS Selector
    public WebElement findByCssSelector(String cssSelector) {
        try {
            WebElement element = driver.findElement(By.cssSelector(cssSelector));
            System.out.println("Element found by CSS Selector: " + cssSelector);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by CSS Selector: " + cssSelector);
            return null;
        }
    }

    // Find Element by XPath
    public WebElement findByXPath(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            System.out.println("Element found by XPath: " + xpath);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found by XPath: " + xpath);
            return null;
        }
    }

    // Find Elements by ID
    public List<WebElement> findElementsById(String id) {
        List<WebElement> elements = driver.findElements(By.id(id));
        System.out.println("Found " + elements.size() + " elements by ID: " + id);
        return elements;
    }

    // Find Elements by Name
    public List<WebElement> findElementsByName(String name) {
        List<WebElement> elements = driver.findElements(By.name(name));
        System.out.println("Found " + elements.size() + " elements by Name: " + name);
        return elements;
    }

    // Find Elements by Class Name
    public List<WebElement> findElementsByClassName(String className) {
        List<WebElement> elements = driver.findElements(By.className(className));
        System.out.println("Found " + elements.size() + " elements by Class Name: " + className);
        return elements;
    }

    // Find Elements by Tag Name
    public List<WebElement> findElementsByTagName(String tagName) {
        List<WebElement> elements = driver.findElements(By.tagName(tagName));
        System.out.println("Found " + elements.size() + " elements by Tag Name: " + tagName);
        return elements;
    }

    // Find Elements by Link Text
    public List<WebElement> findElementsByLinkText(String linkText) {
        List<WebElement> elements = driver.findElements(By.linkText(linkText));
        System.out.println("Found " + elements.size() + " elements by Link Text: " + linkText);
        return elements;
    }

    // Find Elements by Partial Link Text
    public List<WebElement> findElementsByPartialLinkText(String partialLinkText) {
        List<WebElement> elements = driver.findElements(By.partialLinkText(partialLinkText));
        System.out.println("Found " + elements.size() + " elements by Partial Link Text: " + partialLinkText);
        return elements;
    }

    // Find Elements by CSS Selector
    public List<WebElement> findElementsByCssSelector(String cssSelector) {
        List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
        System.out.println("Found " + elements.size() + " elements by CSS Selector: " + cssSelector);
        return elements;
    }

    // Find Elements by XPath
    public List<WebElement> findElementsByXPath(String xpath) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        System.out.println("Found " + elements.size() + " elements by XPath: " + xpath);
        return elements;
    }

    // Wait for Element by ID
    public WebElement waitForElementById(String id) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            System.out.println("Element found after wait by ID: " + id);
            return element;
        } catch (Exception e) {
            System.out.println("Element not found after wait by ID: " + id);
            return null;
        }
    }

    // Wait for Element by Name
    public WebElement waitForElementByName(String name) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
            System.out.println("Element found after wait by Name: " + name);
            return element;
        } catch (Exception e) {
            System.out.println("Element not found after wait by Name: " + name);
            return null;
        }
    }

    // Wait for Element by CSS Selector
    public WebElement waitForElementByCssSelector(String cssSelector) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
            System.out.println("Element found after wait by CSS Selector: " + cssSelector);
            return element;
        } catch (Exception e) {
            System.out.println("Element not found after wait by CSS Selector: " + cssSelector);
            return null;
        }
    }

    // Wait for Element by XPath
    public WebElement waitForElementByXPath(String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            System.out.println("Element found after wait by XPath: " + xpath);
            return element;
        } catch (Exception e) {
            System.out.println("Element not found after wait by XPath: " + xpath);
            return null;
        }
    }

    // Wait for Element to be Clickable by ID
    public WebElement waitForClickableElementById(String id) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
            System.out.println("Element clickable by ID: " + id);
            return element;
        } catch (Exception e) {
            System.out.println("Element not clickable by ID: " + id);
            return null;
        }
    }

    // Wait for Element to be Clickable by XPath
    public WebElement waitForClickableElementByXPath(String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            System.out.println("Element clickable by XPath: " + xpath);
            return element;
        } catch (Exception e) {
            System.out.println("Element not clickable by XPath: " + xpath);
            return null;
        }
    }

    // Wait for Element to be Visible by CSS Selector
    public WebElement waitForVisibleElementByCssSelector(String cssSelector) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            System.out.println("Element visible by CSS Selector: " + cssSelector);
            return element;
        } catch (Exception e) {
            System.out.println("Element not visible by CSS Selector: " + cssSelector);
            return null;
        }
    }

    // Check if Element Exists by ID
    public boolean isElementPresentById(String id) {
        try {
            driver.findElement(By.id(id));
            System.out.println("Element present by ID: " + id);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present by ID: " + id);
            return false;
        }
    }

    // Check if Element Exists by Name
    public boolean isElementPresentByName(String name) {
        try {
            driver.findElement(By.name(name));
            System.out.println("Element present by Name: " + name);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present by Name: " + name);
            return false;
        }
    }

    // Check if Element Exists by CSS Selector
    public boolean isElementPresentByCssSelector(String cssSelector) {
        try {
            driver.findElement(By.cssSelector(cssSelector));
            System.out.println("Element present by CSS Selector: " + cssSelector);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present by CSS Selector: " + cssSelector);
            return false;
        }
    }

    // Check if Element Exists by XPath
    public boolean isElementPresentByXPath(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            System.out.println("Element present by XPath: " + xpath);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not present by XPath: " + xpath);
            return false;
        }
    }

    // Build XPath by Text
    public String buildXPathByText(String tagName, String text) {
        return String.format("//%s[text()='%s']", tagName, text);
    }

    // Build XPath by Partial Text
    public String buildXPathByPartialText(String tagName, String partialText) {
        return String.format("//%s[contains(text(),'%s')]", tagName, partialText);
    }

    // Build XPath by Attribute
    public String buildXPathByAttribute(String tagName, String attribute, String value) {
        return String.format("//%s[@%s='%s']", tagName, attribute, value);
    }

    // Build XPath by Partial Attribute
    public String buildXPathByPartialAttribute(String tagName, String attribute, String partialValue) {
        return String.format("//%s[contains(@%s,'%s')]", tagName, attribute, partialValue);
    }

    // Build XPath with Multiple Attributes (AND condition)
    public String buildXPathWithMultipleAttributes(String tagName, String attr1, String value1, String attr2, String value2) {
        return String.format("//%s[@%s='%s' and @%s='%s']", tagName, attr1, value1, attr2, value2);
    }

    // Build XPath with OR Condition
    public String buildXPathWithOrCondition(String tagName, String attr1, String value1, String attr2, String value2) {
        return String.format("//%s[@%s='%s' or @%s='%s']", tagName, attr1, value1, attr2, value2);
    }

    // Build XPath by Starts-With
    public String buildXPathByStartsWith(String tagName, String attribute, String startValue) {
        return String.format("//%s[starts-with(@%s,'%s')]", tagName, attribute, startValue);
    }

    // Build XPath for Parent Element
    public String buildXPathForParent(String childXPath) {
        return String.format("(%s)/parent::*", childXPath);
    }

    // Build XPath for Following Sibling
    public String buildXPathForFollowingSibling(String elementXPath, String siblingTag) {
        return String.format("(%s)/following-sibling::%s", elementXPath, siblingTag);
    }

    // Build XPath for Preceding Sibling
    public String buildXPathForPrecedingSibling(String elementXPath, String siblingTag) {
        return String.format("(%s)/preceding-sibling::%s", elementXPath, siblingTag);
    }

    // Build XPath by Index
    public String buildXPathByIndex(String xpath, int index) {
        return String.format("(%s)[%d]", xpath, index);
    }

    // Build CSS Selector by ID
    public String buildCssSelectorById(String id) {
        return "#" + id;
    }

    // Build CSS Selector by Class
    public String buildCssSelectorByClass(String className) {
        return "." + className;
    }

    // Build CSS Selector by Attribute
    public String buildCssSelectorByAttribute(String tagName, String attribute, String value) {
        return String.format("%s[%s='%s']", tagName, attribute, value);
    }

    // Build CSS Selector by Partial Attribute (Contains)
    public String buildCssSelectorByPartialAttribute(String tagName, String attribute, String partialValue) {
        return String.format("%s[%s*='%s']", tagName, attribute, partialValue);
    }

    // Build CSS Selector by Starts With
    public String buildCssSelectorByStartsWith(String tagName, String attribute, String startValue) {
        return String.format("%s[%s^='%s']", tagName, attribute, startValue);
    }

    // Build CSS Selector by Ends With
    public String buildCssSelectorByEndsWith(String tagName, String attribute, String endValue) {
        return String.format("%s[%s$='%s']", tagName, attribute, endValue);
    }

    // Build CSS Selector for Child Element
    public String buildCssSelectorForChild(String parentSelector, String childTag) {
        return String.format("%s > %s", parentSelector, childTag);
    }

    // Build CSS Selector for Descendant
    public String buildCssSelectorForDescendant(String ancestorSelector, String descendantTag) {
        return String.format("%s %s", ancestorSelector, descendantTag);
    }

    // Build CSS Selector with Multiple Classes
    public String buildCssSelectorWithMultipleClasses(String class1, String class2) {
        return String.format(".%s.%s", class1, class2);
    }

    // Build CSS Selector by nth-child
    public String buildCssSelectorByNthChild(String parentSelector, int index) {
        return String.format("%s:nth-child(%d)", parentSelector, index);
    }

    // Find Element with Dynamic Wait
    public WebElement findElementWithWait(By locator, int timeoutSeconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element not found with custom wait: " + locator);
            return null;
        }
    }

    // Get Element Count
    public int getElementCount(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        int count = elements.size();
        System.out.println("Element count: " + count);
        return count;
    }

    // Wait for Element to Disappear
    public boolean waitForElementToDisappear(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            System.out.println("Element disappeared");
            return true;
        } catch (Exception e) {
            System.out.println("Element did not disappear");
            return false;
        }
    }

    // Wait for Text to be Present in Element
    public boolean waitForTextInElement(By locator, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            System.out.println("Text present in element: " + text);
            return true;
        } catch (Exception e) {
            System.out.println("Text not present in element: " + text);
            return false;
        }
    }

    public static void main(String[] args) {

         WebDriver driver = new ChromeDriver();
        LocatorsUtility locatorUtil = new LocatorsUtility(driver);
        driver.get("https://www.google.com");

         // Example 1: Find by ID
         WebElement element = locatorUtil.findById("username");

         // Example 2: Find by Name
         WebElement emailField = locatorUtil.findByName("email");

         // Example 3: Find by Class Name
         WebElement button = locatorUtil.findByClassName("btn-primary");

         // Example 4: Find by CSS Selector
         WebElement loginBtn = locatorUtil.findByCssSelector("button[type='submit']");

         // Example 5: Find by XPath
         WebElement heading = locatorUtil.findByXPath("//h1[@class='title']");

         // Example 6: Find by Link Text
         WebElement link = locatorUtil.findByLinkText("Click Here");

         // Example 7: Find all elements by tag name
         List<WebElement> allLinks = locatorUtil.findElementsByTagName("a");

         // Example 8: Find all elements by class name
         List<WebElement> products = locatorUtil.findElementsByClassName("product-item");

         // Example 9: Wait for element by ID
         WebElement dynamicElement = locatorUtil.waitForElementById("loadedContent");

         // Example 10: Wait for clickable element
         WebElement submitBtn = locatorUtil.waitForClickableElementByXPath("//button[@id='submit']");

         // Example 11: Wait for visible element
         WebElement modal = locatorUtil.waitForVisibleElementByCssSelector(".modal-dialog");

         // Example 12: Check if element exists
         boolean exists = locatorUtil.isElementPresentById("errorMessage");

         // Example 13: Build XPath by text
         String xpath = locatorUtil.buildXPathByText("button", "Submit");
         WebElement btn = locatorUtil.findByXPath(xpath);

         // Example 14: Build XPath by attribute
         String xpathAttr = locatorUtil.buildXPathByAttribute("input", "placeholder", "Enter name");

         // Example 15: Build XPath with multiple attributes
         String xpathMulti = locatorUtil.buildXPathWithMultipleAttributes("input", "type", "text", "name", "username");

         // Example 16: Build XPath by partial text
         String xpathPartial = locatorUtil.buildXPathByPartialText("a", "Click");

         // Example 17: Build CSS by attribute
         String css = locatorUtil.buildCssSelectorByAttribute("input", "name", "password");

         // Example 18: Build CSS with starts-with
         String cssStarts = locatorUtil.buildCssSelectorByStartsWith("input", "id", "user");

         // Example 19: Build CSS for nth-child
         String cssNth = locatorUtil.buildCssSelectorByNthChild("ul.menu > li", 3);

         // Example 20: Get element count
         int count = locatorUtil.getElementCount(By.className("item"));

         // Example 21: Wait for element to disappear
         locatorUtil.waitForElementToDisappear(By.id("loadingSpinner"));

         // Example 22: Wait for text in element
         locatorUtil.waitForTextInElement(By.id("status"), "Success");

         driver.quit();
    }
}
