package com.caseStudy1.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigFileReader {
	
	private Properties properties;
	private final String propertiesFilePath = "src/main/resources/Configuration.properties";
	private Logger logger = LogManager.getLogger(ConfigFileReader.class);

	public ConfigFileReader() {
		logger.info("configFileReader object created");
		File file = new File(propertiesFilePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			logger.fatal("properties file not found");
		}
		properties = new Properties();
		try {
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//All the following methods return property value specified by key

	public String getBrowserName() {
		String browserName = properties.getProperty("browser");
		if (browserName != null)
			return browserName;
		else
			throw new RuntimeException("Browser name is not specified in properties file");
	}

	public String getChromeDriverPath() {
		String chromeDriverPath = properties.getProperty("chrome_driver_path");
		if (chromeDriverPath != null)
			return chromeDriverPath;
		else
			throw new RuntimeException("Chrome driver path is not specified in properties file");
	}

	public String getFirefoxDriverPath() {
		String firefixDriverPath = properties.getProperty("firefox_driver_path");
		if (firefixDriverPath != null)
			return firefixDriverPath;
		else
			throw new RuntimeException("Firefox driver path is not specified in properties file");
	}

	public int getImplicitWait() {
		String implicitWait = properties.getProperty("implicitWait");
		if (implicitWait != null)
			return Integer.parseInt(implicitWait);
		else
			throw new RuntimeException("Implicit wait is not specified in properties file");
	}

	public String getAutomationTestingUrl() {
		String url = properties.getProperty("url_automation_testing");
		if (url != null)
			return url;
		else
			throw new RuntimeException("Automation testing site url is not specified in properties file");
	}

	public String getGilletteIndiaUrl() {
		String url = properties.getProperty("url_gillette_india");
		if (url != null)
			return url;
		else
			throw new RuntimeException("Gillette India site url is not specified in properties file");
	}

	public String getGilletteAustriaUrl() {
		String url = properties.getProperty("url_gillette_austria");
		if (url != null)
			return url;
		else
			throw new RuntimeException("Gillette Austria site url is not specified in properties file");
	}

	public String getChromeDriverProperty() {
		String chromeDriverProperty = properties.getProperty("chrome_driver_property");
		if (chromeDriverProperty != null)
			return chromeDriverProperty;
		else
			throw new RuntimeException("Chrome driver property is not specified in properties file");
	}

	public String getFirefoxDriverProperty() {
		String firefoxDriverProperty = properties.getProperty("firefox_driver_property");
		if (firefoxDriverProperty != null)
			return firefoxDriverProperty;
		else
			throw new RuntimeException("Firefix driver property is not specified in properties file");
	}

	public String getUrl(String urlProperty) {
		String url = properties.getProperty(urlProperty);
		if (url != null)
			return url;
		else
			throw new RuntimeException(urlProperty + " is not specified in properties file");
	}

	public String getRegistrationExcelFilePath() {
		String registrationFileProperty = properties.getProperty("registration_file_path");
		if (registrationFileProperty != null)
			return registrationFileProperty;
		else
			throw new RuntimeException("Registration file property is not specified in properties file");
	}

	public String getWindowHandlingExcelFilePath() {
		String windowHandlingFileProperty = properties.getProperty("window_handling_file_path");
		if (windowHandlingFileProperty != null)
			return windowHandlingFileProperty;
		else
			throw new RuntimeException("Window handling file property is not specified in properties file");
	}

	public String getAlertsExcelFilePath() {
		String windowHandlingFileProperty = properties.getProperty("alerts_file_path");
		if (windowHandlingFileProperty != null)
			return windowHandlingFileProperty;
		else
			throw new RuntimeException("Alert file property is not specified in properties file");
	}

	public String getFramesExcelFilePath() {
		String framesFileProperty = properties.getProperty("frames_file_path");
		if (framesFileProperty != null)
			return framesFileProperty;
		else
			throw new RuntimeException("frames file property is not specified in properties file");
	}

	public String getDatePickerFilePath() {
		String datePickerFileProperty = properties.getProperty("date_picker_file_path");
		if (datePickerFileProperty != null)
			return datePickerFileProperty;
		else
			throw new RuntimeException("date Picker file property is not specified in properties file");
	}

	public String getGilletteFilePath() {
		String gilletteIndiaFileProperty = properties.getProperty("gillette_file_path");
		if (gilletteIndiaFileProperty != null)
			return gilletteIndiaFileProperty;
		else
			throw new RuntimeException("gillette india file property is not specified in properties file");
	}

	public String getGilletteAustraliaFilePath() {
		String gilletteAustraliaFileProperty = properties.getProperty("gillette_australia_file_path");
		if (gilletteAustraliaFileProperty != null)
			return gilletteAustraliaFileProperty;
		else
			throw new RuntimeException("gillette australia file property is not specified in properties file");
	}

	public String getSelectableFilePath() {
		String selectableFileProperty = properties.getProperty("selectable_file_path");
		if (selectableFileProperty != null)
			return selectableFileProperty;
		else
			throw new RuntimeException("selectable file property is not specified in properties file");
	}

	public String getRegistrationUrl() {
		String registrationResourceProperty = properties.getProperty("registration_resource");
		if (registrationResourceProperty != null)
			return registrationResourceProperty;
		else
			throw new RuntimeException("Registration resource property is not specified in properties file");
	}

	public String getWindowHandlingUrl() {
		String windowHandlingResourceProperty = properties.getProperty("windows_handling_resource");
		if (windowHandlingResourceProperty != null)
			return windowHandlingResourceProperty;
		else
			throw new RuntimeException("Window handling resource property is not specified in properties file");
	}

	public String getAlertsUrl() {
		String alertResourceProperty = properties.getProperty("alerts_resource");
		if (alertResourceProperty != null)
			return alertResourceProperty;
		else
			throw new RuntimeException("Alert handling resource property is not specified in properties file");
	}

	public String getDatePickerUrl() {
		String datePickerResourceProperty = properties.getProperty("date_picker_resource");
		if (datePickerResourceProperty != null)
			return datePickerResourceProperty;
		else
			throw new RuntimeException("Date picker resource property is not specified in properties file");
	}

	public String getFramesUrl() {
		String framesResourceProperty = properties.getProperty("frames_resource");
		if (framesResourceProperty != null)
			return framesResourceProperty;
		else
			throw new RuntimeException("Frames resource property is not specified in properties file");
	}

	public String getSelectableUrl() {
		String selectableResourceProperty = properties.getProperty("selectable_resource");
		if (selectableResourceProperty != null)
			return selectableResourceProperty;
		else
			throw new RuntimeException("Selectable resource property is not specified in properties file");
	}

}
