package base;
import java.io.FileReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.eqtest.utility.BrowserUtil;

public class BaseHelper {
	
	protected WebDriver driver;
	protected Properties configFile = new Properties();
	
	@BeforeTest
	public void init() {
		
		driver = BrowserUtil.startBrowser(driver, "Chrome");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try {
			FileReader reader = new FileReader("./src/main/resources/config.properties");
			configFile.load(reader);
		} catch(Exception e) {
			e.printStackTrace();
		}
	      
	   
	}
	
	
	@AfterTest
	public void e() {
		BrowserUtil.quitBrowser(driver);
	}
	
	public void getURL(String url) {
		driver.get(url);
	}
	
		
	
}
