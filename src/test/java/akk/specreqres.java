package akk;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.Location;
import POJO.maps;
import input.payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class specreqres {

	public static void main(String[] args) {
		JsonPath js;
	//	RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		maps map=new maps();
		map.setAccuracy(50);
		map.setAddress("a-4-2-12");
		map.setLanguage("telugu");
		map.setPhone_number("45678");
		map.setWebsite("www.google.com");
		map.setName("keeetthi");
		List<String> li=new ArrayList<String>();
		li.add("selenium");
		li.add("resrassured");
		map.setTypes(li);
		Location loc=new Location();
		loc.setLat("-38.383494");
		loc.setLng("38.383494");
		map.setLocation(loc);
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			//	addHeader("Content-Type", "application/json")
				.addQueryParam("key", "qaclick123").build();
		
	//	new ResponseSpecBuilder().expectStatusCode(0)
		
		given().spec(req).header("Content-Type", "application/json")
			.body(map)
			.when().post("maps/api/place/add/json").
			then().log().all().assertThat()
				.statusCode(200);
		

	}

}
