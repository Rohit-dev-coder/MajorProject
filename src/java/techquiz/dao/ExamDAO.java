package techquiz.dao;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.EnrollDTO;
import techquiz.dto.ExamDTO;
import techquiz.dto.examsampleinfo;
import techquiz.dto.stdexamdetails;
import techquiz.dto.testdetail;

public class ExamDAO {

    private static Statement st, st1;
    private static PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, ps12, ps13, ps14, ps15;

    static {
        try {
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "EXAM", null);
            if(rs.next()) {
                System.out.println("Table alredy exits");         
            }
            else{
                System.out.println("Table not found");
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table exam (examid varchar2(5), examtitle varchar2(20), examdatetime timestamp(6), totalquestion number(3), totalmarks number(3), email varchar2(30), status varchar2(5) constraints exam_st_ck check (status in ('NS','S','R','E')))");
                st1.executeQuery("commit");
                System.out.println("table created");  
            }
            rs = dbm.getTables(null, null, "ENROLLTABLE", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table enrolltable (examid varchar2(5), email varchar2(30), status varchar2(5) constraints enroll_st_ck check (status in ('NE','E','R')))");
            }
            rs = dbm.getTables(null, null, "ACTIVEEXAM", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table activeexam (examid varchar2(5),min varchar2(5))");
            }
            

            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE); //for getting new examid every time
            ps = DBConnection.getConnection().prepareStatement("insert into exam values (?,?,to_timestamp(?,'YYYY-MM-DD HH24:MI:SS'),?,?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select * from exam where email = ?");
            ps2 = DBConnection.getConnection().prepareStatement("select * from exam");
            ps3 = DBConnection.getConnection().prepareStatement("insert into enrolltable values(?,?,?)");
            ps4 = DBConnection.getConnection().prepareStatement("select status from enrolltable where examid = ? and email = ?");
            ps5 = DBConnection.getConnection().prepareStatement("select * from exam where examid = ?");
            ps6 = DBConnection.getConnection().prepareStatement("update enrolltable set status = 'R' where email = ? and examid = ?");
            ps7 = DBConnection.getConnection().prepareStatement("select email,status from enrolltable where examid = ?");
            ps8 = DBConnection.getConnection().prepareStatement("update exam set status = 'S' where examid = ?");
            ps9 = DBConnection.getConnection().prepareStatement("update exam set status = 'NS' where examid = ?");
            ps10 = DBConnection.getConnection().prepareStatement("insert into activeexam values(?,?)");
            ps11 = DBConnection.getConnection().prepareStatement("delete from activeexam where examid = ?");
            ps12 = DBConnection.getConnection().prepareStatement("select min from activeexam where examid = ?");
            ps13 = DBConnection.getConnection().prepareStatement("update exam set status = 'R' where examid = ?");
            ps14 = DBConnection.getConnection().prepareStatement("delete from enrolltable where examid = ?");
            ps15 = DBConnection.getConnection().prepareStatement("select examid,examtitle from exam where status = 'R'");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<examsampleinfo> getAllDeclaredExamDetails() throws SQLException {
        ResultSet rs = ps15.executeQuery();
        ArrayList<examsampleinfo> al = new ArrayList<>();
        while (rs.next()) {
            examsampleinfo obj = new examsampleinfo();
            obj.setExamid(rs.getString(1));
            obj.setExamtitle(rs.getString(2));

            al.add(obj);
        }
        return al;
    }

    public static boolean deletestdfromenroll(String eid) throws SQLException {
        ps14.setString(1, eid);
        return ps14.executeUpdate() != 0;
    }

    public static boolean declaredRank(String eid) throws SQLException {
        ps13.setString(1, eid);
        return ps13.executeUpdate() != 0;
    }

    public static String getexamminutes(String eid) throws SQLException {
        ps12.setString(1, eid);
        ResultSet rs = ps12.executeQuery();
        rs.next();
        return rs.getString(1);
    }

