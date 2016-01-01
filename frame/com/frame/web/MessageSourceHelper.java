package com.frame.web;

import java.util.Properties;


public class MessageSourceHelper {
	public static String getText(String code){
		return ((Properties)SpringContextAgent.getBean("resourceMessage")).getProperty(code, null);
	}
}
