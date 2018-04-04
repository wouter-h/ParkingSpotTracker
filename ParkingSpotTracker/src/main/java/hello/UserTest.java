package hello;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import User.UserRequest;

public class UserTest {

	private UserRequest testRequest = new UserRequest(1000, 1000, 250);
	
	public static void sendParkingRequest(){
		String urlParameters  = "?x=10&y=20&threshold=4";
    	byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
    	int    postDataLength = postData.length;
    	String request        = "http://localhost:8080/parking" + urlParameters;
    	System.out.println(request);
    	URL url = null;
		try {
			url = new URL( request );
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
    	HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (IOException e1) {
			e1.printStackTrace();
		}           
    	conn.setDoOutput( true );
    	conn.setInstanceFollowRedirects( false );
    	try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
    	System.out.println("Getting here\n");
    	try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
    		wr.write( postData );
    		wr.flush();
    		wr.close();
    	} catch (IOException e) {
    		e.printStackTrace();
		}
    	
    	//Get Response  
        
		try {
			int status = conn.getResponseCode();
			System.out.println("response code: " + status);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        try {
        	while ((line = rd.readLine()) != null) {
				System.out.println("Receiving something");
				response.append(line);
				response.append('\r');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println(response);
	}
	
    public static void main(String[] args) {
    	for(int i = 0; i < 25; ++i)
    		sendParkingRequest();
    }
}
