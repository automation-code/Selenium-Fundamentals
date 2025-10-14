package Dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DropdownUtility {

    private WebDriver driver;

    // Constructor
    public DropdownUtility(WebDriver driver) {
        this.driver = driver;
    }

    // Get Select Object from WebElement
    private Select getSelect(WebElement element) {
        try {
            return new Select(element);
        } catch (Exception e) {
            System.out.println("Element is not a valid dropdown: " + e.getMessage());
            return null;
        }
    }

    // Select by Visible Text
    public void selectByVisibleText(WebElement element, String text) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                select.selectByVisibleText(text);
                System.out.println("Selected by visible text: " + text);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Option not found with text: " + text);
        } catch (Exception e) {
            System.out.println("Failed to select by visible text: " + e.getMessage());
        }
    }

    // Select by Value Attribute
    public void selectByValue(WebElement element, String value) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                select.selectByValue(value);
                System.out.println("Selected by value: " + value);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Option not found with value: " + value);
        } catch (Exception e) {
            System.out.println("Failed to select by value: " + e.getMessage());
        }
    }

    // Select by Index
    public void selectByIndex(WebElement element, int index) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                select.selectByIndex(index);
                System.out.println("Selected by index: " + index);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Option not found at index: " + index);
        } catch (Exception e) {
            System.out.println("Failed to select by index: " + e.getMessage());
        }
    }

    // Get Selected Option Text
    public String getSelectedOptionText(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                String text = select.getFirstSelectedOption().getText();
                System.out.println("Selected option text: " + text);
                return text;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Failed to get selected option text: " + e.getMessage());
            return null;
        }
    }

    // Get Selected Option Value
    public String getSelectedOptionValue(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                String value = select.getFirstSelectedOption().getAttribute("value");
                System.out.println("Selected option value: " + value);
                return value;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Failed to get selected option value: " + e.getMessage());
            return null;
        }
    }

    // Get All Selected Options (for multi-select)
    public List<String> getAllSelectedOptionsText(WebElement element) {
        List<String> selectedTexts = new ArrayList<>();
        try {
            Select select = getSelect(element);
            if (select != null) {
                List<WebElement> selectedOptions = select.getAllSelectedOptions();
                for (WebElement option : selectedOptions) {
                    selectedTexts.add(option.getText());
                }
                System.out.println("All selected options: " + selectedTexts);
            }
        } catch (Exception e) {
            System.out.println("Failed to get all selected options: " + e.getMessage());
        }
        return selectedTexts;
    }

    // Get All Options Text
    public List<String> getAllOptionsText(WebElement element) {
        List<String> optionsText = new ArrayList<>();
        try {
            Select select = getSelect(element);
            if (select != null) {
                List<WebElement> options = select.getOptions();
                for (WebElement option : options) {
                    optionsText.add(option.getText());
                }
                System.out.println("Total options: " + optionsText.size());
            }
        } catch (Exception e) {
            System.out.println("Failed to get all options: " + e.getMessage());
        }
        return optionsText;
    }

    // Get All Options Values
    public List<String> getAllOptionsValues(WebElement element) {
        List<String> optionsValues = new ArrayList<>();
        try {
            Select select = getSelect(element);
            if (select != null) {
                List<WebElement> options = select.getOptions();
                for (WebElement option : options) {
                    optionsValues.add(option.getAttribute("value"));
                }
                System.out.println("Retrieved all option values");
            }
        } catch (Exception e) {
            System.out.println("Failed to get all option values: " + e.getMessage());
        }
        return optionsValues;
    }

    // Get Options Count
    public int getOptionsCount(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                int count = select.getOptions().size();
                System.out.println("Options count: " + count);
                return count;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Failed to get options count: " + e.getMessage());
            return 0;
        }
    }

    // Check if Dropdown is Multi-Select
    public boolean isMultiSelect(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                boolean isMulti = select.isMultiple();
                System.out.println("Is multi-select: " + isMulti);
                return isMulti;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Failed to check if multi-select: " + e.getMessage());
            return false;
        }
    }

    // Deselect by Visible Text (for multi-select)
    public void deselectByVisibleText(WebElement element, String text) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                select.deselectByVisibleText(text);
                System.out.println("Deselected by visible text: " + text);
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to deselect by visible text: " + e.getMessage());
        }
    }

    // Deselect by Value (for multi-select)
    public void deselectByValue(WebElement element, String value) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                select.deselectByValue(value);
                System.out.println("Deselected by value: " + value);
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to deselect by value: " + e.getMessage());
        }
    }

    // Deselect by Index (for multi-select)
    public void deselectByIndex(WebElement element, int index) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                select.deselectByIndex(index);
                System.out.println("Deselected by index: " + index);
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to deselect by index: " + e.getMessage());
        }
    }

    // Deselect All (for multi-select)
    public void deselectAll(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                select.deselectAll();
                System.out.println("Deselected all options");
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to deselect all: " + e.getMessage());
        }
    }

    // Check if Option Exists by Text
    public boolean isOptionExistsByText(WebElement element, String text) {
        try {
            List<String> allOptions = getAllOptionsText(element);
            boolean exists = allOptions.contains(text);
            System.out.println("Option '" + text + "' exists: " + exists);
            return exists;
        } catch (Exception e) {
            System.out.println("Failed to check if option exists: " + e.getMessage());
            return false;
        }
    }

    // Check if Option Exists by Value
    public boolean isOptionExistsByValue(WebElement element, String value) {
        try {
            List<String> allValues = getAllOptionsValues(element);
            boolean exists = allValues.contains(value);
            System.out.println("Option with value '" + value + "' exists: " + exists);
            return exists;
        } catch (Exception e) {
            System.out.println("Failed to check if option exists: " + e.getMessage());
            return false;
        }
    }

    // Verify Selected Option Text
    public boolean verifySelectedOption(WebElement element, String expectedText) {
        try {
            String actualText = getSelectedOptionText(element);
            if (actualText != null && actualText.equals(expectedText)) {
                System.out.println("Selected option verification passed");
                return true;
            } else {
                System.out.println("Verification failed. Expected: " + expectedText + ", Actual: " + actualText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to verify selected option: " + e.getMessage());
            return false;
        }
    }

    // Print All Options
    public void printAllOptions(WebElement element) {
        try {
            List<String> options = getAllOptionsText(element);
            System.out.println("\n========== Dropdown Options ==========");
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }
            System.out.println("======================================\n");
        } catch (Exception e) {
            System.out.println("Failed to print options: " + e.getMessage());
        }
    }

    // Select Multiple Options by Text (for multi-select)
    public void selectMultipleOptionsByText(WebElement element, List<String> texts) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                for (String text : texts) {
                    select.selectByVisibleText(text);
                }
                System.out.println("Selected multiple options: " + texts);
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to select multiple options: " + e.getMessage());
        }
    }

    // Select Multiple Options by Value (for multi-select)
    public void selectMultipleOptionsByValue(WebElement element, List<String> values) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                for (String value : values) {
                    select.selectByValue(value);
                }
                System.out.println("Selected multiple options by value: " + values);
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to select multiple options: " + e.getMessage());
        }
    }

    // Select Multiple Options by Index (for multi-select)
    public void selectMultipleOptionsByIndex(WebElement element, List<Integer> indices) {
        try {
            Select select = getSelect(element);
            if (select != null && select.isMultiple()) {
                for (Integer index : indices) {
                    select.selectByIndex(index);
                }
                System.out.println("Selected multiple options by index: " + indices);
            } else {
                System.out.println("Dropdown is not multi-select");
            }
        } catch (Exception e) {
            System.out.println("Failed to select multiple options: " + e.getMessage());
        }
    }

    // Select Random Option
    public void selectRandomOption(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                List<WebElement> options = select.getOptions();
                // Skip first option if it's a placeholder (like "Select...")
                int randomIndex = 1 + (int) (Math.random() * (options.size() - 1));
                select.selectByIndex(randomIndex);
                System.out.println("Selected random option at index: " + randomIndex);
            }
        } catch (Exception e) {
            System.out.println("Failed to select random option: " + e.getMessage());
        }
    }

    // Get Selected Options Count (for multi-select)
    public int getSelectedOptionsCount(WebElement element) {
        try {
            Select select = getSelect(element);
            if (select != null) {
                int count = select.getAllSelectedOptions().size();
                System.out.println("Selected options count: " + count);
                return count;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Failed to get selected options count: " + e.getMessage());
            return 0;
        }
    }

    // Check if Specific Option is Selected
    public boolean isOptionSelected(WebElement element, String text) {
        try {
            List<String> selectedOptions = getAllSelectedOptionsText(element);
            return selectedOptions.contains(text);
        } catch (Exception e) {
            System.out.println("Failed to check if option is selected: " + e.getMessage());
            return false;
        }
    }

    // Example Usage Method
    public static void main(String[] args) {

        // Example initialization (you would use your actual WebDriver setup)
        WebDriver driver = new ChromeDriver();
        DropdownUtility dropdownUtil = new DropdownUtility(driver);
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Example 1: Select by visible text
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='country']"));
        dropdownUtil.selectByVisibleText(dropdown, "United Kingdom");

        // Example 2: Select by value
        dropdownUtil.selectByValue(dropdown, "australia");

        // Example 3: Select by index
        dropdownUtil.selectByIndex(dropdown, 2);

        // Example 4: Get selected option
        String selected = dropdownUtil.getSelectedOptionText(dropdown);

        // Example 5: Get all options
        List<String> options = dropdownUtil.getAllOptionsText(dropdown);

        // Example 6: Print all options
        dropdownUtil.printAllOptions(dropdown);

        // Example 7: Verify selected option
        boolean isCorrect = dropdownUtil.verifySelectedOption(dropdown, "United States");

        // Example 8: Check if option exists
        boolean exists = dropdownUtil.isOptionExistsByText(dropdown, "Canada");

        // Example 9: Multi-select dropdown
        // WebElement multiSelect = driver.findElement(By.id("skills"));
        // List<String> skills = Arrays.asList("Java", "Python", "JavaScript");
        // dropdownUtil.selectMultipleOptionsByText(multiSelect, skills);

        // Example 10: Deselect all (multi-select)
        // dropdownUtil.deselectAll(multiSelect);

        // Example 11: Select random option
        // dropdownUtil.selectRandomOption(dropdown);

        driver.quit();
    }
}
