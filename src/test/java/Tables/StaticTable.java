package Tables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class StaticTable {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/web-table-element.php");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Locate the table
        WebElement table = driver.findElement(By.className("dataTable"));

        // Get all rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.println("Total Rows: " + rows.size());

        // Get all columns from header
        List<WebElement> headers = table.findElements(By.tagName("th"));
        System.out.println("Total Columns: " + headers.size());

        // Print header names
        for (WebElement header : headers) {
            System.out.print(header.getText() + " | ");
        }
        System.out.println("\n");

        // Print all table data
        System.out.println("--- Table Data ---");
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.print(cell.getText() + " | ");
            }
            System.out.println();
        }

        // Get specific cell value (Row 2, Column 2)
        WebElement cell = driver.findElement(By.xpath("//table[@class='dataTable']//tr[2]/td[2]"));
        System.out.println("\nCell Value (Row 2, Column 2): " + cell.getText());

        // Get entire row data
        List<WebElement> row3Cells = driver.findElements(
                By.xpath("//table[@class='dataTable']//tr[3]/td")
        );
        for (WebElement rowCell : row3Cells) {
            System.out.print(rowCell.getText() + " | ");
        }

        // Get entire column data
        List<WebElement> column1 = driver.findElements(
                By.xpath("//table[@class='dataTable']//tr/td[1]")
        );
        for (WebElement colCell : column1) {
            System.out.println(colCell.getText());
        }

        // Search for specific data
        String searchText = "Escorts Ltd.";
        boolean found = searchInTable(driver, "dataTable", searchText);
        System.out.println("Found: " + found);

        // Get row count excluding header
        int dataRowCount = rows.size() - 1;
        System.out.println("Data Rows (excluding header): " + dataRowCount);

        driver.quit();
    }

    // Search for text in table
    public static boolean searchInTable(WebDriver driver, String tableClassName, String searchText) {
        WebElement table = driver.findElement(By.className(tableClassName));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(searchText)) {
                return true;
            }
        }
        return false;
    }

    // Get total row count
    public static int getRowCount(WebDriver driver, String tableClassName) {
        WebElement table = driver.findElement(By.className(tableClassName));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows.size();
    }

    // Get total column count
    public static int getColumnCount(WebDriver driver, String tableClassName) {
        WebElement table = driver.findElement(By.className(tableClassName));
        List<WebElement> headers = table.findElements(By.tagName("th"));
        return headers.size();
    }

    // Get specific cell value
    public static String getCellValue(WebDriver driver, String tableClassName, int row, int col) {
        WebElement cell = driver.findElement(
                By.xpath("//table[@class='" + tableClassName + "']//tr[" + row + "]/td[" + col + "]")
        );
        return cell.getText();
    }

    // Get all data from specific row
    public static List<String> getRowData(WebDriver driver, String tableClassName, int rowNumber) {
        List<String> rowData = new ArrayList<>();
        List<WebElement> cells = driver.findElements(
                By.xpath("//table[@class='" + tableClassName + "']//tr[" + rowNumber + "]/td")
        );
        for (WebElement cell : cells) {
            rowData.add(cell.getText());
        }
        return rowData;
    }

    // Get all data from specific column
    public static List<String> getColumnData(WebDriver driver, String tableClassName, int colNumber) {
        List<String> columnData = new ArrayList<>();
        List<WebElement> cells = driver.findElements(
                By.xpath("//table[@class='" + tableClassName + "']//tr/td[" + colNumber + "]")
        );
        for (WebElement cell : cells) {
            columnData.add(cell.getText());
        }
        return columnData;
    }

    // Get all table data as 2D list
    public static List<List<String>> getAllTableData(WebDriver driver, String tableClassName) {
        List<List<String>> tableData = new ArrayList<>();
        WebElement table = driver.findElement(By.className(tableClassName));
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

    // Print entire table
    public static void printTable(WebDriver driver, String tableClassName) {
        WebElement table = driver.findElement(By.className(tableClassName));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
    }
}
