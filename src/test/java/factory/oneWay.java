package factory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(extRep.class)
public class oneWay {
	WebDriver driver;
	static Method m=null;
	static Method Meto=null;
	static Object[][] value=null;
	ArrayList<Integer> al=null;
	Integer rowNumber=null;
	Integer colNumber=null;

	public oneWay() {
		Browser br = new Browser();
		driver=br.browserSelect();
	}
	@Test (priority=0, description="TC_01", dataProvider="dp")
	public void SingleTrip(String tc,String start, String end,String month, String date) throws InterruptedException, IOException, NoSuchMethodException, SecurityException {

		System.out.println("matches");
		homepage hp = new homepage(driver);
		hp.onewaysearch(start,end ,month, date);
		System.out.println("Method completed");




	}

	@Test(priority=1, description="TC_02")
	public void findRate() throws NoSuchMethodException, SecurityException, InterruptedException, IOException {
		System.out.println("Method 2 started");

		schedule s = new schedule(driver);
		java.util.List<WebElement> rt= s.flightRate();
		for (WebElement r: rt) {
			String pri=r.getText();
			System.out.println(pri);
		}
		Thread.sleep(10000);

		driver.close();

	}
	@DataProvider (name="dp")
	public   Object[][] data(Method m) throws IOException  {
		Class[] cArg = new Class[5];
		int j=cArg.length;
		System.out.println(j);
		for (int i=0;i<j;i++) {
			cArg[i] = String.class;
		}
		String met=m.getName();
		System.out.println("Emthod name is "+met);
		try {
			Meto=oneWay.class.getMethod(met, cArg);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String desc=Meto.getAnnotation(Test.class).description();
		System.out.println("file read "+ desc);
		DataFile df= new DataFile();

		try {
			al = df.dat(desc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Integer rowNumber =al.get(0);
		Integer colNumber =al.get(1);
		System.out.println("Row and column no is  "+rowNumber+" "+colNumber);
		Object[][] value= df.xcelrowValues(rowNumber, colNumber);
		//	int length =Array.getLength(value);
		//	System.out.println("Data value is "+ length);
		//	System.out.println(value[1][1]);
		return (value);

	}
}
