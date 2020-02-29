package com.eqtest.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserUtil {

	public static WebDriver startBrowser(WebDriver driver,String browserName) {
		
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else System.out.println("Browser not supported");
		
		return driver;
		
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
	public static boolean windowHandle(WebDriver driver,String str1, String str2) {
		for(String winHandle : driver.getWindowHandles()){
			String title = driver.switchTo().window(winHandle).getTitle().toLowerCase();
			if (title.contains(str1.toLowerCase()) && title.contains(str2.toLowerCase()) ) {
				driver.switchTo().window(winHandle);
				return true;
			}
		}
		return false;
	}
	
	public static boolean windowHandle(WebDriver driver,String str1) {
		for(String winHandle : driver.getWindowHandles()){
			String title = driver.switchTo().window(winHandle).getTitle().toLowerCase();
			if (title.contains(str1.toLowerCase()) ) {
				driver.switchTo().window(winHandle);
				return true;
			}
		}
		return false;
	}
	
}
