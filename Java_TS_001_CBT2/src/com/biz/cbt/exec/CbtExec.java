package com.biz.cbt.exec;

import com.biz.cbt.service.CbtService;
import com.biz.cbt.service.PersonTakeTestService;

public class CbtExec {

	public static void main(String[] args) {
		CbtService cbts=new CbtService();
		PersonTakeTestService ptts=new PersonTakeTestService();
		
		cbts.viewAllList();
		ptts.takeTestMenu();
	}

}
