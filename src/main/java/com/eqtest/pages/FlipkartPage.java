package com.eqtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipkartPage {
	WebDriver driver;
	
	public FlipkartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@class='LM6RPg']")
	static WebElement txtSearchBox;
	@FindBy(css = ".vh79eN[type='Submit']")
	static WebElement btnSearch;
	@FindBy(xpath = "//button[@class='_2AkmmA _29YdH8']")
	static WebElement 	btnCloseOnHompage;	
		
	
	public void searchProduct(String productName) {
		btnCloseOnHompage.click();
		txtSearchBox.sendKeys(productName);
		btnSearch.click();
		try {
		Thread.sleep(5000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProductPrice(String phoneModel,String phoneMemory,String phoneColor) {
		String sprice = null;
		String sproduct = phoneModel + " (" + phoneColor + ", " + phoneMemory + ")";
		WebElement element = driver.findElement(By.xpath("//div[contains(text(),'"+sproduct+"')]"));
		String name = element.getText();
		if(name.contains(phoneColor)&&name.contains(phoneMemory)) {
			if(element.isDisplayed()) {
				sprice = element.findElement(By.xpath("//..//..//div[@class='_1vC4OE _2rQ-NK']")).getText();
			}
		}
		else System.out.println("Search results not displayed");
		
		return sprice;
	}
	
	

}
