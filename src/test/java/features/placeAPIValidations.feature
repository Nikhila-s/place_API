Feature: Validating place APIs


@AddPlaceAPI
Scenario Outline: Verify if placeAPI is succesfully added using AddPlaceAPI
	Given Add place payload with "<Name>" "<Accuracy>" "<Language>" "<Address>"
	When User calls "addPlaceAPI" with "POST" http request
	Then The API call got succes with status code as 200
	Then "status" in response is "OK"
 	Then "scope" in response is "APP" 
 	Then Verify place_Id created maps to "<Name>" using "getPlaceAPI"
 	
Examples:
	|Name | Accuracy | Language | Address|
	|Jim  | 40		 |English	|California|
	|Kim  | 50		 |Hindi		|Gujarat|
	|Dom  | 60		 |Telugu	|Hyderabad|
	
	
@DeletePlaceAPI	
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "POST" http request
	Then The API call got succes with status code as 200
	
	 