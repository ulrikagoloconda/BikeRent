package Model;

import sample.DBType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            cs.setString(2,newBike.getType());
            cs.setInt(3, newBike.getModelYear());
            cs.setString(4, newBike.getColor());
            cs.setInt(5, newBike.getSize());
            ByteArrayInputStream bais = newBike.getImageStream();
            cs.setBinaryStream(6, bais);
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
                byte [] bytes = blob.getBytes(1, (int) blob.length());
                BufferedImage theImage= ImageIO.read(new ByteArrayInputStream(bytes));
                b.setBufferedImage(theImage);
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

    public static String executeBikeLoan(int bikeID, int userID) {
        DBType dataBase = null;
        Connection conn = null;
        Date dayOfReturn = null;
        String returnSring = "";
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }
        try{
            conn = DBUtil.getConnection(dataBase);
            conn.setAutoCommit(false);
            String sql = "CALL execute_bike_loan(?,?,?, ?)";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1,userID);
            cs.setInt(2,bikeID);
            Date date = Date.valueOf(LocalDate.now());
            cs.setDate(3,date);
            cs.registerOutParameter(4,Types.DATE);
            cs.executeQuery();
            dayOfReturn = cs.getDate(4);
            conn.commit();
            returnSring = "Lånet har genomförts. Återlämningsdatum: " + dayOfReturn.toString();
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            returnSring = "Lånet kunde inte genomföras";
        }
        return returnSring;
    }

    public static Map<String, Integer> getSearchValue(String text) {
        Map<String, Integer> mapToReturn = new HashMap<>();
        DBType dataBase = null;
        Connection conn = null;
        Date dayOfReturn = null;
        String returnSring = "";
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }
        try {
            conn = DBUtil.getConnection(dataBase);
            String sql = "CALL search_by_string(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,text);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int i = rs.getInt("bikeID");

                String s1 = rs.getString("color");
                String s2 = rs.getString("brandname");
                String s3 = rs.getString("typeName");
                String s4 = s2 + " " + s1 + " " + s3;
                mapToReturn.put(s4,i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return mapToReturn;

    }

    public static Bike getBikeByID(int bikeID) {
        Bike b = new Bike();

        DBType dataBase = null;
        Connection conn = null;
        Date dayOfReturn = null;
        String returnSring = "";
        if(helpers.PCRelated.isThisNiklasPC()){
            dataBase = DBType.Niklas;
        }else{
            dataBase = DBType.Ulrika;
        }
        try {
            conn = DBUtil.getConnection(dataBase);
            String sql = "Call get_bike_returnedDate_from_ID(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,bikeID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                if(rs.getDate("dayOfActualReturn")== null && rs.getDate("dayOfRent") != null){
                    b.setAvailable(false);
                } else {
                    b.setAvailable(true);
                }
                b.setBrandName(rs.getString("brandname"));

                Blob blob = rs.getBlob("image");
                byte [] bytes = blob.getBytes(1, (int) blob.length());
                BufferedImage theImage= ImageIO.read(new ByteArrayInputStream(bytes));
                b.setBufferedImage(theImage);
                b.setColor(rs.getString("color"));
                b.setType(rs.getString("typeName"));
                b.setModelYear(rs.getInt("modelyear"));
                b.setBikeID(bikeID);
                b.setSize(rs.getInt("size"));
            }

      }catch (Exception e){
            e.printStackTrace();
        }
        return b;
        }

}
