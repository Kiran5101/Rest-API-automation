package Day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HttpMethods {
	int id;
	@Test(priority=1)
	public void performMethods() {
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	@Test(priority=2)
	public void createUser() {
		HashMap data=new HashMap();
		data.put("name", "raju");
		data.put("job", "PlayBoy");
		id=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		/*.then()
			.statusCode(201)
			.log().all();*/
		
	}
	@Test(priority=3,dependsOnMethods= {"createUser"})
	public void updateUser() {
		HashMap data=new HashMap();
		data.put("name", "Yadava");
		data.put("job", "fucker");
		
		given()	
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test(priority=4,dependsOnMethods= {"updateUser"})
	public void deleteUser() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}

}
