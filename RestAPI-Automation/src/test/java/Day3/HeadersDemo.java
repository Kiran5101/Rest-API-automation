package Day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
public class HeadersDemo {
	//@Test(priority=1)
	public void testHeaders() {
		given()
		.when()
			.get("https://www.google.co.in/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws")
			.log().all();
	}
	@Test(priority=2)
	public void getHeaders() {
		Response res=given()
					.when()
					.get("https://www.google.co.in/");
		//get single headers
		System.out.println(res.getHeader("Content-Type"));
		System.out.println(res.getHeader("Content-Encoding"));
		System.out.println(res.getHeader("Server"));
		
		//get all the headers
		Headers myheaders=res.getHeaders();
		for(Header h:myheaders) {
			System.out.println(h.getName()+"::"+h.getValue());
		}
		}

}
