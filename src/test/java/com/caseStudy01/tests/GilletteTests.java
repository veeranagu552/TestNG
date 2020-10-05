package com.caseStudy01.tests;

import java.util.Map;

import org.testng.annotations.Test;
import com.caseStudy1.pageObjects.GilletteSignInPageObjects;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

public class GilletteTests extends Base {
	GilletteSignInPageObjects signInPage = new GilletteSignInPageObjects();

	@Test(dataProvider = "gilletteData")
	public void gillettePositiveTest(Map<String, String> map) {
		signInPage.openUrl(Constants.COUNTRY);
		signInPage.enterEmail(map.get(Constants.EMAIL));
		signInPage.enterPassword(map.get(Constants.PASSWORD));
		signInPage.clickSignInButton();
		signInPage.clickLogout();
	}

	@Test(dataProvider = "gilletteData")
	public void gilletteInvalidPasswordTest(Map<String, String> map) {
		signInPage.openUrl(Constants.COUNTRY);
		signInPage.enterEmail(map.get(Constants.EMAIL));
		signInPage.enterPassword(map.get(Constants.INVALIDPASSWORD));
		signInPage.clickSignInButton();
	}
}
