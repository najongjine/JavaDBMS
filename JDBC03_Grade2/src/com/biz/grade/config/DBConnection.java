package com.biz.grade.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.biz.grade.config.DBContract.DBConn;

public class DBConnection {
	private static Connection dbConn = null;

	/*static 생성자
	 * 프로젝트가 시작됨과 동시에
	 * JVM에 의해서 자동으로 실행되는 클래스와 무관한
	 * 전역 생성자 method.
	 */
	static {
		String jdbcDriver = DBConn.JdbcDriver;
		String url = DBConn.URL;
		String user = DBConn.USER;
		String password = DBConn.PASSWORD;

		try {
			Class.forName(jdbcDriver);
			dbConn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//singletone 기법과 유사
	public static Connection getDBConnection() {
		return dbConn;
	}
}
