package ParkingSpot;

import java.awt.Point;

public class ParkingSpot {
	private int id;
	private Point loc;
	private int freeSpots;
	private int totalSpots;
	
	public ParkingSpot(int id, Point loc, int freeSpots, int totalSpots) {
		this.id = id;
		this.loc = loc;
		this.freeSpots = freeSpots;
		this.totalSpots = totalSpots;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Point getLoc() {
		return loc;
	}
	public void createLoc(int x, int y) {
		this.loc = new Point(x, y);
	}
	public int getFreeSpots() {
		return freeSpots;
	}
	public void setFreeSpots(int freeSpots) {
		this.freeSpots = freeSpots;
	}
	public int getTotalSpots() {
		return totalSpots;
	}
	public void setTotalSpots(int totalSpots) {
		this.totalSpots = totalSpots;
	}
	
	public String toString() {
		return "ParkingSpot(id: " + id + "Point(" + loc.getX() + ", " + loc.getY() + "), freeSpots: " + freeSpots + ", totalSpots: " + totalSpots + ")";
	}
}
