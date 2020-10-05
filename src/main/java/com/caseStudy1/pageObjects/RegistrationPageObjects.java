package com.caseStudy1.pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.ConfigFileReader;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.HelperClass;

public class RegistrationPageObjects extends Base {
	private HelperClass helperClass = new HelperClass();
	private Logger logger = LogManager.getLogger(RegistrationPageObjects.class);
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private String url;
	private WebDriver driver = DriverManager.getDriver();
	public RegistrationPageObjects() {
		PageFactory.initElements(driver, this);
		logger.info("Registration Page Object");
	}

	public void openUrl() {
		url = configFileReader.getRegistrationUrl();
		helperClass.getUrl(url);
	}

	@FindBy(xpath = "//input[@ng-model='FirstName']")
	WebElement firstNameElement;

	public void enterFirstName(String firstName) {
		logger.info("Entering " + firstName + " in firstname box");
		helperClass.sendKeys(firstNameElement, firstName);
	}

	@FindBy(xpath = "//input[@ng-model='LastName']")
	WebElement lastNameElemet;

	public void enterSecondName(String secondName) {
		logger.info("Entering " + secondName + " in lastname box");
		helperClass.sendKeys(lastNameElemet, secondName);
	}

	@FindBy(xpath = "//textarea[@ng-model='Adress']")
	WebElement addressElement;

	public void enterAddress(String address) {
		logger.info("Entering " + address + " in address box");
		helperClass.sendKeys(addressElement, address);
	}

	@FindBy(xpath = "//input[@ng-model='EmailAdress']")
	WebElement emailElement;

	public void enterEmail(String email) {
		logger.info("Entering :" + email + " in email box");
		helperClass.sendKeys(emailElement, email);
	}

	@FindBy(xpath = "//input[@ng-model='Phone']")
	WebElement phoneNumberElement;

	public void enterPhoneNumber(String phoneNumber) {
		logger.info("Entering :" + phoneNumber + " in phone number box");
		helperClass.sendKeys(phoneNumberElement, phoneNumber);
	}

	// returns true if gender found
	// finds no of elements with gender. if only 1 found, returns true
	private boolean checkGender(String gender) {
		logger.info("Searching : " + gender);
		String xpath = "//input[@name='radiooptions' and @value='" + gender + "']";
		List<WebElement> elements = helperClass.findElementsByXpath(xpath);
		int noOfElements = elements.size();
		return noOfElements == 1 ? true : false;
	}

	// checks for gender first, if found clicks
	public void clickGender(String gender) {
		if (checkGender(gender)) {
			logger.debug(gender + " found");
			String xpath = "//input[@value='" + gender + "']";
			WebElement element = helperClass.findElementByXpath(xpath);
			helperClass.clickElement(element);
			logger.info(gender + " clicked");
		} else
			logger.info(gender + " not found");
	}

	// returns true if hobby found
	// finds no of elements with hobby. if only 1 found, returns true
	private boolean checkHobby(String hobby) {
		logger.info("Searching: " + hobby);
		String elementXpath = "//input[@value='" + hobby + "']";
		List<WebElement> elements = helperClass.findElementsByXpath(elementXpath);
		int noOfElements = elements.size();
		return noOfElements == 1 ? true : false;
	}

	// checks for hobby first, if found clicks
	public void clickHobbiesCheckBoxes(String hobbies) {
		String[] hobbiesList = hobbies.split(",");
		for (String hobby : hobbiesList) {
			if (checkHobby(hobby)) {
				logger.info(hobby + " found");
				String elementXpath = "//input[@value='" + hobby + "']";
				helperClass.clickElementByXpath(elementXpath);
				logger.info(hobby + " clicked");
			} else {
				logger.info(hobby + " not found");
			}
		}
	}

	// @FindBy(id = "msdd")
	@FindBy(xpath = "//div[@class='ui-autocomplete-multiselect ui-state-default ui-widget']")
	WebElement languagesDropDown;

	public void clickLanguagesDropdown() {
		logger.info("Clicking languages dropdown");
		// helperClass.clickElementUsingJavaScriptExecutor(languagesDropDown);
		Actions action = new Actions(driver);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		action.moveToElement(languagesDropDown).click().build().perform();
		// helperClass.clickElement(languagesDropDown);
	}

	public void selectLanguages(String languages) {
		String[] listOfLanguages = languages.split(",");// splitting
		for (String language : listOfLanguages) { // for every lang
			if (checkLanguage(language)) { // chcking for lang, if found clicking
				logger.info(language + " found");
				String xpath = "//a[@class='ui-corner-all' and text()='" + language + "']";
				helperClass.clickElementByXpath(xpath);
				logger.info(language + " selected");
			} else { // if not found throwing exception
				logger.info(language + " not found");
				throw new RuntimeException(language + " not found");
			}
		}
	}

	// returns true if hobby language
	// finds no of elements with language. if only 1 found, returns true
	private boolean checkLanguage(String language) {
		logger.info("Searching: " + language);
		String xpath = "//a[@class='ui-corner-all' and text()='" + language + "']";
		List<WebElement> elements = helperClass.findElementsByXpath(xpath);
		int noOfLements = elements.size();
		return noOfLements == 1 ? true : false;
	}

	@FindBy(id = "Skills")
	WebElement skillsDropDown;

	@FindBy(xpath = "//select[@id='Skills']/option")
	List<WebElement> skillsList;

