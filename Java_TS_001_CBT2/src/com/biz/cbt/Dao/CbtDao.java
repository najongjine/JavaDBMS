package com.biz.cbt.Dao;

import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public interface CbtDao {
	public List<CbtDTO> selectAll();
	public CbtDTO findById(long cb_seq);
	public int insert(CbtDTO cbtDTO);
	public int delete(long cb_seq);
	public int update(CbtDTO cbtDTO);
}
