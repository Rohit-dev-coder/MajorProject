/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import techquiz.dbutil.DBConnection;
import techquiz.dto.QuestionDTO;

public class QuestionDAO {
    private static Statement st;
    private static PreparedStatement ps;
    static{
        try{   
            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps = DBConnection.getConnection().prepareStatement("insert into questions values (?,?,?,?)");
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
}
