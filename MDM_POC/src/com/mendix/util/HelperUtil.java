package com.mendix.util;

import java.util.Map;

import com.mendix.tool.*;


// TODO: Auto-generated Javadoc
/**
 * The Class HelperUtil.
 */
public class HelperUtil {
	
/** The execution config map. */
public static Map<String,String> executionConfigMap;

static{
	executionConfigMap=PropertyFileUtil.getPropertiesAsMap(Constants.EXECUTION_CONFIG_FILE);
}


}