

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInTest {
	WebDriver driver;
	
	@FindBy(linkText= "Your trips")
	static WebElement trip;
	
	@FindBy(id= "SignIn")
	static WebElement signin;
	
	@FindBy(id= "signInButton")
	static WebElement signinbutton;
	
	 @BeforeTest
	 public void browserLaunch()
	 {
		 launchBrowser();
		 driver.get("https://www.cleartrip.com/");
	 }
	@Test
    public void ifSignInDetailsAreMissing()
    {
		driver.get("https://www.cleartrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		PageFactory.initElements(driver, SignInTest.class); 
		
		trip.click();
		signin.click();
		
		//The Login Window is a Frame.
		
	    driver.switchTo().frame("modal_window");
	    signinbutton.click();
	    
	    String errors1 = driver.findElement(By.id("errors1")).getText();
         Assert.assertTrue(errors1.contains("There were errors in your submission"));
         driver.quit();
	
    }
	@AfterTest
	public void closeSession()
	{
		driver.quit();
	}
	public void launchBrowser()
	{
		CommonUtils.setDriverPath();
		this.driver = CommonUtils.driver;
	}
}
