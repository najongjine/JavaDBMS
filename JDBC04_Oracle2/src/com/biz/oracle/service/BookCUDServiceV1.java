package com.biz.oracle.service;

import java.util.Scanner;

import com.biz.oracle.persistence.BookDTO;
import com.biz.oracle.persistence.dao.BookDao;
import com.biz.oracle.persistence.dao.BookDaoImp;

public class BookCUDServiceV1 {
	private BookDao bookDao=null;
	private Scanner scanner=null;

	public BookCUDServiceV1() {
		super();
		// TODO Auto-generated constructor stub
		scanner=new Scanner(System.in);
		bookDao=new BookDaoImp();
	}
	
	public void inputBook() {
		while(true) {
			System.out.println("==========================================");
			System.out.println("도서정복 등록");
			System.out.println("-------------------------------------------");
			
			String b_name=null;
			while(true) {
				System.out.print("도서명 (-Q)>> ");
				b_name=scanner.nextLine();
				if(b_name.equalsIgnoreCase("-Q")) break;
				if(b_name.isEmpty()) {
					System.out.println("도서명은 반드시 입력 해야합니다!");
					continue;
				}
				break;
			}
			if(b_name.equalsIgnoreCase("-Q")) break;
			
			System.out.print("출판사 (-Q)>> ");
			String b_comp=scanner.nextLine();
			if(b_comp.equalsIgnoreCase("-Q")) break;
			
			System.out.print("저자 (-Q)>> ");
			String b_writer=scanner.nextLine();
			if(b_writer.equalsIgnoreCase("-Q")) break;
			
			String strB_price=null;
			int b_price=0;
			while(true) {
				try {
					System.out.print("가격 (-Q)>> ");
					strB_price=scanner.nextLine();
					if(strB_price.equalsIgnoreCase("-Q")) break;
					b_price=Integer.valueOf(strB_price);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("가격은 숫자로만 입력!");
					continue;
				}
				break;
			}
			if(strB_price.equalsIgnoreCase("-Q")) break;
			
			BookDTO bookDTO=BookDTO.builder()
					.b_name(b_name)
					.b_comp(b_comp)
					.b_writer(b_writer)
					.b_price(b_price)
					.build();
			int ret=bookDao.insert(bookDTO);
			if(ret>0)
				System.out.println("도서정보 저장 완료");
			else
				System.out.println("도서정보 저장 실패");
		}
	}

	public void deleteBook() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.print("삭제할 책의 b_code >> (-Q)");
			String b_code=scanner.nextLine();
			if(b_code.equalsIgnoreCase("-Q")) break;
			
			BookDTO bookDTO=bookDao.findById(b_code);
			if(bookDTO==null) {
				System.out.println("도서 코드가 없습니다!");
				continue;
			}
			int ret=bookDao.delete(b_code);
			if(ret>0)
				System.out.println("책 정보 삭제 완료");
			else
				System.out.println("책 정보 삭제 실패!");
		}
	}
	public void updateBook() {
		// TODO Auto-generated method stub
		System.out.println("=================================================");
		System.out.println("도서정보 수정");
		System.out.println("-------------------------------------------------");
		System.out.print("수정할 도서커드(-Q) >> ");
		String b_code=scanner.nextLine();
		if(b_code.equalsIgnoreCase("-Q")) {
			return ;
		}
		BookDTO bookDTO=bookDao.findById(b_code);
		System.out.printf("변경할 도서명(%s) >> ",bookDTO.getB_name());
		String b_name=scanner.nextLine();
		if(b_name.trim().length()>0) {
			bookDTO.setB_name(b_name.trim());
		}
		
		System.out.printf("변경할 저자(%s) >> ",bookDTO.getB_writer());
		String b_writer=scanner.nextLine();
		if(b_writer.trim().length()>0) {
			bookDTO.setB_writer(b_writer.trim());
		}
		
		System.out.printf("변경할 출판사(%s) >> ", bookDTO.getB_comp());
		String b_comp=scanner.nextLine();
		if(b_comp.trim().length()>0) {
			bookDTO.setB_comp(b_comp.trim());
		}
		
		while(true) {
			System.out.printf("변경할 가격(%d) >> ", bookDTO.getB_price());
			String strPrice=scanner.nextLine();
			if(strPrice.trim().length()<1) break;
			try {
				int b_price=Integer.valueOf(strPrice);
				bookDTO.setB_price(b_price);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자로만 입력하십시오!");
				continue;
			}
		}
		
		int ret=bookDao.update(bookDTO);
		if(ret>0)System.out.println("업데이트 성공");
		else System.out.println("업데이트 실패!");
	}
	
}
