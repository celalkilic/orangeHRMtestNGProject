package com.qa.orangehrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.pages.AddEmployee;
import com.qa.orangehrm.pages.HomePage;
import com.qa.orangehrm.pages.LoginPage;
import com.qa.orangehrm.util.Credentials;
import com.qa.orangehrm.util.ExcelUtil;

public class AddEmployeeTest {
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	AddEmployee addEmployee;
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
		addEmployee = new AddEmployee(driver);
	}
	@DataProvider
	public Object [][] getEmployeeNamesTestData(){
		Object [][] data = ExcelUtil.getTestData("employee");
		return data;
	}
	@Test(dataProvider = "getEmployeeNamesTestData")
	public void addEmployeeTest(String firsname, String middlename, String lastname) {
		addEmployee.doClick();
		addEmployee.addEmployee(firsname, middlename, lastname);
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
