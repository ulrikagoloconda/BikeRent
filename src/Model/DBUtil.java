package Model;
/**
 * @author Niklas Karlsson
 * @version 1.0
 * @since 2016-09-15
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "1234";
	private static final String CONN_STRING_Ulrika =
			"jdbc:mysql://localhost/bikerentDB";
	private static final String CONN_STRING_Niklas =
			"jdbc:mysql://localhost/bikerentDB";
	private static Connection tempCon;
	public static Connection getConnection_Ulrika(BikeType dbType) throws SQLException {
		switch (dbType) {
		//case MYSQL:

			//return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);


		default:
			return null;
		}
	}
	
	public static Connection getConnection_Niklas(BikeType dbType) throws SQLException {
		switch (dbType) {
		//case MYSQL:
			//return DriverManager.getConnection(CONN_STRING_Niklas, USERNAME, PASSWORD);

		default:
			return null;
		}
	}
	/*
	To use example:
	try {
		   DriverManager.getConnection(CONN_STRING_GAME, USERNAME, PASSWORD);
		   } catch (SQLException e) {
			   processException(e);
		   }
	 */
	public static void processException(SQLException e) { //catch SQL fault :-)
		System.err.println("\n-------------------------------------------------------------------------------");
		System.err.println("error message: " +e.getMessage());
		System.err.println("error codee: " +e.getErrorCode());
		System.err.println("SQL state: " +e.getSQLState());
		System.err.println("-------------------------------------------------------------------------------\n");
		
	}

	public static void tempConnect(){
		try {
			tempCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/dresslibrary", "root", "Forfattare1");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection(){
		return tempCon;
	}

	public static void disConnect(){
		try {
			tempCon.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
