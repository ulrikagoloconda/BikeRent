package Model;

import sample.DBType;

import java.io.ByteArrayInputStream;
import java.sql.*;
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


  public static BikeUser loginUser(String userName, String passW) throws SQLException {
//  userID fname lname memberlevel email phone username passw memberSince
   /*
DROP FUNCTION IF EXISTS check_password;
DELIMITER //
CREATE FUNCTION check_password(tryusername varchar(50), trypassword varchar(50)) RETURNS smallint(6)
    BEGIN
    DECLARE pw VARBINARY(56);
    SET pw = (SELECT passw FROM bikeuser WHERE userName=tryusername);
    if exists(SELECT * from bikeuser WHERE userName= tryusername AND pw=AES_ENCRYPT(trypassword,'tackforkaffet'))
    THEN
    RETURN 1;
    ELSE
    RETURN 0;
    END IF;
    END//
DELIMITER ;
    */
//'Niklas', 'Karlsson', 0, 'cykeltur@gmail.com', 0703032191 , 'cykeltur' , AES_ENCRYPT('1234','tackforkaffet') , CURDATE());

    //String SQLQueryLogInStage1 = "SELECT * FROM bikeuser WHERE userName = ? AND passw = ?";

    DBType dataBase = null;
    if(helpers.PCRelated.isThisNiklasPC()){
      dataBase = DBType.Niklas;
    }else{
      dataBase = DBType.Ulrika;
    }
   // boolean isLoginOK = isLoginInfoOK(userName, passW, dataBase);
    if ( isLoginInfoOK(userName, passW, dataBase) ){
      BikeUser logedInBikeUser = null;
      logedInBikeUser = getUserinfo(userName, dataBase);
      return logedInBikeUser;
    }else{
      return null;
    }

  }

  private static BikeUser getUserinfo(String userName, DBType dataBase) throws SQLException {
    BikeUser logedInBikeUser = null;
 /* CREATE OR REPLACE PROCEDURE .....

DELIMITER //
    CREATE PROCEDURE getUserFromUserName(in_username varchar(50))
    BEGIN
    SELECT * from  bikeuser
    WHERE username = in_username;
END //
DELIMITER ;
    */
    String SQLQuerygetUserinfo = "{call getUserFromUserName(?)}";
    ResultSet rs = null;
    try ( //only in java 7 and later!!
          Connection conn = DBUtil.getConnection(dataBase); //database_user type like ENUM and get PW :-);
          CallableStatement stmt = conn.prepareCall (SQLQuerygetUserinfo);
    ){
      //stmt.registerOutParameter (1, Types.VARCHAR);
      stmt.setString(1,userName);
      //stmt.execute();
      rs = stmt.executeQuery();
      logedInBikeUser.setUserID(rs.getInt("userID"));
      logedInBikeUser.setfName(rs.getString("fname"));
      logedInBikeUser.setlName(rs.getString("lname"));
      logedInBikeUser.setEmail(rs.getString("email"));
      logedInBikeUser.setPhone(rs.getInt("phone"));
      logedInBikeUser.setMemberLevel(rs.getInt("setmemberlevel"));
      logedInBikeUser.setMemberSince(rs.getDate("membersince").toLocalDate());
      //logedInBikeUser.setUserName(rs.getString("username"));
      System.out.print("what do we get from getUserFromUserName: ");
      System.out.println(logedInBikeUser.getEmail());
    } catch (SQLException e) {
      DBUtil.processException(e);
    }finally {
      if(rs != null ) rs.close();
    }
    return logedInBikeUser;
  }


  private static boolean isLoginInfoOK(String userName, String passW, DBType dataBase)throws SQLException  {
    boolean isLoginOK;
    String SQLQueryLogInStage = "{? = call check_password(?, ?)}";
    ResultSet rs = null;
    try ( //only in java 7 and later!!
          Connection conn = DBUtil.getConnection(dataBase); //database_user type like ENUM and get PW :-);
          CallableStatement stmt = conn.prepareCall (SQLQueryLogInStage);
    ){
      stmt.registerOutParameter (1, Types.BOOLEAN);
      stmt.setString(2,userName);
      stmt.setString(3,passW);
      stmt.execute();
      System.out.print("what do we get from check_password T/F: ");
      isLoginOK =stmt.getBoolean (1); //1 or 0....
      System.out.println(isLoginOK);
    } catch (SQLException e) {
      DBUtil.processException(e);
      return false;
    }finally {
      if(rs != null ) rs.close();
    }
    return isLoginOK;
  }


}
