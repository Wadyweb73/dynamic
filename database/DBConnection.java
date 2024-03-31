package database;

import java.sql.*;

public class DBConnection {
	String user;
	String password;
	String url;
	static Connection connection;
	
	public DBConnection() {
		url      = "jdbc:mysql://localhost/3306/person"; 
		user     = "root";
		password = "";		
	} 

	public static Connection getConexao() {
		DBConnection dbcon = new DBConnection();

		try {
			if(connection == null) {
				connection = DriverManager.getConnection(dbcon.url, dbcon.user, dbcon.password);

				return connection;
			}
			else {
				return connection;
			}
		}
		catch(SQLException e) {
			e.getStackTrace();
			return null;
		}
	}
}
