
package techquiz.dao;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import techquiz.dbutil.DBConnection;
import techquiz.dto.tfDTO;

public class tfDAO {
    private static PreparedStatement ps,ps1;
    private static Statement st1;
    static{
        try{   
            DatabaseMetaData dbm = DBConnection.getConnection().getMetaData();
            ResultSet rs = dbm.getTables(null, null, "TF", null);
            if(!rs.next()) {
                st1 = DBConnection.getConnection().createStatement();
                st1.executeQuery("create table tf (qid varchar2(10),canswer varchar2(5) constraints tf_c_ck check (canswer in ('true','false')))");
//                System.out.println("table created");  
                 st1.executeQuery("commit");
            }
            ps = DBConnection.getConnection().prepareStatement("insert into tf values (?,?)");
            ps1 = DBConnection.getConnection().prepareStatement("select canswer from tf where qid = ?");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    
    public static boolean savetfQuestionAnswer(tfDTO tfobj)throws SQLException{
        ps.setString(1, tfobj.getQid());
        ps.setString(2, tfobj.getCanswer());
        return (ps.executeUpdate()!=0);
    }
    
    public static String getCorrAnswer(String qid)throws SQLException{
        ps1.setString(1, qid);
        ResultSet rs = ps1.executeQuery();
        rs.next();
        return rs.getString(1);
    }
}
