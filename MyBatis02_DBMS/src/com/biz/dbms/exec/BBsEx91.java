package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV1;

public class BBsEx91 {

	public static void main(String[] args) {
		BBsServiceV1 bbs=new BBsServiceV1();
		
		bbs.writeBBS();
		bbs.viewBBsList();

	}

}
