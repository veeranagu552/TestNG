package com.caseStudy01.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.caseStudy1.pageObjects.SelectablePageObjects;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

public class SelectableTests extends Base {
	SelectablePageObjects selectablePageObjects = new SelectablePageObjects();
	
	@Test(dataProvider = "selectableData")
	public void selectableTest(Map<String,String> map) {
		selectablePageObjects.openUrl();
		selectablePageObjects.clickOptionInDefault(map.get(Constants.DEFAULTOPTION));
		selectablePageObjects.clickSerializeButton();
		selectablePageObjects.clickOptionInSerialize(map.get(Constants.SERIALIZEOPTION));
	}
}
