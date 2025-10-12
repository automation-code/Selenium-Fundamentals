package Tables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PaginationTable {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://datatables.net/examples/basic_init/zero_configuration.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Wait for table to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("example")));

        // Get total number of pages
        int totalPages = getTotalPages(driver);
        System.out.println("Total Pages: " + totalPages);

        // Get current page number
        int currentPage = getCurrentPage(driver);
        System.out.println("Current Page: " + currentPage);

        // Get total entries
        String entriesInfo = driver.findElement(By.id("example_info")).getText();
        System.out.println("Entries Info: " + entriesInfo);

        // Print first page data
        printCurrentPageData(driver, "example");

        // Navigate to next page
        navigateToNextPage(driver);
        System.out.println("Current Page: " + getCurrentPage(driver));

        // Navigate to previous page
        navigateToPreviousPage(driver);
        System.out.println("Current Page: " + getCurrentPage(driver));

        // Navigate to specific page
        navigateToPage(driver, 3);
        System.out.println("Current Page: " + getCurrentPage(driver));

        // Search across all pages
        String searchText = "Ashton Cox";
        boolean found = searchAcrossPages(driver, "example", searchText);
        System.out.println(searchText + "' found: " + found);

        // Collect all data from all pages
        List<List<String>> allData = collectAllPagesData(driver, "example");
        System.out.println("Total rows collected: " + allData.size());

        // Get specific row data across pagination
        List<String> specificRow = findRowAcrossPages(driver, "example", "Cedric Kelly");
        if (specificRow != null) {
            System.out.println("Found: " + String.join(" | ", specificRow));
        }

        // Count total rows across all pages
        int totalRows = countTotalRows(driver, "example");
        System.out.println("\nTotal Rows Across All Pages: " + totalRows);

        // Navigate to last page
        navigateToLastPage(driver);
        System.out.println("Current Page: " + getCurrentPage(driver));

        // Navigate to first page
        navigateToFirstPage(driver);
        System.out.println("Current Page: " + getCurrentPage(driver));

        driver.quit();
    }

    // Get total number of pages
    public static int getTotalPages(WebDriver driver) {
        try {
            List<WebElement> pageLinks = driver.findElements(By.xpath("//div[@class='dt-paging']//button")
            );

            int maxPage = 0;
            for (WebElement link : pageLinks) {
                String pageText = link.getText().trim();
                if (pageText.matches("\\d+")) {
                    int pageNum = Integer.parseInt(pageText);
                    if (pageNum > maxPage) {
                        maxPage = pageNum;
                    }
                }
            }
            return maxPage;
        } catch (Exception e) {
            return 1;
        }
    }

    // Get current page number
    public static int getCurrentPage(WebDriver driver) {
        try {
            WebElement currentPage = driver.findElement(By.xpath("//div[@class='dt-paging']//button[@aria-label='First']"));
            return Integer.parseInt(currentPage.getText().trim());
        } catch (Exception e) {
            return 1;
        }
    }

    // Navigate to next page
    public static void navigateToNextPage(WebDriver driver) {
        try {
            WebElement nextButton = driver.findElement(By.id("//div[@class='dt-paging']//button"));
            if (!nextButton.getAttribute("class").contains("disabled")) {
                nextButton.click();
            }
        } catch (Exception e) {
            System.err.println("Cannot navigate to next page");
        }
    }

    // Navigate to previous page
    public static void navigateToPreviousPage(WebDriver driver) {
        try {
            WebElement prevButton = driver.findElement(By.id("//div[@class='dt-paging']//button"));
            if (!prevButton.getAttribute("class").contains("disabled")) {
                prevButton.click();
            }
        } catch (Exception e) {
            System.err.println("Cannot navigate to previous page");
        }
    }

    // Navigate to specific page
    public static void navigateToPage(WebDriver driver, int pageNumber) {
        try {
            WebElement pageLink = driver.findElement(
                    By.xpath("//div[@class='dt-paging']//a[text()='" + pageNumber + "']")
            );
            pageLink.click();
        } catch (Exception e) {
            System.err.println("Cannot navigate to page " + pageNumber);
        }
    }

    // Navigate to first page
    public static void navigateToFirstPage(WebDriver driver) {
        try {
            WebElement firstPage = driver.findElement(
                    By.xpath("//div[@class='dt-paging']//button[3]")
            );
            firstPage.click();
        } catch (Exception e) {
            System.err.println("Cannot navigate to first page");
        }
    }

    // Navigate to last page
    public static void navigateToLastPage(WebDriver driver) {
        int totalPages = getTotalPages(driver);
        navigateToPage(driver, totalPages);
    }

    // Print current page data
    public static void printCurrentPageData(WebDriver driver, String tableId) {
        WebElement table = driver.findElement(By.id(tableId));
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {
            System.out.println(row.getText());
        }
    }

    // Search text across all pages
    public static boolean searchAcrossPages(WebDriver driver, String tableId, String searchText) {
        int totalPages = getTotalPages(driver);
        int currentPage = getCurrentPage(driver);

        // Navigate to first page
        navigateToFirstPage(driver);

        try {
            Thread.sleep(1000);

            for (int i = 1; i <= totalPages; i++) {
                WebElement table = driver.findElement(By.id(tableId));
                if (table.getText().contains(searchText)) {
                    return true;
                }

                if (i < totalPages) {
                    navigateToNextPage(driver);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Return to original page
            navigateToPage(driver, currentPage);
        }

        return false;
    }

    // Collect all data from all pages
    public static List<List<String>> collectAllPagesData(WebDriver driver, String tableId) {
        List<List<String>> allData = new ArrayList<>();
        int totalPages = getTotalPages(driver);
        int currentPage = getCurrentPage(driver);

        navigateToFirstPage(driver);

        try {
            Thread.sleep(1000);

            for (int i = 1; i <= totalPages; i++) {
                WebElement table = driver.findElement(By.id(tableId));
                List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

                for (WebElement row : rows) {
                    List<WebElement> cells = row.findElements(By.tagName("td"));
                    List<String> rowData = new ArrayList<>();
                    for (WebElement cell : cells) {
                        rowData.add(cell.getText());
                    }
                    allData.add(rowData);
                }

                if (i < totalPages) {
                    navigateToNextPage(driver);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            navigateToPage(driver, currentPage);
        }

        return allData;
    }

    // Find specific row across all pages
    public static List<String> findRowAcrossPages(WebDriver driver, String tableId, String searchText) {
        int totalPages = getTotalPages(driver);
        int currentPage = getCurrentPage(driver);

        navigateToFirstPage(driver);

        try {
            Thread.sleep(1000);

            for (int i = 1; i <= totalPages; i++) {
                WebElement table = driver.findElement(By.id(tableId));
                List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

                for (WebElement row : rows) {
                    if (row.getText().contains(searchText)) {
                        List<WebElement> cells = row.findElements(By.tagName("td"));
                        List<String> rowData = new ArrayList<>();
                        for (WebElement cell : cells) {
                            rowData.add(cell.getText());
                        }
                        return rowData;
                    }
                }

                if (i < totalPages) {
                    navigateToNextPage(driver);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            navigateToPage(driver, currentPage);
        }

        return null;
    }

    // Count total rows across all pages
    public static int countTotalRows(WebDriver driver, String tableId) {
        int totalRows = 0;
        int totalPages = getTotalPages(driver);
        int currentPage = getCurrentPage(driver);

        navigateToFirstPage(driver);

        try {
            Thread.sleep(1000);

            for (int i = 1; i <= totalPages; i++) {
                WebElement table = driver.findElement(By.id(tableId));
                List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
                totalRows += rows.size();

                if (i < totalPages) {
                    navigateToNextPage(driver);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            navigateToPage(driver, currentPage);
        }

        return totalRows;
    }

    // Change page size (if available)
    public static void changePageSize(WebDriver driver, int size) {
        try {
            WebElement pageSizeDropdown = driver.findElement(By.name("example_length"));
            pageSizeDropdown.sendKeys(String.valueOf(size));
        } catch (Exception e) {
            System.err.println("Page size dropdown not available");
        }
    }

    // Check if next page is available
    public static boolean hasNextPage(WebDriver driver) {
        try {
            WebElement nextButton = driver.findElement(By.id("example_next"));
            return !nextButton.getAttribute("class").contains("disabled");
        } catch (Exception e) {
            return false;
        }
    }

    // Check if previous page is available
    public static boolean hasPreviousPage(WebDriver driver) {
        try {
            WebElement prevButton = driver.findElement(By.id("example_previous"));
            return !prevButton.getAttribute("class").contains("disabled");
        } catch (Exception e) {
            return false;
        }
    }
}
