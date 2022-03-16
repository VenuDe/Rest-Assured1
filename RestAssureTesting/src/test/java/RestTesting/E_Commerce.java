package RestTesting;


import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class E_Commerce {
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	public static String message;
	public static String accessToken;
	public static String id;

	
	
	@Test (priority = 0, enabled = false)
	public String signup()
	{
		RestAssured.baseURI =baseurl;
		
	String 	requestbody = "{\n"
			+ "	\"email\": \"Venud14@gmail.com\",\n"
			+ "	\"password\": \"venu007\"\n"
			+ "}";
	
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/signup")
			
			.then()
			.assertThat().statusCode(201).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	message = js.get("message");
	System.out.println(message);
	return requestbody;
	
		
	}
	
	
	@Test(priority = 1)
	public void Login()
	{
		RestAssured.baseURI =baseurl;
		
			String 	requestbody = signup();
					
	
	Response resposne = given()
			.header("Content-Type","application/json")
			.body(requestbody)
			
			.when()
			.post("/user/login")
			
			.then()
			.assertThat().statusCode(200).contentType(ContentType.JSON)
			.extract().response();	
	
	String jsonresponse = resposne.asString();
	//i want to convert the response in to json format
	//why do i use jsonpath to convert the string in to a json format
	JsonPath js = new JsonPath(jsonresponse);
	accessToken = js.get("accessToken");
	System.out.println("Token: "+accessToken);
	}
	
	
	


	@Test(priority = 2) 
	public void GetAllUsers()
	{
		RestAssured.baseURI = baseurl;
		Response response = given()
		.header("Authorization", "bearer "+accessToken)
		.header("Content-Type","application/json")
		
		
		.when()
		.get("/user")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	

		String jsonresponse = response.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		id= js.get("users[21]._id");
		//System.out.println(message);
		System.out.println("UserNo 20's Id: "+id);
	}
	
	@Test(priority = 3) 
	public void DeleteUserById()
	{
		RestAssured.baseURI = baseurl;
		Response response = given()
		.header("Authorization", "bearer "+accessToken)
		.header("Content-Type","application/json")
		
		
		.when()
		.delete("/user/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	

String jsonresponse = response.asString();
//i want to convert the response in to json format
//why do i use jsonpath to convert the string in to a json format
JsonPath js = new JsonPath(jsonresponse);
message = js.get("message");
System.out.println(message);

	}
}
	