package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello oracle");
		String jdbcDrive="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userName="grade";
		String password="grade";
		
		try {
			//JVM에 driver 설치(메모리에 올림)
			/*
			 * jdbcDriver(oracle.jdbc.driver.OracleDriver.class 파일)을사용할수 있도록 
			 * jvm에 등록(load) 하라는 지시.
			 */
			Class.forName(jdbcDrive);
			
			/*
			 * url:연결주소
			 * username: db사용자
			 * password: 비번
			 * 을 사용하여 dbms와 sql을 주고받을 통로를 설정
			 */
			Connection dbConn=null;
			dbConn=DriverManager.getConnection(url,userName,password);
			System.out.println("Connection complete");
			
			//JDBC와  우리의 App같이 데이터 교환이 이루어지는 영역
			PreparedStatement pStr=null;//DB용 버퍼
			
			//연결된 통로를 통해서 SQL문을 전달하고
			//그 결과를 pStr변수에 받는다.
			String sql="select * from tbl_score";
			pStr=dbConn.prepareStatement(sql);
			
			//sql문을 실행하도록 지시
			ResultSet rst=pStr.executeQuery();
			/*
			 * 이제 rst 객체에는 DB로부터 읽어온 리스트가
			 * ResultSet 이라는 데이터 type으로 저장되어 있을것이다.
			 * 이 ResultSet은 일종의 Iterator 처럼 취급가능.
			 * rst.next() method가 한번 실행 될때마다
			 * 데이터 리스트의 앞쪽부터 한개씩 꺼내서 읽을수 있도록 준비를한다.
			 */
			while(rst.next()) {
				//읽을 준비가된 리스트 요소의 1번 칼럼의 값을 꺼내라.
				//저장된 데이터 칼럼의 값이 숫자형이면 getInt()로 읽을수 있다.
				System.out.print(rst.getInt(1)+"\t");
				System.out.print(rst.getString(2)+"\t");
				System.out.print(rst.getInt(3)+"\n");
			}
			rst.close();
			dbConn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
