package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookDeleteEx01 {
	public static void main(String[] args) {
		BookServiceV1 bs=new BookServiceV1();
		BookCUDServiceV1 bCUD=new BookCUDServiceV1();
		
		bs.viewBookList();
		bCUD.deleteBook();
	}

}
