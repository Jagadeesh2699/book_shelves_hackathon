package utilities;

import testBase.BaseClass;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProviders extends BaseClass{

	ExcelUtility xlutil;
	
	@DataProvider(name="dp")
	public String [][] getData() throws IOException{
		
		xlutil=new ExcelUtility(p.getProperty("getPath"));//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String data[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				data[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return data;//returning two dimension array
				
	}
	
}
