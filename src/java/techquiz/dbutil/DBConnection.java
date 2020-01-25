
package techquiz.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static Connection conn;
    static{
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-GGNGDNPF:1521/xe","paildb","pail");
            System.out.println("Connection opened");
            }
            catch(Exception e)
        {
 
              e.printStackTrace();
        }
           
    }

    public static  Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
     try{   
         conn.close();
         System.out.println("Connection Closed");
     
     }
     catch(SQLException e)
     {
        e.printStackTrace();
     }
    }
    
    
}
