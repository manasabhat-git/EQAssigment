package com.eqtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonPage {
	WebDriver driver;
//
	public AmazonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "twotabsearchtextbox")
	static WebElement txtSearchBox;
	@FindBy(css = ".nav-input[type='Submit']")
	static WebElement btnSearch;
	
	
	public void searchProduct(String productName) {
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
		String sMemoryInAmazon = phoneMemory.replace(" ", "");
		
		String sproduct = phoneModel + " (" + sMemoryInAmazon + ") - " + phoneColor;
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + sproduct +"')]"));
		String name = element.getText();
		
		if(name.contains(phoneColor)&&name.contains(sMemoryInAmazon)) {
			if(element.isDisplayed()) {
				sprice = element.findElement(By.xpath("//..//..//..//..//..//..//..//span[@class='a-price-whole']")).getText();
			}
		}
		else System.out.println("Search results not displayed");
		
		return sprice;
	}
}
