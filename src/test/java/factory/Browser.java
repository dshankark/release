package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Browser {
	static WebDriver driver;
	@Test
	public WebDriver browserSelect() {

		System.setProperty("webdriver.chrome.driver", "c://Chrome//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("window-size=1200*600");
		driver = new ChromeDriver(options);
		driver.get("https://www.cleartrip.com/");
		driver.manage().window().maximize();
		return driver;
	}
}
