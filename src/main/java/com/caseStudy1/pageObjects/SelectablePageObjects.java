package com.caseStudy1.pageObjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.caseStudy1.utils.ConfigFileReader;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.HelperClass;

public class SelectablePageObjects {
	private HelperClass helperClass = new HelperClass();
	private Logger logger = LogManager.getLogger(SelectablePageObjects.class);
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private String url;
	private WebDriver driver = DriverManager.getDriver();
	public SelectablePageObjects() {
		logger.info("Selectable Page Object");
		PageFactory.initElements(driver, this);
	}

	public void openUrl() {
		url = configFileReader.getSelectableUrl();
		helperClass.getUrl(url);
	}

	@FindBy(xpath = "//a[@href='#Serialize']")
	WebElement serializeButton;

	public void clickSerializeButton() {
		helperClass.clickElement(serializeButton);
	}

	@FindBy(xpath = "//ul[@class='deaultFunc']/descendant::b")
	List<WebElement> defaultOptions;

	// checks passed option is exists or not by iterating, returns true if found,
	// else false
	private boolean checkDefaultExists(String option) {
		boolean flag = false;
		for (WebElement element : defaultOptions) {
			String text = element.getText().split("-")[1].trim();
			if (text.equalsIgnoreCase(option))
				flag = true;
		}
		return flag;
	}

	// gets text from currently selected option
	private String getClickedDefaultOptionText() {
		String xpath = "//li[@class='ui-widget-content selected']/b";
		String text = helperClass.getTextFromElement(driver.findElement(By.xpath(xpath))).split("-")[1].trim();
		return text;
	}

	// clicks option if exists or throws exception
	public void clickOptionInDefault(String option) {
		if (checkDefaultExists(option)) {
			String xpath = "//ul[@class='deaultFunc']/descendant::b[contains(text(),'" + option + "')]";
			helperClass.clickElementByXpath(xpath);
			helperClass.assertStrings(getClickedDefaultOptionText(), option);
		} else {
			throw new RuntimeException(option + " not found");
		}
	}

	@FindBy(xpath = "//ul[@class='SerializeFunc']/descendant::b")
	List<WebElement> serializeOptions;

	// checks passed option is exists or not by iterating, returns true if found,
	// else false
	private boolean checkSerializeExists(String option) {
		boolean flag = false;
		for (WebElement element : serializeOptions) {
			String text = element.getText().split("-")[1].trim();
			if (text.equalsIgnoreCase(option))
				flag = true;
		}
		return flag;
	}

	@FindBy(xpath = "//span[@id='result']")
	WebElement serializeSelectedTextElement;

	// returns selected options text
	private String getSelectedSerializeText() {
		return helperClass.getTextFromElement(serializeSelectedTextElement).trim();
	}

	// clicks option if exists or throws exception
	public void clickOptionInSerialize(String option) {
		if (checkSerializeExists(option)) {
			String xpath = "//ul[@class='SerializeFunc']/descendant::b[contains(text(),'" + option + "')]";
			helperClass.clickElementByXpath(xpath);
			helperClass.assertStrings(getSelectedSerializeText(), option);
		} else {
			throw new RuntimeException(option + " not found");
		}
	}
}
