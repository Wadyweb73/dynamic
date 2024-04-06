package database;

import java.sql.*;

public class DBConnection {
    private String url;
    private String user;
    private String password;

    private static Connection connection;

    public DBConnection() {
        url      = "jdbc:mysql://localhost:3306/oficina";
        user     = "root";
        password = "";
    }

    public static Connection getConexao() {
        DBConnection dbParams = new DBConnection();
        
        try {
            if(connection == null) {
                connection = DriverManager.getConnection(dbParams.url, dbParams.user, dbParams.password);
                return connection;
            }
            else {
                return connection;
            }
        }     
        catch(SQLException e) {
            e.getMessage();
            return null;
        }    
    }
}