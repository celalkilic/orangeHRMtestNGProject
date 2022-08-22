package com.qa.orangehrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.util.AppConstant;
import com.qa.orangehrm.util.Credentials;

public class HomePageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialise_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.initialise_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
	}
	@Test(priority = 2, description = "verify home page title")
	public void verifyHomePageTitle() {
		String title = homePage.getHomePageTitle();
		System.out.println("home page title is "+title);
		Assert.assertEquals(title, AppConstant.HOME_PAGE_TITLE);
	}
	@Test(priority = 3, description = "verify home page header")
	public void verifyhomePageHeader() {
		String header = homePage.getHomePageHeader();
		System.out.println("home page header is "+header);
		Assert.assertEquals(header, AppConstant.HOME_PAGE_HEADER);
	}
	@Test(priority = 4, description = "verify account name")
	public void verifyAccountName() {
		String accountName = homePage.getUserName();
		System.out.println("acoount name is "+accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
		//Assert.assertTrue(accountName.contains("Mano Guilpen"));
	}
	@Test(priority = 1, description = "verify account name")
	public void verifyHomePageUrl() {
		String urlHome = homePage.getHomeUrl();
		System.out.println("home page url is "+urlHome);
		Assert.assertTrue(urlHome.endsWith("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
