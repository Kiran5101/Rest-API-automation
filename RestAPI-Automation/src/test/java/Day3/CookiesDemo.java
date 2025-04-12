package Day3;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class CookiesDemo {
	//@Test(priority=1)
	public void validateCookies() {
		given()
		.when()
			.get("https://www.google.co.in/")
		.then()
			.cookie("AEC","AVcja2c1EK05q0pxQLtV2txw26zV09EithxjYf2xmvxNhABH81ray0ha-g")
			.log().all();
	}
	@Test(priority=2)
	public void getAllCoockies() {
	Response res=given()
		.when()
			.get("https://www.google.co.in/");
		//get single cookie value
	//String cookie_value=res.getCookie("AEC");
	//System.out.println(cookie_value);
	
	//get all the cookies
	Map<String,String> cooks=res.getCookies();
	for(String s:cooks.keySet()) {
		System.out.println(res.getCookie(s));
	}
			}
}
