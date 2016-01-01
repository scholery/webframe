package com.frame.util;

public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || str.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 对象转成字符串
	 * @param obj
	 * @return
	 */
	public static String Object2String(Object obj) {
		if (null == obj) {
			return "";
		}
		String str = null;
		try {
			str = String.valueOf(obj);
		} catch (Exception e) {
			str = "";
		}
		return str;
	}
}
