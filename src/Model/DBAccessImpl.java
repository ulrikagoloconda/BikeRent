package Model;

import Interfaces.DBAccess;
import Interfaces.DeleteUser;
import Interfaces.InsertNewUser;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class DBAccessImpl implements DeleteUser, InsertNewUser, DBAccess {
    @Override
    public int insertNewBike(Bike newBike) {
        return AccessBike.insertNewBike(newBike);
    }

    @Override
    public ArrayList<Bike> selectAvailableBikes() {
       return AccessBike.selectAvailableBikes();
    }

    @Override
    public ArrayList<BikeUser> searchUserByWildcard(String search) {
        return null;
    }

    @Override
    public ArrayList<Bike> selectBikeByTypeBrandColor(String brand, String color, BikeType type) {
        return null;
    }

    @Override
    public BikeUser alterUser(String userName, String passw, BikeUser updatedUser) {
        return null;
    }

    @Override
    public ArrayList<String> getAllUserNames() {
        return null;
    }

    @Override
    public boolean deleteBike(int bikeID) {
        return false;
    }

    @Override
    public int averageLoanPerUser() {
        return 0;
    }

    @Override
    public Map<Mounth, Integer> numerOfLoanPerMounnth() {
        return null;
    }

    @Override
    public BikeUser logIn(String userName, String passW) throws SQLException  {
      BikeUser LoginUser = null;
        System.out.println("in the 'try' bikeuser login");
        LoginUser = AccessUser.loginUser(userName, passW);
      return LoginUser;
    }

    @Override
    public boolean DeleteUser(String fname, String lname, int memberlevel, String email, int phone, String username, String passw) {
        return false;
    }

  @Override
  public boolean isUserAvalible(String userName) throws SQLException{
    return AccessUser.isUserAvalible(userName);
  }

  @Override
  public boolean InsertNewUser(String fname, String lname, int memberlevel, String email, int phone, String username, String passw)  {
        System.out.println("in the add user");
        return AccessUser.InsertNewUser(fname, lname, memberlevel, email, phone, username, passw);
      }
}
