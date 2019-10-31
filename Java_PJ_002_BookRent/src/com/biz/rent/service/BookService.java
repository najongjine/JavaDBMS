package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.DBConnection.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.dao.RentDao;
import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;

public class BookService {
	protected BookDao bookDao=null;
	SqlSession sqlSession=null;
	protected Scanner scanner;
	
	public BookService() {
		sqlSession = DBConnection.getSqlSessionFactory().
				openSession(true);
		bookDao=sqlSession.getMapper(BookDao.class);
		scanner=new Scanner(System.in);
	}
	public void menu() {
		String strMenu="";
		while(true) {
			System.out.print("1. 전체 조회 2. 도서제목 조회  3.도서추가 4.도서수정 5.도서삭제 0. 종료 >> ");
			strMenu=scanner.nextLine();
			if(strMenu.equalsIgnoreCase("1")) {
				viewAllBooks();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				viewBooksByName();
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				insertBook();
			}
			else if(strMenu.equalsIgnoreCase("4")) {
				updateBook();
			}
			else if(strMenu.equalsIgnoreCase("5")) {
				deleteBook();
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				return;
			}else {
				System.out.println("book 매뉴 선택을 잘못하셨습니다!!");
			}
			break;
		}
	
	}
	public void viewAllBooks() {
		List<BookDTO> bookList=bookDao.selectAll();
		for(BookDTO dto:bookList) {
			System.out.println(dto.toString());
		}
	}
	public void viewBooksByName() {
		String b_name="";
		System.out.print("찾으려는 도서명 >> ");
		b_name=scanner.nextLine();
		List<BookDTO> bookList=bookDao.findByBookName(b_name);
		if(bookList.size()<1) {
			System.out.println("찾으시려는 도서명이 없습니다!!");
			return;
		}
		for(BookDTO dto:bookList) {
			System.out.println(dto.toString());
		}
	}
	public void viewBooksByAuth() {
		String b_auther="";
		System.out.print("찾으려는 도서명 >> ");
		b_auther=scanner.nextLine();
		List<BookDTO> bookList=bookDao.findByAuthName(b_auther);
		if(bookList.size()<1) {
			System.out.println("찾으시려는 도서명이 없습니다!!");
			return;
		}
		for(BookDTO dto:bookList) {
			System.out.println(dto.toString());
		}
	}
	public void viewBooksByComp() {
		String b_comp="";
		System.out.print("찾으려는 도서명 >> ");
		b_comp=scanner.nextLine();
		List<BookDTO> bookList=bookDao.findByCompName(b_comp);
		if(bookList.size()<1) {
			System.out.println("찾으시려는 도서명이 없습니다!!");
			return;
		}
		for(BookDTO dto:bookList) {
			System.out.println(dto.toString());
		}
	}
	
	public void insertBook() {
		List<BookDTO> bookList=null;
		BookDTO bookDTO=null;
		String b_code="",_maxBcode="";
		String b_name="";
		String b_auther="";
		String b_comp="";
		int intBCode=-1;
		int b_year=-1;
		int b_iprice=-1;
		int b_rprice=-1;
		
		System.out.println("==================================================");
		System.out.println("도서정보 등록 시작");
		System.out.println("----------------------------------------------------");
		
		_maxBcode=bookDao.getMaxCode();
		intBCode=Integer.valueOf(_maxBcode.substring(2));
		intBCode++;
		b_code=_maxBcode.substring(0, 2) + String.format("%04d", intBCode);
		System.out.println(b_code);
		
		while(true) {
			System.out.print("도서 제목 (-Q:quit) >> ");
			b_name=scanner.nextLine();
			if(b_name.equals("-Q")) return;
			bookList=bookDao.findByBookName(b_name);
			if(bookList.size()>0) {
				System.out.println("중복된 도서 이름은 등록하실수 없습니다!!");
				continue;
			}
			break;
		}
		
		System.out.print("저자  (-Q:quit) >> ");
		b_auther=scanner.nextLine();
		if(b_auther.equals("-Q")) return;
		
		System.out.print("출판사  (-Q:quit) >> ");
		b_comp=scanner.nextLine();
		if(b_comp.equals("-Q")) return;
		
		while(true) {
			System.out.println("구입연도(예:20190101)  (-Q:quit) >> ");
			String strYear=scanner.nextLine();
			if(strYear.equals("-Q")) return;
			if(strYear.length()<8 || strYear.length()>8) {
				System.out.println("날짜는 숫자로만 8자리 입력해 주세요");
				continue;
			}
			try {
				b_year=Integer.valueOf(strYear);
			} catch (Exception e) {
				System.out.println("날짜는 숫자로만 8자리 입력해 주세요");
				continue;
			}
			break;
		}//end 구입연도
		while(true) {
			System.out.print("구입 가격 (-Q:quit) >> ");
			String strIPrice=scanner.nextLine();
			if(strIPrice.equals("-Q")) return;
			
			try {
				b_iprice=Integer.valueOf(strIPrice);
			} catch (Exception e) {
				System.out.println("가격은 숫자만 입력해주세요!!");
				continue;
			}
			break;
		}//end irpice
		while(true) {
			System.out.print("대여 가격 (-Q:quit) >> ");
			String strRPrice=scanner.nextLine();
			if(strRPrice.equals("-Q")) return;
			
			try {
				b_rprice=Integer.valueOf(strRPrice);
			} catch (Exception e) {
				System.out.println("가격은 숫자만 입력해주세요!!");
				continue;
			}
			break;
		}//end rptrice
		
		if(b_year< 0 || b_iprice<0 || b_rprice<0) {
			System.out.println("날짜나 구입가격이나 대여가격이 잘못됬습니다!!");
			System.out.println("도서 정보 등록 실패!!");
			return;
		}
		
		bookDTO=BookDTO.builder().b_auther(b_auther).b_code(b_code).b_comp(b_comp)
		.b_iprice(b_iprice).b_name(b_name).b_rprice(b_rprice).b_year(b_year)
		.build();
		
		int ret=bookDao.insert(bookDTO);
		if(ret>0) System.out.println("도서등록 성공");
		else System.out.println("도서등록 실패!!");
	}//end insert
	
