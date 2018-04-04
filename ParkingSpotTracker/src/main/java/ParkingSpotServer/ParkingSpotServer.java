package ParkingSpotServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ParkingSpotServer extends Thread{
	String input;
	BufferedReader in;
	ServerSocket socket;
	InputHandler h;
	
	public ParkingSpotServer(ServerSocket s)
	{
		this.socket = s;
		h = new InputHandler();
		h.start();
		
	}
	
	public void run()
	{
		while(true)
		{
			//Listen
			try {
				Socket client = socket.accept();
				new ParkingSpotConnectionHandler(client, h).start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
