package Tables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTable {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));


        // Wait for table to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customers")));
        WebElement table = driver.findElement(By.id("customers"));

        // Get dynamic row count
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("Dynamic Row Count: " + rows.size());

        // Wait for specific data to appear
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("customers"), "Germany"));

        // Find row containing specific text
        String searchCompany = "Ernst Handel";
        WebElement targetRow = findRowByText(driver, "customers", searchCompany);
        if (targetRow != null) {
            System.out.println("Found row: " + targetRow.getText());
        }

        // Get data from dynamic columns
        List<String> companies = getColumnDataById(driver, "customers", 1);
        System.out.println("Companies: " + companies.size());
        companies.forEach(System.out::println);

        // Extract specific cell based on row and column headers
        String country = getCellByRowAndColumn(driver, "customers", "Ernst Handel", 3);
        System.out.println("Ernst Handel's Country: " + country);

        // Get row as map (column header -> cell value)
        Map<String, String> rowMap = getRowAsMap(driver, "customers", 2);
        rowMap.forEach((key, value) -> System.out.println(key + ": " + value));

        // Count rows matching condition
        int germanyCount = countRowsContainingText(driver, "customers", "Germany");
        System.out.println("\nRows containing 'Germany': " + germanyCount);

        // Verify data exists
        boolean exists = verifyDataExists(driver, "customers", "Island Trading");
        System.out.println("Island Trading' exists: " + exists);

        driver.quit();
    }

    // Wait for table to load with specific row count
    public static void waitForTableToLoad(WebDriver driver, String tableId, int expectedRows) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> {
            WebElement table = driver1.findElement(By.id(tableId));
            List<WebElement> rows = table.findElements(By.tagName("tr"));
            return rows.size() >= expectedRows;
        });
    }

    // Find row containing specific text
    public static WebElement findRowByText(WebDriver driver, String tableId, String text) {
        WebElement table = driver.findElement(By.id(tableId));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                return row;
            }
        }
        return null;
    }

    // Get column data by index
    public static List<String> getColumnDataById(WebDriver driver, String tableId, int colIndex) {
        List<String> columnData = new ArrayList<>();
        List<WebElement> cells = driver.findElements(
                By.xpath("//table[@id='" + tableId + "']//tr/td[" + colIndex + "]")
        );
        for (WebElement cell : cells) {
            columnData.add(cell.getText());
        }
        return columnData;
    }

    // Get cell value by row text and column index
    public static String getCellByRowAndColumn(WebDriver driver, String tableId,
                                               String rowText, int colIndex) {
        WebElement row = findRowByText(driver, tableId, rowText);
        if (row != null) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (colIndex <= cells.size()) {
                return cells.get(colIndex - 1).getText();
            }
        }
        return null;
    }

    // Get row data as map (header -> value)
    public static Map<String, String> getRowAsMap(WebDriver driver, String tableId, int rowIndex) {
        Map<String, String> rowMap = new HashMap<>();

        // Get headers
        List<WebElement> headers = driver.findElements(By.xpath("//table[@id='" + tableId + "']//tr[1]/th")
        );

        // Get row cells
        List<WebElement> cells = driver.findElements(By.xpath("//table[@id='" + tableId + "']//tr[" + rowIndex + "]/td")
        );

        for (int i = 0; i < headers.size() && i < cells.size(); i++) {
            rowMap.put(headers.get(i).getText(), cells.get(i).getText());
        }
        return rowMap;
    }

    // Count rows containing specific text
    public static int countRowsContainingText(WebDriver driver, String tableId, String text) {
        int count = 0;
        WebElement table = driver.findElement(By.id(tableId));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                count++;
            }
        }
        return count;
    }

    // Verify data exists in table
    public static boolean verifyDataExists(WebDriver driver, String tableId, String text) {
        WebElement table = driver.findElement(By.id(tableId));
        return table.getText().contains(text);
    }

    // Get all rows matching condition
    public static List<WebElement> getRowsContaining(WebDriver driver, String tableId, String text) {
        List<WebElement> matchingRows = new ArrayList<>();
        WebElement table = driver.findElement(By.id(tableId));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                matchingRows.add(row);
            }
        }
        return matchingRows;
    }

    // Click element in specific row and column
    public static void clickCellElement(WebDriver driver, String tableId, int row, int col) {
        WebElement cell = driver.findElement(
                By.xpath("//table[@id='" + tableId + "']//tr[" + row + "]/td[" + col + "]//a")
        );
        cell.click();
    }
}