    public static boolean startexam(String eid, String min) throws SQLException {
        ps8.setString(1, eid);
        ps10.setString(1, eid);
        ps10.setString(2, min);
        boolean result = false;
        if (ps8.executeUpdate() != 0) {
            result = true;
        }
        if (ps10.executeUpdate() != 0) {
            result = true;
        }
        return result;
    }

    public static boolean endexam(String eid) throws SQLException {
        ps9.setString(1, eid);
        ps11.setString(1, eid);
        boolean result = false;
        if (ps9.executeUpdate() != 0) {
            result = true;
        }
        if (ps11.executeUpdate() != 0) {
            result = true;
        }
        return result;
    }

//    return examid like ex1,ex2...exn; range(ex1 to ex999)
    public static String getExamID() throws SQLException {
        ResultSet rs = st.executeQuery("select examid from exam");
        if (rs.last()) {
            String s = rs.getString(1);
            int i = Integer.parseInt(s.substring(2));
            return "ex" + (i + 1);
        }
        return "ex100";
    }

    public static boolean saveExam(ExamDTO ob) throws SQLException {
        ps.setString(1, ob.getExamId());
        ps.setString(2, ob.getExamTitle());
        ps.setString(3, ob.getExamDateTime());
        ps.setInt(4, ob.getTotalQuestion());
        ps.setInt(5, ob.getTotalMarks());
        ps.setString(6, ob.getEmail());
        ps.setString(7, "NS");
        return (ps.executeUpdate() != 0);
    }

    public static ArrayList<ExamDTO> getAllExamByEmail(String email) throws SQLException {
        ps1.setString(1, email);
        ResultSet rs = ps1.executeQuery();
        ArrayList<ExamDTO> al = new ArrayList<>();
        while (rs.next()) {
            ExamDTO obj = new ExamDTO();
            obj.setExamId(rs.getString(1));
            obj.setExamTitle(rs.getString(2));
            obj.setExamDateTime(rs.getTimestamp(3).toString());
            obj.setTotalQuestion(rs.getInt(4));
            obj.setTotalMarks(rs.getInt(5));
            obj.setEmail(email);
            obj.setStatus(rs.getString(7));
            al.add(obj);
        }
        return al;
    }

    public static ArrayList<stdexamdetails> getAllExams() throws SQLException {
        ResultSet rs = ps2.executeQuery();
        ArrayList<stdexamdetails> al = new ArrayList<>();
        while (rs.next()) {
            stdexamdetails obj = new stdexamdetails();
            obj.setExamId(rs.getString(1));
            obj.setExamTitle(rs.getString(2));
            obj.setExamDateTime(rs.getTimestamp(3).toString());
            obj.setExamstatus(rs.getString("status"));
            al.add(obj);
        }
        return al;
    }

    public static boolean enrollStd(EnrollDTO o) throws SQLException {
        ps3.setString(1, o.getExamid());
        ps3.setString(2, o.getEmail());
        ps3.setString(3, o.getStatus());
        return (ps3.executeUpdate() != 0);
    }

    public static String getStatus(String e, String f) throws SQLException {
        ps4.setString(1, e);
        ps4.setString(2, f);
        ResultSet rs = ps4.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }
    }

    public static testdetail getExamByID(String exid) throws SQLException {
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

    public static boolean updateEnrollTableStatus(String examid, String email) throws SQLException {
        ps6.setString(1, email);
        ps6.setString(2, examid);
        return (ps6.executeUpdate() != 0);
    }

    public static ArrayList<EnrollDTO> getAllStdByExamId(String exid) throws SQLException {
        ps7.setString(1, exid);
        ResultSet rs = ps7.executeQuery();
        ArrayList<EnrollDTO> al = new ArrayList<>();
        while (rs.next()) {
            EnrollDTO obj = new EnrollDTO();
            obj.setEmail(rs.getString(1));
            obj.setStatus(rs.getString(2));
            al.add(obj);
        }
        return al;
    }
}
