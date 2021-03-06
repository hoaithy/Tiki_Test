/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiki_interview_java;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author User
 */
public class CommonUtil {

    //parse and validate string to double
    public Double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
    //parse and validate string to float
    public Float parseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return (float) -1.0;
        }
    }
    //parse and validate string to int
    public int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //Validate that when user enter some incorrect input text/int/float ...
    //They will automatically ask the user input again
    public Object loopInput(BufferedReader br, Object o, String field) throws Exception {
        String dataType = o.getClass().getSimpleName();
        String message = "Wrong format for " + field + " please enter again";
        switch (dataType) {
            case "Integer":
                int numInt = (int) o;
                while (numInt <= 0) {
                    System.out.println(message);
                    numInt = parseInt(br.readLine());
                }
                return numInt;
            case "Double":
                double numDouble = (double) o;
                while (numDouble <= 0) {
                    System.out.println(message);
                    numDouble = parseDouble(br.readLine());
                }
                return numDouble;
            case "Float":
                float numFloat = (float) o;
                while (numFloat <= 0) {
                    System.out.println(message);
                    numFloat = parseFloat(br.readLine());
                }
                return numFloat;
            case "Date":
                Date date = (Date) o;
                //compare with default date
                //if input date same with default date
                //the program will ask for input again
                while (date.equals(convertStringToDate("01/01/0001"))) {
                    System.out.println(message);
                    date = convertStringToDate(br.readLine());
                }
                return date;
        }
        return null;
    }

    //Convert and validate String to Java date
    //If format is wrong, it will return to default date to compare (validate)
    public Date convertStringToDate(String str) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            return convertStringToDate("01/01/0001");
        }
    }

    public String convertDateToString(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(d);
    }

    //When choice the number of menu
    //some time user will input wrong index
    //the method will automatically ask for do again
    public int choiceNumber(BufferedReader br, int choice, int maxChoice) throws Exception {
        while (choice <= 0 || choice > maxChoice) {
            System.out.println("Please enter number from 1 to " + maxChoice);
            choice = parseInt(br.readLine());
        }
        return choice;
    }
}
