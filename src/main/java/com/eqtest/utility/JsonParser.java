package com.eqtest.utility;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	
	public Map<String, String> ReadJsonAndConvert(String filename) throws Exception {
		
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./src/test/resources/jsonFiles/"+filename);
		
		Object obj = parser.parse(reader);
		JSONObject jo = (JSONObject)obj;
		
		HashMap<String, String> map  =new HashMap<String, String>();
		
		Set<String> list = jo.keySet();
		for(String key:list) {
			map.put(key, (String) jo.get(key));
			
		}
		
		return map;
		
	}
}


