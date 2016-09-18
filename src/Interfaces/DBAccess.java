package Interfaces;

import Model.Bike;
import Model.BikeType;
import Model.BikeUser;
import Model.Mounth;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public interface DBAccess {
    int insertNewBike(Bike newBike);
    ArrayList<Bike> selectAvailableBikes();
    ArrayList<BikeUser> searchUserByWildcard(String search);
    ArrayList<Bike> selectBikeByTypeBrandColor(String brand, String color, BikeType type);
    BikeUser alterUser(String userName, String passw, BikeUser updatedUser);
    ArrayList<String> getAllUserNames();
    boolean deleteBike(int bikeID);
    int averageLoanPerUser();
    Map<Mounth,Integer> numerOfLoanPerMounnth();
    BikeUser logIn(String userName, String passW);




}
