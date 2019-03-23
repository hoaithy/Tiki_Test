/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki_interview_java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

/**
 *
 * @author User
 */
public class InputTIKI {

    private CommonUtil c;
    private ProductCreator pc;

    {
        pc = new ProductCreator();
        c = new CommonUtil();
    }

    private void menu1() {
        System.out.println("********Welcome to TIKI Console********");
        System.out.println("******************Menu*****************");
        System.out.println("1/ Create product");
        System.out.println("2/ Show product list (" + pc.productSize() + (pc.productSize() <= 1 ? " product)" : " products)"));
        System.out.println("3/ Search product");
        System.out.println("4/ Delete product");
        System.out.println("5/ Exit program");
        System.out.println("Please enter action you want(1/2/3/4/5): ");
    }

    private void menu2() {
        System.out.println("1/ Display product detail.");
        System.out.println("2/ Go Back.");
        System.out.println("Enter your choice! (1/2): ");
    }

    private void menu3() {
        System.out.println("Product is empty, Would you like to insert more (1/2): ");
        System.out.println("1/ Yes      2/ No");
        System.out.println("Enter your choice! (1/2): ");
    }

    private void menu4() {
        System.out.println("1/ Search product by index");
        System.out.println("2/ Search product by name");
        System.out.println("Enter your choice! (1/2): ");
    }

    private void menu5() {
        System.out.println("1/ Delete product by index");
        System.out.println("2/ Delete product by name (can remove many)");
        System.out.println("3/ Delete all product");
        System.out.println("Enter your choice! (1/2/3): ");
    }

    private Product productInput(BufferedReader br) throws Exception {
        Product p = new Product();
        System.out.print("Product Name: ");
        p.setProductName(br.readLine());

        System.out.print("Sale Price (Ex: 100.2): ");
        Double salePrice = (Double) c.loopInput(br, c.parseDouble(br.readLine()), "sale price");
        p.setSalePrice(salePrice);

        System.out.print("Market Price (Ex: 100.2): ");
        Double marketPrice = (Double) c.loopInput(br, c.parseDouble(br.readLine()), "market price");
        p.setMarketPrice(marketPrice);
        return p;
    }

    private ProductInfo productInfoInput(BufferedReader br) throws Exception {
        ProductInfo pInfo = new ProductInfo();
        System.out.print("Color: ");
        pInfo.setColor(br.readLine());

        System.out.print("Storage: ");
        pInfo.setStorage((int) c.loopInput(br, c.parseInt(br.readLine()), "storage"));

        System.out.print("Weight: ");
        pInfo.setWeight((float) c.loopInput(br, c.parseFloat(br.readLine()), "weight"));

        System.out.print("How many Image you want to insert(max is 3): ");
        int lengthImage = (int) c.loopInput(br, c.parseInt(br.readLine()), "quantity of image");
        if (lengthImage > 3) {
            lengthImage = 3;
            System.out.println("Just only 3 images can insert");
        }
        String[] imageUrl = new String[lengthImage];
        for (int i = 1; i <= lengthImage; i++) {
            System.out.print("Image link " + i + " : ");
            imageUrl[i - 1] = br.readLine();
        }
        pInfo.setImageUrl(imageUrl);

        System.out.print("Factory Date: ");
        pInfo.setFactoryDate((Date) c.loopInput(br, c.convertStringToDate(br.readLine()), "factory date"));

        System.out.print("Description: ");
        pInfo.setDescription(br.readLine());
        return pInfo;
    }

