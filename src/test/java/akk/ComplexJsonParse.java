package akk;

import org.testng.Assert;
import org.testng.annotations.Test;

import input.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	@Test
	public static void main() {
		JsonPath js = new JsonPath(payload.complexJson());
		int noOfCourses = js.get("courses.size()");
		System.out.println("noOfCourses : " + noOfCourses);

		// print purchase amount
		int purchaseamt = js.get("dashboard.purchaseAmount");
		System.out.println("purchase amount : " + purchaseamt);

		// print all courses title & prices
		int price = 0, copies = 0,total=0;
		int sum[] = new int[noOfCourses];
		System.out.println("**print all courses title & prices : **");
		for (int i = 0; i < noOfCourses; i++) {
			price = js.get("courses[" + i + "].price");
			copies = js.get("courses[" + i + "].copies");
			sum[i] = price * copies;
			total+=sum[i];
			System.out.println(js.get("courses[" + i + "].price") + " " + js.get("courses[" + i + "].title"));
		}
		System.out.println("sum of purchases : "+total);
		Assert.assertEquals(purchaseamt, total);


	}

}
