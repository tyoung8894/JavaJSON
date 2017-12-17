package tyleryoung.zipcode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonReader {

	public static void main(String[] args) throws IOException {
		
		//uses zipcode API and NFL arrest API
		
		//String sURL = "http://freegeoip.net/json/"; //just a string
		String sURL = "http://nflarrest.com/api/v1/crime";
		

		// Connect to the URL using java's native library
		URL url;

		url = new URL(sURL);
		HttpURLConnection request;
		request = (HttpURLConnection) url.openConnection();
		request.connect();
		// Convert to a JSON object to print data
		JsonParser jp = new JsonParser(); //from gson
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
		//JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
		
		JsonArray rootArray = root.getAsJsonArray();
		for(JsonElement element: rootArray){
			JsonObject rootobj = element.getAsJsonObject();
			Set<String> keyset = rootobj.keySet();
//			for(String str: keyset){
//				System.out.println(str);
//			}
			
			Set<Entry<String, JsonElement>> set = rootobj.entrySet();
			for(Entry<String, JsonElement> entryset : set){
				System.out.println(entryset.getKey() + " " + entryset.getValue().getAsString());
				
			}
			
			//String dui = rootobj.get("DUI").getAsString();
			//System.out.println(dui);
		}
		
		//String zipcode = rootobj.get("Category").getAsString(); //just grab the zipcode
		//System.out.println(zipcode);


	}

}
