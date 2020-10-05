package com.caseStudy01.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.caseStudy1.pageObjects.WindowsPageObjects;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

public class WindowHandlingTests extends Base {
	WindowsPageObjects windowsPageObjects = new WindowsPageObjects();

	@Test(dataProvider = "windowHandling")
	public void windowHandling(Map<String, String> map) {
		windowsPageObjects.openUrl();
		windowsPageObjects.clickNewTab();
		windowsPageObjects.assertTextSelenium(map.get(Constants.SELENIUMTEXT));
		windowsPageObjects.clickNewSeperateWindowsButton();
		windowsPageObjects.clickToOpenNewSeperateWindow();
		windowsPageObjects.assertTextSelenium(map.get(Constants.SELENIUMTEXT));
		windowsPageObjects.clickNewSeperateMultipleWindowsButton();
		windowsPageObjects.clickToOpenMultipleWindows();
		windowsPageObjects.assertTitle(map.get(Constants.TITLES));
	}
}
