package com.caseStudy1.pageObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.ConfigFileReader;
import com.caseStudy1.utils.DriverManager;
import com.caseStudy1.utils.HelperClass;

public class DatePickerPageObject extends Base {
	private HelperClass helperClass = new HelperClass();
	private String url;
	private WebDriver driver = DriverManager.getDriver();
	ConfigFileReader configFileReader = new ConfigFileReader();
	Logger logger = LogManager.getLogger(DatePickerPageObject.class);

	public DatePickerPageObject() {
		logger.info("Date Picker Object");
		PageFactory.initElements(driver, this);
	}

	public void openUrl() {
		url = configFileReader.getDatePickerUrl();
		helperClass.getUrl(url);
	}

	@FindBy(xpath = "//input[@id='datepicker2']")
	WebElement datePickerEnabled;

	public void clickDatePickerEnabled() {
		logger.info("Clicking date picker");
		helperClass.clickElement(datePickerEnabled);
	}

	@FindBy(xpath = "//input[@id='datepicker1']")
	WebElement datePickerDisabled;

	public void clickDatePickerDisabled() {
		logger.info("Clicking date picker");
		helperClass.clickElement(datePickerDisabled);
	}

	@FindBy(xpath = "//span[@class='ui-datepicker-month']")
	WebElement monthElement;

	// returns month text
	private String getMonth() {
		String month = helperClass.getTextFromElement(monthElement);
		logger.info("Checked month : " + month);
		return month;
	}

	// clicks next button until month found
	private void gotoMonthForward(String month) {
		while (!month.equalsIgnoreCase(getMonth()))
			clickNextButton();
	}

	// clicks back button until month found
	private void gotoMonthBackward(String month) {
		while (!month.equalsIgnoreCase(getMonth()))
			clickPrevButton();
	}

	@FindBy(xpath = "//span[@class='ui-datepicker-year']")
	WebElement yearElement;

	// returns year text
	private String getYear() {
		String year = helperClass.getTextFromElement(yearElement);
		logger.info("Checked year: " + year);
		return year;
	}

	// clicks next button until year is found
	private void gotoYearForward(String year) {
		while (!getYear().equalsIgnoreCase(year)) {
			clickNextButton();
		}
	}

	// clicks back button until year is found
	private void gotoYearBackward(String year) {
		while (!getYear().equalsIgnoreCase(year))
			clickPrevButton();
	}

	@FindBy(xpath = "//span[text()='Next']")
	WebElement nextButton;

	private void clickNextButton() {
		logger.info("Clicking next Button");
		helperClass.clickElement(nextButton);
	}

	@FindBy(xpath = "//span[text()='Prev']")
	WebElement prevButton;

	private void clickPrevButton() {
		logger.info("Clicking prev Button");
		helperClass.clickElement(prevButton);
	}
	//selects day
	private void clickDay(String day) {
		logger.info("Clicking day : " + day);
		String xpath = "//a[text()='" + day + "']";
		driver.findElement(By.xpath(xpath)).click();
	}
	// true for future dates, false for past dates
	private boolean checkDate(Date date) {
		boolean flag = false; 
		Date toDay = new Date();
		if (toDay.compareTo(date) < 0)
			flag = true;
		return flag;
	}

