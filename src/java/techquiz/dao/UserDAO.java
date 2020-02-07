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
    
     private static PreparedStatement ps,ps1,ps2;
    static{
        
     try{   
          
        ps=DBConnection.getConnection().prepareStatement("select * from user_details where email=? and pwd=? and usertype=?");
        ps1 = DBConnection.getConnection().prepareStatement("Select * from user_details where usertype = ?");
        ps2 = DBConnection.getConnection().prepareStatement("Select * from user_details where email = ?");
     }
     catch(SQLException ex)
     {
         if(DBConnection.getConnection()!=null)
             System.out.println("not null");
         ex.printStackTrace();
    }
}
    public static boolean validateUser(UserDTO user)throws SQLException{        
     ps.setString(1,user.getUsername());
     ps.setString(2,user.getPassword());
     ps.setString(3, user.getType());
     ResultSet rs=ps.executeQuery();
     return rs.next();         
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
    
    public static UserDetails getSingleUserDetail(String email) throws SQLException
    {
        ps2.setString(1, email);
        ResultSet rs = ps2.executeQuery();
        UserDetails obj = new UserDetails();
        if(!rs.next())
        {
            obj = null;
        }
        else{
            obj.setFname(rs.getString(1));
            obj.setSname(rs.getString(2));
            obj.setEmail(rs.getString(3));
            obj.setGender(rs.getString(5));
            obj.setMobile(rs.getLong(4));
        }
        
        return obj;
    }
}
