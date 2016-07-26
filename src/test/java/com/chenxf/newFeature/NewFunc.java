package com.chenxf.newFeature;

import java.lang.reflect.Field;

import org.springframework.aop.framework.ProxyFactoryBean;

public class NewFunc {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		Class clazz = loader.loadClass("org.springframework.aop.framework.ProxyFactoryBean");
		
		Field[] field = clazz.getDeclaredFields();
		
		for(int i=0;i<field.length;i++){
			System.out.println(field[i].getName());
		}
	}

}
