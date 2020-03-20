/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techquiz.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import techquiz.dbutil.DBConnection;
import techquiz.dto.chatmessageDTO;

public class ChatFileHandlingDAO {

    private static Statement st1,st2,st3;
    private static PreparedStatement ps, ps1;

    static {
        try {
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "wchat", null);
            if (rs.next()) {
                System.out.println("Table Created");
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table wchat(msgid number(38),utype varchar2(10),uname varchar2(50), datetime varchar2(20),msg varchar2(4000))");
                st1.executeQuery("commit");

            } else {
                System.out.println("table already created");
            }
            st2 = DBConnection.getConnection().createStatement();
            st3 = DBConnection.getConnection().createStatement();
            ps = DBConnection.getConnection().prepareStatement("insert into wchat values (?,?,?,?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select * from wchat");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean insertonemsg(chatmessageDTO obj) throws Exception {
        ResultSet rs = st2.executeQuery("select count(*) from wchat");
        rs.next();
        ps.setInt(1, rs.getInt(1)+1);
        ps.setString(2, obj.getUtype());
        ps.setString(3, obj.getUemail());
        ps.setString(4, obj.getDatetime());
        ps.setString(5, obj.getMessage());
        return (ps.executeUpdate() != 0);
    }

    public static ArrayList<chatmessageDTO> readmsg() throws Exception {
        ResultSet rs = ps1.executeQuery();
        ArrayList<chatmessageDTO> al = new ArrayList<>();
        while (rs.next()) {
            chatmessageDTO o = new chatmessageDTO();
            o.setDatetime(rs.getString("datetime"));
            o.setUemail(rs.getString("uname"));
            o.setUtype(rs.getString("utype"));
            o.setMessage(rs.getString("msg"));
            al.add(o);
        }
        return al;
    }
    
    public static chatmessageDTO readlast() throws Exception{
        ResultSet rs = st3.executeQuery("select uname,datetime,msg,utype from wchat where msgid = (select max(msgid) from wchat)");
        rs.next();
        chatmessageDTO obj = new chatmessageDTO();
        obj.setDatetime(rs.getString(2));
        obj.setMessage(rs.getString(3));
        obj.setUemail(rs.getString(1));
        obj.setUtype(rs.getString(4));
        return obj;
    }

}
