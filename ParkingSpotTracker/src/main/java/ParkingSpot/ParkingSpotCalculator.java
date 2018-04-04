package ParkingSpot;

import java.util.*;

import User.UserRequest;

public class ParkingSpotCalculator {
	
	public static ParkingSpot[] findParkingSpots(UserRequest r, ParkingSpot[] spots) {
		List<ParkingSpot> l = new ArrayList<ParkingSpot>();
		for(ParkingSpot s : spots) {
			int xDiff = (int) (r.getP().getX() - s.getLoc().getX());
			int yDiff = (int) (r.getP().getY() - s.getLoc().getY());
			if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) < r.getThreshold()){
				l.add(s);
			}
		}
		return l.toArray(new ParkingSpot[l.size()]);
	}
}
