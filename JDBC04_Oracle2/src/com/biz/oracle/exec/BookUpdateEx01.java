package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookUpdateEx01 {
/*
 * 도서명을 입력 받아서 리스트를 보여주고 
 * 수정할 도서코드를 입력받고
 * 해당하는 도서를 수정
 * 1. 각 항목을 보여주고
 *  새로운 값을 입력하면 수정.
 *  그냥 enter 입력시 그대로 유지
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookCUDServiceV1 bCUD=new BookCUDServiceV1();
		BookServiceV1 bs=new BookServiceV1();
		
		String b_name=bs.searchBookName();
		if(b_name.equalsIgnoreCase("-Q")) {
			System.out.println("도서정보 변경 업무 종료");
		}else {
			bCUD.updateBook();
		}
		
	}

}
