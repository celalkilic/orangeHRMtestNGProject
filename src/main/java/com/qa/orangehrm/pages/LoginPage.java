package com.qa.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.util.Credentials;
import com.qa.orangehrm.util.ElementUtil;

public class LoginPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By username = By.cssSelector("input[placeholder='Username']");
	By password = By.cssSelector("input[placeholder='Password']");
	By loginBtn = By.cssSelector("button[type='submit']");
	By forgetPass = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']");
	By loginErrorText = By.cssSelector(".oxd-text.oxd-text--p.oxd-alert-content-text");
			
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver); //2
	}
	
	//Methods
	
	public String getPageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkForgetPassLink() {
		elementUtil.waitForElementVisible(forgetPass);
		return elementUtil.doIsDisplayed(forgetPass);
	}
	
    public HomePage doLogin(Credentials userCred) {
    	elementUtil.waitForElementVisible(username);
		elementUtil.doSendKeys(username, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginBtn);

		return new HomePage(driver);
	}
    public boolean checkLoginErrorMassage() {
		return elementUtil.doIsDisplayed(loginErrorText);
	}

}