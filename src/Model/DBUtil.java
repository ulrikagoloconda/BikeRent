package Model;
/**
 * @author Niklas Karlsson
 * @version 1.0
 * @since 2016-09-15
 */
import sample.DBType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
  private static final String USERNAME_Ulrika = "dbuser";
  private static final String PASSWORD_Ulrika = "1234";
  private static final String CONN_STRING_Ulrika =
      "jdbc:mysql://localhost/bikerent";

  private static final String USERNAME_Niklas = "dbuser";
  private static final String PASSWORD_Niklas = "1234";
  private static final String CONN_STRING_Niklas =
      "jdbc:mysql://localhost/bikerent";

  public static Connection getConnection(DBType dbType) throws SQLException {
    System.out.println("in model/dbutil");
    switch (dbType) {
      case Ulrika:
        System.out.println("Ulrikas inloggning");
        return DriverManager.getConnection(CONN_STRING_Ulrika, USERNAME_Ulrika, PASSWORD_Ulrika);
			/* return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD); */

      case Niklas:
        System.out.println("Niklas inloggning");
        return DriverManager.getConnection(CONN_STRING_Niklas, USERNAME_Niklas, PASSWORD_Niklas);
      default:
        System.out.println("No database user...");
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
}
