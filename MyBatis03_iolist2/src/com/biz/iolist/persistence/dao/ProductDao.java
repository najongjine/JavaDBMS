package com.biz.iolist.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.ProductDTO;

public interface ProductDao {
	public List<ProductDTO> selectAll();
	public ProductDTO findById(String strip_code);
	public List<ProductDTO> findByPName(String p_name);
	public ProductDTO findBySName(String p_name);
	public List<ProductDTO> findByIPrice(@Param("sprice") int sprice,@Param("eprice") int eprice);
	public String getMaxPCode();
	public int insert(ProductDTO productDTO);
	public int update(ProductDTO productDTO);
	public int delete(String strip_code);
}
