package ParkingSpotServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Database.ExecuteQuery;
import ParkingSpot.ParkingSpot;

public class InputHandler extends Thread{
	private int freespot;
	private int id;
	private String message, query;
	private String[] messageArray;
	public Vector<String> messageQueue;
	private boolean messageQueueLocked;
	
	//DataBase info
	private String table_name = "PARKINGSPOTS";
	private String columnName = "FREESPOTS";
	private String columnNameID = "ID";
	
	public InputHandler()
	{
		this.messageQueue = new Vector<>();
		this.messageQueueLocked = false;
	}
	
	public void run()
	{
		while(true)
		{
			//readinput
			while(true) {
				boolean flag = false;
				if(!this.isMessageQueueLocked()) {
					if(!this.messageQueue.isEmpty()) {
						this.message = messageQueue.remove(0);
						flag = true;
					}
					this.releaseMessageQueueLocked();
				}
				if(flag) break;
			}
			
			readInput(this.message);
			constructMessage();
			
			ExecuteQuery eq = new ExecuteQuery();
				
		    while(true) {
		    	if(!eq.isLocked()) {
		    		System.out.println("Executing query: " + query);
		    		eq.executeQuery(query);
		    		eq.releaseLock();
		    		break;
		    	}
		    }
			//connectAndSend();
		}
	}
	
	private void constructMessage() {
		// TODO Auto-generated method stub
		Query q = new Query();
		query = q.updateFreeSpotQuery(table_name, freespot, id);
			
	}
	
	private void readInput(String message) {
		messageArray = message.split(",");
		for(int i = 0;i<2;i++)
		{
			getData(messageArray[i]);
		}
	}
	private void getData(String data)
	{
		switch(data.charAt(0))
		{
			case 'I':
				id = Integer.parseInt(data.substring(3));
				break;
			case 'f':
				freespot = Integer.parseInt(data.substring(2));
				break;
		}
	}
	
	public synchronized boolean isMessageQueueLocked() {
		if(!messageQueueLocked) {
			messageQueueLocked = true;
			return false;
		}
		return true;
	}
	
	public synchronized void releaseMessageQueueLocked() {
		this.messageQueueLocked = false;
	}

}
