package com.frame.util;

public class AssertUtil {
    public static void isNotNull(Object obj, String msg) {
	if (obj == null) {
	    throw new IllegalArgumentException(msg);
	}
    }

    public static void isNotNull(Object obj) {
	isNotNull(obj, "The value is null.");
    }

    public static void isNotEmpty(String str, String msg) {
	if ((str == null) || (str.length() == 0)) {
	    throw new IllegalArgumentException(msg);
	}
    }

    public static void isNotEmpty(String str) {
	isNotEmpty(str, "The value is null.");
    }

    public static void isFalse(boolean blVal, String msg) {
	if (blVal) {
	    throw new IllegalArgumentException(msg);
	}
    }

    public static void isFalse(boolean blVal) {
	isFalse(blVal, "The value is not false.");
    }

    public static void isTrue(boolean blVal, String msg) {
	if (!blVal) {
	    throw new IllegalArgumentException(msg);
	}
    }

    public static void isTrue(boolean blVal) {
	isTrue(blVal, "The value is not true.");
    }

    public static void areEqual(Object obj1, Object obj2, String msg) {
	if (!obj1.equals(obj2)) {
	    throw new IllegalArgumentException(msg);
	}
    }

    public static void areEqual(Object obj1, Object obj2) {
    	areEqual(obj1, obj2, "The object is not equal.");
    }

    public static void isNotEmpty(Object[] array, String message) {
		if (isEmpty(array)) {
		    throw new IllegalArgumentException(message);
		}
    }

    public static boolean isEmpty(Object[] array) {
	return (array == null || array.length == 0);
    }

    public static boolean isEmpty(byte[] array) {
	return (array == null || array.length == 0);
    }

    public static void isNotEmpty(byte[] array, String message) {
	if (isEmpty(array)) {
	    throw new IllegalArgumentException(message);
	}
    }

    public static void isNotEmpty(byte[] array) {
	isNotEmpty(
		array,
		"[Assertion failed] - this byte array must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Object[] array) {
	isNotEmpty(
		array,
		"[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

}
