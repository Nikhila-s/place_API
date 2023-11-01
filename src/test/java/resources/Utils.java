package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public 	Response response;
	public JsonPath js;
	public String resp;


	public static RequestSpecification  req;
	public RequestSpecification requestSpecification() throws IOException {
		
	//	RestAssured.baseURI="https://rahulshettyacademy.com";
		
		if(req==null) {
		PrintStream stream =new PrintStream(new FileOutputStream("logging.txt"));

		
		  req =new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123")
				 
				  .addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream))
				  .setContentType(ContentType.JSON).build();
		
		return req;
		}
		return req;
	}
	
	
	public String getGlobalValues(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream file=new FileInputStream("D:\\api_rest\\apiProjects\\cucumber_placeAPI\\src\\test\\java\\resources\\global.properties");
		prop.load(file);
		return prop.getProperty(key);
		
	}
	
	public String getJSONPath(Response response, String key) {
		
		// method receives response and we need to pass key
		
		   resp= response.asString();
		   js=new JsonPath(resp);
		  return js.get(key).toString();
	}
}
