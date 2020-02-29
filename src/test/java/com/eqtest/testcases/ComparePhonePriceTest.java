package com.eqtest.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.eqtest.pages.AmazonPage;
import com.eqtest.pages.FlipkartPage;
import com.eqtest.utility.JsonParser;
import com.eqtest.utility.CommonUtil;

import base.BaseHelper;

public class ComparePhonePriceTest extends BaseHelper {

	
	@Test
	public void phoneCompare(){
		String url = null;	
		Map<String, Object> map = new HashMap<String,Object>();
		
		JsonParser js = new JsonParser();
		
		try {
			map = js.ReadJsonAndConvert("phone.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String phoneModel = map.get("model_number").toString();
		String phoneMemory = map.get("memory_size").toString();
		String phoneColor = map.get("color").toString();
				
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
		
		int aprice = Integer.parseInt(CommonUtil.refinedNumberString(priceInAmazon));
		int fprice = Integer.parseInt(CommonUtil.refinedNumberString(priceInFlipkart));
		
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
