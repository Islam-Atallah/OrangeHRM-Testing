import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class loginfeaturse {
    WebDriver driver;
    LoginGui loginPage;
    employeefeatures employee;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0");
        extent = ExtentReportManager.getReportInstance();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait = new WebDriverWait(driver, 10);
        loginPage = new LoginGui(driver);
        employee = new employeefeatures(driver);
    }

    // ✅ Login Helper Method
    public void loginWithValidUser() {
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickButtonLogin();
        wait.until(ExpectedConditions.urlContains("/dashboard/index"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/index"), "Login failed.");
    }

    
    @Test(dataProvider = "loginDataFromExcel", dataProviderClass = LoginDataProvider.class)
    public void testLoginScenarios(String username, String password, boolean shouldLoginSuccessfully) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickButtonLogin();

        if (shouldLoginSuccessfully) {
            wait.until(ExpectedConditions.urlContains("/dashboard/index"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard/index"), "Login failed.");
        } else {
            Assert.assertFalse(driver.getCurrentUrl().contains("/dashboard/index"), "Login should not be successful.");
        }
    }
  
    @Test(priority = 1, dataProvider = "employeeDataFromExcel", dataProviderClass = EmployeeDataProvider.class)
    public void testAddOrUpdateEmployee(String firstName, String middleName, String lastName, String id, String jobTitle, String action) {
        loginWithValidUser();  // تسجيل الدخول أولاً
        employee.goToemployeeList();
        wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));

        if (action.equalsIgnoreCase("add")) {
            System.out.println("Performing Add operation for ID: " + id);
            employee.clickAdd();
            wait.until(ExpectedConditions.urlContains("/pim/addEmployee"));
            employee.addnewemployee(firstName, middleName, lastName, id, jobTitle);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            employee.verifySuccessMessage();
        } else if (action.equalsIgnoreCase("update")) {
            System.out.println("Performing Update operation for ID: " + id);
            employee.updateEmployee(id, firstName, middleName, lastName, jobTitle);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            employee.verifySuccessMessage();
        } else {
            System.out.println("Invalid action: " + action + " for employee ID: " + id);
        }
    }

    
    @Test(priority = 2 )
    public void testSearchJobTitle() {
        loginWithValidUser();  
        employee.goToemployeeList();
        wait.until(ExpectedConditions.urlContains("/pim/viewEmployeeList"));
        try {
        	Thread.sleep(1000);
        	} catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        	}
        employee.searchjop("HR Manager");
        
        try {
        	Thread.sleep(1000);
        	} catch (InterruptedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        	}
    employee.verifySearchResultStatus("HR Manager");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();

    }
}
