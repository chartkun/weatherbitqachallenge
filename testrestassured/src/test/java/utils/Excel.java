package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Excel {
	static XSSFWorkbook workbook;
	static XSSFSheet worksheet;
	public Excel(String excelPath, String sheetName) {
		try {
			workbook = new XSSFWorkbook(excelPath);
			worksheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	public static String getCellData(int RowNum, int ColNum) {
		String celdata = worksheet.getRow(RowNum).getCell(ColNum).toString();
		return celdata;
	}
	
	public Object[][] getTableArray() {   
		String[][] rowArray = null;
		int startRow = 1;
		int startCol = 0;
		int ci,cj;
		int totalRows = worksheet.getLastRowNum();
		int totalCols = worksheet.getRow(0).getLastCellNum();
		rowArray=new String[totalRows][totalCols];
		ci=0;
		for (int i=startRow;i<=totalRows;i++, ci++) {           	   
			cj=0;
			for (int j=startCol;j<totalCols;j++, cj++){
				rowArray[ci][cj]=getCellData(i,j);
			}
		}
		return(rowArray);
	}
}
