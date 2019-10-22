package com.biz.addr.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrService {
	Scanner scanner=null;
	private AddrDao addrDao=null;
	
	public AddrService() {
		addrDao=new AddrDaoImp();
		scanner=new Scanner(System.in);
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
	
	public AddrDTO searchID() {
		
		long id=0;
		while(true) {
			System.out.print("찾을 ID 입력 (-Q:quit)>> ");
			String strID=scanner.nextLine();
			if(strID.equalsIgnoreCase("-Q")) return null;
			try {
				id=Long.valueOf(strID);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자로만 입력!!");
				continue;
			}
		}
		AddrDTO addrDTO=addrDao.findById(id);
		System.out.println(addrDTO.toString());
		return addrDTO;
	}// end
	public void searchName() {
		System.out.print("찾을 이름 (-Q:quit) >> ");
		String name=scanner.nextLine();
		List<AddrDTO> addrList=addrDao.findByName(name);
		for(AddrDTO dto:addrList) {
			System.out.println(dto.toString());
		}
		
	}//end
	public void searchAddr() {
		System.out.print("입력한 단어가 포함된 찾을 주소 (-Q:quit) >> ");
		String addr=scanner.nextLine();
		List<AddrDTO> addrList=addrDao.findByAddr(addr);
		for(AddrDTO dto:addrList) {
			System.out.println(dto.toString());
		}
		
	}//end
	public void searchTel() {
		System.out.print("입력한 단어가 포함된 찾을 전화번호 (-Q:quit) >> ");
		String tel=scanner.nextLine();
		List<AddrDTO> addrList=addrDao.findByTel(tel);
		for(AddrDTO dto:addrList) {
			System.out.println(dto.toString());
		}
		
	}//end
	public void searchChain() {
		System.out.print("입력한 단어가 포함된 찾을 관계 (-Q:quit) >> ");
		String chain=scanner.nextLine();
		List<AddrDTO> addrList=addrDao.findByTel(chain);
		for(AddrDTO dto:addrList) {
			System.out.println(dto.toString());
		}
		
	}//end
	public void viewAllList() {
		List<AddrDTO> addrList=addrDao.selectALL();
		for(AddrDTO dto:addrList) {
			System.out.println(dto.toString());
		}
	}
}
