package Model;

import Interfaces.DBAccess;
import Interfaces.DeleteUser;
import Interfaces.InsertNewUser;

import java.io.ByteArrayInputStream;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class DBAccessImpl implements DeleteUser, InsertNewUser, DBAccess{
    @Override
    public int insertNewBike(ByteArrayInputStream imageStream, int brandID, Year modelYear, String color, int size, BikeType type) {
        return 0;
    }

    @Override
    public ArrayList<Bike> selectAvailableBikes() {
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
