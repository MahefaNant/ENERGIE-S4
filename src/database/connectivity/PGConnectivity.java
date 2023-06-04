package database.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PGConnectivity {

    private static String url = "jdbc:postgresql://localhost/energie";
    private static String user = "energie";
    private static String password = "energie";
    private static Connection con;

    private PGConnectivity(){
    }

    public static Connection getCon() throws SQLException {
        Connection conn = null;
        if(con == null){
            conn = DriverManager.getConnection(url, user, password);
        }
        return conn;
    }

    public static void close() throws SQLException {
        con.close();
    }

    public static void terminate() {
        con = null;
    }

    public static void commit() throws SQLException {
        con.commit();
    }

}
    

