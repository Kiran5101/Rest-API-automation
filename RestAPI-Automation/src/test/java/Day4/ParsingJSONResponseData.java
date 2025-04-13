package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	//@Test(priority=1)
	public void parsingData() {
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/Book")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("category[2]",equalTo("Action Thriller"))
			.body("author[1]", equalTo("Ram charan"));
	}
	//@Test(priority =2)
	public void parsingDataSecondApproach(){
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/Book");
		
		Assert.assertEquals(res.getStatusCode(),200);
		String value=res.jsonPath().get("category[2]").toString();
		Assert.assertEquals(value, "Action Thriller");
		System.out.println(res.getBody());
	}
	//@Test(priority=3)
	public void transverseJsonObjects() {
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/book");
		
		JSONObject jo=new JSONObject(res.asString());// converting response to JSON Object
		System.out.println(jo.getJSONArray("book").length()); 
		
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String title=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(title);
		}
		
	}
	@Test(priority=4)
	public void validatePresenceOfJSONField(){
		boolean status=false;
		Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/book");
			
		JSONObject jo=new JSONObject(res.asString());
		for(int i=0;i<jo.getJSONArray("book").getJSONObject(i).length();i++) {
			String title=jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(title.equals("Hello world")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status,true);
	}
	@Test(priority=5)
	public void getTotalPrice() {
		double price=0;
		Response res=given()
				.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/book");
		
		JSONObject jo=new JSONObject(res.asString());
		for(int i=0;i<jo.getJSONArray("book").getJSONObject(i).length();i++) {
		String prices=jo.getJSONArray("book").getJSONObject(i).getString("price").toString();
		price+=Double.parseDouble(prices);
		Assert.assertEquals("totalPrice", 53.92);
		}
	}
}
