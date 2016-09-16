package Interfaces;

import Model.Bike;
import Model.BikeType;
import Model.BikeUser;
import Model.Mounth;

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
public interface DBAccess {
    int insertNewBike(ByteArrayInputStream imageStream, int brandID, Year modelYear, String color, int size, BikeType type);
    ArrayList<Bike> selectAvailableBikes();
    ArrayList<BikeUser> searchUserByWildcard(String search);
    ArrayList<Bike> selectBikeByTypeBrandColor(String brand, String color, BikeType type);
    BikeUser alterUser(String userName, String passw, BikeUser updatedUser);
    ArrayList<String> getAllUserNames();
    boolean deleteBike(int bikeID);
    int averageLoanPerUser();
    Map<Mounth,Integer> numerOfLoanPerMounnth();
    BikeUser logIn(String userName, String passW) throws SQLException;




}