	// returns true if skill language
	// finds no of elements with skill. if only 1 found, returns true
	private boolean checkSkill(String skill) {
		logger.info("Searching skill :" + skill);
		boolean found = false;
		for (WebElement element : skillsList) {
			String skillText = element.getText();
			if (skillText.equalsIgnoreCase(skill))
				found = true;
		}
		return found;
	}

	// checks for skill first, if found clicks, else throws throws exception
	public void selectSkill(String skill) {
		if (checkSkill(skill)) {
			logger.info(skill + " found");
			helperClass.dropdownSelectByVisibleText(skill, skillsDropDown);
		} else {
			logger.info(skill + " not found");
		}

	}

	@FindBy(id = "countries")
	WebElement countriesDropDown;

	public void selectCountry(String country) {
		logger.debug("Selecting country :" + country);
		helperClass.dropdownSelectByVisibleText(country, countriesDropDown);
	}

	@FindBy(xpath = "//span[@role='combobox']")
	WebElement clickCountyElement;

	public void clickCountryDropdown() {
		logger.info("Clicking select country dropdown");
		helperClass.clickElement(clickCountyElement);
	}

	public void clickCountry(String country) {
		logger.debug("Selecting :" + country);
		String xpath = "//li[@class='select2-results__option' and text() = '" + country + "']";
		WebElement element = helperClass.findElementByXpath(xpath);
		helperClass.clickElement(element);
	}

	public void selectDateOfBirth(String dob) {
		Date date = new Date(dob);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMMM/yyyy");
		dob = formatter.format(date);
		String[] dobArray = dob.split("/");// splitting and storing
		String day = dobArray[0];
		String month = dobArray[1];
		String year = dobArray[2];
		logger.info(day + " " + month + " " + year);
		int day2 = Integer.parseInt(day); // day is returning with leading 0.
		day = String.valueOf(day2); // removing it
		selectDay(day);
		selectMonth(month);
		selectYear(year);
	}

	@FindBy(id = "yearbox")
	WebElement yearDropDown;

	@FindBy(xpath = "//select[@ng-model='yearbox']/option")
	List<WebElement> yearOptions;

	// checks for year in the dropdown, if found returns true
	private boolean checkYear(String year) {
		logger.info("Searching : " + year);
		boolean flag = false;
		for (WebElement element : yearOptions) {
			if (element.getText().equals(year))
				flag = true;
		}
		return flag;
	}

	// if year found, clicks
	private void selectYear(String year) {
		if (checkYear(year)) {
			logger.info(year + " found");
			helperClass.dropdownSelectByVisibleText(year, yearDropDown);
		} else {
			logger.info(year + " not found");
			throw new RuntimeException(year + " not found");
		}
	}

	@FindBy(xpath = "//select[@ng-model='monthbox']")
	WebElement monthDropDown;

	@FindBy(xpath = "//select[@ng-model='monthbox']/option")
	List<WebElement> monthOptions;

	// checks for month in the dropdown, if found returns true
	private boolean checkMonth(String month) {
		logger.info("Searching : " + month);
		boolean flag = false;
		for (WebElement element : monthOptions) {
			String monthText = element.getText();
			if (monthText.equals(month))
				flag = true;
		}
		return flag;
	}

	// if month found, clicks
	private void selectMonth(String month) {
		if (checkMonth(month)) {
			logger.info(month + " found");
			helperClass.dropdownSelectByVisibleText(month, monthDropDown);
		} else {
			logger.info(month + " not found");
			throw new RuntimeException(month + " not found");
		}
	}

	@FindBy(id = "daybox")
	WebElement dayDropDown;

	@FindBy(xpath = "//select[@ng-model='daybox']/option")
	List<WebElement> dayOptions;

	// checks for day on the in the dropdown, if found returns true
	private boolean checkDay(String day) {
		logger.info("Checking : " + day);
		boolean flag = false;
		for (WebElement element : dayOptions) {
			int dayFromElement = 0;
			try {
				dayFromElement = Integer.parseInt(element.getText());
				// converting to integers to remove leading zero
			} catch (NumberFormatException e) {
				logger.info("NumberFormatException " + e.getMessage());
			}
			if (dayFromElement == Integer.parseInt(day))
				flag = true;
		}
		return flag;
	}

	// if day found, clicks
	private void selectDay(String day) {
		if (checkDay(day)) {
			logger.info(day + " found");
			// helperClass.dropdownSelectByVisibleText(day, dayDropDown);
			helperClass.dropdownSelectByVlaue(day, dayDropDown); // using value because for 1 visible text has space -
																	// '1 '
		} else {
			logger.info(day + " not found");
			throw new RuntimeException(day + " not found");
		}
	}

	@FindBy(xpath = "//input[@ng-model='Password']")
	WebElement passwordElement;

	public void enterPassword(String password) {
		logger.info("Entering password " + password);
		helperClass.sendKeys(passwordElement, password);
	}

	@FindBy(xpath = "//input[@ng-model='CPassword']")
	WebElement confirmPasswordElement;

	public void enterConfirmpassword(String confirmPassrword) {
		logger.info("Confirming password " + confirmPassrword);
		helperClass.sendKeys(confirmPasswordElement, confirmPassrword);
	}

	@FindBy(id = "submitbtn")
	WebElement submitButton;

	public void clickSubmit() {
		helperClass.clickElement(submitButton);
	}
}