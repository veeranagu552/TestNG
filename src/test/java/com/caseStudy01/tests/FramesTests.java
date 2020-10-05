package com.caseStudy01.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.caseStudy1.pageObjects.FramesPageObject;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

public class FramesTests extends Base {
	FramesPageObject framesPageObject = new FramesPageObject();

	@Test(dataProvider = "framesData")
	public void framesTest(Map<String, String> map) {
		framesPageObject.openUrl();
		framesPageObject.enterTextInSingleFrameTextBox(map.get(Constants.SINGLEFRAMETEXT));
		framesPageObject.enterTextInNestedFramesTextBox(map.get(Constants.NESTEDFRAMETEXT));
	}
}
