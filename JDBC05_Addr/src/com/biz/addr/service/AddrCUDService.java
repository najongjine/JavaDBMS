package com.biz.addr.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrCUDService {
	AddrService addrs=null;
	Scanner scanner=null;
	private AddrDao addrDao=null;

	public AddrCUDService() {
		scanner=new Scanner(System.in);
		addrDao=new AddrDaoImp();
	}
	private AddrDTO rstTO_DTO(ResultSet rst) throws SQLException {
		AddrDTO addrDTO=AddrDTO.builder().id(rst.getLong("id"))
				.name(rst.getString("name"))
				.tel(rst.getString("tel"))
				.chain("chain")
				.addr("addr")
				.build();
		return addrDTO;
	}//end
	
	public void insertAddr() {
		AddrDTO addrDTO = null;
		String name=null;
		System.out.println("새로운 정보를 입력하겠습니다~~~");
		
		while(true) {
			System.out.print("이름입력 >> ");
			name=scanner.nextLine();
			if(name.trim().length()<1) {
				System.out.println("이름은 필수로 입력해야 합니다!!");
				continue;
			}
			break;
		}
		System.out.print("전화번호 입력 >> ");
		String tel=scanner.nextLine();
		
		System.out.println("주소 입력 >> ");
		String addr=scanner.nextLine();
		
		System.out.println("관계 입력 >> ");
		String chain=scanner.nextLine();
		
		addrDTO=AddrDTO.builder()
				.name(name)
				.tel(tel)
				.chain(chain)
				.addr(addr)
				.build();
		int ret=addrDao.insert(addrDTO);
		if(ret>0)System.out.println("데이터 추가 성공");
		else System.out.println("데이터 추가 실패!!");
	}//end
	public void delete_A_Addr() {
		long id=0;
		while(true) {
			System.out.print("찾을 ID 입력 (-Q:quit)>> ");
			String strID=scanner.nextLine();
			if(strID.equalsIgnoreCase("-Q")) return;
			try {
				id=Long.valueOf(strID);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자로만 입력!!");
				continue;
			}
		}
		int ret=addrDao.delete(id);
		if(ret >0) System.out.println("데이터 삭제 성공");
		else System.out.println("데이터 삭제 실패!!");
	}//end
	public void update_A_Addr() {
		addrs=new AddrService();
		AddrDTO addrDTO = null;
		String name=null;
		System.out.println(" 정보를 수정하겠습니다~~~");
		addrDTO=addrs.searchID();
		
		while(true) {
			System.out.print("이름입력 >> ");
			name=scanner.nextLine();
			if(name.length()<1) {
				break;
			}
			if(name.trim().length()<1) {
				System.out.println("이름은 공백문자열을 입력하지 마십시오!!");
				continue;
			}
			addrDTO.setName(name);
			break;
		}
		System.out.println("전화번호 입력 >> ");
		String tel=scanner.nextLine();
		if(tel.length()>0) {
			addrDTO.setTel(tel);
		}
		
		System.out.println("주소 입력 >> ");
		String addr=scanner.nextLine();
		if(addr.length()>0) {
			addrDTO.setAddr(addr);
		}
		
		System.out.println("관계 입력 >> ");
		String chain=scanner.nextLine();
		if(chain.length()>0) {
			addrDTO.setChain(chain);
		}
		
		int ret=addrDao.update(addrDTO);
		if(ret>0)System.out.println("데이터 수정 성공");
		else System.out.println("데이터 수정 실패!!");
	}
}
