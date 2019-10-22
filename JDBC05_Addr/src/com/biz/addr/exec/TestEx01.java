package com.biz.addr.exec;

import java.util.Scanner;

import com.biz.addr.service.AddrCUDService;
import com.biz.addr.service.AddrService;

public class TestEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddrService addrs=new AddrService();
		AddrCUDService addrCUD=new AddrCUDService();
		Scanner scanner=new Scanner(System.in);
		
		System.out.print("1. 조회 2. insert 3. update 4. delete ");
		String strMenu=scanner.nextLine();
		
		if(strMenu.equalsIgnoreCase("1")) {
			System.out.println("1. 전체조회 2.이름조회 3.전화번호조회 4.관계조회 ");
			strMenu=scanner.nextLine();
			
			if(strMenu.equalsIgnoreCase("1")) {
				addrs.viewAllList();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				addrs.searchName();
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				addrs.searchTel();
			}
			else if(strMenu.equalsIgnoreCase("4")) {
				addrs.searchChain();
			}
		}
		else if(strMenu.equalsIgnoreCase("2")) {
			addrCUD.insertAddr();
		}
		else if(strMenu.equalsIgnoreCase("3"))
		{
			addrCUD.update_A_Addr();
		}
		else if(strMenu.equalsIgnoreCase("4")) {
			addrCUD.delete_A_Addr();
		}
	}

}
