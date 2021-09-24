package testrestassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.Excel;

public class AC2 {

	String API_key = "75b37e35d9bc4ad7af8250fad3c0e50b";

	@DataProvider(name = "TestData")
	public Object[][] GetTestData(){

		String excelPath = "./data/TestData.xlsx";
		String sheetName = "AC2";

		Excel excelData = new Excel(excelPath,sheetName);

		Object[][] testData = excelData.getTableArray();

		return testData;
	}

	@Test(dataProvider = "TestData")
	public void WeatherByCities (String cities, String key) {

		RestAssured.baseURI = "https://api.weatherbit.io/";
		RestAssured.basePath = "v2.0/current";
		given().
			contentType(ContentType.JSON).
		with().
			queryParam("cities", cities).
			queryParam("key", key).
		when().
			get("").
		then().
			statusCode(200).
			log().body();	
	}
}
