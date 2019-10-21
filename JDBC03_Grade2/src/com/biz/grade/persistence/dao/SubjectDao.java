package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.DeptDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public abstract class SubjectDao {
protected Connection dbConn=null;
	
	public SubjectDao() {
		dbConn=DBConnection.getDBConnection();
	}
	
	public abstract List<SubjectDTO> selectAll();
	public abstract SubjectDTO findById(String sb_code);
	public abstract List<SubjectDTO> findByName(String sb_name);
	public abstract List<SubjectDTO> findByPro(String sb_pro);
	public abstract int insert(SubjectDTO subjectDTO);
	public abstract int update(SubjectDTO subjectDTO);
	public abstract int delete(String sb_code);
	
}
