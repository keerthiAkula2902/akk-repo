package akk;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import input.jsonResusableMethod;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DynamicJsonPayload {
	
	@Test(dataProvider = "inputs")
	public void add(String bookname,Integer booknum) throws IOException {
	
		JsonPath j;
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response=
		given().log().all().header("Content-Type","application/json")
	//	.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\U1133463\\eclipse-workspace\\akk\\jsonInputPaayloads\\addBook.txt"))))
		.body("{\r\n"
				+ "    \"name\": \"ak\",\r\n"
				+ "    \"isbn\": \""+bookname+"\",\r\n"
				+ "    \"aisle\": \""+booknum+"\",\r\n"
				+ "    \"author\": \"keerthi\"\r\n"
				+ "}")
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		j=jsonResusableMethod.rawToJson(response);
		String id=j.get("ID");
		System.out.println("**Book created with ID ** : "+id);
		//deleting the book
		String deletedBookId=
		given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"ID\":\""+id+"\"\r\n"
				+ "}")
		.when().delete("Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		j=jsonResusableMethod.rawToJson(deletedBookId);
		System.out.println("**Deleted Book ID : "+j.get("msg"));
	}
	
	@DataProvider(name="inputs")
	public Object[][] akdata(){
		
		return new Object[][] {{"keerthi",100},{"restAssured",200},{"Selenium",400}};
	}

}
