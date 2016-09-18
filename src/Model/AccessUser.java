package Model;

import sample.DBType;

import java.sql.*;
import java.time.LocalDate;

/**
 * Created by NIK1114 on 2016-09-18.
 */
public class AccessUser {


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

      //if ( isLoginInfoOK(userName, passW, dataBase) ){
      BikeUser logedInBikeUser = new BikeUser();
      logedInBikeUser = getUserinfo(userName, dataBase);
      System.out.println("accessBike in loginuser");
      System.out.println("logedInBikeUser: " + logedInBikeUser.getEmail());
      return logedInBikeUser;
      //}else{
      //  return null;
      //}

    }
    public static boolean isUserAvalible (String userName)throws SQLException {
      DBType dataBase = null;
      if(helpers.PCRelated.isThisNiklasPC()){
        dataBase = DBType.Niklas;
      }else{
        dataBase = DBType.Ulrika;
      }
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
        if (!rs.next()){
          System.out.println("ledig användare");
          return true;
        }
        else{
          System.out.println("UPPTAGEN användare");
          return false;
        }
      }finally {
        if(rs != null ) rs.close();
      }

  }

  private static BikeUser getUserinfo(String userName, DBType dataBase) throws SQLException {
    BikeUser logedInBikeUser = new BikeUser();
 /* CREATE OR REPLACE PROCEDURE .....

DROP PROCEDURE getUserFromUserName();

DELIMITER //
    CREATE PROCEDURE getUserFromUserName(in_username varchar(50))
    BEGIN
    SELECT * FROM bikeuser
    WHERE username = in_username;
END //
DELIMITER ;

CALL getUserFromUserName('1');
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
      rs.next();
      System.out.print("after rs.next() getUserFromUserName: ");
      System.out.println(rs.getRow());
      logedInBikeUser.setUserID(rs.getInt("userID"));
      logedInBikeUser.setfName(rs.getString("fname"));
      logedInBikeUser.setlName(rs.getString("lname"));
      logedInBikeUser.setEmail(rs.getString("email"));
      logedInBikeUser.setPhone(rs.getInt("phone"));
      logedInBikeUser.setMemberLevel(rs.getInt("memberlevel"));
      logedInBikeUser.setMemberSince(rs.getDate("membersince").toLocalDate());
      //logedInBikeUser.setUserName(rs.getString("username"));
      System.out.print("what do we get from getUserFromUserName: ");
      System.out.println(logedInBikeUser.getEmail());
   // } catch (SQLException e) {
   //   DBUtil.processException(e);
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
    //} catch (SQLException e) {
    //  DBUtil.processException(e);
    //  return false;
    }finally {
      if(rs != null ) rs.close();
    }
    return isLoginOK;
  }

  public boolean InsertNewUser(String fname, String lname, int memberlevel, String email, int phone, String username, String passw, LocalDate membersince) throws SQLException  {
    // insert_new_user(in_fname varchar(50),in_lname varchar(50),in_memberlevel varchar(50),in_email varchar(50),in_phone varchar(50),in_username varchar(50), in_passw varchar(50)) RETURNS smallint(6)
     String SQLInsertUser = "{? = call insert_new_user(?, ?, ?, ?, ?, ?, ?, ?)}";
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
        //} catch (SQLException e) {
        //  DBUtil.processException(e);
        //  return false;
      }finally {
        if(rs != null ) rs.close();
      }
      return isLoginOK;
    }
}
