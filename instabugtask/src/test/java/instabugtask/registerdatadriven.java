package instabugtask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class registerdatadriven {
	
	WebDriver driver;
    WebDriverWait wait;
    WebElement usernamebtn;
    WebElement passwordbtn;
    WebElement loginbtn;
    WebElement closebtn;
	@BeforeTest
	public void envsetup() throws InterruptedException {
		//set firefox driver using web driver manager library 
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		//Set the required web page link 
		driver.get("https://www.facebook.com/login.php?next=https%3A%2F%2Fdevelopers.facebook.com%2Fapps");
		wait = new WebDriverWait(driver,30);
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[3]/div[2]/form/div[2]/div[4]/div[3]/a")).click();
		Thread.sleep(1000);
	}

	
	@Test(dataProvider="facebookregdata")
public void register( String myfirstname ,String mylastname , String phonenumber , String mypassword, String myday, String mymonth , String myyear ) throws InterruptedException  {
		
	
WebElement username = 	driver.findElement(By.name("firstname"));
username.clear();
username.sendKeys(myfirstname);
	WebElement lastname = driver.findElement(By.name("lastname"));
	lastname.clear();
	lastname.sendKeys(mylastname);
	WebElement emailorphone= driver.findElement(By.name("reg_email__"));
	emailorphone.clear();
	emailorphone.sendKeys(phonenumber);
	WebElement password = driver.findElement(By.name("reg_passwd__"));
	password.clear();
	password.sendKeys(mypassword);
	//driver.findElement(By.name("reg_email_confirmation__")).sendKeys(myemail1);

	Select day=new Select(driver.findElement(By.id("day")));
	day.selectByVisibleText(myday);
	Select month=new Select(driver.findElement(By.id("month")));
	month.selectByVisibleText(mymonth);
	Select year=new Select(driver.findElement(By.id("year")));
	year.selectByVisibleText(myyear);
	driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/div[2]/div/div/div[1]/form/div[1]/div[7]/span/span[1]/label")).click();
	driver.findElement(By.name("websubmit")).click();
	Thread.sleep(10000);
/*	wait = new WebDriverWait(driver,40);
    
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_error_inner")));
	
	if (!driver.findElement(By.id("reg_error_inner")).isDisplayed()) {
		System.out.println("user register passed");
	}else {		System.out.println("user register failed");
}*/
	String expectedUrl = "https://www.facebook.com/confirmemail.php?next=https%3A%2F%2Fwww.facebook.com%2F&rd";
	Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
System.out.println("User Name: "+phonenumber+" registered successfully");

}
	
	
	//Read the Data from the excel sheet
	@DataProvider(name = "facebookregdata")
	public  Object[][] getdata() {
		String projectpath=	System.getProperty("user.dir");
		String excelpath = "/excel/reg.xlsx";
		String sheetname = "Sheet2";
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
			
		String celldata1 =	dat.getcelldata(i, j);
		data[i-1][j] = celldata1;
	}
	}
	return data;


}
}