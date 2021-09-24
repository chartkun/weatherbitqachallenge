package testrestassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utils.Excel;

public class AC3 {

	String API_key = "75b37e35d9bc4ad7af8250fad3c0e50b";

	@DataProvider(name = "TestData")
	public Object[][] GetTestData(){

		String excelPath = "./data/TestData.xlsx";
		String sheetName = "AC3";

		Excel excelData = new Excel(excelPath,sheetName);

		Object[][] testData = excelData.getTableArray();

		return testData;
	}

	@Test(dataProvider = "TestData")
	public void AirQualityByPostalCode (String postal_code, String key) {
				
		RestAssured.baseURI = "https://api.weatherbit.io/";
		RestAssured.basePath = "v2.0/current/airquality";
		given().
			contentType(ContentType.JSON).
		with().
			queryParam("postal_code", postal_code).
			queryParam("key", key).
		when().
			get("").
		then().
			statusCode(200).
			log().body();
	}
}
