
package techquiz.dao;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.rankDTO;
import techquiz.dto.resultDTO;
import techquiz.dto.resultFrGDTO;


public class ResultDAO {
    private static Statement st1;
    private static PreparedStatement ps1,ps2,ps3,ps4,ps5;
    static{
        try{   
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "RESULTS", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table RESULTS (email varchar2(30), examid varchar2(5), totalques number(3), totalattempt number(3), rightans number(3), wrongans number(3), unattempt number(3), totalmarks number(10), percentage number(5,2))");
                 st1.executeQuery("commit");
            }
            
            ps3 = DBConnection.getConnection().prepareStatement("select email,percentage from results where examid = ? order by percentage desc");
            ps1 = DBConnection.getConnection().prepareStatement("insert into results values (?,?,?,?,?,?,?,?,?)");
            ps2 = DBConnection.getConnection().prepareStatement("select * from results where email = ? and examid = ?");
            ps4 = DBConnection.getConnection().prepareStatement("delete from results where examid = ?");
            ps5 = DBConnection.getConnection().prepareStatement("select examtitle, percentage from results r inner join exam e on e.examid = r.examid where r.email = ?");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static boolean saveResult(resultDTO obj)throws SQLException{
       ps1.setString(1, obj.getEmailid());
       ps1.setString(2, obj.getExamid());
       ps1.setInt(3,obj.getTotalque());
       ps1.setInt(4, obj.getTotalattempt());
       ps1.setInt(5, obj.getRightans());
       ps1.setInt(6, obj.getWrongans());
       ps1.setInt(7, obj.getUnattempt());
       ps1.setInt(8, obj.getTotalmarks());
       ps1.setDouble(9, obj.getPercentage());
       return ps1.executeUpdate()!=0;
    }
    
    public static resultDTO getResultByid(String email,String examid)throws SQLException{
        ps2.setString(1, email);
        ps2.setString(2, examid);
        ResultSet rs = ps2.executeQuery();
        rs.next();
        resultDTO robj = new resultDTO();
        robj.setEmailid(email);
        robj.setExamid(examid);
        robj.setRightans(rs.getInt("rightans"));
        robj.setTotalattempt(rs.getInt("totalattempt"));
        robj.setTotalmarks(rs.getInt("totalmarks"));
        robj.setTotalque(rs.getInt("totalques"));
        robj.setUnattempt(rs.getInt("unattempt"));
        robj.setWrongans(rs.getInt("wrongans"));
        robj.setPercentage(rs.getDouble("percentage"));
        return robj;
    }
    
    public static ArrayList<rankDTO> getAllResultbyexamid(String eid) throws SQLException{
        ps3.setString(1, eid);
        ResultSet rs = ps3.executeQuery();
        ArrayList<rankDTO> al = new ArrayList<>();
        
        while(rs.next()){
            rankDTO obj = new rankDTO();
            obj.setEmail(rs.getString(1));
            obj.setPer(rs.getDouble(2));
            al.add(obj);
        }
        return al;
    }
    
    public static boolean deleteAllResultbyexamid(String eid) throws SQLException{
        ps4.setString(1, eid);
        return ps4.executeUpdate()!=0;
    }
    
    public static ArrayList<resultFrGDTO> getResultForGraphByEmail(String eid) throws SQLException{
        ps5.setString(1, eid);
        ResultSet rs = ps5.executeQuery();
        ArrayList<resultFrGDTO> al = new ArrayList<resultFrGDTO>();
        while (rs.next()){
            resultFrGDTO o = new resultFrGDTO();
            o.setEtitle(rs.getString(1));
            o.setPercentage(rs.getDouble(2));
            al.add(o);
        }
        return al;
    }
}
