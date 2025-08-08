package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LogInData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testData\\OpenCart_TestData.xlsx";
		
		ExcelUtility xlFile = new ExcelUtility(path);
		int rows = xlFile.getRowCount("TestData");
		int cells = xlFile.getCellCount("TestData", 1);
		
		String logInData[][] = new String[rows][cells];
		
		for(int i=1; i<=rows; i++) {
			for(int j=0; j<cells; j++) {
				logInData[i-1][j] = xlFile.getCellData("TestData", i, j);
			}
		}
		return logInData;
	}
	
	

}
