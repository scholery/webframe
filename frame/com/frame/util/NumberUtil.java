package com.frame.util;

import java.math.BigDecimal;

public class NumberUtil {

	public static String BigDecimal2String(BigDecimal bd) {
		if (null == bd) {
			return null;
		}
		return bd.toString();
	}
	
	public static Float BigDecimal2Float(BigDecimal bd) {
		if (null == bd) {
			return null;
		}
		return bd.floatValue();
	}
	
	public static long BigDecimal2Long(BigDecimal bd) {
		if (null == bd) {
			return 0l;
		}
		return bd.longValue();
	}
	
	public static long BigDecimal2Long(Object bd) {
		if (null == bd || bd instanceof BigDecimal == false) {
			return 0l;
		}
		
		return BigDecimal2Long((BigDecimal)bd);
	}
	
	public static long String2Long(String obj) {
		if (null == obj) {
			return 0;
		}
		try{
			return Long.parseLong(obj);
		}catch(Exception e){
			
		}
		return 0;
	}
	
	public static long Obj2Long(Object obj) {
		if (null == obj) {
			return 0;
		}
		try{
			return Long.parseLong(obj.toString());
		}catch(Exception e){
			
		}
		return 0;
	}
	
	public static int Obj2Int(Object obj) {
		if (null == obj) {
			return 0;
		}
		try{
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			
		}
		return 0;
	}
	
	public static Integer Obj2Integer(Object obj) {
		if (null == obj) {
			return null;
		}
		try{
			return Integer.parseInt(obj.toString());
		}catch(Exception e){
			
		}
		return null;
	}
	
	public static float String2Float(String str) {
		if (null == str) {
			return 0.0f;
		}
		try {
			return Float.parseFloat(str);
		} catch (Exception e) {
			
		}
		return 0.0f;
	}
}
