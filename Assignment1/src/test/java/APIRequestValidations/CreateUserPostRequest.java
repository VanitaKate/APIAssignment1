package APIRequestValidations;

import org.testng.Assert;
import org.testng.annotations.Test;
import POJOs.PayloadPOJO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateUserPostRequest extends Report{
	@Test	
	public void CreateUser() {
		PayloadPOJO payload=new PayloadPOJO("Vanita", "leader");
		RestAssured.baseURI="https://reqres.in/";
		Response response=RestAssured.given()
				.contentType("application/json")
				.accept("application/json")
				.body(payload).when().post("/api/users").then().statusCode(201).extract().response();

		System.out.println("String Reponse: " +response.asString());
		
//		JSONObject json=new JSONObject(response);
		System.out.println(response.jsonPath().get("name").toString());
		Assert.assertEquals(response.jsonPath().get("name"), "Vanita", "User did not get added");

	}

}
