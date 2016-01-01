package com.frame.util;

import java.lang.reflect.Method;

public class ReflectionUtils {
	
	public static void invokeAndWrap(String method,Class<?>[] parameterTypes,Object bean,Object...parameters) {
		try {
			Method targetMethod = bean.getClass().getDeclaredMethod(method, parameterTypes);
			targetMethod.invoke(bean, parameters);
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}
}
