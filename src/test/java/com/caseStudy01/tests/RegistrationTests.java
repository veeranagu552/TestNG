package com.caseStudy01.tests;

import java.util.Map;
import org.testng.annotations.Test;

import com.caseStudy1.pageObjects.RegistrationPageObjects;
import com.caseStudy1.utils.Base;
import com.caseStudy1.utils.Constants;

class RegistrationTests extends Base {
	RegistrationPageObjects registrationPageObjects = new RegistrationPageObjects();
	@Test(dataProvider = "registration")
	public void registrationTest(Map<String, String> map) {
		registrationPageObjects.openUrl();
		registrationPageObjects.enterFirstName(map.get(Constants.FIRSTNAMEHEADER));
		registrationPageObjects.enterSecondName(map.get(Constants.LASTNAMEHEADER));
		registrationPageObjects.enterAddress(map.get(Constants.ADDRESS));
		registrationPageObjects.enterEmail(map.get(Constants.EMAIL));
		registrationPageObjects.enterPhoneNumber(map.get(Constants.PHONE));
		registrationPageObjects.clickGender(map.get(Constants.GENDER));
		registrationPageObjects.clickHobbiesCheckBoxes(map.get(Constants.HOBBIES));
		registrationPageObjects.clickLanguagesDropdown();
		registrationPageObjects.selectLanguages(map.get(Constants.LANGUAGES));
		registrationPageObjects.selectSkill(map.get(Constants.SKILLS));
		registrationPageObjects.selectCountry(map.get(Constants.COUNTRY1));
		registrationPageObjects.clickCountryDropdown();
		registrationPageObjects.clickCountry(map.get(Constants.COUNTRY2));
		registrationPageObjects.selectDateOfBirth(map.get(Constants.DOB));
		registrationPageObjects.enterPassword(map.get(Constants.PASSWORD));
		registrationPageObjects.enterConfirmpassword(map.get(Constants.CONFIRMPASSWORD));
		registrationPageObjects.clickSubmit();
	}
}
