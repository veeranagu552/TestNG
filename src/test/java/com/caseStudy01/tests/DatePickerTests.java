package com.caseStudy01.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.caseStudy1.pageObjects.DatePickerPageObject;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

public class DatePickerTests extends Base {
	DatePickerPageObject datePickerObjects = new DatePickerPageObject();
	
	@Test(dataProvider = "dataPickerData")
	public void dataPickerTest(Map<String, String> map) {
		datePickerObjects.openUrl();
		datePickerObjects.clickDatePickerDisabled();
		datePickerObjects.selectDateInDisabledDatePicker(map.get(Constants.DATEFORDISABLED));
		datePickerObjects.clickDatePickerEnabled();
		datePickerObjects.selectDateInEnabledDatePicker(map.get(Constants.DATEFORENABLED));
	}
}
