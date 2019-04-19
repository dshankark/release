package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataFile {
	static Object[][] val=null;

	ArrayList<Integer> al=null;


	public  ArrayList<Integer> dat(String des) throws IOException {

		System.out.println("desc value is "+des);
		FileInputStream fs = new FileInputStream("c://ss//Testdata.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sh = wb.getSheet("Sheet1");
		XSSFRow Row=sh.getRow(0);
		int totalNoOfCols = Row.getLastCellNum();
		int totalNoOfRows = sh.getPhysicalNumberOfRows();
		System.out.println(totalNoOfCols+" "+totalNoOfRows);
		for (int g=0;g<totalNoOfRows;g++) {
			XSSFCell cel=sh.getRow(g).getCell(0);
			String valu = cel.getStringCellValue();
			System.out.println("value is "+valu);
			if(des.equalsIgnoreCase(valu))
			{
				System.out.println("Matches with the tc "+ valu);
				int rownum = g;
				System.out.println("rownum is "+g);
				System.out.println("Vlaue of rownum "+rownum);
				al= new ArrayList<Integer>();
				al.add(rownum);
				al.add(totalNoOfCols);
			}
		}
		return al;
	}

	public Object[][] xcelrowValues(Integer rowNumber, Integer colNumber) throws IOException {

		System.out.println("Method excel row is executed");
		FileInputStream fs = new FileInputStream("c://ss//Testdata.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sh = wb.getSheet("Sheet1");
		//	XSSFRow Row=sh.getRow(0);



		XSSFRow  row=sh.getRow(rowNumber);
		int totalNoOfCols = colNumber;
		int totalNoOfRows = rowNumber;
		System.out.println(totalNoOfRows+"  "+totalNoOfCols);

		Object val[][] = new Object[1][totalNoOfCols];
		for (int j=0; j <= totalNoOfCols; j++) {
			if(row==null) {
				val[0][j]= "";
				System.out.println("value is null");

			}
			else {
				XSSFCell cell= row.getCell(j);
				if((cell !=null && cell.getCellTypeEnum()==CellType.STRING)){
					String value= cell.getStringCellValue();
					System.out.println(value);

					val[0][j]=value;
				}
				if(cell !=null && cell.getCellTypeEnum()==CellType.NUMERIC){
					String value= NumberToTextConverter.toText(cell.getNumericCellValue());
					System.out.println(value);
					val[0][j]= value;
				}
			}
			//System.out.println(val[0][j]);
		}


		//	System.out.println("The set va;ue is "+val[1][1]);





		return val;
	}

}




