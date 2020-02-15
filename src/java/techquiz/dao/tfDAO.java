
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import techquiz.dbutil.DBConnection;
import techquiz.dto.tfDTO;

public class tfDAO {
    private static PreparedStatement ps,ps1;
    static{
        try{   
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
