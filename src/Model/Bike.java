package Model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

/**
 * @author Ulrika Goloconda Fahlén
 * @version 1.0
 * @since 2016-09-16
 */
public class Bike {
    private int bikeID;
    private String brandName;
    private int modelYear;
    private String color;
    private String imagePath;
    private int size;
    private String type;
    private boolean available;
    private ByteArrayInputStream imageStream;
    private BikeUser createdBy;
    private String imageFileName;
    private BufferedImage bufferedImage;
   private FileInputStream fileInputImage;

    public Bike(){}

    public Bike( String brandName, int modelYear, String color, int size, String type){
        this.brandName = brandName;
        this.modelYear = modelYear;
        this.color=color;
        this.size = size;
        this.type = type;
    }

    public Bike( String brandName, int modelYear, String color, int size, String type, int bikeID, String imagePath){

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

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
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

    public BikeUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BikeUser createdBy) {
        this.createdBy = createdBy;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public FileInputStream getFileInputImage() {
        return fileInputImage;
    }

    public void setFileInputImage(FileInputStream fileInputImage) {
        this.fileInputImage = fileInputImage;
    }
}
