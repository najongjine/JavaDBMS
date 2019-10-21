package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.persistence.domain.DeptDTO;
import com.biz.grade.persistence.domain.StudentDTO;

public abstract class DeptDao {
	protected Connection dbConn=null;
	
	public DeptDao() {
		dbConn=DBConnection.getDBConnection();
	}
	
	
	public abstract List<DeptDTO> selectAll();
	public abstract DeptDTO findById(String d_num);
	public abstract List<DeptDTO> findByName(String d_name);
	public abstract int insert(DeptDTO deptDTO);
	public abstract int update(DeptDTO deptDTO);
	public abstract int delete(String d_num);
	
}
