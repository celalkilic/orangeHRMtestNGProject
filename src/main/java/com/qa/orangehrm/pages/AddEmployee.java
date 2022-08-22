package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.orangehrm.util.ElementUtil;

public class AddEmployee {
	WebDriver driver;
	ElementUtil elementUtil;
	
	//Locators
	By clickPIM = By.cssSelector(".oxd-main-menu-item.active");
	By addEmployee = By.cssSelector("header[class='oxd-topbar'] li:nth-child(3)");
	By firstName = By.cssSelector("input[placeholder='First Name']");
	By middleName = By.cssSelector("input[placeholder='Middle Name']");
	By lastName = By.cssSelector("input[placeholder='Last Name']");
	By saveBtn = By.cssSelector("button[type='submit']");
	By pageHeader = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
	public AddEmployee(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAddEmployeeHeader() {
		String header = elementUtil.getElement(pageHeader).getText();
		System.out.println(header);
		
		return header;
	}
	
	public void doClick() {
		elementUtil.doClick(clickPIM);
		elementUtil.doClick(addEmployee);
	}
	
	public void addEmployee(String fname, String mname, String lname) {
		elementUtil.doSendKeys(firstName, fname);
		elementUtil.doSendKeys(middleName, mname);
		elementUtil.doSendKeys(lastName, lname);
		elementUtil.doClick(saveBtn);
	}
	
	
}
