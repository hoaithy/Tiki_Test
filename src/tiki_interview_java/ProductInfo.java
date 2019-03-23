/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki_interview_java;

import java.util.Date;

/**
 *
 * @author User
 */
public class ProductInfo {
    private String color;
    private int storage;
    private Date factoryDate;
    private String description;
    private float weight;
    private String[] imageUrl;
    private CommonUtil com;

    public ProductInfo(String color, int storage, Date factoryDate, String description, float weight, String[] imageUrl) {
        this.color = color;
        this.storage = storage;
        this.factoryDate = factoryDate;
        this.description = description;
        this.weight = weight;
        this.imageUrl = imageUrl;
        com = new CommonUtil();
    }
    
    public ProductInfo() {
        com = new CommonUtil();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl){
        this.imageUrl = imageUrl;
    }
    //get the image with the index and avoid OutOfIndexException occur
    public String getSafeUrlImage(int index){
        if (index > imageUrl.length - 1|| index < 0) {
            return "";
        } else {
            return imageUrl[index];
        }
    }
    //get productInfo with horizontal display
    public String toString(int index) {
        return String.format("%-7d%-15s%-15s%-15s%-15s%-15s\nImage 1: %s\nImage 2: %s\nImage 3: %s",index, 
                this.color, this.storage + "GB",this.weight +"g",com.convertDateToString(this.factoryDate),this.description,
                this.getSafeUrlImage(0),this.getSafeUrlImage(1),this.getSafeUrlImage(2));
    }
}
