package Employee.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTestTest {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        System.out.println("Step 1: Browser launched");
    }

    @Test
    public void addNewEmployeeWithLoginDetails() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        System.out.println("Step 2: Navigated to OrangeHRM");

        // Step 3: Login as Admin
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        System.out.println("Step 3: Logged in as Admin");

        // Step 4: Navigate to Add Employee
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add Employee']"))).click();
        System.out.println("Step 4: Navigated to Add Employee page");

        // Step 5: Fill Employee Details
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");

        // Clear and set Employee ID
        WebElement empId = driver.findElement(By.xpath("//label[text()='Employee Id']/../following-sibling::div/input"));
        empId.clear();
        empId.sendKeys("12345");
        System.out.println("Step 5: Entered employee details");

        // Step 6: Create Login Details
        WebElement toggleLogin = driver.findElement(By.xpath("//span[contains(text(),'Create Login Details')]"));
        toggleLogin.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='Username']/../following-sibling::div/input"))).sendKeys("john.doe");
        driver.findElement(By.xpath("//label[text()='Password']/../following-sibling::div/input")).sendKeys("John@1234");
        driver.findElement(By.xpath("//label[text()='Confirm Password']/../following-sibling::div/input")).sendKeys("John@1234");

        WebElement statusDropdown = driver.findElement(By.xpath("//label[text()='Status']/../following-sibling::div//div[@class='oxd-select-text-input']"));
        statusDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='listbox']//span[text()='Enabled']"))).click();
        System.out.println("Step 6: Created login credentials");

        // Step 7: Click Save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        System.out.println("Step 7: Clicked on Save");

        // Step 8: Wait for Personal Details Page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
        System.out.println("Step 8: Navigated to Personal Details page");

        // Step 9: Verify Header and Name
        WebElement header = driver.findElement(By.xpath("//h6[text()='Personal Details']"));
        Assert.assertTrue(header.isDisplayed(), "Personal Details header is not displayed");

        WebElement fullName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@name='firstName']/../../../../div[1]//input[@name='firstName']")));
        String firstName = fullName.getAttribute("value");

        WebElement lastNameField = driver.findElement(By.xpath("//input[@name='lastName']"));
        String lastName = lastNameField.getAttribute("value");

        String displayedName = firstName + " " + lastName;
        Assert.assertEquals(displayedName.trim(), "John Doe", "Employee name doesn't match");
        System.out.println("Step 9: Verified employee name: " + displayedName);

        System.out.println("Test Case: PASSED");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}











