package com.caseStudy1.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.caseStudy1.utils.ConfigFileReader;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.HelperClass;

public class AlertsPageObject {
	private HelperClass helperClass = new HelperClass();
	private String url;
	private Logger logger = LogManager.getLogger(AlertsPageObject.class);
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private WebDriver driver = DriverManager.getDriver();
	public AlertsPageObject() {
		logger.info("Alerts Page Object");
		PageFactory.initElements(driver, this);
	}

	public void openUrl() {
		url = configFileReader.getAlertsUrl();
		helperClass.getUrl(url);
	}

	@FindBy(xpath = "//button[@class='btn btn-danger']")
	WebElement alert1Box;

	@FindBy(xpath = "//ul[@class='nav nav-tabs nav-stacked']/child::li[2]")
	WebElement alert2Button;

	@FindBy(xpath = "//button[@class='btn btn-primary']")
	WebElement alert2Box;

	@FindBy(id = "demo")
	WebElement alert2Text;

	@FindBy(xpath = "//ul[@class='nav nav-tabs nav-stacked']/child::li[3]")
	WebElement alert3Button;

	@FindBy(xpath = "//button[@class='btn btn-info']")
	WebElement alert3Box;

	@FindBy(id = "demo1")
	WebElement alert3Text;

	public String getAlert3Text(String name) {
		return helperClass.getTextFromElement(alert3Text);
	}

	public void clickAlert1Box() {
		logger.info("Clicking Alert1 Box");
		helperClass.clickElement(alert1Box);
	}

	public void acceptAlert1() {
		logger.info("Accepting alert1");
		helperClass.acceptAlert();
	}

	public void clickAlert2Button() {
		logger.info("Clicking alert 2 button");
		helperClass.clickElement(alert2Button);
	}

	public void clickAlert2Box() {
		logger.info("Clicking alert 2 box");
		helperClass.clickElement(alert2Box);
	}

	// if string passed is ok, accepts alert
	// if string passed in cancel, dismisses alert
	// else throws exception
	public void alert2Action(String action) {
		String expected = null;
		if (action.equalsIgnoreCase("OK")) {
			acceptAlert2();
			expected = "You pressed Ok";
		} else if (action.equalsIgnoreCase("CANCEL")) {
			dismissAlert2();
			expected = "You Pressed Cancel";
		} else {
			logger.info("Invalid action: " + action);
			try {
				throw new Exception("Invalid Action " + action);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// asserting texts
		if (expected != null)
			helperClass.assertStrings(getAlert2Text(), expected);
	}

	private void acceptAlert2() {
		logger.info("Accepting alert2");
		helperClass.acceptAlert();
	}

	private void dismissAlert2() {
		logger.info("Dismissing Alert2");
		helperClass.cancelAlert();
	}

	private String getAlert2Text() {
		logger.info("Getting alert2 text");
		return helperClass.getTextFromElement(alert2Text);
	}

	public void clickAlert3Button() {
		helperClass.clickElement(alert3Button);
	}

	public void clickAlert3Box() {
		helperClass.clickElement(alert3Box);
	}

	private void enterTextInAlert3(String keysToSend) {
		logger.info("Entering " + keysToSend + " in alert box");
		helperClass.sendKeysAlert(keysToSend);
	}

	// accepts alert with passed name, if nothing passed, Automation Testing user
	// default
	public void acceptAlert3(String name) {
		enterTextInAlert3(name);
		if (name == null)
			name = "Automation Testing user";
		logger.info("Accepting alert with :" + name);
		helperClass.acceptAlert();
		String expected = "Hello " + name + " How are you today";
		helperClass.assertStrings(getAlert3Text(), expected);
	}

	public void dismissAlert3() {
		logger.info("Dismissing Alert");
		helperClass.cancelAlert();
	}

	private String getAlert3Text() {
		return helperClass.getTextFromElement(alert3Text);
	}
}
