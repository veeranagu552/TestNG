package com.caseStudy1.utils;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HelperClass {
	private WebDriver driver = DriverManager.getDriver();
	private WebDriverWait wait = new WebDriverWait(driver, 10);
	private Actions actions = new Actions(driver);
	static Logger logger = LogManager.getLogger(HelperClass.class);
	private Select select;
	private JavascriptExecutor ex = (JavascriptExecutor) driver;

	public void dropdownSelectByVisibleText(String text, WebElement webElement) {
		select = new Select(webElement);
		try {
			flash(webElement);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			logger.info("Not able to select : " + text);
			return;
		}
		logger.info("Selected : " + text);
	}

	public void dropdownSelectByIndex(int index, WebElement webElement) {
		select = new Select(webElement);
		try {
			flash(webElement);
			select.selectByIndex(index);
		} catch (NoSuchElementException e) {
			logger.info("Not able to select");
			return;
		}
		logger.info("Selected option at index: " + index);
	}

	public String getTextFromElement(WebElement element) {
		// flash(element);
		elementBorder(element);
		String text = element.getText();
		logger.info("Extracted text : " + text);
		return text;
	}

	public String getTextFromAlert(Alert alert) {
		wait.until(ExpectedConditions.alertIsPresent());
		return alert.getText();
	}

	public void sendKeys(WebElement element, String keysToSend) {
		try {
			flash(element);
			element.clear();
			element.sendKeys(keysToSend);
			logger.debug(keysToSend + " entered");
		} catch (IllegalArgumentException e) {
			logger.info("unable to enter " + keysToSend);
		}
	}

	public void sendKeysAlert(String keysToSend) {
		driver.switchTo().alert().sendKeys(keysToSend);
	}

	public void clickElement(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		flash(element);
		element.click();
		logger.info("Element Clicked");
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
		logger.info("Alext accpetd");
	}

	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
		logger.info("Alext dismissed");
	}

	public void switchToFrame(WebElement frameElemet) {
		flash(frameElemet);
		logger.info("Switching to frame");
		driver.switchTo().frame(frameElemet);
	}

	public void switchToDefaultContent() {
		logger.info("Switching to default content");
		driver.switchTo().defaultContent();
	}

	public void switchToWindow(String window) {
		driver.switchTo().window(window);
		logger.info("Switched to window: " + window);
	}

	public void actionsMoveToElement(WebElement element) {
		flash(element);
		actions.moveToElement(element).build().perform();
		;
	}

	public WebElement findElementByXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	public void closeWindow() {
		logger.info("Closing window");
		driver.close();
	}

	public List<WebElement> findElementsByXpath(String xpath) {
		List<WebElement> elements = driver.findElements(By.xpath(xpath));
		return elements;
	}

	public void clickElementByXpath(String elementXpath) {
		WebElement element = findElementByXpath(elementXpath);
		flash(element);
		clickElement(element);
	}

	public void clickElementUsingJavaScriptExecutor(WebElement element) {
		flash(element);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void assertStrings(String actual, String expected) {
		logger.info("Asserting - " + actual + " with " + expected);
		Assert.assertEquals(actual, expected);

	}

	public void dropdownSelectByVlaue(String text, WebElement webElement) {
		select = new Select(webElement);
		try {
			flash(webElement);
			select.selectByValue(text);
		} catch (NoSuchElementException e) {
			logger.info("Not able to select : " + text);
			return;
		}
		logger.info("Selected : " + text);
	}

	public void getUrl(String url) {
		logger.info("Navigating to: " + url);
		driver.get(url);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			ex.executeScript("arguments[0].style.backgroundColor ='#F933FF'", element);
			ex.executeScript("arguments[0].style.backgroundColor ='" + bgcolor + "'", element);
		}
	}

	public void elementBorder(WebElement element) {
		ex.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public String getTitleUsingJS() {
		String title = ex.executeScript("return document.title;").toString();
		return title;
	}

	public void generateAlertUsingJS(String message) {
		ex.executeScript("alert('" + message + "')");
	}

	public void refreshPageUsingJS() {
		ex.executeScript("history.go(0)");
	}

	public void scrollIntoViewUsingJS(WebElement element) {
		ex.executeScript("argument[0].scroolIntoView(true);", element);
	}

	public void scrollDownPageToEndUsingJS() {
		ex.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
}
