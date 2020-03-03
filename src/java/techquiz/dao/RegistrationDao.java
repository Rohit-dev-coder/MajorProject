/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import techquiz.dbutil.DBConnection;
import techquiz.dto.UserDetails;

/**
 *
 * @author PC
 */
public class RegistrationDao {
    private static Statement st1;
    private static PreparedStatement ps,ps1;
    
    static{
        try{
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null,"USER_DETAILS", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table user_details (fname varchar2(15),sname varchar2(15), email varchar2(30), mobile_no number(10), gender varchar2(10), pwd varchar2(30), usertype varchar2(10) constraints user_type_ck check (usertype in ('student','teacher')))");
            }
            
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
