package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class basePage {
	Object[][] val=null;
	int k=0;
	int v=0;
	int rownum;
	Method Meto=null;
	XSSFRow Row;

	@DataProvider
	public  Object[][] dat(Method m) throws IOException, NoSuchMethodException, SecurityException {
		Class[] cArg = new Class[5];
		int j=cArg.length;
		System.out.println(j);
		for (int i=0;i<j;i++) {
			cArg[i] = String.class;

		}


		String met=m.getName();
		System.out.println(met);
		Meto=basePage.class.getMethod(met, cArg);
		String desc=Meto.getAnnotation(Test.class).description();
		System.out.println("file read "+ desc);

		FileInputStream fs = new FileInputStream("c://ss//Testdata.xlsx");
		String des ="TC_01";
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sh = wb.getSheet("Sheet1");
		XSSFRow Row=sh.getRow(0);
		int totalNoOfCols = Row.getLastCellNum();
		int totalNoOfRows = sh.getPhysicalNumberOfRows();
		System.out.println("RC "+totalNoOfCols+" "+totalNoOfRows);
		for (int g=0;g<totalNoOfRows;g++) {
			System.out.println("Value of gis "+g);
			int rownum=g;
			XSSFCell cel=sh.getRow(g).getCell(0);
			String vale = cel.getStringCellValue();

			System.out.println("Row number is "+rownum);
			if(des.equalsIgnoreCase(vale)) {
				String val[][] = new String[1][5];
				int len = Array.getLength(val);
				System.out.println("Length is "+len);
				System.out.println("Matches with the tc "+ vale);
				XSSFRow  row=sh.getRow(rownum);
				for (int j1=0; j1 <= totalNoOfCols; j1++) {
					if(row==null) {
						val[k][v]= "";
					}
					else {
						XSSFCell cell= row.getCell(j1);
						if((cell !=null && cell.getCellTypeEnum()==CellType.STRING)){
							String value= cell.getStringCellValue();
							val[k][v]=value;
							System.out.println(value);
							System.out.println("value of i is "+rownum+"Value of j is "+j1);
						}
						if(cell !=null && cell.getCellTypeEnum()==CellType.NUMERIC){
							String value= NumberToTextConverter.toText(cell.getNumericCellValue());
							val[k][v]= value;
							System.out.println(value);
							System.out.println("value of i is "+rownum+"Value of j is "+j1);
						}

						System.out.println("Final "+rownum+" "+j);
						int leng = Array.getLength(val);
						System.out.println("Length is "+leng);
					}
				}
			}}

		/*	Object val[][] = new Object[totalNoOfRows-1][totalNoOfCols];

		for (int i= 0 ; i < totalNoOfRows-1; i++) {
			//	System.out.println("First value of i is "+i);
			XSSFRow  row=sh.getRow(i+1);
			//		System.out.println("value of i is "+i);
			for (int j=0; j < totalNoOfCols; j++) {
				if(row==null) {
					val[i][j]= "";
				}
				else {
					XSSFCell cell= row.getCell(j);
					if((cell !=null && cell.getCellTypeEnum()==CellType.STRING)){
						String value= cell.getStringCellValue();
						val[i][j]=value;
					}
					if(cell !=null && cell.getCellTypeEnum()==CellType.NUMERIC){
						String value= NumberToTextConverter.toText(cell.getNumericCellValue());
						val[i][j]= value;
					}

					//	System.out.println(i+""+j);
				}}}


		k++; */

		return val;

	}


	@org.testng.annotations.Test (dataProvider="dat", description="Hai Hello")
	public void tet(String a, String b, String c, String d, String e) {
		System.out.println("First MEthod value is "+a+b+c+d+e);

	}
	@org.testng.annotations.Test (dataProvider="dat", description="Hai 2nd method")
	public void tent(String a, String b, String c, String d, String e) {
		System.out.println("second MEthod value is "+a+b+c+d+e);
	}
}

