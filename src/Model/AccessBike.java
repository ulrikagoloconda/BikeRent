package Model;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

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

    public static ArrayList<Bike> selectAvailableBikes() {
        ArrayList<Bike> availableBikes = new ArrayList<>();
        try {
            DBUtil.tempConnect();
            DBUtil.getConnection().setAutoCommit(false);
            String sql = "SELECT bike.bikeID,bike.modelyear, bike.color, bike.image, bike.size, type.typeName, brand.brandname FROM bike" +
                    "  LEFT OUTER JOIN rentbridge" +
                    "    ON bike.bikeID = rentbridge.bikeID" +
                    "  JOIN brand ON bike.brandid = brand.brandid" +
                    "  JOIN type ON bike.typeID = type.typeID" +
                    "WHERE dayOfReturn <> null" +
                    "      OR dayOfActualReturn <> null";
            PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
           ResultSet rs =  ps.executeQuery();
            int i = 0;
            while (rs.next()){
                i++;
                Bike b = new Bike();
                b.setAvailable(true);
                b.setBikeID(rs.getInt("bikeID"));
                b.setColor(rs.getString("color"));
                b.setSize(rs.getInt("size"));
                LocalDate tempDate = rs.getDate("modelyear").toLocalDate();
                b.setModelYear(Year.of(tempDate.getYear()));
                Blob blob = rs.getBlob("file");
                InputStream in = blob.getBinaryStream();
                String paths = "BikeRent\\src\\Image\\tempImageDir"+i+".jpg";
                OutputStream out = new FileOutputStream(paths);
                b.setImagePath(paths);
                b.setType(rs.getString("typeName"));
                b.setBrandName(rs.getString("brandname"));
                availableBikes.add(b);
            }
            DBUtil.getConnection().commit();

        }catch (Exception e){
            try {
                DBUtil.getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return availableBikes;
    }
}
