import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class TestAddProduct {
    private ChromeDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust the timeout as needed
        driver.manage().window().maximize();

        // Navigate to login page
        driver.get("http://localhost:2069/CD_Web_war/login");

        // Log in as admin
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit' and contains(., 'Log in')]"));

        usernameField.sendKeys("thuxidau"); // Admin's username
        passwordField.sendKeys("123321"); // Admin's password
        loginButton.click();

        // Wait for the login to complete and redirect to the home page
        wait.until(ExpectedConditions.urlContains("/home"));

        // Click on the "Manage Product" link to navigate to the Manage Product page
        WebElement manageProductLink = driver.findElement(By.xpath("//a[@href='manage' and contains(text(), 'Manage Product')]"));
        manageProductLink.click();

        // Wait for the Manage Product page to load
        wait.until(ExpectedConditions.urlContains("/manage"));
    }

    @Test
    public void checkTitle() throws InterruptedException {
        String title = "Manage Product";
        Assertions.assertEquals(title, driver.getTitle());
        Thread.sleep(5000);
    }

    @DisplayName("Add Product Test")
    @ParameterizedTest(name = "{index} => name={0}, price={1}, quantity={2}, description={3}, image={4}, category={5}, country={6}, expectedTitle={7}")
    @CsvFileSource(resources = "/add_product_data.csv")
    public void addProductSuccess(String name, String price, String quantity, String description, String image, String category, String country, String expectedTitle) throws IOException, InterruptedException, CsvValidationException {

        // Click on the "Add New Product" button to open the modal
        WebElement addProductButton = driver.findElement(By.xpath("//a[@href='#addProduct' and contains(@class, 'btn-danger')]"));
        addProductButton.click();

        // Wait for the modal to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addProduct")));

        // Fill out the form
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("price")).sendKeys(price);
        driver.findElement(By.name("quantity")).sendKeys(quantity);
        driver.findElement(By.name("description")).sendKeys(description);
        driver.findElement(By.name("image")).sendKeys(image);
        driver.findElement(By.name("category")).sendKeys(category);
        driver.findElement(By.name("country")).sendKeys(country);

        // Click the "Add" button
        WebElement addButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Add']"));
        addButton.click();

        // Wait for the page to reload and the modal to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("addProduct")));

        // Verify the page title to ensure we are back on the Manage Product page
        Assertions.assertEquals(expectedTitle, driver.getTitle());

        // Check if the product appears in the list
        verifyProductInList(name, price, quantity, description, image);

        Thread.sleep(5000);
    }

    private void verifyProductInList(String name, String price, String quantity, String description, String image) {
        // Wait until the product table is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-striped table-hover']")));

        // Locate the first row in the product table
        WebElement firstRow = driver.findElement(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr[1]"));

        // Get the text of each cell in the first row
        String firstRowText = firstRow.getText();

        // Verify that the new product details are present in the first row
        Assertions.assertTrue(firstRowText.contains(name));
        Assertions.assertTrue(firstRowText.contains(price));
        Assertions.assertTrue(firstRowText.contains(quantity));
        Assertions.assertTrue(firstRowText.contains(description));

        // Verify that the image URL is present in the image cell
        WebElement imageElement = firstRow.findElement(By.xpath(".//td[5]/img"));
        String src = imageElement.getAttribute("src");
        Assertions.assertTrue(src.contains(image));
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}