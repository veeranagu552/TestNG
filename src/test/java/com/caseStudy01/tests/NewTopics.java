package com.caseStudy01.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.caseStudy1.utils.ConfigFileReader;

public class NewTopics {
	public static void main(String[] args) {
		ConfigFileReader config = new ConfigFileReader();
		System.setProperty(config.getChromeDriverProperty(), config.getChromeDriverPath());

		// Create object of ChromeOptions class
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);

		// dasabling developer mode extention
		options.addArguments("--disable-extensions");

		// chrome show notifications popup

		// Start the chrome session
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://www.google.com");
		
		driver.quit();
	}
}
