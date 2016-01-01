package com.frame.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring Application Context Agent
 * @author kylin
 * @since  2008-3-6
 */
public class SpringContextAgent implements ApplicationContextAware {

	/**
	 * Spring Application Context
	 */
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	public static Object getBean(String name,Object[] args) {
		return applicationContext.getBean(name,args);
	}
	
	public static Object getBeanByClassName(Class className) {
		return applicationContext.getBean(className.getName());
	}
	
	public static <T>  T getBean(Class<T> className){
		return (T)getBeanByClassName(className);
	}
	
	public static boolean isBeanDefined(String name){
		return applicationContext.containsBeanDefinition(name);
	}

}
