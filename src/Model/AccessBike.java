package Model;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class AccessBike {
    public static int insertNewBike(Bike newBike) {
        DBUtil.tempConnect();
        try {
            String sql = "CALL insert_bike(?,?,?,?,?,?,?)";
            CallableStatement cs = DBUtil.getConnection().prepareCall(sql);
            cs.setString(1,newBike.getBrandName());
            cs.setString(2, newBike.getType());
            cs.setInt(3, newBike.getModelYear());
            cs.setString(4, newBike.getColor());
            cs.setInt(5, newBike.getSize());
            byte[] array = new byte[newBike.getImageStream().available()];
            Blob blob = new javax.sql.rowset.serial.SerialBlob(array);
            cs.setBlob(6, blob);
            cs.registerOutParameter(7, Types.INTEGER);

          cs.executeQuery();
            newBike.setBikeID(cs.getInt(7));


        } catch (Exception e) {
            e.printStackTrace();
        }

        return newBike.getBikeID();
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
                b.setModelYear(rs.getInt("modelyear"));
                Blob blob = rs.getBlob("image");
                InputStream in = blob.getBinaryStream();
                String paths = "C:\\Users\\Rickard\\IdeaProjects\\github\\BikeRent\\src\\Image\\tempImageDir\\image"+i+".png";
                OutputStream out = new FileOutputStream(paths);
                b.setImagePath(paths);
                byte[] buff = blob.getBytes(1,(int)blob.length());
                out.write(buff);
                b.setType(rs.getString("typeName"));
                b.setBrandName(rs.getString("brandname"));
                b.setImageFileName(rs.getString("imageFileName"));
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
