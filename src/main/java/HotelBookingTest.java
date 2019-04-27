import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.sun.javafx.PlatformUtil;

public class HotelBookingTest {
	WebDriver driver;
	@FindBy(xpath= "/html[1]/body[1]/section[2]/div[1]/aside[1]/nav[1]/ul[1]/li[2]/a[1]")
	static WebElement hotels;
	
	@FindBy(id= "Tags") 
	static WebElement localityTextBox;
	
	@FindBy(id= "ui-id-1") 
	static WebElement localityAutocompleteList;
	
	@FindBy(xpath ="/html/body/div[6]/div[2]/table/tbody/tr[1]/td[3]/a")
	static WebElement checkinDate;
	
	@FindBy(id="ui-datepicker-div")
	static WebElement checkoutDate;
		
	@FindBy(id = "travellersOnhome")
    static WebElement travellerSelection;
		
	 @FindBy(id = "SearchHotelsButton")
	 static WebElement searchButton;
	 
	 @BeforeTest
	 public void browserLaunch()
	 {
		 launchBrowser();
		 driver.get("https://www.cleartrip.com/");
	 }
	 	
	@Test
	public void TestHotel() throws InterruptedException
	{
		
		//Implicit Wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //To avoid NullPointerException thrown by @FindBy annotation,use PageFactory's initElements method.
        PageFactory.initElements(driver, HotelBookingTest.class); 
        hotels.click();
        localityTextBox.clear();
        localityTextBox.sendKeys("Indiranagar, Bangalore");
    	
        //selecting the first option from the Dropdown
    	List<WebElement> dropdownoption= localityAutocompleteList.findElements(By.tagName("li"));
    	dropdownoption.get(1).click();

        //Checkin Date is May 1st
    	checkinDate.findElement(By.xpath("/html/body/div[6]/div[2]/table/tbody/tr[1]/td[3]/a")).click();
        
    	//CheckOut Date is May 3rd
    	checkoutDate.click();         
    	driver.findElement(By.xpath("/html/body/div[6]/div[1]/table/tbody/tr[1]/td[5]/a")).click();
    	
    	//Selecting Travellers from the Dropdown
    	new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        searchButton.click();
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