    public void action() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String choice = "";
            do {
                menu1();
                choice = br.readLine();
                switch (choice) {
                    case "1":
                        insertProduct(br);
                        System.out.println("");
                        break;
                    case "2":
                        showAllProduct(br);
                        break;
                    case "3":
                        searchProduct(br);
                        break;
                    case "4":
                        deleteProduct(br);
                        break;
                }
                if (choice.equals("5")) {
                    break;
                }
                System.out.println("\nPlease enter any key for continue ... ");
                br.readLine();
            } while (true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(BufferedReader br) throws Exception {
        menu5();
        int choice = c.choiceNumber(br, c.parseInt(br.readLine()), 3);
        if (choice == 3) {
            System.out.println("Are you sure you want to delete all? ");
            System.out.println("1. Yes  2. No");
            int select = c.choiceNumber(br, c.parseInt(br.readLine()), 2);
            if (select == 1) {
                pc.removeAllProduct();
            }
        } else if (choice == 1) {
            if (pc.printAllProduct()) {
                int index = c.choiceNumber(br, c.parseInt(br.readLine()), pc.productSize());
                if (pc.getProduct(index)) {
                    System.out.println("Are you sure you want to delete it? ");
                    System.out.println("1. Yes  2. No");
                    int select = c.choiceNumber(br, c.parseInt(br.readLine()), 2);
                    if (select == 1) {
                        pc.removeProduct(index);
                    }
                } else {
                    askForInsert(br, false);
                }

            } else {
                askForInsert(br, false);
            }
        } else {
            System.out.print("Enter product name: ");
            String name = br.readLine();
            int isFound = pc.getProduct(name);
            if (isFound == 1) {
                System.out.println("Are you sure you want to delete them? ");
                System.out.println("1. Yes  2. No");
                int select = c.choiceNumber(br, c.parseInt(br.readLine()), 2);
                if (select == 1) {
                    pc.removeProduct(name);
                }
            } else if (isFound == -1) {
                askForInsert(br, false);
            }
        }

    }

    private void searchProduct(BufferedReader br) throws Exception {
        menu4();
        boolean isEmpty = true;
        int choice = (int) c.loopInput(br, c.parseInt(br.readLine()), "numeric");
        if (choice == 1) {
            System.out.print("Enter index (1/2/3 ...): ");
            int index = (int) c.loopInput(br, c.parseInt(br.readLine()), "numeric");
            if ((isEmpty = pc.getProduct(index)) == true) {
                askForDetailProduct(br, index);
            }
        } else {
            System.out.print("Enter product name: ");
            int isFound = pc.getProduct(br.readLine());
            if (isFound == 1) {
                askForDetailProduct(br);
            } else if (isFound == -1) {
                isEmpty = false;
            }
        }

        if (!isEmpty) {
            askForInsert(br, false);
        }
    }

    private void insertProduct(BufferedReader br) throws Exception {
        Product p = productInput(br);
        System.out.print("How many type of product you want to insert? (enter number): ");
        int types = (int) c.loopInput(br, c.parseInt(br.readLine()), "numeric");
        ProductInfo[] pInfo = new ProductInfo[types];
        for (int i = 0; i < types; i++) {
            pInfo[i] = productInfoInput(br);
        }
        p.setProductInfo(pInfo);
        pc.addProduct(p);
        System.out.println("Insert product success!");
    }

    private void showAllProduct(BufferedReader br) throws Exception {
        if (pc.printAllProduct()) {
            askForDetailProduct(br);
        } else {
            askForInsert(br, false);
        }
    }

    private void showProduct(BufferedReader br) throws Exception {
        System.out.println("Enter product you want to show detail: (1/2/3 ...)");
        boolean isEmpty = pc.printDetailOfProductByIndex((int) c.loopInput(br, c.parseInt(br.readLine()), "numeric"));
        askForInsert(br, isEmpty);
    }

    private void askForDetailProduct(BufferedReader br) throws Exception {
        menu2();
        int choice = (int) c.loopInput(br, c.parseInt(br.readLine()), "numeric");
        if (choice == 1) {
            showProduct(br);
        }
    }

    private void askForDetailProduct(BufferedReader br, int index) throws Exception {
        menu2();
        int choice = (int) c.loopInput(br, c.parseInt(br.readLine()), "numeric");
        if (choice == 1) {
            pc.printDetailOfProductByIndex(index);
        }
    }

    private void askForInsert(BufferedReader br, boolean isEmpty) throws Exception {
        if (!isEmpty) {
            menu3();
            int prdChoice = (int) c.loopInput(br, c.parseInt(br.readLine()), "numeric");
            if (prdChoice == 1) {
                insertProduct(br);
            }
        }
    }

    public void init() {
        pc.addProduct(new Product("IphoneX 64Gb Black", 999, 1099, new ProductInfo[]{
            new ProductInfo("Black", 64, c.convertStringToDate("20/10/2017"), "What do you think about this product?", (float) 132.1, new String[]{
                "https://tiki.vn/iphonex64gb/image1.jpg", "https://tiki.vn/iphonex64gb/image2.jpg", "https://tiki.vn/iphonex64gb/image3.jpg",})
        }));
        pc.addProduct(new Product("IphoneX 128Gb Yellow", 950, 1050, new ProductInfo[]{
            new ProductInfo("Yellow", 128, c.convertStringToDate("20/10/2017"), "What do you think about this product?", (float) 132.1, new String[]{
                "https://tiki.vn/iphonex128gb/image1.jpg", "https://tiki.vn/iphonex128gb/image2.jpg", "https://tiki.vn/iphonex128gb/image3.jpg",})
        }));
        pc.addProduct(new Product("IphoneX", 950, 1050, new ProductInfo[]{
            new ProductInfo("Black", 64, c.convertStringToDate("20/10/2017"), "What do you think about this product?", (float) 132.1, new String[]{
                "https://tiki.vn/iphonex64gb/image1.jpg", "https://tiki.vn/iphonex64gb/image2.jpg", "https://tiki.vn/iphonex64gb/image3.jpg",}),
            new ProductInfo("Yellow", 128, c.convertStringToDate("20/10/2017"), "What do you think about this product?", (float) 132.1, new String[]{
                "https://tiki.vn/iphonex128gb/image1.jpg", "https://tiki.vn/iphonex128gb/image2.jpg", "https://tiki.vn/iphonex128gb/image3.jpg",})
        }));
    }

}
