/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import techquiz.dbutil.DBConnection;
import techquiz.dto.UserDetails;

/**
 *
 * @author PC
 */
public class RegistrationDao {
    
    private static PreparedStatement ps,ps1;
    
    static{
        try{   
            ps=DBConnection.getConnection().prepareStatement("select * from user_details where email=?");
            ps1=DBConnection.getConnection().prepareStatement("insert into user_details values(?,?,?,?,?,?,?)");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public static boolean searchUser(String email)throws SQLException{
        ps.setString(1,email);
        return ps.executeQuery().next();
    }
    
    public static boolean registerUser(UserDetails user)throws SQLException{
        
        ps1.setString(1,user.getFname());
        ps1.setString(2,user.getSname());
        ps1.setString(3,user.getEmail());
        ps1.setLong(4,user.getMobile());
        ps1.setString(5,user.getGender());
        ps1.setString(6,user.getPwd());
        ps1.setString(7,"student");
        return (ps1.executeUpdate()!=0);
        
    }
    
}
