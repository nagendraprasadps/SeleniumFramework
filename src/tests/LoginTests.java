package tests;

import org.testng.annotations.Test;

import PageObjects.DashboardPage;
import PageObjects.LoginPage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.apache.commons.io.FileUtils;

public class LoginTests {
ITestResult result;
    
	
	LoginPage loginPage;
	public static WebDriver driver;
	String baseURL="https://opensource-demo.orangehrmlive.com/index.php/auth/login";

	String fogotPasswordURL="https://opensource-demo.orangehrmlive.com/index.php/auth/requestPasswordResetCode";
	String dashboardURL="https://opensource-demo.orangehrmlive.com/index.php/dashboard";

	@Test(groups = { "sanity", "functionality" }, retryAnalyzer = Retry.class)
	//@Test(dataProvider = "loginSuccessData",groups = { "sanity", "functionality" })
	//@Test (groups = { "sanity" })
	@Parameters({"uname","pword"})
	  public void successfulLogin(String uname, String pword) throws Exception {
		String currentDir = System.getProperty("user.dir");
		launchApp();
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);			//Hardcoded for simplicity of the demo
		lp.setPassword(pword);
		takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_001_before.png") ;
		lp.clickLoginButton();
		
		takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_001_after.png") ;     
		Assert.assertEquals(driver.getCurrentUrl(),dashboardURL);
		
		driver.quit();
	  }

	@Test (groups = { "sanity" },dependsOnMethods = { "tests.LoginTests.successfulLogin" })
	//@Test (groups = { "sanity" })
	@Parameters({"uname","pword"})
	  public void loginLogout(String uname, String pword) throws Exception {
		String currentDir = System.getProperty("user.dir");
		launchApp();
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);			//Hardcoded for simplicity of the demo
		lp.setPassword(pword);
		//takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_001_before.png") ;
		lp.clickLoginButton();
		
		//takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_001_after.png") ;     
		Assert.assertEquals(driver.getCurrentUrl(),dashboardURL);
		Thread.sleep(10000);
		/*
		Thread.sleep(10000);
		WebDriverWait  w = new WebDriverWait(driver,30); 
		w.until(ExpectedConditions.elementToBeClickable(By.id("welcome")));
		driver.findElement(By.id("welcome")).click();
		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[10]/ul/li[2]/a")).click();
		*/
		DashboardPage dp = new DashboardPage(driver);
		dp.ClickWelcome();
		dp.ClickLogout();
		driver.quit();
	  }
	
	//@Test(dataProvider = "loginFailureData")
	@Test (groups = { "regression" })
	@Parameters({"wrongUname","pword"})
	  public void wrongCredentials(String username, String password) throws Exception {
		String currentDir = System.getProperty("user.dir");
		launchApp();
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);		//Hardcoded for simplicity of the demo
		lp.setPassword(password);
		takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_002_before.png") ;
		lp.clickLoginButton();
		takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_002_after.png") ;
		Assert.assertEquals(lp.getMessage(),"Invalid credentials");
		driver.quit();
		
	  }
	@Test (groups = {  "sanity","functionality" })	//Retry analyzer to be included here
	  public void clickForgotYourPassword() throws Exception {
		String currentDir = System.getProperty("user.dir");
			launchApp();
			LoginPage lp = new LoginPage(driver);
			takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_003_before.png") ;
			lp.clickForgotYourPassword();
			takeSnapShot(driver, currentDir+"\\ScreenShots\\TC_003_aftre.png") ;
			Assert.assertEquals(driver.getCurrentUrl(),fogotPasswordURL);
			driver.quit();
	  }
	
  @DataProvider
  public Object[][] loginSuccessData() {
    return new Object[][] {
      new Object[] {"Admin", "admin123" },
      
    };
  }
  @DataProvider
  public Object[][] loginFailureData() {
    return new Object[][] {
    	 new Object[] {"Admin", "admin1234" }
      
    };
  }
  @BeforeTest
  public void beforeTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
	  
  }
//Actually this is beforesuite. Quitting the driver  to avoid logout in another page.
  public void launchApp() {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\nagen\\Downloads\\geckodriver-v0.27.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
      
  }
  
  //Screen shot function
  public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
      TakesScreenshot scrShot =((TakesScreenshot)webdriver);
              File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
              File DestFile=new File(fileWithPath);
              FileUtils.copyFile(SrcFile, DestFile);
  }
}

