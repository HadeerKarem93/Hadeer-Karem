package instabugtask;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xsldat {
	
	
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
    
    
    
    public xsldat(String excelpath , String sheetname) {
    	try {
    	// load the workbook
    	String projectpath =System.getProperty("user.dir");
    	workbook = new XSSFWorkbook(projectpath + excelpath);
    	
    	//load the sheet which has data
    	sheet = workbook.getSheet(sheetname);
    	}
    	catch(Exception exp) {
    		
    		System.out.println(exp.getMessage());
    		System.out.println(exp.getCause());
    		exp.printStackTrace();
    	}
    }
    
    


//Get number of rows which has data 
public int getrowcount() {
	int rowcount=0;
	rowcount = sheet.getPhysicalNumberOfRows();
	return rowcount;
	}
//Get number of coloumn which has data 
public int getcolcount() {
	int colcount=0;
	 colcount = sheet.getRow(0).getPhysicalNumberOfCells();
	return colcount;
    }
// Get the string data in the cell 
	public String getcelldatastr(int rownum , int colnum) {	
		String celldata=null;
		 celldata =sheet.getRow(rownum).getCell(colnum).getStringCellValue();	
		return celldata;
	
		
	}
	// Get the numrical data in the cell
	public double getcelldatanum(int rownum , int colnum) {	
double celldata = 0;
	 celldata =sheet.getRow(rownum).getCell(colnum).getNumericCellValue();
			return celldata;
		}
		
	// Get the numrical data in the cell
		public String getcelldata(int rownum , int colnum) {	
			DataFormatter formatter = new DataFormatter();
		String	celldata1 = formatter.formatCellValue(sheet.getRow(rownum).getCell(colnum));
				return celldata1;
			}
}


