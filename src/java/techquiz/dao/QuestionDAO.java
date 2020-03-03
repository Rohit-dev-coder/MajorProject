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
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.QuestionDTO;

public class QuestionDAO {
    private static Statement st,st1;
    private static PreparedStatement ps,ps1,ps2,ps3;
    static{
        try{  
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "QUESTIONS", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table questions (examid varchar2(5), qtype varchar2(4) constraints ques_ck check (qtype in ('mcq','tf','fups')),qid varchar2(10), question varchar2(4000))");
            }
            
            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps = DBConnection.getConnection().prepareStatement("insert into questions values (?,?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select * from questions where examid = ?");
            ps2 = DBConnection.getConnection().prepareStatement("select qtype from questions where qid = ?");
            ps3 = DBConnection.getConnection().prepareStatement("select qid from questions where examid = ?");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public static String getNewQuestionID()throws SQLException{
        ResultSet rs = st.executeQuery("select qid from questions");       
        if(rs.last()){
            String s = rs.getString(1);
            int i = Integer.parseInt(s.substring(1));
            return "Q"+(i+1);
        }
        return "Q1000";
    }
    
    public static boolean saveQuestion(QuestionDTO qobj)throws SQLException{
        ps.setString(1,qobj.getExamId());
        ps.setString(2, qobj.getqType());
        ps.setString(3, qobj.getQid());
        ps.setString(4, qobj.getQuestion());
        return (ps.executeUpdate()!=0);
    }
    
    public static ArrayList<QuestionDTO> getAllQuestionByExamid(String examid)throws SQLException{
        ps1.setString(1, examid);
        ResultSet rs = ps1.executeQuery();
        ArrayList<QuestionDTO> al = new ArrayList<>();
        while(rs.next()){
            QuestionDTO o = new QuestionDTO();
            o.setExamId(examid);
            o.setQid(rs.getString("qid"));
            o.setQuestion(rs.getString("question"));
            o.setqType(rs.getString("qtype"));
            al.add(o);
        }
        return al;
    }
    
    public static String getQuestionType(String qid)throws SQLException{
        ps2.setString(1, qid);
        ResultSet rs = ps2.executeQuery();
        rs.next();
        return rs.getString(1);
    }
    
    public static ArrayList<String> getAllQidbyEid(String qid) throws SQLException{
        ps3.setString(1, qid);
        ResultSet rs = ps3.executeQuery();
        ArrayList<String> al = new ArrayList<>();
        while(rs.next()){
            al.add(rs.getString(1));
        }
        return al;
    }
    
}
