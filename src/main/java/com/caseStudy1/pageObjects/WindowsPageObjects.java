package com.caseStudy1.pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.caseStudy1.utils.ConfigFileReader;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.HelperClass;

public class WindowsPageObjects {
	private HelperClass helperClass = new HelperClass();
	private WebDriver driver =DriverManager.getDriver();
	private ConfigFileReader configFileReader = new ConfigFileReader();
	Logger logger = LogManager.getLogger(WindowsPageObjects.class);
	private String url;

	public WindowsPageObjects() {
		logger.info("Window Handling Page Object");
		PageFactory.initElements(driver, this);
	}

	public void openUrl() {
		url = configFileReader.getWindowHandlingUrl();
		helperClass.getUrl(url);
	}

	@FindBy(xpath = "//a[@target='_blank']/child::button")
	WebElement newTabButton;

	public void clickNewTab() {
		logger.info("Clicking new tab button");
		helperClass.clickElement(newTabButton);
	}

	@FindBy(xpath = "//section[@class='hero homepage']/h1[1]")
	WebElement textNewTabWindow;

	@FindBy(id = "email")
	WebElement emailElement;

	@FindBy(xpath = "//a[@href='#Seperate']")
	WebElement newSperateWindowsButton;

	public void clickNewSeperateWindowsButton() {
		logger.info("Clicking new seperate windows button");
		helperClass.clickElement(newSperateWindowsButton);
	}

	@FindBy(xpath = "//button[text()='click']")
	WebElement clickInNewSeperateWindow;

	public void clickToOpenNewSeperateWindow() {
		logger.info("Clicking click button");
		helperClass.clickElement(clickInNewSeperateWindow);
	}

	@FindBy(xpath = "//a[@href='#Multiple']")
	WebElement newSperateMultipleWindowsButton;

	@FindBy(xpath = "//button[text()='click ']")
	WebElement clickInNewSeperateMultipleWindows;

	public void clickToOpenMultipleWindows() {
		logger.info("Clicking click button");
		helperClass.clickElement(clickInNewSeperateMultipleWindows);
	}

	public void clickNewSeperateMultipleWindowsButton() {
		logger.info("Clicking new seperate multiple windows button");
		helperClass.clickElement(newSperateMultipleWindowsButton);
	}
	//switches to child window, gets title, closes window, returns title
	private String getWindowTitle(String parent) {
		logger.info("Getting window title");
		String stringToReturn = null;
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> windowIterator = windows.iterator();
		while (windowIterator.hasNext()) {
			String child = windowIterator.next();
			if (!parent.equals(child)) {
				helperClass.switchToWindow(child);
				stringToReturn = helperClass.getTitle();
				helperClass.closeWindow();
				helperClass.switchToWindow(parent);
				return stringToReturn;
			}
		}

		return stringToReturn;
	}

	public void assertTitle(String expectedTitles) {
		String[] expectedTitlesArray = expectedTitles.split(",");//splitting and storing titles
		logger.info("Asserting title " + expectedTitlesArray[0]);
		String parentWindow = driver.getWindowHandle();
		String actualTitle = getWindowTitle(parentWindow);
		//asserting actual title and passed title
		helperClass.assertStrings(actualTitle, expectedTitlesArray[0]); 
		logger.info("Asserting title " + expectedTitlesArray[1]);
		actualTitle = getWindowTitle(parentWindow);
		//asserting actual title and passed title
		helperClass.assertStrings(actualTitle, expectedTitlesArray[1]);
	}

	//switches to child window, asserts text
	public void assertTextSelenium(String expected) {
		logger.info("Asserting text: " + expected);
		String text = null;
		String parent = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!parent.equals(window)) {
				driver.switchTo().window(window);
				text = helperClass.getTextFromElement(textNewTabWindow);
				helperClass.assertStrings(text, expected);
				helperClass.closeWindow();
			}
		}
		helperClass.switchToWindow(parent);
	}
}
