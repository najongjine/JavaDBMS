package com.biz.iolist.service.pro;

import java.util.List;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV2 extends ProductServiceV1 {
	public void searchPName() {
		System.out.println("검색할 상품명(Enter:전체) >> ");
		String p_name=scanner.nextLine();
		
		List<ProductDTO> proList=null;
		if(p_name.trim().isEmpty()) {
			proList=proDao.selectAll();
		}else {
			proList=proDao.findByPName(p_name);
		}
		for(ProductDTO dto:proList) {
			System.out.println(dto.toString());
		}
	}
}
