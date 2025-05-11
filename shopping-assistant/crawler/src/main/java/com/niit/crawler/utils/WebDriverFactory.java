package com.niit.crawler.utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;

public class WebDriverFactory {

	public String BROWSER_PATH="C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe";

	public String BROWSER_DRIVER_PATH="D:\\code\\Graduationproject\\msedgedriver.exe";

	private static volatile WebDriver driver;

	private WebDriverFactory(){
		System.setProperty("webdriver.edge.bin",BROWSER_PATH);
		EdgeOptions options = new EdgeOptions();
		options.setCapability("binary", BROWSER_PATH);    //导入edge安装路径

		System.setProperty("webdriver.edge.driver",BROWSER_DRIVER_PATH);
		driver = new EdgeDriver(options);
	}

	public static WebDriver getInstance() {
		/*if (driver == null) {
			synchronized (WebDriverFactory.class) {
				if (driver == null) {*/
					new WebDriverFactory();
/*				}
			}
		}*/
		return driver;
	}
}
