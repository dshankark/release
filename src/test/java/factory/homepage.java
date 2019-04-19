package factory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepage {
	WebDriver driver;
	@FindBy (xpath="//input[@id='OneWay']")
	WebElement oneWay;
	@FindBy (xpath="//input[@id='RoundTrip']")
	WebElement roundWay;
	@FindBy (xpath="//input[@id='FromTag']")
	WebElement from;
	@FindBy (xpath="//input[@id='ToTag']")
	WebElement to;
	@FindBy (xpath="//input[@id='DepartDate']")
	WebElement depDate;
	@FindBy (xpath="//input[@id='ReturnDate']")
	WebElement retDate;
	@FindBy (xpath="//span[@class='ui-datepicker-month']")
	List<WebElement> datePick;
	@FindBy (xpath="//a[contains(@class,'ui-state-default ')]")
	List<WebElement> dayPick;
	@FindBy (xpath="//select[@id='Adults']")
	WebElement persons;
	@FindBy (xpath="//input[@id='SearchBtn']")
	WebElement search;

	public homepage(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}


	public void singleSearch(String frm, String dest, String month, String date) throws InterruptedException {
		System.out.println("emthod single search called");
		//	Thread.sleep(5000);
		oneWay.click();
		System.out.println("True");
		from.sendKeys(frm);
		to.sendKeys(dest);
		depDate.click();
		for (WebElement e:datePick) {
			System.out.println(e.getText());
			Thread.sleep(3000);
			if((e.getText()).equalsIgnoreCase(month)) {
				System.out.println("Month is correct");
				for (WebElement w: dayPick) {
					//	System.out.println(w.getText());
					if(w.getText().equalsIgnoreCase(date)) {
						w.click();
						break;
					}
				}
			}

		}
		search.click();
	}

	public void doubleSearch(String frm, String dest, String month, String date) throws InterruptedException {
		WebElement theDate = null;
		System.out.println("emthod double search called");
		//	Thread.sleep(5000);
		roundWay.click();
		System.out.println("True");
		from.sendKeys(frm);
		to.sendKeys(dest);
		depDate.click();
		for (WebElement e:datePick) {
			System.out.println(e.getText());
			Thread.sleep(3000);
			if((e.getText()).equalsIgnoreCase(month)) {
				System.out.println("Month is correct");
				for (WebElement w: dayPick) {
					//	System.out.println(w.getText());
					if(w.getText().equalsIgnoreCase(date)) {
						theDate=w;
						w.click();
						System.out.println("set date clicked");
						break;
					}
				}
			}

		}

		System.out.println("Dep date clicked");
		search.click();
		System.out.println("emthod double search completed");
	}



	public void onewaysearch(String start, String end, String month, String date) throws InterruptedException {
		// TODO Auto-generated method stub
		this.singleSearch(start, end , month , date);
	}

	public void twoWaysearch(String start, String end, String month, String date) throws InterruptedException {
		// TODO Auto-generated method stub
		this.doubleSearch(start, end, month, date);
	}
}