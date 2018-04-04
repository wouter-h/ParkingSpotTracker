package ParkingSpotServer;

public class Query {
	
	public String deleteQuery(String table_name, int id)
	{
		return "DELETE FROM ".concat(table_name).concat(" WHERE ID =").concat(String.valueOf(id));
	}
	
	public String insertQuery(String table_name,int id,int x,int y, int f,int t)
	{
		return "INSERT INTO".concat(table_name).concat("(ID, X, Y, FREESPOTS, TOTALSPOTS) VALUES(").concat(String.valueOf(id)).concat(",")
					.concat(String.valueOf(x)).concat(",").concat(String.valueOf(y)).concat(",")
						.concat(String.valueOf(f)).concat(",").concat(String.valueOf(t)).concat("");
	}
	
	public String updateFreeSpotQuery(String table_name,int freespot, int id)
	{
		return "UPDATE ".concat(table_name).concat(" SET FREESPOTS = ").concat(String.valueOf(freespot))
				.concat(" WHERE ID = ").concat(String.valueOf(id));
	}

}
