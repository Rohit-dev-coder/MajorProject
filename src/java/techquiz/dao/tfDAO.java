
package techquiz.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import techquiz.dbutil.DBConnection;
import techquiz.dto.tfDTO;

public class tfDAO {
    private static PreparedStatement ps;
    static{
        try{   
            ps = DBConnection.getConnection().prepareStatement("insert into tf values (?,?)");
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
}
