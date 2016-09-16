package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "1234";
	private static final String CONN_STRING_Ulrika =
			"jdbc:mysql://localhost/bikeRentDb";
	private static final String CONN_STRING_GAME =
			"jdbc:mysql://localhost/bikeRentDb";
	
	public static Connection getConnection_Ulrika(DBType dbType) throws SQLException {
		switch (dbType) {
		case MYSQL:
			//return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

		default:
			return null;
		}
	}
	
	public static Connection getConnection_Niklas(DBType dbType) throws SQLException {
		switch (dbType) {
		case MYSQL:
			return DriverManager.getConnection(CONN_STRING_GAME, USERNAME, PASSWORD);		

		default:
			return null;
		}
	}
	
	public static void processException(SQLException e) { //catch SQL fault :-)
		System.err.println("\n-------------------------------------------------------------------------------");
		System.err.println("error message: " +e.getMessage());
		System.err.println("error codee: " +e.getErrorCode());
		System.err.println("SQL state: " +e.getSQLState());
		System.err.println("-------------------------------------------------------------------------------\n");
		
	}
}
