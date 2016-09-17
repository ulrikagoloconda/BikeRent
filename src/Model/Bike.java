package Model;

import java.io.ByteArrayInputStream;
import java.time.Year;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class Bike {
    private int bikeID;
    private String brandName;
    private Year modelYear;
    private String color;
    private String imagePath;
    private int size;
    private String type;
    private boolean available;
    private ByteArrayInputStream imageStream;

    public Bike(){}

    public Bike( String brandName, Year modelYear, String color, int size, String type){
        this.brandName = brandName;
        this.modelYear = modelYear;
        this.color=color;
        this.size = size;
        this.type = type;
    }

    public Bike( String brandName, Year modelYear, String color, int size, String type, int bikeID, String imagePath){

        this.brandName = brandName;
        this.modelYear = modelYear;
        this.color=color;
        this.size = size;
        this.type = type;
        this.bikeID=bikeID;
        this.imagePath=imagePath;

    }
    public int getBikeID() {
        return bikeID;
    }

    public void setBikeID(int bikeID) {
        this.bikeID = bikeID;
    }

    public String getBrandID() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Year getModelYear() {
        return modelYear;
    }

    public void setModelYear(Year modelYear) {
        this.modelYear = modelYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBrandName() {
        return brandName;
    }

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }
}
