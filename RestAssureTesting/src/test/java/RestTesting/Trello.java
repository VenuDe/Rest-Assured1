package RestTesting;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import static io.restassured.RestAssured.given;

public class Trello 
{
	
	public static String baseurl ="https://api.trello.com";
	public static String id;
	public static String name;
	@Test(priority=0)
	public void createBoard()
	{
		RestAssured.baseURI = baseurl;
		Response response =given().queryParam("name", "VenuMoolyaBoard")
		.queryParam("key", "80cc0d16f996a945133ee3275f058fe9")
		.queryParam("token", "d1492078ce15feb2afdf32d69cef71eba9ec0b20be0cd88b1b6bc3086760f3bb")
		.header("Content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
	
		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		id = js.get("id");
	}
	@Test(priority =1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
	
		Response response=given()
		.queryParam("key", "80cc0d16f996a945133ee3275f058fe9")
		.queryParam("token", "d1492078ce15feb2afdf32d69cef71eba9ec0b20be0cd88b1b6bc3086760f3bb")
		.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		JsonPath js = new JsonPath(jsonresponse);
		name = js.get("name");
		System.out.println("Board Id= "+id);
		System.out.println("Board Name= " +name);
	}
	@Test(priority =2)
	public void deleteBoard()
	{
		RestAssured.baseURI = baseurl;
	
		Response response=given()
		.queryParam("key", "80cc0d16f996a945133ee3275f058fe9")
		.queryParam("token", "d1492078ce15feb2afdf32d69cef71eba9ec0b20be0cd88b1b6bc3086760f3bb")
		.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
	
	}

	
	
}
