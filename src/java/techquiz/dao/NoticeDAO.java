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
import techquiz.dto.NoticeDTO;

/**
 *
 * @author PC
 */
public class NoticeDAO {
    private static Statement st,st1,st2;
    private static PreparedStatement ps,ps1;
    static{
        try{  
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "notices", null);
            if(rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table notices(nid varchar2(10),email varchar2(30),message varchar2(4000),pdate date default sysdate)");
                System.out.println("table created");  
                st1.executeQuery("commit");
            }
            else{
                System.out.println("table already created");
            }
            st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ps = DBConnection.getConnection().prepareStatement("insert into notices (nid,email,message) values (?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("delete from notices where nid = ?");
            st2 = DBConnection.getConnection().createStatement();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    public static String getNewQuestionID()throws SQLException{
        ResultSet rs = st.executeQuery("select nid from notices");       
        if(rs.last()){
            String s = rs.getString(1);
            int i = Integer.parseInt(s.substring(1));
            return "N"+(i+1);
        }
        return "N1000";
    }
    
    public static boolean insertNotice(String msg,String email) throws SQLException{
        ps.setString(1,getNewQuestionID());
        ps.setString(3,msg);
        ps.setString(2, email);
        return ps.executeUpdate()!=0;
    }
    
    public static ArrayList<NoticeDTO> getAllNotice()throws SQLException{
         
        ResultSet rs = st2.executeQuery("select fname,sname,message,pdate,u.email as eml,nid from notices n inner join user_details u on u.email = n.email");
        ArrayList<NoticeDTO> obj = new ArrayList<>();
        while(rs.next()){
            NoticeDTO o = new NoticeDTO();
            o.setMessage(rs.getString("message"));
            o.setDate(rs.getString("pdate"));
            o.setFname(rs.getString("fname"));
            o.setSname(rs.getString("sname"));
            o.setEmail(rs.getString("eml"));
            o.setNid(rs.getString("nid"));
            obj.add(o);
        }
        return obj;
    }
    
    public static boolean deleteSingleNotice(String nid) throws SQLException{
        ps1.setString(1, nid);
        return ps1.executeUpdate()!=0;
    }
}
