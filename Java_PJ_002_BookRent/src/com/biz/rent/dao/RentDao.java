package com.biz.rent.dao;

import java.util.List;

import com.biz.rent.persistence.RentDTO;

public interface RentDao {
	public List<RentDTO> selectAll();
	public RentDTO findById(long rent_seq);
	public RentDTO findByBCode(String rent_bcode);
	public RentDTO findByUCode(String rent_ucode);
	public int insert(RentDTO rentDTO);
	public int update(RentDTO rentDTO);
	public int delete(long rent_seq);
}
