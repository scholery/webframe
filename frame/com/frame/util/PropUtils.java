package com.frame.util;

import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.velocity.tools.generic.SafeConfig;

public class PropUtils extends SafeConfig {

	public Object value(Object obj, String propertyName) throws Exception {
		return PropertyUtils.getProperty(obj, propertyName);
	}

	public int size(Object obj) throws Exception {
		if (obj != null && obj.getClass().isArray()) {
			return ((Object[]) obj).length;
		} else if (obj instanceof Collection<?>) {
			return ((Collection<?>) obj).size();
		} else {
			return 0;
		}
	}
}