	public void updateBook() {
		BookDTO bookDTO=null;
		String b_code="",_maxBcode="";
		String b_name="";
		String b_auther="";
		String b_comp="";
		int intBCode=-1;
		int b_year=-1;
		int b_iprice=-1;
		int b_rprice=-1;
		System.out.println("==============================================");
		System.out.println("도서정보 수정");
		System.out.println("------------------------------------------------");
		while(true) {
			System.out.print("수정할 도서의 도서코드 (-Q:quit) >> ");
			b_code=scanner.nextLine();
			if(b_code.equals("-Q")) return;
			bookDTO=bookDao.findById(b_code);
			if(bookDTO==null) {
				System.out.println("찾으시는 도서 코드가 없습니다!");
				continue;
			}
			break;
		}
		System.out.println(bookDTO.toString());
		
		System.out.printf("수정할 제목(현재제목:%s) (-Q:quit) >> ",bookDTO.getB_name());
		b_name=scanner.nextLine();
		if(b_name.equals("-Q")) return;
		if(!b_name.trim().isEmpty()) {
			bookDTO.setB_name(b_name);
		}
		
		System.out.printf("수정할 저자(현재저자:%s) (-Q:quit) >> ",bookDTO.getB_auther());
		b_auther=scanner.nextLine();
		if(b_auther.equals("-Q")) return;
		if(!b_auther.trim().isEmpty()) {
			bookDTO.setB_name(b_auther);
		}
		
		System.out.printf("수정할 출판사(현재출판사:%s) (-Q:quit) >> ",bookDTO.getB_comp());
		b_comp=scanner.nextLine();
		if(b_comp.equals("-Q")) return;
		if(!b_comp.trim().isEmpty()) {
			bookDTO.setB_name(b_comp);
		}
		
		while(true) {
			System.out.printf("수정할 구입년도(현재구입년도:%s) (-Q:quit) >> ",bookDTO.getB_year());
			String strYear=scanner.nextLine();
			if(strYear.equals("-Q")) return;
			if(strYear.trim().isEmpty())break;
			if(strYear.length()<8 || strYear.length()>8) {
				System.out.println("날짜는 숫자 8자리로 입력!! 예:20190101");
				continue;
			}
			if(!strYear.trim().isEmpty()) {
				try {
					b_year=Integer.valueOf(strYear);
					bookDTO.setB_iprice(b_year);
				} catch (Exception e) {
					System.out.println("가격은 숫자로만 입력!!");
					continue;
				}
			}
			break;
		}//end 구입년도
		
		while(true) {
			System.out.printf("수정할 구입가격(현재구입가격:%s) (-Q:quit) >> ",bookDTO.getB_iprice());
			String strIprice=scanner.nextLine();
			if(strIprice.equals("-Q")) return;
			if(!strIprice.trim().isEmpty()) {
				try {
					b_iprice=Integer.valueOf(strIprice);
					bookDTO.setB_iprice(b_iprice);
				} catch (Exception e) {
					System.out.println("가격은 숫자로만 입력!!");
					continue;
				}
			}
			break;
		}//end 구입가격
		
		while(true) {
			System.out.printf("수정할 대여가격(현재대여가격:%s) (-Q:quit) >> ",bookDTO.getB_rprice());
			String strRprice=scanner.nextLine();
			if(strRprice.equals("-Q")) return;
			if(!strRprice.trim().isEmpty()) {
				try {
					b_rprice=Integer.valueOf(strRprice);
					bookDTO.setB_iprice(b_rprice);
				} catch (Exception e) {
					System.out.println("가격은 숫자로만 입력!!");
					continue;
				}
			}
			break;
		}//end 대여가격
		
		int ret=bookDao.update(bookDTO);
		if(ret>0) System.out.println("도서수정 성공");
		else System.out.println("도서수정 실패!!");
	}//end update
	
	public void deleteBook() {
		BookDTO bookDTO=null;
		String b_code="";
		
		while(true) {
			System.out.print("삭제할 도서의 코드 (-Q:quit) >> ");
			b_code=scanner.nextLine();
			if(b_code.equals("-Q")) return;
			bookDTO=bookDao.findById(b_code);
			if(bookDTO==null) {
				System.out.println("삭제하려는 도서의 코드가 업습니다!!");
				continue;
			}
			if(checkIsBookRented(bookDTO.getB_code())) {
				System.out.println("현재 책은 대여중이라서 삭제가 불가능 합니다...");
				return;
			}
			int ret=bookDao.delete(b_code);
			if(ret>0) System.out.println("도서삭제 성공");
			else System.out.println("도서삭제 실패!!");
			break;
		}//도서코드 end
		
	}//end delete
	public boolean checkIsBookRented(String rent_bcode) {
		RentDao rentDao=sqlSession.getMapper(RentDao.class);
		RentDTO rentDTO=rentDao.findByBCode(rent_bcode);
		if(rentDTO==null) return false;
		if(rentDTO.getRent_retur_yn().equalsIgnoreCase("y")) {
			return true;
		}
		return false;
	}
}
