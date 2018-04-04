package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExecuteQuery {
	
	public static boolean locked = false;

	public static void executeQuery(String query) {
		Connection con = new HsqlDatabase().conn;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(query);
			System.out.println("Doing query: " + query);
		} catch (SQLException e) {
			System.out.println("Something went wrong while executing a query: " + query);
			Logger logger = LogManager.getLogger(ParkingSpotService.class);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized boolean isLocked() {
		if(locked) return true;
		else {
			locked = true;
			return false;
		}
	}
	
	public static synchronized void releaseLock() {
		locked = false;
	}
}
