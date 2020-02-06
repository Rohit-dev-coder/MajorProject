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
import techquiz.dto.mcqDTO;


public class McqDAO {
    private static PreparedStatement ps;
    static{
        try{   
            ps = DBConnection.getConnection().prepareStatement("insert into mcq values (?,?,?,?,?,?)");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public static boolean saveMcqQuestionAnswer(mcqDTO mcqobj)throws SQLException{
        ps.setString(1, mcqobj.getQid());
        ps.setString(2, mcqobj.getOption1());
        ps.setString(3, mcqobj.getOption2());
        ps.setString(4, mcqobj.getOption3());
        ps.setString(5, mcqobj.getOption4());
        ps.setString(6, mcqobj.getCanswer());
        return (ps.executeUpdate()!=0);
    }
}


