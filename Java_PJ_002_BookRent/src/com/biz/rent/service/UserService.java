package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.DBConnection.config.DBConnection;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.UserDTO;

public class UserService {
	protected UserDao userDao = null;
	SqlSession sqlSession = null;
	protected Scanner scanner;

	public UserService() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		userDao = sqlSession.getMapper(UserDao.class);
		scanner = new Scanner(System.in);
	}

	public void menu() {
		String strMenu="";
		while(true) {
			System.out.print("1. 전체 조회 2. 이름 조회  3.전화번호 조회 4.회원추가 5.회원수정 0. 종료 >> ");
			strMenu=scanner.nextLine();
			if(strMenu.equalsIgnoreCase("1")) {
				viewAllUsers();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				findUserByName();
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				findUserByTel();
			}
			else if(strMenu.equalsIgnoreCase("4")) {
				insertUser();
			}
			else if(strMenu.equalsIgnoreCase("5")) {
				updateUser();
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				return;
			}else {
				System.out.println("user 매뉴 선택을 잘못하셨습니다!!");
				continue;
			}
			break;
		}
	
	}
	public void viewAllUsers() {
		List<UserDTO> userList = userDao.selectAll();

		if (userList.size() < 1) {
			System.out.println("찾으실 회원이 없습니다!!");
			return;
		}
		for (UserDTO dto : userList) {
			System.out.println(dto.toString());
		}
	}
	public void findUserByName() {
		String u_name = "";
		System.out.print("찾으실 회원의 이름 >> ");
		u_name = scanner.nextLine();
		List<UserDTO> userList = userDao.findByUserName(u_name);

		if (userList.size() < 1) {
			System.out.println("찾으실 회원의 이름이 없습니다!!");
			return;
		}
		for (UserDTO dto : userList) {
			System.out.println(dto.toString());
		}
	}

	public void findUserByTel() {
		String u_tel = "";
		System.out.print("찾으실 회원의 전화번호 >> ");
		u_tel = scanner.nextLine();
		List<UserDTO> userList = userDao.findByUserTel(u_tel);

		if (userList.size() < 1) {
			System.out.println("찾으실 회원의 전화번호가 없습니다!!");
			return;
		}
		for (UserDTO dto : userList) {
			System.out.println(dto.toString());
		}
	}

	public void insertUser() {
		List<UserDTO> userList = null;
		UserDTO userDTO = null;
		String u_code = "", _maxUcode = "";
		String u_name = "";
		String u_tel = "";
		String u_addr = "";
		int intUCode = -1;

		System.out.println("==================================================");
		System.out.println("유저정보 등록 시작");
		System.out.println("----------------------------------------------------");

		_maxUcode = userDao.getMaxCode();
		intUCode = Integer.valueOf(_maxUcode.substring(1));
		intUCode++;
		u_code = _maxUcode.substring(0, 1) + String.format("%05d", intUCode);
		System.out.println(u_code);

		while (true) {
			System.out.print("회원 이름 (-Q:quit) >> ");
			u_name = scanner.nextLine();
			if (u_name.equals("-Q"))
				return;
			userList = userDao.findByUserName(u_name);

			System.out.print("전화번호 (예:010-1111-1111)  (-Q:quit) >>");
			u_tel = scanner.nextLine();
			if (u_tel.equals("-Q"))
				return;

			for (UserDTO dto : userList) {
				if (dto.getU_name().equalsIgnoreCase(u_name) && dto.getU_tel().equalsIgnoreCase(u_tel)) {
					System.out.println("이름과 전화번호가 동일한 회원은 등록할수 없습니다!!");
					continue;
				}
			}
			break;
		}

		System.out.print("회원 주소 (-Q:quit) >> ");
		u_addr = scanner.nextLine();
		if (u_addr.equals("-Q"))
			return;

		userDTO=UserDTO.builder().u_addr(u_addr).u_code(u_code).u_name(u_name).u_tel(u_tel).build();

		int ret = userDao.insert(userDTO);
		if (ret > 0)
			System.out.println("회원등록 성공");
		else
			System.out.println("회원등록 실패!!");
	}// end insert

	public void updateUser() {
		UserDTO userDTO = null;
		String u_code = "";
		String u_name = "";
		String u_tel = "";
		String u_addr = "";
		System.out.println("=============================================");
		System.out.println("회원정보 수정 시작");
		System.out.println("-------------------------------------------------");
		while (true) {
			System.out.print("수정할 회원의 코드 (-Q:quit) >> ");
			u_code = scanner.nextLine();
			if (u_code.equals("-Q"))
				return;
			userDTO = userDao.findById(u_code);
			if (userDTO == null) {
				System.out.println("회원의 코드가 잘못되거나 존재하지 않습니다!!");
				continue;
			}
			break;
		} // end

		System.out.printf("수정할 이름(%s) (-Q:quit)>> ", userDTO.getU_name());
		u_name = scanner.nextLine();
		if (u_name.equals("-Q"))
			return;
		if (!u_name.trim().isEmpty()) {
			userDTO.setU_name(userDTO.getU_name());
		}

		while (true) {
			System.out.printf("수정할 전화번호(%s) (-Q:quit)>> ", userDTO.getU_tel());
			u_tel = scanner.nextLine();
			if (u_tel.equals("-Q"))
				return;
			if(u_tel.trim().isEmpty()) break;
			if (!u_tel.trim().isEmpty() && u_tel.length() == 13) {
				userDTO.setU_tel(u_tel);
				break;
			} else if (u_tel.length() < 13 || u_tel.length() > 13) {
				System.out.println("전화번호는 010-1111-1111 이런식으로 입력해 주세요!!");
				continue;
			}
			break;
		}

		System.out.printf("수정할 주소(%s) (-Q:quit)>> ", userDTO.getU_addr());
		u_addr = scanner.nextLine();
		if (u_addr.equals("-Q"))
			return;
		if (!u_addr.trim().isEmpty()) {
			userDTO.setU_name(u_addr);
		}
		
		int ret = userDao.update(userDTO);
		if (ret > 0)
			System.out.println("회원수정 성공");
		else
			System.out.println("회원수정 실패!!");
	}//end update
}
