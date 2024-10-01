package akk;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import input.jsonResusableMethod;
import input.payload;

public class basics {

	public static void main(String[] args) {
		JsonPath js;
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response=given().log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(payload.tostring())
			.when().post("maps/api/place/add/json").
			then().log().all().assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Content-Length", "194")
				.extract().response().asString();
		//System.out.println("**"+response);
	
	js=	jsonResusableMethod.rawToJson(response);
		String placeId=js.get("place_id");
		System.out.println("place id is : "+js.get("place_id"));
		
		//updating the newly created resource
		String Address="NELLORE  , INDIA";
		given().relaxedHTTPSValidation().log().all().queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+Address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		//getting the updated resource
		String f=
		given().queryParam("key", "qaclick123").queryParam("place_id", ""+placeId+"")
		.when().get("maps/api/place/get/json")
		.then().log().all().statusCode(200).extract().response().asString();
		
		js=	jsonResusableMethod.rawToJson(f);
		String opadd=js.get("address");
		System.out.println("** "+js.get("address"));
		Assert.assertEquals(Address, opadd);
		

	}

}
