package com.eqtest.utility;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	
	public Map<String, Object> ReadJsonAndConvert(String filename) throws Exception {
		
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader("./src/test/resources/jsonFiles/"+filename);
		
		Object obj = parser.parse(reader);
		JSONObject jo = (JSONObject)obj;
		
		Map<String, Object> map  =new HashMap<String, Object>();
		
		Set<String> list = jo.keySet();
		for(String key:list) {
			map.put(key,  jo.get(key));
			
		}
		
		return map;
		
	}
}


