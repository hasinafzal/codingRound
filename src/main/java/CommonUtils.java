import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.sun.javafx.PlatformUtil;


public class CommonUtils {
	public static WebDriver driver;


	public static WebDriver setDriverPath() {
	    if (PlatformUtil.isMac()) {
	        System.setProperty("webdriver.chrome.driver", "chromedriver");
	    }
	    if (PlatformUtil.isWindows()) {
	    	
			disablenotification();
			
			}
	    if (PlatformUtil.isLinux()) {
	        System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
	    }
	    return driver;
	}
	public static void disablenotification()
	{
		WebDriverManager.chromedriver().setup();

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
	}
}
