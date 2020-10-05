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

public class GilletteSignInPageObjects {
	private HelperClass helperClass = new HelperClass();
	private WebDriver driver = DriverManager.getDriver();
	private Logger logger = LogManager.getLogger(GilletteSignInPageObjects.class);
	private ConfigFileReader configFileReader = new ConfigFileReader();
	public GilletteSignInPageObjects() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@data-key='signInEmailAddress']")
	WebElement indiaEmailElement;

	@FindBy(id = "username")
	WebElement austriaEmailElement;

	private void enterIndiaEmail(String email) {
		helperClass.sendKeys(indiaEmailElement, email);
	}

	private void enterAustriaEmail(String email) {
		helperClass.sendKeys(austriaEmailElement, email);
	}

	public void enterEmail(String email) {
		if (helperClass.getTitle().contains("Deutschland")) {
			logger.info("Entering email for austria");
			enterAustriaEmail(email);
		} else {
			logger.info("Entering email for india");
			enterIndiaEmail(email);
		}
	}

	@FindBy(xpath = "//input[@type='password']")
	WebElement passwordElement;

	public void enterPassword(String password) {
		helperClass.sendKeys(passwordElement, password);
	}

	@FindBy(xpath = "//input[@type='submit']")
	WebElement signInButtonIndia;
	
	private void clickIndiaSignInButton() {
		helperClass.clickElement(signInButtonIndia);
	}
	
	@FindBy(id = "login-submit")
	WebElement signInButtonAustria;
	
	private void clickAustriaSignInButton() {
		helperClass.clickElement(signInButtonAustria);
	}
	@FindBy(className = "logoutbtn")
	WebElement logOutButtonIndia;
	
	private void clickLogOutIndia() {
		helperClass.clickElement(logOutButtonIndia);
	}
	
	private void clickLogoutAustria() {
		
	}
	
	public void clickLogout() {
		if (helperClass.getTitle().contains("Deutschland")) {
			logger.info("log out -  austria");
			clickLogoutAustria();
		} else {
			logger.info("clicking sign in button - india");
			clickLogOutIndia();	
		}
	}

	public void clickSignInButton() {
		if (helperClass.getTitle().contains("Deutschland")) {
			logger.info("clicking sign in button -  austria");
			clickAustriaSignInButton();
		} else {
			logger.info("clicking sign in button - india");
			clickIndiaSignInButton();	
		}
	}

	
	public void openUrl(String country) {
		String url = null;
		if(country.contentEquals("india")) {
			logger.info("India url");
			url = configFileReader.getGilletteIndiaUrl();
			helperClass.getUrl(url);
		}
		else {
			logger.info("Austri url");
			url = configFileReader.getGilletteAustriaUrl();
			helperClass.getUrl(url);
		}
	}
}
