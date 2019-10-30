package com.biz.iolist.persistence.dao;

import java.util.List;

import com.biz.iolist.persistence.IolistViewVO;

public interface IolistViewDao {
	public List<IolistViewVO> selectAll();
	public IolistViewVO findById(long io_seq);
	public List<IolistViewVO> findByDcode(String io_dcode);
	public List<IolistViewVO> findByPcode(String io_pcode);
	public List<IolistViewVO> findByDName(String io_dname);
	public List<IolistViewVO> findByPName(String io_pname);
}
