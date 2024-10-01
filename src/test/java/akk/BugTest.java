package akk;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import input.jsonResusableMethod;

public class BugTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://keerthiakula2902.atlassian.net";
		JsonPath js;
		String bugResponse=
		given().header("Content-Type","application/json").header("Authorization","Basic a2VlcnRoaWFrdWxhMjkwMkBnbWFpbC5jb206QVRBVFQzeEZmR0YwS2ZaUUxicndEckthb0FUOTB6S2VfVGQyZEc4RTNTcWpBUlpINHpnVFVnZmxjRTZrS0FaQ202VEhFR1pTeGFmR2FvN0VFMW9TVGVaeUR1U1E3SmdhT09tV20wTXE4S1B2VllRdFhqT3M1QnU5Y0FndzJHbjljZnFZcmhteVZXdzJSb2xGaEVZdEdLYVNYVF9xdGFmMExyTFFMdEt1Nl8xbExCUHplY1Iybmw0PTRBMzRDRUZD")
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"SCRUM\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"SPend connector not working\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		js=jsonResusableMethod.rawToJson(bugResponse);
		String bugId=js.get("id");
		System.out.println("bug created with id : "+bugId);
		
		
		//attachments adding to the bug id
		String response=
		given().header("Authorization","Basic a2VlcnRoaWFrdWxhMjkwMkBnbWFpbC5jb206QVRBVFQzeEZmR0YwS2ZaUUxicndEckthb0FUOTB6S2VfVGQyZEc4RTNTcWpBUlpINHpnVFVnZmxjRTZrS0FaQ202VEhFR1pTeGFmR2FvN0VFMW9TVGVaeUR1U1E3SmdhT09tV20wTXE4S1B2VllRdFhqT3M1QnU5Y0FndzJHbjljZnFZcmhteVZXdzJSb2xGaEVZdEdLYVNYVF9xdGFmMExyTFFMdEt1Nl8xbExCUHplY1Iybmw0PTRBMzRDRUZD")
		.header("X-Atlassian-Token","no-check").pathParam("key", bugId)
		.multiPart("file",new File("C:\\Users\\U1133463\\eclipse-workspace\\akk\\jsonInputPaayloads\\image1.PNG"))
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		js=jsonResusableMethod.rawToJson(response);
		System.out.println("** File name is : ** "+js.get("filename"));
		
		

	}

}
