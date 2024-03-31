package database;

import java.sql.*;

public class DBConnection {
	private String user;
	private String password;
	private String url;
	private static Connection connection;
	
	public DBConnection() {
		url      = "jdbc:mysql://localhost/3306/oficina"; 
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