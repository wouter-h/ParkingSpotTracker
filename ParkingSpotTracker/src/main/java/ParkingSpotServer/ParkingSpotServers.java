package ParkingSpotServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;


public class ParkingSpotServers{
	int sockets = 10;
	int startPort = 8000;
	Vector<ParkingSpotServer> serverSockets;
	ServerSocket server;
	
	public ParkingSpotServers()
	{
		serverSockets = new Vector<>();
		initializeSockets();
		
	}
	
	private void initializeSockets() {
		for(int i=0;i<1;i++) {
			try {
				serverSockets.addElement(new ParkingSpotServer(new ServerSocket(startPort + i)));
				serverSockets.elementAt(i).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
