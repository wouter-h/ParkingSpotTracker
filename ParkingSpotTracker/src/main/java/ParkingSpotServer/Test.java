package ParkingSpotServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Database.ParkingSpotService;
import hello.BridgeToDatabase;

public class Test {

	static Socket socket;
	static PrintWriter out;
	public static void main(String[] args) {
		
		ParkingServers s = new ParkingServers();
		
		for(int i=0;i<250;i++)
		{
			try {
				System.out.println("Sending an update");
				socket = new Socket("127.0.0.1", 8000);
				out = new PrintWriter(socket.getOutputStream(), 
		                 true);
				out.write("ID=2,f=15");
				out.flush();
				out.close();
				socket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
