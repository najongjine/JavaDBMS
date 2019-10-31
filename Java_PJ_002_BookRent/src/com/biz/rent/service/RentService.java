package com.biz.rent.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.DBConnection.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.dao.RentDao;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;
import com.biz.rent.persistence.UserDTO;

public class RentService {
	protected RentDao rentDao = null;
	SqlSession sqlSession = null;
	BookService bs;
	UserService us;
	protected Scanner scanner;
	
	
	public RentService() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		rentDao = sqlSession.getMapper(RentDao.class);
		bs=new BookService();
		us=new UserService();
		scanner = new Scanner(System.in);
	}
	
	public void menu() {
		String strMenu="";
		while(true) {
			System.out.print("1. 대여 등록 2. 반납 처리 3.삭제 0. 종료 >> ");
			strMenu=scanner.nextLine();
			if(strMenu.equalsIgnoreCase("1")) {
				rentBook();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				returnBook();
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				delete();
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				return;
			}else {
				System.out.println("rent 매뉴 선택을 잘못하셨습니다!!");
				continue;
			}
			break;
		}
	
	}
	public void viewAllRents() {
		List<RentDTO> rentList=rentDao.selectAll();
		if(rentList.size()<1) {
			System.out.println("대여 테이블에 정보가 하나도 없습니다!!");
			return;
		}
		for(RentDTO dto:rentList) {
			System.out.println(dto.toString());
		}
	}
	public void rentBook() {
		RentDTO rentDTO=null;
		UserDTO userDTO=null;
		UserDao userDao=sqlSession.getMapper(UserDao.class);
		BookDTO bookDTO=null;
		BookDao bookDao=sqlSession.getMapper(BookDao.class);
		LocalDate localDate=null;
		long rent_seq=-1;
		String rent_date="";
		String rent_return_date="";
		String rent_bcode="";
		String rent_ucode="";
		String rent_retur_yn="N";
		int rent_point=0;
		System.out.println("================================================");
		System.out.println("책 대여 시작");
		System.out.println("------------------------------------------------");
		while(true) {
			us.findUserByName();
			System.out.print("대여할 회원의 code (-Q:quit) >> ");
			rent_ucode=scanner.nextLine();
			if(rent_ucode.equals("-Q")) return;
			if(!checkIsUserExist(rent_ucode)) {
				System.out.println("회원 code가 잘못됬거나 존재하지 않습니다!!");
				continue;
			}
			break;
		}//회원 존재 검사
		
		while(true) {
			bs.viewBooksByName();
			System.out.print("대여할 도서 code (-Q:quit) >> ");
			rent_bcode=scanner.nextLine();
			if(rent_bcode.equals("-Q")) return;
			if(!checkIsBookRentable(rent_bcode)) {
				System.out.println("도서 code가 잘못됬거나 이미 대여중입니다!!");
				continue;
			}
			break;
		}//도서 존재,중복 검사
		
		while(true) {
			System.out.print("대여 날짜(예:2019-01-01) (-Q:quit) >> ");
			rent_date=scanner.nextLine();
			if(rent_date.equals("-Q")) return;
			if(rent_date.length()<10 || rent_date.length()<10) {
				System.out.println("날짜는 2019-01-01 이런 형식으로 입력해 주세요!!");
				continue;
			}
			try {
				localDate=LocalDate.parse(rent_date);
			} catch (Exception e) {
				System.out.println("날짜는 2019-01-01 이런 형식으로 입력해 주세요!!");
				continue;
			}
			break;
		}
		
		rent_return_date=localDate.plusDays(14).toString();
		rentDTO = RentDTO.builder().rent_bcode(rent_bcode).rent_date(rent_date)
		.rent_point(rent_point).rent_retur_yn(rent_retur_yn)
		.rent_return_date(rent_return_date).rent_ucode(rent_ucode).build();
		
		int ret=rentDao.insert(rentDTO);
		if(ret>0) System.out.println("도서 대여 성공");
		else System.out.println("도서 대여 실패!!");
		
	}//end 도서 대여
	
	public void returnBook() {
		RentDTO rentDTO=null;
		UserDTO userDTO=null;
		UserDao userDao=sqlSession.getMapper(UserDao.class);
		BookDTO bookDTO=null;
		BookDao bookDao=sqlSession.getMapper(BookDao.class);
		String rent_date="";
		String rent_return_date="",_rent_return_date="";
		String rent_bcode="";
		String rent_ucode="";
		String rent_retur_yn="";
		int rent_point=0;
		long rent_seq=-1;
		System.out.println("================================================");
		System.out.println("도서 반납 시작");
		System.out.println("-------------------------------------------------");
		viewAllRents();
		while(true) {
			System.out.print("반납하는 SEQ (-Q:quit) >> ");
			String strSeq=scanner.nextLine();
			if(strSeq.equals("-Q")) return;
			try {
				rent_seq=Long.valueOf(strSeq);
				rentDTO=rentDao.findById(rent_seq);
				if(rentDTO==null) {
					System.out.println("찾으시는 SEQ가 없거나 잘못됬습니다!!");
					continue;
				}
			} catch (Exception e) {
				System.out.println("SEQ는 숫자로만 입력!!");
				continue;
			}
			
			break;
		}
		
		while(true) {
			System.out.print("반납일을 입력하세요(예:2019-01-01) (-Q:quit) >> ");
			_rent_return_date=scanner.nextLine();
			if(_rent_return_date.equals("-Q")) return;
			if(_rent_return_date.length()<10 || _rent_return_date.length()<10) {
				System.out.println("날짜는 2019-01-01 이런 형식으로 입력해 주세요!!");
				continue;
			}
			break;
		}
		
		if(_rent_return_date.compareToIgnoreCase(rentDTO.getRent_return_date())>0) {
			rentDTO.setRent_retur_yn("Y");
			System.out.println("반납 예정일이 지났습니다. 포인트 계산은 없습니다.");
			rentDao.update(rentDTO);
			return;
		}
		rentDTO.setRent_retur_yn("Y");
		rentDTO.setRent_point(rentDTO.getRent_point()+5);
		System.out.println("반납 예정일을 지켰습니다. 포인트가 쌓였습니다.");
		rentDao.update(rentDTO);
	}// end return;
	public void delete() {
		long rent_seq=-1;
		RentDTO rentDTO=null;
		while(true) {
			System.out.print("삭제할 데여 리스트의 SEQ (-Q)>> ");
			String strSeq=scanner.nextLine();
			if(strSeq.equals("-Q")) return;
			try {
				rent_seq=Long.valueOf(strSeq);
			} catch (Exception e) {
				System.out.println("SEQ는 숫자로만 입력하세요!!");
				continue;
			}
			rentDTO=rentDao.findById(rent_seq);
			if(rentDTO==null) {
				System.out.println("삭제할 SEQ가 없습니다!!");
				break;
			}
			System.out.println(rentDTO.toString());
			System.out.print("정말 삭제하시겠습니까? (Y/N) >> ");
			String yesNO=scanner.nextLine();
			if(!yesNO.equalsIgnoreCase("Y")) {
				return;
			}
			break;
		}
		int ret=rentDao.delete(rent_seq);
		if(ret>0) System.out.println("대여 삭제 성공");
		else System.out.println("대여 삭제 실패!!");
	}//end delete
	public boolean checkIsBookRentable(String b_code) {
		BookDTO bookDTO=null;
		BookDao bookDao=sqlSession.getMapper(BookDao.class);
		bookDTO=bookDao.findById(b_code);
		if(bookDTO==null) return false;
		
		RentDTO rentDTO=null;
		rentDTO=rentDao.findByBCode(b_code);
		if(rentDTO==null) return true;
		if(rentDTO.getRent_retur_yn()!="Y") return false;
		return false;
	}
	public boolean checkIsUserExist(String u_code) {
		UserDTO userDTO=null;
		UserDao userDao=sqlSession.getMapper(UserDao.class);
		userDTO=userDao.findById(u_code);
		if(userDTO==null) return false;
		return true;
	}
}
