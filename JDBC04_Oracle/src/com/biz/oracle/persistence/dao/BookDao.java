package com.biz.oracle.persistence.dao;

import java.util.List;

import com.biz.oracle.config.DBConnection;
import com.biz.oracle.persistence.BookDTO;

public abstract class BookDao {
	/* B_CODE : findByID(String b_code)
	B_NAME : findByName(String b_name)
	B_COMP :findByComp(String b_comp)
	B_WRITER :findByWriter(String b_writer)
	B_PRICE : findByPrice(int price) | sprice~eprice */
	
	
	public abstract List<BookDTO> selectALL();
	public BookDao() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	public abstract BookDTO findById(String b_code);
	public abstract List<BookDTO> findByName(String b_name);
	public abstract List<BookDTO> findByComp(String b_comp);
	public abstract List<BookDTO> findByWriter(String b_writer);
	public abstract List<BookDTO> findByPrice(int b_price);
	public abstract List<BookDTO> findByPrice(int sprice, int eprice);
	
	public abstract int insert(BookDTO bookDTO);
	public abstract int update(BookDTO bookDTO);
	public abstract int delete(String b_code);
	
}
