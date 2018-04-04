package User;

import java.awt.Point;

public class UserRequest {
	private Point p;
	private int threshold;  //in meters
	
	public UserRequest(int x, int y, int threshold) {
		this.p = new Point(x, y);
		this.threshold = threshold;
	}
	
	public Point getP() {
		return p;
	}
	
	public void createP(int x, int y) {
		p = new Point(x, y);
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public void setThreshold(int threshhold) {
		this.threshold = threshhold;
	}	
}
