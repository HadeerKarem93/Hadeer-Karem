package instabugtask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class facebooklogin {
	
	WebDriver driver;
    WebDriverWait wait;
    WebElement usernamebtn;
    WebElement passwordbtn;
    WebElement loginbtn;
    WebElement closebtn;
	@BeforeTest
	public void envsetup() {
		//set firefox driver using web driver manager library 
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		//Set the required web page link 
		driver.get("https://www.facebook.com/login.php?next=https%3A%2F%2Fdevelopers.facebook.com%2Fapps");
		wait = new WebDriverWait(driver,30);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	@Test(dataProvider="facebooklogindata")
	public void facebooklogin(String username , String password ) {
		//Enter user name in user name text box
		usernamebtn= driver.findElement(By.id("email"));
		usernamebtn.clear();
		usernamebtn.sendKeys(username);
		//enter password in password text box 
		passwordbtn = driver.findElement(By.id("pass"));
		passwordbtn.clear();
		passwordbtn.sendKeys(password);
		//press login button 
		loginbtn = driver.findElement(By.id("loginbutton"));
		loginbtn.click();
		//check that the right user name logged in successfully 
		String expectedUrl = "https://developers.facebook.com/apps";
		Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
		  System.out.println("User Name: "+username+" logged in successfully");
		
	}
	
	//Read the Data from the excel sheet
	@DataProvider(name = "facebooklogindata")
	public  Object[][] getdata() {
		String projectpath=	System.getProperty("user.dir");
		String excelpath = "/excel/login.xlsx";
		String sheetname = "Sheet1";
	Object data[][]=testdata(excelpath , sheetname);
	return data;
	}
	
	//Create an object array with the size of data to read the data from the all rows we added 
	public Object[][] testdata(String excelpath , String sheetname) {
		
		xsldat dat = new xsldat(excelpath , sheetname);
		int colcount = dat.getcolcount();
	int rowcount=	dat.getrowcount();
	
	Object data[][] = new Object[rowcount-1][colcount];
	
	for (int i=1 ; i<rowcount ;i++) {
		for (int j=0 ; j<colcount ; j++) {
			
		String celldata =	dat.getcelldatastr(i, j);
		data[i-1][j] = celldata;
	}
	}
	return data;


}
}