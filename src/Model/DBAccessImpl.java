package Model;

import Interfaces.DBAccess;
import Interfaces.DeleteUser;
import Interfaces.InsertNewUser;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class DBAccessImpl implements DeleteUser, InsertNewUser, DBAccess{
    @Override
    public int insertNewBike(ByteArrayInputStream imageStream, int brandID, Year modelYear, String color, int size, BikeType type) {
        return AccessBike.insertNewBike(imageStream, brandID, modelYear, color, size,  type);
    }

    @Override
    public ArrayList<Bike> selectAvailableBikes() {
        return null;
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
    public BikeUser logIn(String userName, String passW)  {
      try {
        System.out.println("in the bikeuser login");
        AccessBike.loginUser(userName,passW);
      } catch (SQLException e) {
        DBUtil.processException(e);
      }
      return null;
    }

    @Override
    public boolean DeleteUser(String fname, String lname, int memberlevel, String email, int phone, String username, String passw, Date membersince) {
        return false;
    }

    @Override
    public boolean InsertNewUser(String fname, String lname, int memberlevel, String email, int phone, String username, String passw, Date membersince) {
        return false;
    }
}
