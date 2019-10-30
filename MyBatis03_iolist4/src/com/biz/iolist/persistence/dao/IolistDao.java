package com.biz.iolist.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.IolistDTO;

public interface IolistDao {
	public List<IolistDTO> selectAll();
	public IolistDTO findById(long io_seq);
	public List<IolistDTO> findExistingDcodeNPName(@Param("io_dcode")String io_dcode,@Param("io_pcode")String io_pcode);
	public int insert(IolistDTO iolistDTO);
	public int update(IolistDTO iolistDTO);
	public int delete(long io_seq);
}
