package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	Utils utils=new Utils();
		public AddPlace addPlacePayload(String Name, String Accuracy, String Language, String Address) throws IOException {
			AddPlace p =new AddPlace();
			p.setAccuracy(Integer.parseInt(Accuracy));
			p.setAddress(Address);
			p.setLanguage(Language);
			p.setPhone_number("(+91) 983 893 3937");
			p.setWebsite(utils.getGlobalValues("baseUrl"));
			p.setName(Name);
			List<String> myList =new ArrayList<String>();
			myList.add("shoe park");
			myList.add("shop");

			p.setTypes(myList);
			Location l =new Location();
			l.setLat(-38.383494);
			l.setLng(33.427362);
			p.setLocation(l);
		
			return p;
		}
		
		public String deletePlacePayload(String placeId)
		{
			return "{\r\n\"place_id\": \""+placeId+"\"\r\\n}";
		}
}
