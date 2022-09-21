package com.WebDriverUniversityFrameworkSubPages.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.WebDriverUniversityFrameworkSubPages.utils.Constant;

public class ReadConfigFile {
	protected InputStream input = null;
	protected Properties properties = null;

	public ReadConfigFile() {
//		try {
//			input = ReadConfigFile.class.getClassLoader().getResourceAsStream(Constant.CONFIG_PROPERTIES_DIRECTORY);
//			properties = new Properties();
//			properties.load(input);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		try(FileInputStream fis = new FileInputStream(new File(Constant.CONFIG_PROPERTIES_DIRECTORY).getAbsolutePath())){
			properties = new Properties();
			properties.load(fis);
		} catch(Exception e) {
			e.getMessage();
		}
	}

	public String getBrowser() {
		if (properties.getProperty("browserName") == null) {
			System.out.println("The browserName is empty.");
			return "";
		}
			
		return properties.getProperty("browserName");
	}
}
