package com.biz.oracle.service;

import java.util.List;
import java.util.Scanner;

import com.biz.oracle.persistence.BookDTO;
import com.biz.oracle.persistence.dao.BookDao;
import com.biz.oracle.persistence.dao.BookDaoImp;

public class BookServiceV1 {
	BookDao bookDao=null;
	Scanner scanner=null;
	
	public BookServiceV1() {
		super();
		// TODO Auto-generated constructor stub
		scanner=new Scanner(System.in);
		bookDao=new BookDaoImp();
	}
	private void viewList(List<BookDTO> bookList) {
		System.out.println("==========================================");
		System.out.println("전체 도서 리스트V!");
		System.out.println("---------------------------------------------");
		System.out.println("코드\t도서명\t출판사\t저자\t가격");
		for(BookDTO dto:bookList) {
			System.out.printf("%s\t%s\t%s\t%s\t%d\n",dto.getB_code()
					,dto.getB_name(),dto.getB_comp(),dto.getB_writer(),dto.getB_price());
		}
		System.out.println("===============================================");
	}//end
	public void viewBookList() {
		List<BookDTO> bookList=bookDao.selectALL();
		viewList(bookList);
		
	}//end
	public void searchBookName(boolean bConti) {
		String b_name=null;
		List<BookDTO> bookList=null;
		while(true) {
			if(searchBookName().trim().length()>0) break;
		}
		
	}//end
	public String searchBookName() {
		String b_name=null;
			
		System.out.print("검색할 도서의 제목 (-Q:quit) >> ");
		b_name=scanner.nextLine();
		searchBookName(b_name);
		return b_name;
	}//end
	public boolean searchBookName(String b_name) {
		
		List<BookDTO> bookList=null;
			
		if(b_name.equalsIgnoreCase("-Q")) return true;
		bookList=bookDao.findByName(b_name);
			
		if(bookList==null || bookList.size()<1) {
			System.out.println("찾는 도서명이 없음!");
			return false;
		}
		viewList(bookList);
		return true;
	}//end
	public void searchBookPrice() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				System.out.print("찾고자 하는 시작 가격대 (-Q:quit) >> ");
				String strSPrice=scanner.nextLine();
				if(strSPrice.equalsIgnoreCase("-Q")) break;
				int sprice=Integer.valueOf(strSPrice);
				
				System.out.print("찾고자 하는 최대 가격대 (-Q:quit) >> ");
				String strEPrice=scanner.nextLine();
				if(strEPrice.equalsIgnoreCase("-Q")) break;
				
				int eprice=Integer.valueOf(strEPrice);
				
				List<BookDTO> bookList=bookDao.findByPrice(sprice, eprice);
				viewList(bookList);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자로만 입력해 주세요");
				continue;
			}
			
		}
	}
	
}
