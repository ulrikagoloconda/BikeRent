package Model;

import java.time.Year;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class Bike {
    private int bikeID;
    private int brandID;
    private Year modelYear;
    private String color;
    private String imagePath;
    private int size;
    private BikeType type;
    private boolean available;

    public Bike(){}

    public Bike( int brandID, Year modelYear, String color, int size, BikeType type){
        this.brandID = brandID;
        this.modelYear = modelYear;
        this.color=color;
        this.size = size;
        this.type = type;
    }

    public Bike( int brandID, Year modelYear, String color, int size, BikeType type, int bikeID, String imagePath){

        this.brandID = brandID;
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

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
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

    public BikeType getType() {
        return type;
    }

    public void setType(BikeType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}