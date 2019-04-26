
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FlightBookingTest {
	
    static WebDriver driver ;
   
    
    @Test
    public void testThatResultsAppearForAOneWayJourney() {
    	launchBrowser();
        driver.get("https://www.cleartrip.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("OneWay")).click();
        driver.findElement(By.id("FromTag")).clear();
        driver.findElement(By.id("FromTag")).sendKeys("Bangalore");

        WebElement el1=driver.findElement(By.id("ui-id-1"));
		 List<WebElement> originOptions=el1.findElements(By.xpath("//*[starts-with(@id,'ui-id')]/li"));
		 originOptions.get(0).click();
			
        driver.findElement(By.id("ToTag")).clear();
        driver.findElement(By.id("ToTag")).sendKeys("Delhi");


        //select the first item from the destination auto complete list
        List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
        destinationOptions.get(0).click();
         //click on  may 1st
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[1]/td[3]/a")).click();
        //all fields filled in. Now click on search
        driver.findElement(By.id("SearchBtn")).click();

        //verify that result appears for the provided journey search
        AssertJUnit.assertTrue(isElementPresent(By.className("searchSummary")));

        //close the browser
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
