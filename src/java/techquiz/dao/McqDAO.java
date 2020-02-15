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
import techquiz.dto.mcqOptionsDTO;


public class McqDAO {
    private static PreparedStatement ps,ps1,ps2;
    static{
        try{   
            ps = DBConnection.getConnection().prepareStatement("insert into mcq values (?,?,?,?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select option1,option2, option3, option4 from mcq where qid = ?");
            ps2 = DBConnection.getConnection().prepareStatement("select canswer from mcq where qid = ?");
            
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
    
    public static mcqOptionsDTO getAllOptions(String qid)throws SQLException{
        ps1.setString(1, qid);
        ResultSet rs = ps1.executeQuery();
        rs.next();
        mcqOptionsDTO obj = new mcqOptionsDTO(qid, rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4));
        return obj;
    }
    
    public static String getcanswer(String qid)throws SQLException{
        ps2.setString(1, qid);
        ResultSet rs = ps1.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}


