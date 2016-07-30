package com.chenxf.utils;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	
	//属性名标记（是否需要解密）
	private String[] encryptPropNames={"jdbc.username","jdbc.password"};
	
	@Override
	protected String convertProperty(String propertyName, String propertyValue){
		if(isEncryptProp(propertyName)){
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}
	
	private boolean isEncryptProp(String propertyName){
		for(String encryptPropName: encryptPropNames){
			if(encryptPropName.equals(propertyName)){
				return true;
			}
		}
		return false;
	}

}
