package Interfaces;

import Model.Bike;
import Model.BikeType;

import java.io.ByteArrayInputStream;
import java.time.Year;
import java.util.ArrayList;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public interface DBAccess {
    int insertNewBike(ByteArrayInputStream imageStream, int brandID, Year modelYear, String color, int size, BikeType type);
    ArrayList<Bike> selectAvailableBikes();

}
