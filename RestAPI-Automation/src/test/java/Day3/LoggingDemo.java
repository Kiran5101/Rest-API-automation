package Day3;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class LoggingDemo {
	
	@Test
	public void LoggingConcept() {
		given()
		.when()
			.get("https://www.google.co.in/")
		.then()
			//.log().all();   --->logs all the information(status code,status message,headers,cookies,headers)
			//.log().body();  ---> logs only the response body
			//.log().cookies();--> logs only the cookies information
			.log().headers(); // ---> logs only the headers information
	}

}
