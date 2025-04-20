import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginGui {
	 private WebDriver driver;
	public LoginGui(WebDriver driver) {
        this.driver = driver; 
    }
    //username
	By usernameField = By.name("username");			
	//password
	By passwordField = By.name("password");
	
	By loginButton = By.className("oxd-button--main");

	public void enterUsername(String yourusername) {
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		    WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
		   
		    usernameField.sendKeys(yourusername);
		}
	
		public void enterPassword(String yourpassword) {
			
			driver.findElement(passwordField).sendKeys(yourpassword);
		}
		public void clickButtonLogin()
		{
			driver.findElement(loginButton).click();
		}			
}
