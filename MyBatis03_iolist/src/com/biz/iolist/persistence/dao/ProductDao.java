package com.biz.iolist.persistence.dao;

import java.util.List;

import com.biz.iolist.persistence.ProductDTO;

public interface ProductDao {
	public List<ProductDTO> selectAll();
	public ProductDTO findById(String strip_code);
	public List<ProductDTO> findByPName(String p_name);
	public int insert(ProductDTO productDTO);
	public int update(ProductDTO productDTO);
	public int delete(String strip_code);
}
