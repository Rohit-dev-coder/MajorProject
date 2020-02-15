
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.EnrollDTO;
import techquiz.dto.ExamDTO;
import techquiz.dto.stdexamdetails;
import techquiz.dto.testdetail;

public class ExamDAO {
    private static Statement st;
    private static PreparedStatement ps,ps1,ps2,ps3,ps4,ps5,ps6,ps7;
    
    static{
        try{   
            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); //for getting new examid every time
            ps = DBConnection.getConnection().prepareStatement("insert into exam values (?,?,to_timestamp(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select * from exam where email = ?");
            ps2 = DBConnection.getConnection().prepareStatement("select * from exam");
            ps3 = DBConnection.getConnection().prepareStatement("insert into enrolltable values(?,?,?)");
            ps4 = DBConnection.getConnection().prepareStatement("select status from enrolltable where examid = ? and email = ?");
            ps5 = DBConnection.getConnection().prepareStatement("select * from exam where examid = ?");
            ps6 = DBConnection.getConnection().prepareStatement("update enrolltable set status = 'R' where email = ? and examid = ?");
            ps7 = DBConnection.getConnection().prepareStatement("select email from enrolltable where examid = ?");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    
//    return examid like ex1,ex2...exn; range(ex1 to ex999)
    public static String getExamID()throws SQLException{
        ResultSet rs = st.executeQuery("select examid from exam");       
        if(rs.last()){
            String s = rs.getString(1);
            int i = Integer.parseInt(s.substring(2));
            return "ex"+(i+1);
        }
        return "ex100";
    }
    
    public static boolean saveExam(ExamDTO ob)throws SQLException{
        ps.setString(1, ob.getExamId());
        ps.setString(2, ob.getExamTitle());
        ps.setString(3, ob.getExamDateTime());
        ps.setInt(4, ob.getTotalQuestion());
        ps.setInt(5, ob.getTotalMarks());
        ps.setString(6, ob.getEmail());
        return (ps.executeUpdate() != 0);
    }
    
    public static ArrayList<ExamDTO> getAllExamByEmail(String email)throws SQLException{
        ps1.setString(1, email);
        ResultSet rs = ps1.executeQuery();
        ArrayList<ExamDTO> al = new ArrayList<>();
        while(rs.next())
        {
            ExamDTO obj = new ExamDTO();
            obj.setExamId(rs.getString(1));
            obj.setExamTitle(rs.getString(2));
            obj.setExamDateTime(rs.getTimestamp(3).toString());
            obj.setTotalQuestion(rs.getInt(4));
            obj.setTotalMarks(rs.getInt(5));
            obj.setEmail(email);
            al.add(obj);
        }
        return al;
    }
    
    public static ArrayList<stdexamdetails> getAllExams()throws SQLException{
        ResultSet rs = ps2.executeQuery();
        ArrayList<stdexamdetails> al = new ArrayList<>();
        while(rs.next())
        {
            stdexamdetails obj = new stdexamdetails();
            obj.setExamId(rs.getString(1));
            obj.setExamTitle(rs.getString(2));
            obj.setExamDateTime(rs.getTimestamp(3).toString());
            al.add(obj);
        }
        return al;
    }
    
    public static boolean enrollStd(EnrollDTO o)throws SQLException{
        ps3.setString(1, o.getExamid());
        ps3.setString(2, o.getEmail());
        ps3.setString(3, o.getStatus());
        return (ps3.executeUpdate() != 0);
    }
    
    public static String getStatus(String e,String f)throws SQLException{
        ps4.setString(1, e);
        ps4.setString(2, f);
        ResultSet rs = ps4.executeQuery();
        if(rs.next())
            return rs.getString(1);
        else
            return null;
    }
    
    public static testdetail getExamByID(String exid)throws SQLException{
        ps5.setString(1, exid);
        ResultSet rs = ps5.executeQuery();
        rs.next();
        testdetail obj = new testdetail();
        obj.setExamId(exid);
        obj.setExamDateTime(rs.getString("examdatetime"));
        obj.setExamTitle(rs.getString("examtitle"));
        obj.setTotalMarks(rs.getInt("totalmarks"));
        obj.setTotalQuestion(rs.getInt("totalquestion"));
        obj.setEmail(rs.getString("email"));
        obj.setStatus(rs.getString("status"));
        return obj;
    }
    
    public static boolean updateEnrollTableStatus(String examid, String email) throws SQLException{
        ps6.setString(1, email);
        ps6.setString(2, examid);
        return (ps6.executeUpdate()!=0);
    }
    
    public static ArrayList<String> getAllStdByExamId(String exid) throws SQLException{
        ps7.setString(1, exid);
        ResultSet rs = ps7.executeQuery();
        ArrayList<String> al = new ArrayList<>();
        while(rs.next()){
            al.add(rs.getString(1));
        }
        return al;
    }
}
