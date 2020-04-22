/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dbutil;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class sample {
    public static void main(String[] args) {
        Date d1 = new Date();
        for(int i =0;i<20000;i++){
            Connection conn = null;
            
            try {
                conn = DBConnection.getConnection();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(sample.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(conn+" :"+i);
        }
        Date d2 = new Date();
        System.out.println(d1);
        System.out.println(d2);
    }
}
