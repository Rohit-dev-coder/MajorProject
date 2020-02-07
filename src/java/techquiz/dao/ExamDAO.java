
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.ExamDTO;

public class ExamDAO {
    private static Statement st;
    private static PreparedStatement ps,ps1;
    
    static{
        try{   
            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); //for getting new examid every time
            ps = DBConnection.getConnection().prepareStatement("insert into exam values (?,?,to_timestamp(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select * from exam where email = ?");
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
    
}
