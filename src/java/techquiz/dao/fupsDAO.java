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
import techquiz.dto.fupsDTO;
import techquiz.dto.mcqDTO;


public class fupsDAO {
    private static PreparedStatement ps,ps1;
    private static Statement st1;
    static{
        try{ 
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "FUPS", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table fups (qid varchar2(1000),canswer varchar2(20))");
//                System.out.println("table created");  
                st1.executeQuery("commit");
            }
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
