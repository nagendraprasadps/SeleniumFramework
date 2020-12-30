package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPage {
	WebDriver driver; //Initialized before suite.
	@FindBy (id="txtUsername")
	WebElement userName;
	@FindBy (id="txtPassword")
	WebElement passWord;
	@FindBy (id="btnLogin")
	WebElement loginButton;
	@FindBy (css="#forgotPasswordLink > a:nth-child(1)")
	WebElement forgotPasswordLink;
	@FindBy (id="divLogo")
	WebElement logoDiv;
	@FindBy (id="footer")
	WebElement footer;
	@FindBy (id="social-icons")
	WebElement socialIcons;
	@FindBy (id="spanMessage")
	WebElement message;
	
	
	
	//Constructor
	 public LoginPage(WebDriver driver){
	        PageFactory.initElements(driver, this);
	   }
	 	//Set the username field 
	    public void setUserName(String strUserName){
	        userName.sendKeys(strUserName);     
	    }
	  //Set the password field 
	    public void setPassword(String strPassword){
	        passWord.sendKeys(strPassword);     
	    }
	    
	    //Click on LOGIN button
	    public void clickLoginButton(){
	        loginButton.click();     
	    }
	    
	    //Click on "Forgot your password?" link
	    public void clickForgotYourPassword(){
	    	forgotPasswordLink.click();     
	    }
	    //Click on "Forgot your password?" link
	    public String getMessage(){
	    	return message.getText();
	    }
	    // NOT CONSIDERING logo and footer at this point of time.
}

/*
txtUsername

txtPassword

btnLogin

forgotPasswordLink

divLogo

footer
*/