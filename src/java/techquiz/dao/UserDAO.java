/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import techquiz.dbutil.DBConnection;
import techquiz.dto.UserDTO;

/**
 *
 * @author hp
 */
public class UserDAO {
    
     private static PreparedStatement ps;
    static{
        
     try{   
          
         ps=DBConnection.getConnection().prepareStatement("select * from user_details where email=? and pwd=?");
        
         
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
    
    
}
