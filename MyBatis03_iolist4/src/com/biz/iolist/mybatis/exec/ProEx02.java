package com.biz.iolist.mybatis.exec;

import com.biz.iolist.service.pro.ProductServiceV2;

public class ProEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductServiceV2 ps=new ProductServiceV2();
		ps.searchPName();
		ps.proUpdate();
	}

}
