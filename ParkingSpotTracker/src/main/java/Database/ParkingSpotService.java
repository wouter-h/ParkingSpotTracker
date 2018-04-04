package Database;

import java.awt.Point;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ParkingSpot.ParkingSpot;

public class ParkingSpotService {

	private static final String INSERT_PARKINGSPOT_QUERY = "INSERT INTO PARKINGSPOTS (ID, X, Y, FREESPOTS, TOTALSPOTS) VALUES(?, ?, ?, ?, ?)";

	private static final String DELETE_PARKINGSPOT_QUERY = "DELETE FROM PARKINGSPOTS WHERE ID=?";

	HsqlDatabase db = new HsqlDatabase();

	public static Logger logger = LogManager.getLogger(ParkingSpotService.class);
	
	public void insertParkingSpots(List<ParkingSpot> parkingSpots) {
		for (ParkingSpot parkingSpot : parkingSpots) {
			insertParkingSpot(parkingSpot);
		}
	}

	private void insertParkingSpot(ParkingSpot parkingSpot) {
		PreparedStatement st = null;
		try {
			st = db.conn.prepareStatement(INSERT_PARKINGSPOT_QUERY);
			st.setInt(1, parkingSpot.getId());
			st.setInt(2, (int) parkingSpot.getLoc().getX());
			st.setInt(3, (int) parkingSpot.getLoc().getY());
			st.setInt(4, parkingSpot.getFreeSpots());
			st.setInt(5, parkingSpot.getTotalSpots());
			st.execute();
		} catch (SQLException e) {
			logger.fatal("Query Failed : " + INSERT_PARKINGSPOT_QUERY, e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// Ignore - nothing we can do..
				}
			}
		}
	}

	public void deleteParkingSpot(int id) {
		PreparedStatement st = null;
		try {
			st = db.conn.prepareStatement(DELETE_PARKINGSPOT_QUERY);
			st.setInt(1, id);
			st.execute();
		} catch (SQLException e) {
			logger.fatal("Query Failed : " + DELETE_PARKINGSPOT_QUERY, e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// Ignore - nothing we can do..
				}
			}
		}
	}

	public List<ParkingSpot> retrieveAllParkingSpots() throws SQLException {
		List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
		Statement st = db.conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM PARKINGSPOTS");
		while (rs.next()) {
			parkingSpots.add(new ParkingSpot(rs.getInt(1), new Point(rs.getInt(2), rs.getInt(3)), rs.getInt(4), rs.getInt(5)));
		}
		st.close();
		if(parkingSpots.size() == 0) return null;
		return parkingSpots;
	}

	public static void main(String[] args) throws SQLException {

		HsqlDatabase d = new HsqlDatabase();
		d.deleteTable("PARKINGSPOTS");
		d.createTableParkingSpot();
		d.closeConnection();
		ParkingSpotService parkingSpotService = new ParkingSpotService();

		List<ParkingSpot> list = new ArrayList<>();
		
		for(int i = 0; i < 10; ++i) {
			list.add(new ParkingSpot(i, new Point(10, 20), 10, 20));
		}
		
		parkingSpotService.insertParkingSpots(list);
		parkingSpotService.deleteParkingSpot(1234);
		List<ParkingSpot> parkingSpots = parkingSpotService.retrieveAllParkingSpots();
		logger.info(parkingSpots);
		parkingSpotService.db.closeConnection();
	}
}
