package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.*;

public class Base {

	
	@Before("@DeletePlaceAPI")   /// so before executing DeletePlaceAPI tagged scenario we need to run this below method
	public void base() throws IOException {
		
	/*
	 * Here dependency is we need place_id
	 * so write a code using which we can fetch place_id
	 * 
	 * 
	 * And execute this code only when place_id is null
	 * */
		
		PlaceAPI	place =new PlaceAPI();
		
		
		
		if(PlaceAPI.place_id==null) {
			
			place.add_place_payload_with("David", "80","English","Australia");
	
			place.user_calls_with_http_request("addPlaceAPI", "POST");
			place.verify_place_id_created_maps_to_using("David", "getPlaceAPI");
			
		}
	
	}

}
