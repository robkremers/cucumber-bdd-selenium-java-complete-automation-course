package com.WebDriverUniversityFrameworkSubPages.utils;

import java.nio.file.Paths;

public class Constant {
	
	/** Configuration Properties file */
	public final static String CONFIG_PROPERTIES_DIRECTORY = System.getProperty("user.dir") + "/src/main/java/com/WebDriverUniversityFrameworkSubPages/properties/config.properties";
	
	public final static String CHROME_DRIVER_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/WebDriverUniversityFrameworkSubPages/resources/chromedriver";
	public final static String GECKO_DRIVER_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/WebDriverUniversityFrameworkSubPages/resources/geckodriver";
	public final static String IE_DRIVER_DIRECTORY = "";

	public final static String REPORTS_CONFIG_XML = System.getProperty("user.dir") + "/src/main/java/com/WebDriverUniversityFrameworkSubPages/utils/ReportsConfig.xml";
}
