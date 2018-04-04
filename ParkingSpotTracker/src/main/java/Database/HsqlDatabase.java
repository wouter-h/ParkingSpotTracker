package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class HsqlDatabase {
	public static Connection conn;

	// Connecting to database =>
	// Executing Query
	public HsqlDatabase() {
		try {
			loadJdbcDriverForHsqlDb();
			setupConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void setupConnection() throws SQLException {
		conn = DriverManager.getConnection("jdbc:hsqldb:db_file", "sa", "");
	}

	private void loadJdbcDriverForHsqlDb() throws ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
	}

	private void shutdownHsqlDatabase() throws SQLException {
		Statement st = conn.createStatement();
		st.execute("SHUTDOWN");
	}

	public void closeConnection() throws SQLException {
		shutdownHsqlDatabase();
		conn.close(); // if there are no other open connection
	}
	
	public void createTableParkingSpot() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE PARKINGSPOTS ("
	        		+ "id INT NOT NULL, x INT NOT NULL, y INT NOT NULL,"
	        		+ "freeSpots INT NOT NULL, totalSpots INT NOT NULL,"
	        		+ "PRIMARY KEY (id));"
	        		+ "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTable(String tableName) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("DROP TABLE " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
