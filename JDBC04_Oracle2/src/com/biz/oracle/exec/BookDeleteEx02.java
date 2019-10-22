package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookDeleteEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookServiceV1 bs=new BookServiceV1();
		BookCUDServiceV1 bCUD=new BookCUDServiceV1();
		
		bs.searchBookName();
		bCUD.deleteBook();
		bs.viewBookList();
	}

}
