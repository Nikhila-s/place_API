package resources;

public enum APIEndpoints {

	
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	String endpoint;
	
	
	 APIEndpoints(String endpoint){   // here our methods addPlaceAPI etc accepting one argument which is a string ( endpoint)
		 
		this.endpoint=endpoint;
	}
	 
	 public String getEndpoint() {
		 return endpoint;
	 }
	
}
