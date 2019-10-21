package com.biz.oracle.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection dbConn=null;
	
	static {
		String jdbcDrive="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="user4";
		String pass="user4";
		
		try {
			Class.forName(jdbcDrive);
			dbConn=DriverManager.getConnection(url, user, pass);
			System.out.println("DB Connection Complete");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getDBConnection() {
		return dbConn;
	}
}
