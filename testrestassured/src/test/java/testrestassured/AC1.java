package testrestassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.Excel;

public class AC1 {

	@DataProvider(name = "TestData")
	public Object[][] GetTestData(){

		String excelPath = "./data/TestData.xlsx";
		String sheetName = "AC1";

		Excel excelData = new Excel(excelPath,sheetName);

		Object[][] testData = excelData.getTableArray();

		return testData;
	}

	@Test(dataProvider = "TestData")
	public void WeatherByCoordinates (String lat, String lon, String key) {

		RestAssured.baseURI = "https://api.weatherbit.io/";
		RestAssured.basePath = "v2.0/current";
		given().
			contentType(ContentType.JSON).
		with().
			queryParam("lat", lat).
			queryParam("lon", lon).
			queryParam("key", key).
		when().
			get("").
		then().
			statusCode(200).
			log().body();	
	}
}
