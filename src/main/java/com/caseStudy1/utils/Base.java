package com.caseStudy1.utils;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.google.common.io.Files;

public class Base {
	protected WebDriver driver;
	private ConfigFileReader configFileReader;
	static Logger logger = LogManager.getLogger(Base.class);

	@BeforeTest()
	public void setup() {
		configFileReader = new ConfigFileReader();
		logger.info("Starting driver");
		driver = DriverManager.getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitWait(), TimeUnit.SECONDS);

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE); // capturing screenshot
		String destinationFile = System.getProperty("user.dir") + "\\Reports\\" + testCaseName + ".png";
		File dest = new File(destinationFile);
		try {
			Files.copy(source, dest); // copying file
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationFile; // returning copied file path
	}

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

	@DataProvider(name = "alertsData")
	public Object[][] getAlertsData() {
		return ExcelFileReader.readExcel(configFileReader.getAlertsExcelFilePath());
	}

	@DataProvider(name = "dataPickerData")
	public Object[][] getDatePickerData() {
		return ExcelFileReader.readExcel(configFileReader.getDatePickerFilePath());
	}

	@DataProvider(name = "framesData")
	public Object[][] getFramesData() {
		return ExcelFileReader.readExcel(configFileReader.getFramesExcelFilePath());
	}

	@DataProvider(name = "gilletteData")
	public Object[][] getGilletteAustraliaData() {
		return ExcelFileReader.readExcel(configFileReader.getGilletteFilePath());
	}

	@DataProvider(name = "registration")
	public Object[][] getRegistrationData() {
		return ExcelFileReader.readExcel(configFileReader.getRegistrationExcelFilePath());
	}

	@DataProvider(name = "selectableData")
	public Object[][] getSelectableData() {
		return ExcelFileReader.readExcel(configFileReader.getSelectableFilePath());
	}

	@DataProvider(name = "windowHandling")
	public Object[][] getWindowHanldingData() {
		return ExcelFileReader.readExcel(configFileReader.getWindowHandlingExcelFilePath());
	}
}
