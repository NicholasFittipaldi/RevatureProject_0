package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection {

private static Connection con = null;
	
	public static Connection getConnection() {
		
		if (con == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				Properties props = new Properties();
				FileInputStream fis = new FileInputStream("src/main/resources/connection.properties");
				props.load(fis);
				
				String endpoint = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				con = DriverManager.getConnection(endpoint, username, password);
				
				return con;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			catch (SQLException e) {
				e.printStackTrace();
				return null;
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} 
			catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		else 
			return con;
	}
}
