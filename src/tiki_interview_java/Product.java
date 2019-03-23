/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki_interview_java;

/**
 *
 * @author User
 */
public class Product {
    private String productName;
    private double salePrice;
    private double marketPrice;
    private ProductInfo[] productInfo;

    public Product() {
    }

    public Product(String productName, double salePrice, double marketPrice, ProductInfo[] productInfo) {
        this.productName = productName;
        this.salePrice = salePrice;
        this.marketPrice = marketPrice;
        this.productInfo = productInfo;
    }

    public Product(String productName, double salePrice, double marketPrice) {
        this.productName = productName;
        this.salePrice = salePrice;
        this.marketPrice = marketPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public ProductInfo[] getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductInfo[] productInfo) {
        this.productInfo = productInfo;
    }
    
    public String toString(int index) {
        return String.format("%-7d%-30s$%-19.02f$%-20.02f",index,this.productName,this.salePrice,this.marketPrice);
    }
    
    public String toStringDetail() {
        return String.format("Product Name: %-5s\nSale Price: $%-5.02f\nMarket Price: $%-5.02f\n", this.productName,this.salePrice, this.marketPrice);
    }
}
