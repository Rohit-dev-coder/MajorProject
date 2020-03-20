package techquiz.dbutil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static Connection conn;

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-GGNGDNPF:1521/xe", "paildb", "pail");
            System.out.println("Connection opened");            
//            DatabaseMetaData dbm = conn.getMetaData();
//            ResultSet rs = dbm.getSchemas();
//            int fdcount = 0;
//            while(rs.next()){
//                String dbname = rs.getString(1);
//                if(dbname.equalsIgnoreCase("paildb12")){
//                    fdcount++;
//                    break;
//                }
//            }
//            if(fdcount==0){
//                Statement st = conn.createStatement();
//                st.executeQuery("grant connect to create user paildb12 identified by pail");
//                System.out.println("user created");
//                
//            }

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        
        return conn;
    }

    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection Closed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
