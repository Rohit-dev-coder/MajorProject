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
import java.util.HashMap;
import java.util.Map;
import techquiz.dbutil.DBConnection;
import techquiz.dto.CorsesDTO;
import techquiz.dto.CoursesWithProgress;
import techquiz.dto.LecturesDTO;
import techquiz.dto.StudentLearingRec;

public class LearningDAO {

    private static Statement st, st1;
    private static PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7,ps8,ps9,ps10;

    static {
        try {
            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            st1 = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps = DBConnection.getConnection().prepareStatement("select crsid,crstitle,crsduration from coursesTable");
            ps1 = DBConnection.getConnection().prepareStatement("select crsid, sum(watchedmin) from studentLearnRecord where email = ? group by crsid");
            ps2 = DBConnection.getConnection().prepareStatement("select lecid, watchedmin from studentLearnRecord where email = ? and crsid = ?");
            ps3 = DBConnection.getConnection().prepareStatement("select * from lectable where crsid = ?");
            ps4 = DBConnection.getConnection().prepareStatement("select crstitle from coursestable where crsid = ?");
            ps5 = DBConnection.getConnection().prepareStatement("select lecref from lectable where crsid = ? and lecid = ?");
            ps6 = DBConnection.getConnection().prepareStatement("select watchedmin from studentLearnRecord where crsid = ? and lecid = ?");
            ps7 = DBConnection.getConnection().prepareStatement("update studentLearnRecord set watchedmin = ? where email = ? and lecid = ? and crsid = ?");
            ps8 = DBConnection.getConnection().prepareStatement("select email from studentLearnRecord where email =? and crsid = ? and lecid = ?");
            ps9 = DBConnection.getConnection().prepareStatement("insert into studentLearnRecord values(?,?,?,?)");
            ps10 = DBConnection.getConnection().prepareStatement("select lectime from lectable where crsid = ? and lecid = ?");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String getNewCrsID() throws SQLException {
        ResultSet rs = st1.executeQuery("select crsid from coursesTable");
        if (rs.last()) {
            String s = rs.getString(1);
            int i = Integer.parseInt(s.substring(2));
            return "cr" + (i + 1);
        }
        return "cr100";
    }

    public static String getNewLecID() throws SQLException {
        ResultSet rs = st.executeQuery("select lecid from lecTable");
        if (rs.last()) {
            String s = rs.getString(1);
            int i = Integer.parseInt(s.substring(2));
            return "lc" + (i + 1);
        }
        return "lc100";
    }

    public static HashMap<String, Integer> getCourseProgressByCrs(String email) throws SQLException {
        ps1.setString(1, email);
        ResultSet rs = ps1.executeQuery();
        HashMap<String, Integer> hm = new HashMap<>();
        while (rs.next()) {
            String cid = rs.getString(1);
            int min = rs.getInt(2);
            hm.put(cid, min);
        }
        return hm;
    }

    public static ArrayList<CorsesDTO> getAllCourses() throws SQLException {
        ResultSet rs = ps.executeQuery();
        ArrayList<CorsesDTO> al = new ArrayList<>();
        while (rs.next()) {
            CorsesDTO o = new CorsesDTO();
            o.setCrsId(rs.getString("crsid"));
            o.setCrsTitle(rs.getString("crstitle"));
            o.setCrsDuration(rs.getInt("crsduration"));
            al.add(o);
        }
        return al;
    }

    public static HashMap<String, Integer> getlecturesProgressByEmail(String email, String cid) throws SQLException {
        ps2.setString(1, email);
        ps2.setString(2, cid);
        ResultSet rs = ps2.executeQuery();
        HashMap<String, Integer> hm = new HashMap<>();
        while (rs.next()) {
            String lid = rs.getString(1);
            int min = rs.getInt(2);
            hm.put(lid, min);
        }
        System.out.println("Dao" + hm);
        return hm;
    }

    public static ArrayList<LecturesDTO> getAllLecturesByCrsId(String cid) throws SQLException {
        ps3.setString(1, cid);
        ResultSet rs = ps3.executeQuery();
        ArrayList<LecturesDTO> al = new ArrayList<>();
        while (rs.next()) {
            LecturesDTO obj = new LecturesDTO();
            obj.setCrsId(rs.getString("crsid"));
            obj.setLecId(rs.getString("lecid"));
            obj.setLecTitle(rs.getString("lectitle"));
            obj.setLecRef(rs.getString("lecref"));
            obj.setLecTime(rs.getInt("lectime"));
            al.add(obj);
        }
        return al;
    }

    public static String getCrsNameById(String id) throws SQLException {
        ps4.setString(1, id);
        ResultSet rs = ps4.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public static String getVideoRefrence(String cid, String lid) throws SQLException {
        ps5.setString(1, cid);
        ps5.setString(2, lid);
        ResultSet rs = ps5.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public static StudentLearingRec getStudentLearningRecByLecAndCrs(String cid, String lid) throws SQLException {
        ps6.setString(1, cid);
        ps6.setString(2, lid);
        ResultSet rs = ps6.executeQuery();
        StudentLearingRec obj = new StudentLearingRec();
        obj.setCrsId(cid);
        obj.setLecId(lid);
        if (rs.next()) {
            obj.setWatchedMin(rs.getInt(1));
        }
        return obj;
    }
    
    public static boolean UpdateStudentLearnRec(int min,String email,String crsid, String lecid) throws SQLException{
        ps7.setInt(1,min);
        ps7.setString(2,email);
        ps7.setString(3, lecid);
        ps7.setString(4,crsid);
        return (ps7.executeUpdate()!=0);
    }
    
    public static boolean isStudentRecAval(String email,String crsid, String lecid) throws SQLException{
        ps8.setString(1,email);
        ps8.setString(2,crsid);
        ps8.setString(3,lecid);
        ResultSet rs = ps8.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }
    
    public static boolean insertStudentRecData(String email,String crsid, String lecid,int min)throws SQLException{
        ps9.setString(1, email);
        ps9.setString(2,lecid);
        ps9.setString(3, crsid);
        ps9.setInt(4,min);
        return ps9.executeUpdate()!=0;
    }
    
    public static int getLecTime(String crsid,String lecid)throws SQLException{
        ps10.setString(1, crsid);
        ps10.setString(2, lecid);
        ResultSet rs = ps10.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
