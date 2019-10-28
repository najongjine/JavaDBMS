package com.biz.iolist.mybatis.exec;

import com.biz.iolist.service.dept.DeptServiceV1;

public class DeptEx01 {

	public static void main(String[] args) {
		DeptServiceV1 ds= new DeptServiceV1();
		ds.viewAllList();
		ds.viewNameList();
		//ds.viewNameAndCEOList();

	}

}
