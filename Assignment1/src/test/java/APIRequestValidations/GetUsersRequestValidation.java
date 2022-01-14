package APIRequestValidations;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUsersRequestValidation {
	@Test
public void ListUsers() {
	RestAssured.baseURI="https://reqres.in/";
	Response response=RestAssured.given()
			.contentType("application/json")
			.accept("application/json")
			.when().get("/api/users?page=2").then().statusCode(200).extract().response();
	
JsonPath Extractor=response.jsonPath();

	System.out.println("Ids from Response: " +Extractor.get("data[0].id"));
	System.out.println("Ids from Response: " +Extractor.get("total_pages"));
	List<Object> DataList=Extractor.getList("data");
	System.out.println(Extractor.getList("data").toString());
	
	for (int i=0;i<DataList.size();i++) {
		DataList.get(i).toString();
		String DataElement= DataList.get(i).toString();
		Assert.assertTrue(DataElement.contains("id"));
		Assert.assertTrue(DataElement.contains("email"));
		Assert.assertTrue(DataElement.contains("first_name"));
		Assert.assertTrue(DataElement.contains("last_name"));
		Assert.assertTrue(DataElement.contains("avatar"));
	
	}
Assert.assertEquals(Extractor.get("data[0].id"), 7);
Assert.assertEquals(Extractor.get("data[0].email"),"michael.lawson@reqres.in");
}
}

