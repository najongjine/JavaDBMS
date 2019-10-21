package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.StudentDTO;

public abstract class StudentDao {
	protected Connection dbConn=null;
	
	public StudentDao() {
		dbConn=DBConnection.getDBConnection();
	}
	
	public abstract List<StudentDTO> selectAll();
	public abstract StudentDTO findById(String st_num);
	public abstract List<StudentDTO> findByName(String st_name);
	public abstract List<StudentDTO> findByGrade(int st_grade);
	public abstract int insert(StudentDTO studentDTO);
	public abstract int update(StudentDTO studentDTO);
	public abstract int delete(String st_num);
	
}
