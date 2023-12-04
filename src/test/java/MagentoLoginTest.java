import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MagentoLoginTest {

    private WebDriver driver;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private static final String USERNAME = "halynasereda@coherentsolutions.com";
    private static final String PASSWORD = "2013000Gs";

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HalynaSereda\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    void loginTest() {
        // Navigate to the Magento login page
        driver.get(BASE_URL);

        // Click on the "Sign In" link
        driver.findElement(By.linkText("Sign In")).click();

        // Enter username and password
        driver.findElement(By.id("email")).sendKeys(USERNAME);
        driver.findElement(By.id("pass")).sendKeys(PASSWORD);

        // Click on the "Sign In" button
        driver.findElement(By.id("send2")).click();

        // Wait for the "logged-in" element to be visible
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logged-in")));

        // Verify the content of the "logged-in" element
        String customerName = loggedInElement.getText();
        String expectedWelcomeMessage = "Welcome, Halyna Sereda!";
        assertTrue(customerName.contains(expectedWelcomeMessage), "Login successful");

        // Perform assertions after login
        assertEquals("Home Page", driver.getTitle(), "Page title is incorrect after login");
    }

    @AfterEach
    void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}