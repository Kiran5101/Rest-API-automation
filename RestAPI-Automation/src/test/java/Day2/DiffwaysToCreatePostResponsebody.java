package Day2;

import java.awt.JobAttributes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

/*Different ways to create Post request body
1)Post request body using HashMap
2)Post request body using JSON Object
3)Post request body using POJO class
4)Post Request body using External JSON Body*/

public class DiffwaysToCreatePostResponsebody {
	@Test
	void testPostUsingHashMap() {
	HashMap data=new HashMap();
	data.put("name", "Ram");
	data.put("location", "Bangalore");
	data.put("Phone", "4536543");
	String[] courseArr= {"Java","Selenium"};
	data.put("courses", courseArr);
	
	given()
		.contentType("application/json")
		.body(data)
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.body("name", equalTo("Ram"))
		.body("location",equalTo("Bangalore"))
		.body("Phone", equalTo("4536543"))
		.body("courses[0]", equalTo("Java"))
		.body("courses[1]", equalTo("Selenium"))
		.log().all();
	}
	
	@Test
	void createRequestBodyusingJSONObject() {
		JSONObject data=new JSONObject();
		data.put("name","Kiran");
		data.put("location", "Hyderabad");
		data.put("Phone", "743896535");
		String[] CourseArr= {"PlyWright","Phython"};
		data.put("courses", CourseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Kiran"))
			.body("location", equalTo("Hyderabad"))
			.body("Phone", equalTo("743896535"))
			.body("courses[0]", equalTo("PlyWright"))
			.body("courses[1]", equalTo("Phython"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();
			
		
	}
	@Test(priority=3)
	void createRequestBodyUsingPOJO() {
		POJO data=new POJO();
		data.setName("charan");
		data.setLocation("Pune");
		data.setPhone("458568");
		String[] arr= {"Java","Cypress"};
		data.setCourses(arr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("charan"))
			.body("location", equalTo("Pune"))
			.body("phone", equalTo("458568"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]", equalTo("Cypress"))
			.headers("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	@Test
	void createRequestBodyUsingexternalJSONFile() throws FileNotFoundException {
		File f=new File(".\\body.json");
		FileReader fr=new FileReader(f);
		JSONTokener jt=new JSONTokener(fr);
		JSONObject jo=new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(jo.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("vinod"))
			.body("location", equalTo("mumbai"))
			.body("phone",equalTo("837563"))
			.body("courses[0]",equalTo("c"))
			.body("courses[1]", equalTo("c++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}

}
