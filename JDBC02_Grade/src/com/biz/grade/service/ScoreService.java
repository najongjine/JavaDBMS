package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.utils.DBContract;

public  abstract class ScoreService {
	protected Connection dbConn=null;
	
	protected void dbConnection() {
		try {
			Class.forName(DBContract.DBCONN.JDBCDRIVER);
			dbConn=DriverManager.getConnection(DBContract.DBCONN.URL, DBContract.DBCONN.USER,
					DBContract.DBCONN.PASSWORD);
			System.out.println("DB connection complete");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver not Found!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DBMS connection failed!");
		}
	}
	
	//CRUD
	public abstract int insert(ScoreDTO scoreDTO);
	public abstract List<ScoreDTO> selectAll();
	public abstract ScoreDTO findById(long id);
	public abstract List<ScoreDTO> findByName(String name);
	public abstract List<ScoreDTO> findBySubject(String subject);
	public abstract int update(ScoreDTO scoreDTO);
	public abstract int delete(long id);
}
