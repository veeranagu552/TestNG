package com.caseStudy01.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {
public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.get("http://demo.automationtesting.in/Register.html");
	driver.findElement(By.id("msdd")).click();
	String language = "English";
	String xpath = "//a[@class='ui-corner-all' and text()='"+language+"']";
	System.out.println(driver.findElements(By.xpath(xpath)).size());
	driver.findElement(By.xpath(xpath)).click();
}
}
