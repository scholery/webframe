package com.frame.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String objToJson(Object obj){
		String json = "";
		if(obj == null){
			return json;
		}
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			
		}
		return json;
		
	}
	
	public static String listToJson(List list){
		String json = "";
		if(list == null){
			return json;
		}
		try {
			json = mapper.writeValueAsString(list);
		} catch (Exception e) {
			
		}
		return json;
		
	}
	
	public static String objArrayToJson(Object[] objs){
		String json = "";
		if(objs == null || objs.length == 0){
			return json;
		}
		try {
			json = mapper.writeValueAsString(objs);
		} catch (Exception e) {
			
		}
		return json;
		
	}
	
	public static Long[] jsonToArray(String json){
		if(json == null){
			return null;
		}
		Long[] ids = null;
		try {
			ids = mapper.readValue(json, Long[].class);
		} catch (Exception e) {
			
		}
		return ids;
		
	}
	
	public static List<Map> jsonToList(String json){
		List<Map> list = null;
		if(json == null){
			return list;
		}
		try {
			list = mapper.readValue(json, new TypeReference<List<Map>>(){});
		} catch (Exception e) {
			
		}
		return list;
		
	}

}
