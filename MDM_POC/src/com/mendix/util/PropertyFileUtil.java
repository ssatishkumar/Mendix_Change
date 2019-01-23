package com.mendix.util;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyFileUtil.
 */
public class PropertyFileUtil {

/**
 * Gets the properties as map.
 *
 * @param strFile the str file
 * @return the properties as map
 */
public static Map<String,String> getPropertiesAsMap(String strFile){
	Map<String,String> map = new HashMap<>();
	
	Properties properties=new Properties();
	
	try{
		properties.load(new FileInputStream(strFile));
		
		
		for(Entry<Object, Object> entry:properties.entrySet()){
			String strKey=entry.getKey().toString();
			String strValue=entry.getValue().toString();
			map.put(strKey, strValue);
		}
	}
	catch(Exception e){
		
	}
	
	return map;
}
}
