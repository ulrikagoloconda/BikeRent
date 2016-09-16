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
    private int brandID;
    private Year modelYear;
    private String color;
    private ByteArrayInputStream image;
    private int size;
    private BikeType type;

    public Bike(){}

    public Bike( int brandID, Year modelYear, String color, int size, BikeType type){
        this.brandID = brandID;
        this.modelYear = modelYear;
        this.color=color;
        this.size = size;
        this.type = type;
    }

    public Bike( int brandID, Year modelYear, String color, int size, BikeType type, int bikeID, ByteArrayInputStream image){

        this.brandID = brandID;
        this.modelYear = modelYear;
        this.color=color;
        this.size = size;
        this.type = type;
        this.bikeID=bikeID;
        this.image=image;

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

    public ByteArrayInputStream getImage() {
        return image;
    }

    public void setImage(ByteArrayInputStream image) {
        this.image = image;
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
}
