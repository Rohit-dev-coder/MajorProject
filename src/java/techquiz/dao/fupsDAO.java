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
import techquiz.dto.fupsDTO;
import techquiz.dto.mcqDTO;


public class fupsDAO {
    private static PreparedStatement ps,ps1;
    static{
        try{   
            ps = DBConnection.getConnection().prepareStatement("insert into fups values (?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select canswer from fups where qid = ?");
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
    
    public static String getCorrAnswer(String qid)throws SQLException{
        ps1.setString(1, qid);
        ResultSet rs = ps1.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}
