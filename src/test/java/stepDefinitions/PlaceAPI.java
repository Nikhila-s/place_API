package stepDefinitions;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIEndpoints;
import resources.TestDataBuild;
import resources.Utils;

public class PlaceAPI extends Utils{
	
	
	 ResponseSpecification resspec;
	 RequestSpecification res;
		Response response;
		
		 String resp;
		static String place_id;
		
		TestDataBuild testData=new TestDataBuild();
		
		
		
	//	Utils utils=new Utils(); 
		/*
		 * instead of creating object and calling methods from this,
		 *  we are going with inheritance, bcoz this we will use alot
		 */
	 
	@Given("Add place payload with {string} {string} {string} {string}")
	public void add_place_payload_with(String Name, String Accuracy, String Language, String Address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		// RestAssured.baseURI="https://rahulshettyacademy.com";

		/* **** moved this to TestDataBuild file****
		 * 
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
	*/
		
//		RequestSpecification  req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
//				 .setContentType(ContentType.JSON).build();
				  
		//  resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
				// --> moving response spec to post request method , where we are using response
		
				
				  res=given().spec(requestSpecification())
				 .body(testData.addPlacePayload(Name,Accuracy,Language,Address));
	}
	
	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request( String endpoint, String method) {
	    // Write code here that turns the phrase above into concrete actions
		  resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		  APIEndpoints apiEndpoint=APIEndpoints.valueOf(endpoint);
		  
			if(method.equalsIgnoreCase("POST")) {
				
				 response =res.when().post(apiEndpoint.getEndpoint());
			System.out.println(apiEndpoint.getEndpoint());
			}
				else if(method.equalsIgnoreCase("GET")) {
					 response =res.when().get(apiEndpoint.getEndpoint());
			System.out.println(apiEndpoint.getEndpoint());
				}
		}
	
		
		//  System.out.println(apiEndpoint.getEndpoint());
//		 response =res.when().post(apiEndpoint.getEndpoint()).
//				then().spec(resspec).extract().response();
	
	
//	@Then("The API call got success with status code as {int}")
//	public void the_api_call_got_succes_with_status_code_as(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//	assertEquals(response.getStatusCode(), 200);
//	}
	
	@Then("The API call got succes with status code as {int}")
	public void the_api_call_got_succes_with_status_code_as(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}
	
	
	
	@Then("{string} in response is {string}")  // keys in json path is case sensitive
	public void in_response_is(String JsonKey, String JSONValue) {
	    // Write code here that turns the phrase above into concrete actions
//	   resp= response.asString();
//	   js=new JsonPath(resp);
//	  System.out.println(js.get(JsonKey));
	//  System.out.println(js.get(JsonKey).toString());
//	 assertEquals(js.getString(JsonKey).toString(), JSONValue); 
//	 assertEquals(js.get(JsonKey).toString(), JSONValue); 
		
		assertEquals(getJSONPath(response, JsonKey), JSONValue);
	}
	
	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String endpoint) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		  // extract place_id from json response so that we can use this in get requests
		
//		  js=new JsonPath(resp);
//		 place_id= js.get("place_id");
		// build request specification

	// -------construct the request ------------	
	 place_id=	getJSONPath(response, "place_id");
	res=given().spec(requestSpecification()).queryParam("place_id", place_id);
	
	// --- next step-- we need to hit GET request, that is taken care in When ()
	
	user_calls_with_http_request(endpoint,"GET");
	
	// validation
	String actualName=	getJSONPath(response, "name");
	
	 assertEquals(actualName, expectedName);
	
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions

		res=given().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));		
	}
}
