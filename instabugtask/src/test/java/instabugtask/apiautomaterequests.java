package instabugtask;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;


public class apiautomaterequests {
	JSONObject request = new JSONObject();

	@Test

	public void testpricechange() {
	//TVs with free shipping and price between $300 and $400 and get body to check if price in this range 

given().get("http://localhost:3030/products?category.name=TVs&price[$gt]=300&price[$lt]=400&shipping[$eq]=0").then().statusCode(200).log().all();

	}
	@Test

	public void testpricechange1() {
	
//TVs with free shipping and price between $500 and $600 and get body to check if price in this range 
given().get("http://localhost:3030/products?category.name=TVs&price[$gt]=500&price[$lt]=&shipping[$eq]=0").then().statusCode(200).log().all();

	}
	
	@Test

	public void categoryname() {
	
//Get categories with TV in name  
given().get("http://localhost:3030/categories?name[$like]=*TV*").then().statusCode(200).log().all();

	}
	@Test

	public void categoryname1() {
	
		//Get categories with radio in the name  
given().get("http://localhost:3030/categories?name[$like]=*Radio*").then().statusCode(200).log().all();

	}
	
	@Test

	public void categoryname2() {
	
//Send invalid category name 
		given().get("http://localhost:3030/categories?name[$like]=*Hadeer*").then().statusCode(200).log().all();

	}
	
	@Test

	public void Createproduct() {
	
//create a new product 
		Response response =get("http://localhost:3030/product");
		request.put("name", "New Product");
		request.put("type", "Hard Good");
		request.put("upc", "12345676");
		request.put("price", 99.99);
		request.put("description", "This is a placeholder request for creating a new product.");
		request.put("model", "NP12345");
		given().header("Content-Type","application.JSON").then().statusCode(201).log().all();
       System.out.println(request.toJSONString());
	}
	
	@Test

	public void Createcategory() {
	
//create a new category
		Response response =get("http://localhost:3030/categories");
		request.put("name", "New Category");
		request.put("id", "pcmcat12345");
		
		given().header("Content-Type","application.JSON").then().statusCode(201).log().all();
       System.out.println(request.toJSONString());
	}
	
	
	
	
	
	
	
	
	
}