	public void selectDateInDisabledDatePicker(String dateToClick) {
		logger.info("Selecting date in disabled date picker");
		System.out.println("date to click : "+dateToClick);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	//changing format
		Date date=null;
		try {
			date = formatter.parse(dateToClick);
		} catch (ParseException e) {
			logger.info("Invalid date Format");
			e.printStackTrace();
		}
		System.out.println(date);
		formatter = new SimpleDateFormat("dd/MMMM/yyyy");
		dateToClick = formatter.format(date);
		String[] dobArray = dateToClick.split("/");	//splitting and storing
		String day = dobArray[0];
		String month = dobArray[1];
		String year = dobArray[2];
		logger.info(day + " " + month + " " + year);
		day = String.valueOf(Integer.parseInt(day)); // day is returning with leading 0. removing it
		String today = formatter.format(date);
		System.out.println(today);
		if (checkDate(date)) {
			logger.info("Future date");
			gotoYearForward(year);	//clicks next button until year is found
			gotoMonthForward(month); //clicks next button until month is found
		} else {
			logger.info("Past Date");
			gotoYearBackward(year);		//clicks back button until year is found
			gotoMonthBackward(month);	//clicks back button until month is found
		}
		clickDay(day);
	}
	public static void main(String[] args) {
		String dateToClick = "12-21-2019";
		SimpleDateFormat format =new SimpleDateFormat("MM-dd-yyyy") ;
		Date date=null;
		try {
			date = format.parse(dateToClick);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(date);
	}

	@FindBy(xpath = "//select[@title='Change the year']/option")
	WebElement upButtonForYear;

	@FindBy(xpath = "//select[@title='Change the year']/option[23]")
	WebElement downButtonForYear;

	@FindBy(xpath = "//select[@title='Change the year']/option")
	List<WebElement> yearOptions;

	@FindBy(xpath = "//select[@title='Change the year']/option[2]")
	WebElement firstYearInOptions;

	@FindBy(xpath = "//select[@title='Change the year']/option[22]")
	WebElement lastYearInOptions;

	@FindBy(xpath = "//select[@title='Change the month']")
	WebElement monthSelect;

	private void selectMonthInEnabledDatePicker(String month) {
		logger.info("Selecting month : " + month);
		helperClass.dropdownSelectByVisibleText(month, monthSelect);
	}

	@FindBy(xpath = "//select[@title='Change the year']")
	WebElement yearSelect;

	//checks year present in the current list
	//if not found, clicks up or down buttons accordingly
	private void selectYearInEnabledDatePicker(String year) {
		while (!checkYear(year)) { // year not present in the list
			logger.info("Year not found in the current list");
			if (pastYearOrFuture(year)) { // future year
				logger.info("Future year");
				logger.info("Clicking down button");
				helperClass.dropdownSelectByIndex(22, yearSelect);	//clicking down button

			} else { // past year
				logger.info("Past year");
				logger.info("Clicking up button");
				helperClass.dropdownSelectByIndex(0, yearSelect);	//clicking up button
			}
		}
		logger.info("Clicking year " + year);
		helperClass.dropdownSelectByVisibleText(year, yearSelect);

	}

	// checks year is present in the current year options
	private boolean checkYear(String year) {
		logger.info("Checking " + year + " present in the current list");
		boolean flag = false;
		for (WebElement yearElement : yearOptions) {
			String yearChecking = helperClass.getTextFromElement(yearElement);
			logger.info("Checking year: " + yearChecking);
			if (yearChecking.equals(year)) {
				flag = true;
				logger.info(year + " found");
				break;
			}
		}
		return flag;
	}

	// checks if the year is past year or future year.
	// control comes here if the year is not present in the current list
	// false for past year
	private boolean pastYearOrFuture(String year) {
		int yearText = Integer.parseInt(year);
		boolean flag = false;
		int lastYearInOptionsText = Integer.parseInt(helperClass.getTextFromElement(lastYearInOptions));
		if (yearText > lastYearInOptionsText) // future year
			flag = true;
		return flag; // not greater than the last option and not present in the list. i.e past year.
						// so flag is false by default
	}
	//clicks day
	private void selectDayInEnabledDatePicker(String day) {
		logger.info("Clicking day : " + day);
		String xpath = "//div[@class='datepick-month']/descendant::a[text()='" + day + "']";
		WebElement element = driver.findElement(By.xpath(xpath));
		helperClass.clickElement(element);
	}

	public void selectDateInEnabledDatePicker(String dateToClick) {
		logger.info("Selecting date in enabled date picker");
		System.out.println(dateToClick);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	//changing format
		Date date=null;
		try {
			date = formatter.parse(dateToClick);
		} catch (ParseException e) {
			logger.info("Invalid date Format");
			e.printStackTrace();
		}
		System.out.println(date);
		formatter = new SimpleDateFormat("dd/MMMM/yyyy");
		dateToClick = formatter.format(date);
		String[] dobArray = dateToClick.split("/");	//splitting and storing
		String day = dobArray[0];
		String month = dobArray[1];
		String year = dobArray[2];
		logger.info(day + " " + month + " " + year);
		day = String.valueOf(Integer.parseInt(day)); // day is returning with leading 0. removing it
		selectYearInEnabledDatePicker(year);
		selectMonthInEnabledDatePicker(month);
		selectDayInEnabledDatePicker(day);
	}
}
