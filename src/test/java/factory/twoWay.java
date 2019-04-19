package factory;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(extRep.class)
public class twoWay {
	WebDriver driver;
	static Method m=null;
	static Method Meth=null;
	public twoWay() {
		Browser br= new Browser();
		driver= br.browserSelect();
	}
	@Test (priority=0, description="TC_03", dataProvider="dp1")
	public void twoWayTrip(String tc,String start, String end,String month, String date) throws InterruptedException {

		System.out.println("Method Twowaytrip started");
		homepage hp= new homepage(driver);
		hp.doubleSearch(start, end, month, date);
		System.out.println("Method Twowaytrip completed");
	}

	@Test (priority=1, description="TC_04")
	public void twoWayRate() throws InterruptedException {

		System.out.println("Method TwowayRate started");
		schedule sc= new schedule(driver);
		java.util.List<WebElement> rate=sc.twoflightRate();
		for(WebElement rt:rate) {
			String rates=rt.getText();
			System.out.println(rates);
		}
		int a=5;int b=3;
		assertTrue(a>b, "TwowayRate executed successfully");

		System.out.println("Method TwowayRate completed");
		Thread.sleep(5000);
		driver.close();
	}
	@DataProvider(name="dp1")
	public Object[][] twowaydp(Method m) throws NoSuchMethodException, SecurityException, IOException {
		ArrayList<Integer> al =null;
		Class[] cArg= new Class[5];
		int j= cArg.length;
		//	System.out.println(j);
		for (int i=0;i<j;i++) {
			//	System.out.println(i);
			cArg[i]=String.class;
		}
		String Mname=m.getName();
		System.out.println(Mname);
		Meth= twoWay.class.getMethod(Mname, cArg);
		String des=Meth.getAnnotation(Test.class).description();
		System.out.println(des);
		DataFile dt= new DataFile();
		al=new ArrayList<Integer>();
		al=dt.dat(des);
		Integer rowNumber =al.get(0);
		Integer colNumber =al.get(1);
		System.out.println("Row and column no is  "+rowNumber+" "+colNumber);
		Object[][] value= dt.xcelrowValues(rowNumber, colNumber);

		return(value);
	}
}
