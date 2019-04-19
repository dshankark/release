package factory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class schedule {
	WebDriver driver;
	@FindBy (xpath="//table[contains(@class,'resultUnit flightDetailsLink ')]//th[@id='BaggageBundlingTemplate']")
	List<WebElement> rate;
	@FindBy (xpath="//div[contains(@class,' leg col12')][2]//ul[contains(@class,'listView flights')]//li//th[contains(@class,'price')]")
	List<WebElement> tworate;

	public schedule(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public List<WebElement> flightRate() throws InterruptedException {
		Thread.sleep(15000);
		return rate;

	}

	public List<WebElement> twoflightRate() throws InterruptedException {
		Thread.sleep(15000);
		System.out.println("twoflightrate method executed");

		return tworate;

	}

}


