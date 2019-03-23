/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki_interview_java;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class ProductCreator {

    private ArrayList<Product> product;

    public int productSize() {
        return product.size();
    }

    public ProductCreator() {
        product = new ArrayList();
    }

    public void addProduct(Product prd) {
        product.add(prd);
    }

    public boolean removeProduct(int index) {
        if (product.isEmpty()) {
            return false;
        }
        product.remove(index - 1);
        return true;
    }

    public boolean removeProduct(String name) {
        if (product.isEmpty()) {
            return false;
        }
        Iterator<Product> iter = product.iterator();
        while(iter.hasNext()) {
            Product p = iter.next();
            if (p.getProductName().toLowerCase().contains(name.toLowerCase())) {
                iter.remove();
            }
        }
        return true;
    }

    public void removeAllProduct() {
        product.clear();
    }

    public boolean getProduct(int index) {
        if (product.isEmpty()) {
            return false;
        }
        if (index > productSize()) {
            System.out.println("Maximum index is: " + productSize());
            index = productSize();
        }

        System.out.printf("%-7s%-30s%-20s%-20s\n", "No.", "Product Name", "Sale Price", "Market Price");
        System.out.println(product.get(index - 1).toString(index));
        return true;
    }

    public int getProduct(String name) {
        if (product.isEmpty()) {
            return -1;
        }
        int isFound = 0;
        System.out.printf("%-7s%-30s%-20s%-20s\n", "No.", "Product Name", "Sale Price", "Market Price");
        int i = 1;
        for (Product p : product) {
            if (p.getProductName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(p.toString(i));
                isFound = 1;
                i++;
            }
        }
        if (isFound == 0) {
            System.out.println("Search not found!");
        }
        return isFound;
    }

    public boolean printAllProduct() {
        if (product.isEmpty()) {
            return false;
        }
        System.out.printf("%-7s%-30s%-20s%-20s\n", "No.", "Product Name", "Sale Price", "Market Price");
        int i = 0;
        for (Product p : product) {
            i++;
            System.out.println(p.toString(i));
        }
        return true;
    }

    public boolean printDetailOfProductByIndex(int index) {
        if (product.isEmpty()) {
            return false;
        }
        if (index > productSize()) {
            System.out.println("Maximum index is: " + productSize());
            index = productSize();
        }
        Product p = product.get(index - 1);
        ProductInfo[] pInfos = p.getProductInfo();
        System.out.println(p.toStringDetail());
        System.out.printf("%-7s%-15s%-15s%-15s%-15s%-15s\n", "No.", "Color", "Storage", "Weight", "Factory Date", "Description");
        int i = 1;
        for (ProductInfo pInfo : pInfos) {
            System.out.println(pInfo.toString(i));
            System.out.println("");
            i++;
        }
        return true;
    }

}
