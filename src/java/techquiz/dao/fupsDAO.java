/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import techquiz.dbutil.DBConnection;
import techquiz.dto.fupsDTO;
import techquiz.dto.mcqDTO;


public class fupsDAO {
    private static PreparedStatement ps;
    static{
        try{   
            ps = DBConnection.getConnection().prepareStatement("insert into fups values (?,?)");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public static boolean savefupsQuestionAnswer(fupsDTO fobj)throws SQLException{
        ps.setString(1, fobj.getQid());
        ps.setString(2, fobj.getCanswer());
        return (ps.executeUpdate()!=0);
    }
}
