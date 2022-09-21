package com.webDriverUniversityFramework.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.webDriverUniversityFramework.utils.Constant;
import com.webDriverUniversityFramework.utils.ReadConfigFile;

public class TestConstants {

	public static void main(String[] args) throws URISyntaxException {
		
		System.out.println(Constant.CHROME_DRIVER_DIRECTORY);
		System.out.println(Constant.GECKO_DRIVER_DIRECTORY);
		System.out.println(Constant.CONFIG_PROPERTIES_DIRECTORY);
		
		File chromeDriver = new File(Constant.CHROME_DRIVER_DIRECTORY);
		if (chromeDriver.exists()) {
			System.out.println("The chromedriver exists.");
		} else {
			System.out.println("The chromedriver does not exist.");
		}
		
		File geckoDriver = new File(Constant.GECKO_DRIVER_DIRECTORY);
		if (geckoDriver.exists()) {
			System.out.println("The geckodriver exists.");
		} else {
			System.out.println("The geckodriver does not exist.");
		}
		
		File propertiesFile = new File(Constant.CONFIG_PROPERTIES_DIRECTORY);
		if (propertiesFile.exists()) {
			System.out.println("The propertiesFile exists.");
		} else {
			System.out.println("The propertiesFile does not exist.");
		}	
		
		
		ReadConfigFile file = new ReadConfigFile();
		String browserName = file.getBrowser();
		
		System.out.println("The browsername = " + browserName);
		
		String configFilePath = "/Users/rkremers/Documents/My files/Documenten/Studie/Cucumber/CucumberFramework/WebDriverUniversityFrameworkAdvanced/src/main/java/com/webDriverUniversityFramework/properties/config.properties";
		String configFilePathTest = "config.properties";
		ClassLoader classLoader = TestConstants.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(configFilePathTest);

		if (inputStream == null) {
			System.out.println("The inputstream is empty.");
		}
		
//		File configFile = new File(classLoader.getResource(configFilePathTest).getFile());
		
		Path path = Paths.get(propertiesFile.toURI() );
		System.out.println(path.getFileName());
		
		Properties properties = null;

		try(FileInputStream fis = new FileInputStream(propertiesFile.getAbsolutePath())){
			properties = new Properties();
			properties.load(fis);
		} catch(Exception e) {
			e.getMessage();
		} 
		System.out.println(properties.getProperty("browserName"));
		System.out.println("End of the test");
	}

}
