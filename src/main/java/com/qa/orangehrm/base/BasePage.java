package com.qa.orangehrm.base;
import java.io.FileInputStream;


import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	//WebDriver driver;
	Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver initialise_driver(String browserName) {
		highlightElement = prop.get("highlight").equals("yes") ? true : false;
		System.out.println("browser name is "+browserName);
		optionsManager = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
		}else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			
		}else {
			System.out.println("browser name "+browserName+" is not found.");
		}
		getDriver().manage().window().maximize();;
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("url"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		return getDriver();
	}
	public Properties initialise_properties() {
		prop = new Properties();
		String path = "C:\\Users\\zeyne\\eclipse-workspace"
				+ "\\OrangeHRM\\src\\main\\java\\com\\qa"
				+ "\\orangehrm\\config\\config.properties";
		try {
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}