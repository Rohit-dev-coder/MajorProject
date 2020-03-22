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
import techquiz.dto.mcqDTO;
import techquiz.dto.mcqOptionsDTO;


public class McqDAO {
    private static Statement st1,st2;
    private static PreparedStatement ps,ps1,ps2,ps3,ps4;
    static{
        try{  
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "MCQ", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table mcq (qid varchar2(10), option1 varchar2(1000), option2 varchar2(1000),option3 varchar2(1000), option4 varchar2(1000), canswer varchar2(30))");
//                System.out.println("table created");  
                 st1.executeQuery("commit");
            }
            ps = DBConnection.getConnection().prepareStatement("insert into mcq values (?,?,?,?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select option1,option2, option3, option4 from mcq where qid = ?");
            ps2 = DBConnection.getConnection().prepareStatement("select canswer from mcq where qid = ?");
            ps3 = DBConnection.getConnection().prepareStatement("select * from mcq where qid = ?");
            st2 = DBConnection.getConnection().createStatement();
            ps4 = DBConnection.getConnection().prepareStatement("update mcq set canswer = ? where qid = ?");
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
    
    public static mcqDTO getMCQDTOById (String qid) throws SQLException{
        ps3.setString(1, qid);
        ResultSet rs = ps3.executeQuery();
        rs.next();
        mcqDTO o = new mcqDTO();
        o.setCanswer(qid);
        o.setOption1(rs.getString("option1"));
        o.setOption2(rs.getString("option2"));
        o.setOption3(rs.getString("option3"));
        o.setOption4(rs.getString("option4"));
        o.setCanswer(rs.getString("canswer"));
        return o;
    }
    
    public static boolean updateoptions(String option,String data,String qid) throws SQLException{
        return st2.executeUpdate("update mcq set "+option+" = '"+data+"' where qid = '"+qid+"'")!=0;
    }
    
    public static boolean updateCans(String data, String qid) throws SQLException{
        ps4.setString(1, data);
        ps4.setString(2, qid);
        return ps4.executeUpdate()!=0;
    }
    
}


