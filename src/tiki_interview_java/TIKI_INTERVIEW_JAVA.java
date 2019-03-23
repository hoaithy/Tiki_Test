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
public class TIKI_INTERVIEW_JAVA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InputTIKI tiki = new InputTIKI();
        tiki.init();
        tiki.action();
//        CommonUtil c = new CommonUtil();
//        System.out.println();
//        System.out.println(c.convertStringToDate("02/01/0001").equals(c.convertStringToDate("01/22/2019")));
    }
    
    public static void test(Object o) {
        System.out.println(o.getClass().getSimpleName());
    }
    
}
