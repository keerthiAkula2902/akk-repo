package akk;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.GetCourseDetails;
import POJO.WebAutomation;
import POJO.api;
import input.jsonResusableMethod;

public class oauth_practice {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		JsonPath js;

		
		String response = given()
				.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
				formParam("grant_type", "client_credentials")
				.formParam("scope", "trust").
				when().post("oauthapi/oauth2/resourceOwner/token").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		js = jsonResusableMethod.rawToJson(response);
		String token = js.get("access_token");
		System.out.println(token);
		
		String coursetitles[]= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		GetCourseDetails gc=
		given().log().all().queryParam("access_token", token)
		.when().get("oauthapi/getCourseDetails")
		.as(GetCourseDetails.class);
		System.out.println("***"+gc.getInstructor()+"**");
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		int len=gc.getCourses().getApi().size();
		for(int i=0;i<len;i++) {
			System.out.println( gc.getCourses().getApi().get(i).getCourseTitle());
			System.out.println( gc.getCourses().getApi().get(i).getPrice());
		}
		
		List<api>apicourses=gc.getCourses().getApi();
		for(int i=0;i<apicourses.size();i++) {
			if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("SoapUI Webservices testing  "+ apicourses.get(i).getPrice());
			}
		}
		
		List<String> output_courseTitles=new ArrayList<String>();
		List<WebAutomation>webcourses=gc.getCourses().getWebAutomation();
		for(int i=0;i<webcourses.size();i++) {
			output_courseTitles.add(webcourses.get(i).getCourseTitle());
		}
		
		List<String> explist=Arrays.asList(coursetitles);
		Assert.assertTrue(explist.equals(output_courseTitles));
	}

}
