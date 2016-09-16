package Model;

import TabelsRS.UserLoginTable;
import sample.DBType;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class AccessBike {
    public static int insertNewBike(ByteArrayInputStream imageStream, int brandID, Year modelYear, String color, int size, BikeType type) {
        Bike newBike = new Bike();
        try {
            String sql = "INSERT into bike (brandid, modelyear, color, image, size, typeEnum) VALUES (?,?,?,?,?,?)";
        } catch (Exception e) {
            e.printStackTrace();
        }
return 0;
    }


  public static boolean loginUser(String userName, String passW) throws SQLException {
    String SQLQueryLogInStage1 = "SELECT * FROM bikeuser WHERE userName = ? ";//AND passw = ?";
    ResultSet rs = null;
    DBType dataBase = null;
    if(helpers.PCRelated.isThisNiklasPC()){
      dataBase = DBType.Niklas;
    }else{
      dataBase = DBType.Ulrika;
    }

    try ( //only in java 7 and later!!
          Connection conn = DBUtil.getConnection(dataBase); //database_user type like ENUM and get PW :-);
          PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(SQLQueryLogInStage1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    ){
      stmt.setString(1, userName);
      rs = stmt.executeQuery(); //the statement is already there because of the prepareStatement..
      System.out.println( "result is : " );
      UserLoginTable.displayData(rs);
    } catch (SQLException e) {

      DBUtil.processException(e);
    }finally {
      if(rs != null ) rs.close();
    }
    return true;
  }
}
