package PageObjects;


import java.util.List;

//import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DashboardPage {
	WebDriver driver; //Initialized before suite.
	//@FindBy (css="#welcome-menu > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")
 	//WebElement logout;
	@FindBy (id="welcome")
 	WebElement welcome;
	@FindBy(xpath="/html/body/div[1]/div[1]/div[10]/ul/li[2]/a")
	WebElement logOut;
	
	
  
	//Constructor
		 public DashboardPage(WebDriver driver){
		        PageFactory.initElements(driver, this);
		   }
		 	//Set the username field 
		 public void ClickWelcome(){
		        this.welcome.click();
		   }
		 public void ClickLogout(){
		        this.logOut.click();
		   }
		    
}
