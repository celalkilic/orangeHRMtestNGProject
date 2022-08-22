package com.qa.orangehrm.tests;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.util.AppConstant;
import com.qa.orangehrm.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : create login features")
@Feature("UK -501 : create tests for login system on HRM using valid and invalid credentials")
public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	Logger  log = Logger.getLogger(LoginPageTest.class);
	
	
	@BeforeMethod
	@Parameters(value = {"browser"})
	public void setUp(String browser) {
		String browserName = null;
		basePage = new BasePage();
		prop = basePage.initialise_properties();
		
		if (browser.equals(null)) {
			browserName = prop.getProperty("browser");
		}else {
			browserName = browser;
		}
		
		driver = basePage.initialise_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority=1, description="verify page title method", enabled = true)
	@Description("verifiy login page title")
	@Severity(SeverityLevel.NORMAL)
	
	public void verifyLoginPageTitleTest() {
		log.info("starting title method-----------> verfiy login page");

		String title = loginPage.getPageTitle();
		System.out.println("login page title is "+ title);
		Assert.assertEquals(title, AppConstant.LOGIN_PAGE_TITLE);
		log.info("ending title method--------------> verify login page");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	
	}
	
	@Test(priority=2, description="verify sign up link is displayed", enabled = true)
	@Description("verifiy login page sign up link is displayed")
	@Severity(SeverityLevel.NORMAL)	
	public void verifyForgetPassLink() {
		log.info("starting forget pass--------------> login page");
		Assert.assertTrue(loginPage.checkForgetPassLink());
		log.info("ending forget password method--------------> verify forget password link");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	@Test(priority = 3, description = "verify valid login credentials", enabled = true)
	@Description("verifiy valid login credentials")
	@Severity(SeverityLevel.CRITICAL)
	public void LoginTest() {
		log.info("starting do login method--------------> login page dologin");
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getUserName();
		System.out.println("account name is " + accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));
		log.info("ending do login method--------------> verify valid credential");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
		//Assert.assertTrue(accountName.contains(prop.getProperty("accountname")));
	}
	//parameterised Testing: means we will use wrong user name and wrong password
	// there are two way to do these one of them is @dataprovider in TestNG another one is external database system by using excel file
	@DataProvider
	public Object[][] getLoginInvalidData(){
		
		Object data[][] = {{"hasan","test123"},
				           {"refia","321test"},
				           {"richard","res123"},
				           {"furkan","ewr123"}
						};
		return data;
	}
	@Test(priority = 4, dataProvider = "getLoginInvalidData", enabled = false)
	@Description("verifiy invalid login credentials")
	@Severity(SeverityLevel.BLOCKER)
	public void login_InvalidTestCase(String username, String pwd) {
		log.info("starting dologin method with invalid credentials--------------> login page invalid credential dologin");
		userCred.setAppUsername(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMassage());
		log.info("ending do login method with invalid credential --------------> verify invalid credentials dologin");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}