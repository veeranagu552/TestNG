package com.caseStudy1.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.ConfigFileReader;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.HelperClass;

public class FramesPageObject extends Base {
	private HelperClass helperClass = new HelperClass();
	private Logger logger = LogManager.getLogger(FramesPageObject.class);
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private String url;
	private WebDriver driver = DriverManager.getDriver();
	public FramesPageObject() {
		logger.info("Frames Object");
		PageFactory.initElements(driver, this);
	}
	
	public void openUrl() {
		url = configFileReader.getFramesUrl();
		helperClass.getUrl(url);
	}
	
	@FindBy(xpath="//iframe[@id='singleframe']")
	WebElement singleFrameElemet;	
	
	private void switchToSingleFrame() {
		logger.info("Switching to frame");
		helperClass.switchToFrame(singleFrameElemet);
	}
	
	@FindBy(xpath="//input[@type='text']")
	WebElement textBox;
	
	private void enterTextInTextBox(String text) {
		logger.info("Entering "+text+" in text box");
		helperClass.sendKeys(textBox, text);
	}
	
	public void enterTextInSingleFrameTextBox(String text) {
		switchToSingleFrame();
		enterTextInTextBox(text);
		helperClass.switchToDefaultContent();
	}
	
	@FindBy(xpath="//a[@href='#Multiple']")
	WebElement nestedFrameButton;
	
	private void clickNestedFrameButton() {
		logger.info("Clicking nested frame button");
		helperClass.clickElement(nestedFrameButton);
	}
	
	@FindBy(xpath="//iframe[@src='MultipleFrames.html']")
	WebElement outerFrame;
	
	private void switchToOuterFrame() {
		logger.info("Switching to outer frame");
		helperClass.switchToFrame(outerFrame);
	}

	@FindBy(xpath="//iframe[@src='SingleFrame.html' and contains(@style,'250')]")
	WebElement nestedFrameElement;
	
	private void switchToInnerFrame() {
		logger.info("Switching to nested frame");
		helperClass.switchToFrame(nestedFrameElement);
	}
	
	public void enterTextInNestedFramesTextBox(String text) {
		clickNestedFrameButton();
		switchToOuterFrame();
		switchToInnerFrame();
		enterTextInTextBox(text);
		helperClass.switchToDefaultContent();
	}
}
