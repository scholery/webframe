package com.frame.util;

import java.util.Properties;

import com.frame.web.SpringContextAgent;


public class ConfigHelper {
	public static String getProperty(String key){
		return ((Properties)SpringContextAgent.getBean("configProperties")).getProperty(key, null);
	}
}
