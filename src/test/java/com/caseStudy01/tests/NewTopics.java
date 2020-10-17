package com.caseStudy01.tests;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.caseStudy1.utils.ConfigFileReader;

public class NewTopics {
	public String getScreenShotPathUsingRobotClass(String testCaseName, WebDriver driver) {
		BufferedImage image = null;
		String destinationFile = System.getProperty("user.dir") + "\\Reports\\" + testCaseName + ".png";
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e1) {
			e1.printStackTrace();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

		try {
			ImageIO.write(image, "png", new File(destinationFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationFile;
	}

	public static void main(String[] args) {
		ConfigFileReader config = new ConfigFileReader();
		System.setProperty(config.getChromeDriverProperty(), config.getChromeDriverPath());

		// Create object of ChromeOptions class
		ChromeOptions options = new ChromeOptions();

		// chrome show notifications popup
		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting it will override the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);

		// dasabling developer mode extention
		options.addArguments("--disable-extensions");

		// Start the chrome session
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://www.google.com");

		driver.quit();

	}
}
