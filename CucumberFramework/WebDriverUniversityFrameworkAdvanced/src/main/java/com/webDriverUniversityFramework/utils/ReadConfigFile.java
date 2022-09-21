package com.webDriverUniversityFramework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigFile {

	protected InputStream input = null;
	protected Properties properties = null;
	
	public ReadConfigFile()  {
		
		try(FileInputStream fis = new FileInputStream(new File(Constant.CONFIG_PROPERTIES_DIRECTORY).getAbsolutePath())){
			properties = new Properties();
			properties.load(fis);
		} catch(Exception e) {
			e.getMessage();
		}
	}
	
	public String getBrowser() {
		String browserName = properties.getProperty("browserName");
		if (browserName.isEmpty()) {
			return "";
		}
		return browserName;
		
	}
	
}
