
import org.testng.AssertJUnit;
import com.sun.javafx.PlatformUtil;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {
	
    WebDriver driver ;
    
    @FindBy(id= "OneWay") 
  	static WebElement onewayRadioButton;
    
    @FindBy(id= "FromTag") 
  	static WebElement originLocation;
    
    @FindBy(id= "ui-id-1") 
  	static WebElement autoCompleteListforOriginLocaion;
    
    @FindBy(id= "ToTag") 
  	static WebElement destinationLocation;
    
    @FindBy(id= "ui-id-2") 
  	static WebElement autoCompleteListforDestinationLocaion;
    
    @FindBy(xpath = "//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[1]/td[3]/a") 
  	static WebElement dateSelector;
    
    @FindBy(id= "SearchBtn") 
  	static WebElement SearchBtn;
    
    @BeforeTest
    public void browserlaunch()
    {
      launchBrowser();
      driver.get("https://www.cleartrip.com/");
      
    }
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, FlightBookingTest.class); 
        
        onewayRadioButton.click();
        originLocation.clear();
        originLocation.sendKeys("Bangalore");

        List<WebElement> originOptions=autoCompleteListforOriginLocaion.findElements(By.xpath("//*[starts-with(@id,'ui-id')]/li"));
		 originOptions.get(0).click();
			
		 destinationLocation.clear();
		 destinationLocation.sendKeys("Delhi");


        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = autoCompleteListforDestinationLocaion.findElements(By.tagName("li"));
        destinationOptions.get(0).click();
        
         //click on  may 1st
        dateSelector.click();
        
        //all fields filled in. Now click on search
        SearchBtn.click();

        //verify that result appears for the provided journey search
        AssertJUnit.assertTrue(isElementPresent(By.className("searchSummary")));


    }
    @AfterTest
    public void closeBrowser()
    {
        driver.quit();

    }

   private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

   public void launchBrowser()
	{
		CommonUtils.setDriverPath();
		this.driver = CommonUtils.driver;
	}
    
}
