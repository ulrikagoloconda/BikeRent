package Model;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.*
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
    public static int insertNewBike(Bike newBike) {
        /*
        IN typeIn VARCHAR(30),
    IN modelYearIn YEAR,
    IN colorIn VARCHAR(50),
    IN sizeIn SMALLINT(6),
  IN imageIn BLOB,
  OUT bikeIDOut INT(11)
         */
        Year
        int bikeID = 0;
        DBUtil.tempConnect();
        try {
            String sql = "? = CALL insert_bike(?,?,?,?,?)";

            CallableStatement cs = DBUtil.getConnection().prepareCall(sql);
            cs.setInt(1,bikeID);
            cs.setString(2,newBike.getType());
            int year = newBike.getModelYear().getValue();
            String date = "" + year + "-01-01";
            cs.setDate(3,Date.valueOf(date));
            cs.setString(4, newBike.getColor());
            cs.setInt(5,newBike.getSize());

            /*
               Blob blob = rs.getBlob("file");
            InputStream in = blob.getBinaryStream();
            String paths = "C:\\Users\\Rickard\\IdeaProjects\\DressLibraryFX\\src\\image\\image"+i+".jpg";
            OutputStream out = new FileOutputStream(paths);
             */
            Blob blob = newBike.getImageStream();
            cs.setBlob(newBike.getImageStream());


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
            String sql = "CALL search_available_bikes()";
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
