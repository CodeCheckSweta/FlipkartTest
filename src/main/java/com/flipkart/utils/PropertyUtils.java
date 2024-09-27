package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import com.flipkart.constants.FrameworkConstants;

public final class PropertyUtils {
	private PropertyUtils() {}
	
	private static Properties properties = new Properties(); 
	private static final Map<String, String> CONFIGMAP = new HashMap<>();
	
	static {
		try {
			FileInputStream propFileReader = new FileInputStream(FrameworkConstants.getConfigfilepath());
			properties.load(propFileReader);			
			for(Map.Entry<Object, Object> entry: properties.entrySet()) {
				CONFIGMAP.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()).trim()); //remove trailing n leading spaces
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("project.properties file not found at property file path "+ FrameworkConstants.getConfigfilepath());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static String get(String key) throws Exception {
		if(Objects.isNull(key)||Objects.isNull(CONFIGMAP.get(key))) {
			throw new Exception("Property name "+ key + " is not found. Please check config.properties file.");
		}
		return CONFIGMAP.get(key);
	}
	
}
