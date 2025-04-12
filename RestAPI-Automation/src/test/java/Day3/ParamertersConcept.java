package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ParamertersConcept {
	@Test
	public void pathAndQueryParameters() {
		given()
			.pathParam("Path1", "api")
			.pathParam("Path2", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
			.get("https://reqres.in/{Path1}/{Path2}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
