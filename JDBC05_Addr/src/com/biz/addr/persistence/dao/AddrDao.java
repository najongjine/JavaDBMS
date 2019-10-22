package com.biz.addr.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.addr.DBConnection.DBConnection;
import com.biz.addr.persistence.AddrDTO;

/*
 * dbConnection 부분
selectAll();
findById(long id);
findByName(String name);
findByTel(String tel);
findByChain(String chain);
id
name
tel
addr
chain
 */
public abstract class AddrDao {
	protected Connection dbConn=null;

	public AddrDao() {
		dbConn=DBConnection.getDbConn();
	}
	
	public abstract List<AddrDTO> selectALL();
	public abstract AddrDTO findById(long id);
	public abstract List<AddrDTO> findByName(String name);
	public abstract List<AddrDTO> findByAddr(String addr);
	public abstract List<AddrDTO> findByTel(String tel);
	public abstract List<AddrDTO> findByChain(String chain);
	
	public abstract int insert(AddrDTO addrDTO);
	public abstract int update(AddrDTO addrDTO);
	public abstract int delete(long id);
}
