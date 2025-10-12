package Tables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableUtility {

    private WebDriver driver;

    public TableUtility(WebDriver driver) {
        this.driver = driver;
    }

    // Get row count
    public int getRowCount(String tableLocator) {
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        return table.findElements(By.tagName("tr")).size();
    }

    // Get column count
    public int getColumnCount(String tableLocator) {
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        return table.findElements(By.tagName("th")).size();
    }

    // Get cell value
    public String getCellValue(String tableLocator, int row, int col) {
        return driver.findElement(
                By.xpath("//" + tableLocator + "//tr[" + row + "]/td[" + col + "]")
        ).getText();
    }

    // Get row data
    public List<String> getRowData(String tableLocator, int row) {
        List<String> rowData = new ArrayList<>();
        List<WebElement> cells = driver.findElements(
                By.xpath("//" + tableLocator + "//tr[" + row + "]/td")
        );
        for (WebElement cell : cells) {
            rowData.add(cell.getText());
        }
        return rowData;
    }

    // Get column data
    public List<String> getColumnData(String tableLocator, int col) {
        List<String> columnData = new ArrayList<>();
        List<WebElement> cells = driver.findElements(
                By.xpath("//" + tableLocator + "//tr/td[" + col + "]")
        );
        for (WebElement cell : cells) {
            columnData.add(cell.getText());
        }
        return columnData;
    }

    // Search in table
    public boolean searchInTable(String tableLocator, String text) {
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        return table.getText().contains(text);
    }

    // Get all table data
    public List<List<String>> getAllTableData(String tableLocator) {
        List<List<String>> allData = new ArrayList<>();
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            allData.add(rowData);
        }
        return allData;
    }

    // Print table
    public void printTable(String tableLocator) {
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        System.out.println(table.getText());
    }

    // Find row by text
    public WebElement findRowByText(String tableLocator, String text) {
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                return row;
            }
        }
        return null;
    }

    // Count rows containing text
    public int countRowsWithText(String tableLocator, String text) {
        int count = 0;
        WebElement table = driver.findElement(By.cssSelector(tableLocator));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(text)) {
                count++;
            }
        }
        return count;
    }
}
