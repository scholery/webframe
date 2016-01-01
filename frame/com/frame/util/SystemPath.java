package com.frame.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

public class SystemPath {
	
	public static String getRootPath(){
		return getClassLocationBefore(SystemPath.class, "WEB-INF");
	}
	
	public static String getWebInfPath(){
		return getClassLocationBefore(SystemPath.class, "WEB-INF")+"WEB-INF/";
	}
	
	public static String getAbsolutePath(String relativePath) {
		return getRootPath() + relativePath;
	}
	
	private static String getClassLocationBefore(final Class cls, String folder) {
		String returnStr = getClassLocation(cls);
		int iPos = returnStr.indexOf(folder);
		if (iPos >= 0) {
			returnStr = returnStr.substring(0, iPos);
		}
		if (!returnStr.endsWith("/"))
			returnStr += "/";

		Properties props = System.getProperties();
		String osName = props.getProperty("os.name");

		if (-1 == osName.toLowerCase().indexOf("windows"))
			returnStr = "/" + returnStr;
		return returnStr;
	}
	
	private static String getClassLocation(final Class cls) {
		String returnStr = "";
		if (cls == null) {
		} else {
			URL url = cls.getProtectionDomain().getCodeSource().getLocation();
			try {
				returnStr = URLDecoder.decode(url.getPath(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (returnStr.length() != 0) {
				// filter the first character
				returnStr = returnStr.substring(1);
			}
		}
		return returnStr;
	}

}
