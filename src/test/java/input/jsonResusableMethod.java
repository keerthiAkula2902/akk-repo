package input;

import io.restassured.path.json.JsonPath;

public class jsonResusableMethod {
	
	public static JsonPath rawToJson(String response) {
		JsonPath js=new JsonPath(response);
		return js;
	}

}
