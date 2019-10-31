package com.biz.rent.exec;

import java.util.Scanner;

import com.biz.rent.service.BookService;
import com.biz.rent.service.RentService;
import com.biz.rent.service.UserService;

public class BookRentManageExec {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BookService bs = new BookService();
		UserService us = new UserService();
		RentService rs = new RentService();

		while (true) {
			System.out.print("1.도서정보 관련 2.회원정보 관련 3.대여관련 0.종료 >> ");
			String strMenu = scanner.nextLine();
			if (strMenu.equalsIgnoreCase("1")) {
				bs.menu();
			}
			if (strMenu.equalsIgnoreCase("2")) {
				us.menu();
			}
			if (strMenu.equalsIgnoreCase("3")) {
				rs.menu();
			}
			if (strMenu.equalsIgnoreCase("0")) {
				break;
			} else {
				System.out.println("main 메뉴를 잘못 선택하셨습니다!!");
				continue;
			}

		}
		System.out.println("도서프로그램 종료...");
	}

}
