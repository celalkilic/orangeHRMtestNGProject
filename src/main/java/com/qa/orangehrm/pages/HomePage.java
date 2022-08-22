package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.ElementUtil;

public class HomePage {
	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.cssSelector(".oxd-text.oxd-text--h6.oxd-topbar-header-breadcrumb-module");
	By accountName = By.cssSelector(".oxd-userdropdown-name");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	public String getHomePageTitle() {
		return elementUtil.doGetPageTitle();
	}
	public String getHomePageHeader() {
		return elementUtil.doGetText(header);
	}
	public String getHomeUrl() {
		return elementUtil.doGetPageUrl();
	}
	public String getUserName() {
		return elementUtil.doGetText(accountName);
	}
}
