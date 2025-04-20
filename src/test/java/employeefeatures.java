import java.util.NoSuchElementException;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.handler.timeout.TimeoutException;


public class employeefeatures {
    WebDriver driver;
    EmployeeGui employee;
    By search = By.xpath("//button[normalize-space()='Search']");
    public employeefeatures(WebDriver driver) {
        this.driver = driver;
        this.employee = new EmployeeGui(driver); //
    }
    
   public void goToemployeeList() {
         employee.clickPim();
           }

    public void clickAdd() {
    	employee.clickaddbutton();
    	
    
    }

public void clicksave() {
	employee.saveUser();
}
public void verifySuccessMessage() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    
   
    WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector(".oxd-toast-container .oxd-toast-content-text")));
    
    
    String message = toastMessage.getText();
    System.out.println("Toast Message: " + message);
    
    
    Assert.assertTrue(message.contains("Success"), "❌ Expected success message not found. Actual: " + message);
    
  
}



public void addnewemployee(String fname,String mname ,String lname ,String id,String title) {
	employee.enterfirstname(fname);
	employee.entermiddlename(mname);
	employee.enterlastname(lname);

	 employee.enterid(id);

	
	  try {
      	Thread.sleep(5000);
      	} catch (InterruptedException e) {
      	// TODO Auto-generated catch block
      	e.printStackTrace();
      	}
	employee.saveUser();
	
	   try {
       	Thread.sleep(1000);
       	} catch (InterruptedException e) {
       	// TODO Auto-generated catch block
       	e.printStackTrace();
       	}
	   verifySuccessMessage();
	employee.updatejop(title);
	  try {
	    	Thread.sleep(5000);
	    	} catch (InterruptedException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    	}
	   clicksave();
	   try {
	   	Thread.sleep(1000);
	   	} catch (InterruptedException e) {
	   	// TODO Auto-generated catch block
	   	e.printStackTrace();
	   	}
	    verifySuccessMessage();
}
public void clickUpdate() {
	employee.clickUpdate();
}
public void updateEmployee(String id, String fnamen, String mnamen, String lnamen,String jtitle) {
	   WebDriverWait wait = new WebDriverWait(driver, 30);
	   
 // 1. البحث عن الـ id المحدد والضغط على زر التعديل
 try {
     String xpath = String.format("//div[text()='Id']/following::div[text()='%s']", id);
     WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
     editButton.click();
 } catch (TimeoutException e) {
     System.out.println("ID not found: " + id);
     return;
 }

 // 2. انتظار صفحة "Personal Details"
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));

 try {
 	Thread.sleep(5000);
 	} catch (InterruptedException e) {
 	// TODO Auto-generated catch block
 	e.printStackTrace();
 	}
 // 3. تعديل البيانات
 employee.clearfeild();
 employee.enterfirstname(fnamen);
 employee.entermiddlename(mnamen);
 employee.enterlastname(lnamen);
 try {
 	Thread.sleep(5000);
 	} catch (InterruptedException e) {
 	// TODO Auto-generated catch block
 	e.printStackTrace();
 	}
 clicksave();
 try {
 	Thread.sleep(1000);
 	} catch (InterruptedException e) {
 	// TODO Auto-generated catch block
 	e.printStackTrace();
 	}
 verifySuccessMessage();
 System.out.println("Name updated for ID: " + id);
 try {
 	Thread.sleep(3000);
 	} catch (InterruptedException e) {
 	// TODO Auto-generated catch block
 	e.printStackTrace();
 	}
 employee.updatejop(jtitle);
 try {
 	Thread.sleep(5000);
 	} catch (InterruptedException e) {
 	// TODO Auto-generated catch block
 	e.printStackTrace();
 	}
clicksave();
try {
	Thread.sleep(1000);
	} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
 verifySuccessMessage();
} 
/*
public void updateEmployee(String id, String fnamen, String mnamen, String lnamen, String jtitle) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    boolean idFound = false;

 
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li/button[contains(@class, 'oxd-pagination-page-item--page')]")));

    
  java.util.List<WebElement> pageButtons = driver.findElements(By.xpath("//li/button[contains(@class, 'oxd-pagination-page-item--page')]"));
    int totalPages = pageButtons.size();

    System.out.println("Total number of pages: " + totalPages);

    
    for (int page = 1; page <= totalPages; page++) {
        System.out.println("🔍 Checking page " + page);
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String pageXpath = String.format("//li/button[contains(@class, 'oxd-pagination-page-item--page') and text()='%d']", page);
        WebElement pageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(pageXpath)));
        pageButton.click();

        
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       
        try {
            String idXpath = String.format("//div[text()='Id']/following::div[text()='%s']", id);
            try {
                Thread.sleep(7000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement editButton = driver.findElement(By.xpath(idXpath));
            editButton.click();
            idFound = true;
            System.out.println("✅ ID found on page " + page);
            break;
        } catch (NoSuchElementException e) {
            System.out.println("❌ ID not found on page " + page);
        }
    }

    if (!idFound) {
        System.out.println("⚠️ Employee with ID " + id + " not found in any page.");
        return;
    }
    try {
        Thread.sleep(2000); 
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
   
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

 
    employee.clearfeild();
    employee.enterfirstname(fnamen);
    employee.entermiddlename(mnamen);
    employee.enterlastname(lnamen);

    clicksave();
    verifySuccessMessage();
    System.out.println("📝 Name updated for ID: " + id);

    
    employee.updatejop(jtitle);
    clicksave();
    verifySuccessMessage();
    System.out.println("🎯 Job title updated successfully.");
}
*/

public void verifySearchResultStatus(String jop) {
	String expectedJobTitle = jop; 
	WebDriverWait wait = new WebDriverWait(driver, 10);

	
	String xpath = String.format("//div[text()='Job Title']/following::div[text()='%s']", expectedJobTitle);


	try {
	    WebElement jobTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	    
	  
	    String actualJobTitle = jobTitleElement.getText();
	    
	   
	    if (actualJobTitle == " " || actualJobTitle.trim().isEmpty()) {
	        System.out.println("Job title is empty or null");
	        Assert.assertTrue(true);  
	    } else if (actualJobTitle.equals(expectedJobTitle)) {
	        System.out.println("Job title found and matches: " + actualJobTitle);
	        Assert.assertTrue(true); 
	    } else {
	        System.out.println("Job title found but does not match: " + actualJobTitle);
	        Assert.assertTrue(false);  
	    }

	} catch (TimeoutException e) {
	    System.out.println("Job title not found");
	    Assert.assertTrue(false);  
	}

}
public void searchjop(String joptitle)
{
	  WebDriverWait wait = new WebDriverWait(driver, 10);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Employee Information']")));
 wait = new WebDriverWait(driver, 10);
		 
 WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//div[label[normalize-space()='Job Title']]/following-sibling::div//div[contains(@class, 'oxd-select-text')]")
		));
		dropdown.click();


			   
			    String optionXPath = String.format("//div[@role='option' and normalize-space()='%s']", joptitle);
			    WebElement desiredOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXPath)));
			    desiredOption.click();

			    System.out.println("Selected job title: " + joptitle);

			    try {
		        	Thread.sleep(3000);
		        	} catch (InterruptedException e) {
		        	// TODO Auto-generated catch block
		        	e.printStackTrace();
		        	}
		    wait.until(ExpectedConditions.elementToBeClickable(search)).click();
		    try {
	        	Thread.sleep(5000);
	        	} catch (InterruptedException e) {
	        	// TODO Auto-generated catch block
	        	e.printStackTrace();
	        	}
		  
}	    
}