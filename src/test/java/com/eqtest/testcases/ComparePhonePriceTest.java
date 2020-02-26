package com.eqtest.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.eqtest.pages.AmazonPage;
import com.eqtest.pages.FlipkartPage;
import com.eqtest.utility.JsonParser;
import com.eqtest.utility.MiscUtil;

import base.BaseHelper;

public class ComparePhonePriceTest extends BaseHelper {

	
	@Test
	public void phoneCompare() throws Exception {
		String url = null;	
		Map<String,String> map = new HashMap<String,String>();
		
		JsonParser js = new JsonParser();
		
		map = js.ReadJsonAndConvert("phone.json");
		
		String phoneModel = map.get("model_number");
		String phoneMemory = map.get("memory_size");
		String phoneColor = map.get("color");
				
		String productName = map.get("model_number") + " ("+map.get("memory_size")+")" + " - "+map.get("color");
		
		url = configFile.getProperty("Amazon_URL");
		getURL(url);
		AmazonPage amazonPage = new AmazonPage(driver);
		amazonPage.searchProduct(productName);
		String priceInAmazon = amazonPage.getProductPrice(phoneModel,phoneMemory,phoneColor);
	
		url = configFile.getProperty("Flipkart_URL");
		getURL(url);
		FlipkartPage flipkartPage = new FlipkartPage(driver);
		flipkartPage.searchProduct(productName);
		String priceInFlipkart = flipkartPage.getProductPrice(phoneModel,phoneMemory,phoneColor);
		
		int aprice = Integer.parseInt(MiscUtil.refinedNumberString(priceInAmazon));
		int fprice = Integer.parseInt(MiscUtil.refinedNumberString(priceInFlipkart));
		
		if(aprice==fprice) {
			System.out.println(productName + " is available for same price of " + priceInAmazon + " in both Amazon and Flipkart");
		}
		else if(aprice<fprice) {
			System.out.println(productName + " is available for lesser price of " + priceInAmazon + " in Amazon");
		}
		else if(aprice>fprice) {
			System.out.println(productName + " is available for lesser price of " + priceInFlipkart + " in Flipkart");
		}
		
	}
}
