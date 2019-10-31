package com.biz.rent.dao;

import java.util.List;

import com.biz.rent.persistence.BookDTO;

public interface BookDao {
	public List<BookDTO> selectAll();
	public BookDTO findById(String b_code);
	public List<BookDTO> findByBookName(String b_name);
	public List<BookDTO> findByAuthName(String b_auther);
	public List<BookDTO> findByCompName(String b_comp);
	public String getMaxCode();
	public int insert(BookDTO bookDTO);
	public int update(BookDTO bookDTO);
	public int delete(String b_code);
}
