package nagarro.test.restClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestClientDemo {

	public JSONObject getJsonResponse(String source) {
		//String output = null;

		try {
			/*
			 * Client client = Client.create();
			 * 
			 * WebResource webResource = client.resource(url);
			 * 
			 * ClientResponse response =
			 * webResource.accept("application/json").get(ClientResponse.class);
			 */

			URL url = new URL(source);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			/*
			 * if (con.getStatus() != 200) { throw new
			 * RuntimeException("Failed : HTTP error code : " + response.getStatus()); }
			 * 
			 */

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String input;

			StringBuffer outputString = new StringBuffer();
			while ((input = br.readLine()) != null) {
				outputString.append(input);
				System.out.println(input);
			}
			br.close();

			// output = response.getEntity(String.class);

			JSONParser parser = new JSONParser();
			JSONArray json = (JSONArray) parser.parse(outputString.toString());
			
			JSONObject ob = new JSONObject();
			ob.put("Response", json);

			//System.out.println("Output from Server .... \n");
			//System.out.println(output);

			return ob;
		} catch (ParseException pex) {
			JSONObject ob = new JSONObject();
			ob.put("Error", "failed to parse response");
			return ob;
		} catch (Exception ex) {
			JSONObject ob = new JSONObject();
			ob.put("Error", ex.getMessage());
			return ob;
		}
	}

	public static void main(String[] args) {
		RestClientDemo clientDemo = new RestClientDemo();
		System.out.println(clientDemo.getJsonResponse("http://restcountries.eu/rest/v2/all"));
	}

}
