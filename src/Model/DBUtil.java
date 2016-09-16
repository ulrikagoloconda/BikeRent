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
private  static Connection tempCon;


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
