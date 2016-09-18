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




}
