import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ch.qos.logback.core.util.Duration;

public class EmployeeGui {
	private WebDriver driver;
	
By pim =By.xpath("//a[.//span[text()='PIM']]");
By addButton = By.xpath("//button[normalize-space()='Add']");

  By roleDropdownArrow = By.xpath("//div[label[normalize-space()='User Role']]/following::i[contains(@class,'oxd-select-text--arrow')][1]");
  By employeename = By.cssSelector("input[placeholder='Type for hints...']");
  By status= By.xpath("//label[normalize-space()='Status']");//role 
  By username = By.xpath("//label[text()='Username']/following::input[1]");// username
  By password = By.xpath("//label[text()='Password']/following::input[1]"); // password
  By Confirmation_password = By.xpath("//label[text()='Confirm Password']/following::input[1]"); //confirmation pass
  By firstname= By.name("firstName"); //first name
  By middle = By.name("middleName"); //middle
  By lastname=By.name("lastName"); //last name
  By id = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");
  By savebutton = By.xpath("//button[normalize-space()='Save']");
//update
  By updatebutton  =By.xpath("//button[@type='button']//i[contains(@class, 'bi-pencil-fill')]");

    //job
  By jop = By.xpath("//a[contains(normalize-space(), 'Job')]");
  By jopTitle = By.xpath("//div[label[normalize-space()='Job Title']]/following::div[contains(@class,'oxd-select-text')][1]");
  By nextPageButton = By.xpath("//button[contains(@class, 'oxd-pagination-page-item--previous-next')]/i[contains(@class, 'bi-chevron-right')]");
  By search = By.xpath("//button[normalize-space()='Search']");
	public  EmployeeGui(WebDriver driver) {
		this.driver=driver;
	}
	

	public void clickPim()
	{
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		    WebElement Pim = wait.until(ExpectedConditions.visibilityOfElementLocated(pim));
		  Pim.click();
		   
	
	}
	public void clickaddbutton()
	{
		 WebDriverWait wait = new WebDriverWait(driver, 20);
		    WebElement AddUser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']")));
		
         AddUser.click();
         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Add Employee']")));

         System.out.println("User is on the Add employee form");
         }
	
	public void enterfirstname(String firstName) {
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  WebElement	 employeefirstname= wait.until(ExpectedConditions.visibilityOfElementLocated(firstname));
		  employeefirstname.sendKeys(firstName); 
		  
	}
	
	public void entermiddlename(String middlename) {
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  WebElement	  employeemiddlename= wait.until(ExpectedConditions.visibilityOfElementLocated(middle));
		  employeemiddlename.sendKeys(middlename); 
	}
	public void enterlastname(String lastName) {
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  WebElement	employeelastname= wait.until(ExpectedConditions.visibilityOfElementLocated(lastname));
		  employeelastname.sendKeys(lastName); 
	}
	public  void enterid(String Id) {
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  WebElement	 employeeid= wait.until(ExpectedConditions.visibilityOfElementLocated(id));
		  employeeid.sendKeys(Keys.CONTROL, "a"); 
		   
		    employeeid.sendKeys(Keys.BACK_SPACE);
		    try {
		    	Thread.sleep(5000);
		    	} catch (InterruptedException e) {
		    	
		    	e.printStackTrace();
		    	}
		  employeeid.sendKeys(Id);
		  
	}
	public void clearfeild() {
	    WebDriverWait wait = new WebDriverWait(driver, 20);

	    WebElement   employeefirstname = wait.until(ExpectedConditions.visibilityOfElementLocated(firstname));
	    WebElement   employeemiddlename = wait.until(ExpectedConditions.visibilityOfElementLocated(middle));
	    WebElement    employeelastname = wait.until(ExpectedConditions.visibilityOfElementLocated(lastname));

	  
	    employeefirstname.sendKeys(Keys.CONTROL, "a"); 
	  
	    employeefirstname.sendKeys(Keys.BACK_SPACE);
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    
	    	e.printStackTrace();
	    	}
	    employeemiddlename.sendKeys(Keys.CONTROL, "a"); 
	  
	    employeemiddlename.sendKeys(Keys.BACK_SPACE);
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    
	    	e.printStackTrace();
	    	}
	    employeelastname.sendKeys(Keys.CONTROL, "a"); 
	 
	    employeelastname.sendKeys(Keys.BACK_SPACE);
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    
	    	e.printStackTrace();
	    	}
	    
	}

	
	public void saveUser() {
		 WebDriverWait wait = new WebDriverWait(driver, 20);
		    WebElement Saveuser = wait.until(ExpectedConditions.elementToBeClickable(savebutton ));
		
     Saveuser.click();
     System.out.print("iam reach here in save");
	}
	public void clickUpdate() {
		 WebDriverWait wait = new WebDriverWait(driver, 20);
		    WebElement userUpdate = wait.until(ExpectedConditions.elementToBeClickable(updatebutton));
		userUpdate.click();
	}
	
	public void updatejop(String jobTitle) {
	    WebDriverWait wait = new WebDriverWait(driver, 20);

	 
	    WebElement jobTab = wait.until(ExpectedConditions.elementToBeClickable(jop));
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    	}
	    jobTab.click();
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    	}
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Job Details']")));
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    	}
 
	    WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(
	        By.cssSelector("i.oxd-select-text--arrow")));
	    dropdownArrow.click();
	    try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    	}
	 
	    String optionXPath = String.format("//div[@role='option' and normalize-space()='%s']", jobTitle);
	    WebElement desiredOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
	    desiredOption.click();

	    System.out.println("Selected job title: " + jobTitle);
	}
	
	

}
