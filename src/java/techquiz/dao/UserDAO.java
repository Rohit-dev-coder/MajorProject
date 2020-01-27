/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.UserDTO;
import techquiz.dto.UserDetails;

/**
 *
 * @author hp
 */
public class UserDAO {
    
     private static PreparedStatement ps,ps1;
    static{
        
     try{   
          
         ps=DBConnection.getConnection().prepareStatement("select * from user_details where email=? and pwd=?");
        ps1 = DBConnection.getConnection().prepareStatement("Select * from user_details where usertype = ?");
         
     }
     catch(SQLException ex)
     {
         if(DBConnection.getConnection()!=null)
             System.out.println("not null");
         ex.printStackTrace();
    }
}
    public static String validateUser(UserDTO user)throws SQLException{        
     ps.setString(1,user.getUsername());
     ps.setString(2,user.getPassword());
     ResultSet rs=ps.executeQuery();
     if(rs.next()){
     return rs.getString(7);
    }
     else
         return null;        
    }
    
    public static ArrayList<UserDetails> getAllUsers(String type) throws SQLException{
        ps1.setString(1, type);
        ResultSet rs = ps1.executeQuery();
        ArrayList<UserDetails> al = new ArrayList<>();
        while(rs.next()){
            UserDetails obj = new UserDetails();
            obj.setFname(rs.getString(1));
            obj.setSname(rs.getString(2));
            obj.setEmail(rs.getString(3));
            obj.setGender(rs.getString(5));
            obj.setMobile(rs.getLong(4));
            al.add(obj);
        }
        return al;
    }
    
}
