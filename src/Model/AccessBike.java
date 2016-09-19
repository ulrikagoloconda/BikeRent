package Model;

import sample.DBType;

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
        DBType dataBase = null;
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }

        try {
            Connection conn = DBUtil.getConnection(dataBase);
            String sql = "CALL insert_bike(?,?,?,?,?,?,?)";
            CallableStatement cs = conn.prepareCall(sql);
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
        DBType dataBase = null;
        Connection conn = null;
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }

        try {
            conn = DBUtil.getConnection(dataBase);
           conn.setAutoCommit(false);
            String sql = "CALL search_available_bikes()";
            PreparedStatement ps = conn.prepareStatement(sql);
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
            conn.commit();

        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return availableBikes;
    }


    public static ArrayList<Bike> getAllBikes() {
        ArrayList<Bike> allBikes =  new ArrayList<>();
        DBType dataBase = null;
        Connection conn = null;
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }
        try{
            conn = DBUtil.getConnection(dataBase);
            String sql = "CALL get_all_bikes()";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Bike b = new Bike();
                b.setBikeID(rs.getInt("bikeID"));
                b.setColor(rs.getString("color"));
                b.setSize(rs.getInt("size"));
                b.setModelYear(rs.getInt("modelyear"));
                b.setType(rs.getString("typeName"));
                b.setBrandName(rs.getString("brandname"));
                allBikes.add(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allBikes;
    }

    public static boolean deleteBike(int bikeID) {
        DBType dataBase = null;
        Connection conn = null;
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }
        try {
            conn = DBUtil.getConnection(dataBase);
            conn.setAutoCommit(false);
            String sql = "CALL delete_bike(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bikeID);
            ps.executeQuery();
            conn.commit();
            return true;
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }
}
