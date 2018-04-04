package ParkingSpotServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ParkingSpotConnectionHandler extends Thread {
	
	private Socket client;
	private String input;
	private BufferedReader in;
	private InputHandler h;
	
	public ParkingSpotConnectionHandler(Socket incoming, InputHandler h) {
		this.client = incoming;
		this.h = h;
	}
	
	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(
			        client.getInputStream()));
			handle(in.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handle(String input) {
		while(true) {
			if(!h.isMessageQueueLocked()) {
				h.messageQueue.add(input);
				h.releaseMessageQueueLocked();
				break;
			}
		}
	}
}
