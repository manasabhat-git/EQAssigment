package com.eqtest.testcases;

import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import com.eqtest.pages.TripAdvisorPage;
import com.eqtest.utility.JsonParser;

import base.BaseHelper;

public class WriteHotelReviewTest extends BaseHelper{
	
	@Test
	public void writeReview(){
	
		String url = configFile.getProperty("TripAd_URL");
		System.out.println("opening " + url);
		getURL(url);
		System.out.println("URL opened");
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonParser js = new JsonParser();
		
		try {
			map = js.ReadJsonAndConvert("comments.json");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String strHotelName =  map.get("search_text").toString();
		String strSearchFor = map.get("search_for").toString();
		String strReviewComments=map.get("review_comment").toString();
		int ireviewRating=Integer.parseInt(map.get("review_rating").toString()) ;
		System.out.println("Starting search...");
		
		TripAdvisorPage tripPage=new TripAdvisorPage(driver);
		if(tripPage.searchHotel(strHotelName,strSearchFor)) {
			tripPage.writeReview(strReviewComments,ireviewRating);
		}
		
		
	}

}
