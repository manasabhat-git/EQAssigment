package com.eqtest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eqtest.utility.BrowserUtil;

public class TripAdvisorPage {

	WebDriver driver;
	
	public TripAdvisorPage(WebDriver driver) {
		this.driver=driver;
	}
	
		
	By txtSearchBox = By.xpath("//input[@title='Search']");
	By linksResults = By.xpath("//div[@class='result-title']");
	By tabReview = By.xpath("//span[text()='Reviews']");
	By btnWriteReview  = By.xpath("//a[text()='Write a review']");
	By btnBubbleRate = By.xpath("//span[@id='bubble_rating']");
	By txtReviewTitle = By.xpath("//input[@id='ReviewTitle']");
	By txtReviewText = By.xpath("//textarea[@id='ReviewText']");
	
	
	public boolean searchHotel(String shotelName, String sSearchFor) {
		
		try {
			
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		Thread.sleep(10000);
		
		List<WebElement> elements = driver.findElements(txtSearchBox);
		int index = elements.size()-1;
		elements.get(index).click();
		Thread.sleep(10000);
		elements.get(index).sendKeys(shotelName);
		elements.get(index).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);
		
		System.out.println("Searched " + shotelName);
		
		List<WebElement> resultElements = driver.findElements(linksResults);
		resultElements.get(0).click();
		System.out.println("Clicked on the first search link");
		Thread.sleep(10000); 
		if(BrowserUtil.windowHandle(driver, shotelName, sSearchFor)) {
			return true;
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void writeReview(String sReviewComments, int ireviewRating) {
		try{
			Thread.sleep(10000); 
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(5000);
			
			driver.findElement(tabReview).click();
			Thread.sleep(5000);
			
			driver.findElement(btnWriteReview).click();
			System.out.println("Clicked on write review button");
			Thread.sleep(10000);
			
			BrowserUtil.windowHandle(driver, "write a review");
			
			Thread.sleep(5000);
			
			if(selectRatingByHover(ireviewRating)) {
				if(provideReviewComments(sReviewComments)) {
					System.out.println("Selecting rating and providing review comments was success");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean selectRatingByHover(int nRating) {
		try {
			WebElement bubbleRater = driver.findElement(btnBubbleRate);
			Actions actions = new Actions(driver);
			actions.moveToElement(bubbleRater);
			
			actions.moveByOffset(-60, 0).click().perform();
			for(int i=1;i<nRating;i++) {
				actions.moveByOffset(30, 0).click().perform();
			}	
			System.out.println("Rating selected");
			Thread.sleep(10000);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean provideReviewComments(String str) {
		try {
			Thread.sleep(5000);
			if(driver.findElement(txtReviewTitle).isDisplayed()) {
				driver.findElement(txtReviewTitle).sendKeys(str);
				Thread.sleep(5000);
			}
			if(driver.findElement(txtReviewText).isDisplayed()) {
				driver.findElement(txtReviewText).sendKeys(str);
				Thread.sleep(5000);
			}
			System.out.println("Review comments entered");
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,2000)");
			if(driver.findElement(By.xpath("//input[@id='noFraud']")).isDisplayed()){ 
				driver.findElement(By.xpath("//input[@id='noFraud']")).click();
				if(driver.findElement(By.xpath("//input[@id='noFraud']")).isSelected())
				System.out.println("Checkbox Submit your review checked");
				else
					System.out.println("Checkbox not clicked");
				Thread.sleep(5000);
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
			
	
}
