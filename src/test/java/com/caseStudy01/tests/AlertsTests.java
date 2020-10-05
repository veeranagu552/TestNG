package com.caseStudy01.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.caseStudy1.pageObjects.AlertsPageObject;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

public class AlertsTests extends Base {
	AlertsPageObject alertsPageObject = new AlertsPageObject();

	@Test(dataProvider = "alertsData")
	public void alerTest(Map<String, String> map) {
		alertsPageObject.openUrl();
		alertsPageObject.clickAlert1Box();
		alertsPageObject.acceptAlert1();
		alertsPageObject.clickAlert2Button();
		alertsPageObject.clickAlert2Box();
		alertsPageObject.alert2Action(map.get(Constants.ALERT2TEXT));
		alertsPageObject.clickAlert3Button();
		alertsPageObject.clickAlert3Box();
		//alertsPageObject.acceptAlert3(map.get(Constants.ALERT3TEXT));
		alertsPageObject.dismissAlert3();
	}
}
