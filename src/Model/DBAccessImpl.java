package Model;

import Interfaces.DBAccess;
import Interfaces.DeleteUser;
import Interfaces.InsertNewUser;

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
    public BikeUser logIn(String userName, String passW) {
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
