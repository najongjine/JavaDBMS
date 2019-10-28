package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.dao.DeptDao;

public class DeptServiceV1 {
	protected DeptDao deptDao;
	Scanner scanner;
	public DeptServiceV1() {
		deptDao=DBConnection.getSqlSessionFactory().openSession(true).getMapper(DeptDao.class);
		scanner=new Scanner(System.in);
	}
	public void viewAllList() {
		List<DeptDTO> dptList=deptDao.selectAll();
		if(dptList.size()<1 || dptList==null) {
			System.out.println("리스트가 없음!");
			return;
		}
		viewList(dptList);
	}
	public void viewNameList() {
		List<DeptDTO> dptList=null;
		System.out.print("검색할 거래처 이름 >> ");
		String d_name="";
		d_name=scanner.nextLine();
		dptList=deptDao.findByName(d_name);
		if(dptList.size()<1 || dptList==null) {
			System.out.println("리스트가 없음!");
			return;
		}
		viewList(dptList);
	}
	public void viewCEOList() {
		List<DeptDTO> dptList=null;
		System.out.print("검색할 대표자 명 >> ");
		String d_ceo="";
		d_ceo=scanner.nextLine();
		if(dptList.size()<1 || dptList==null) {
			System.out.println("리스트가 없음!");
			return;
		}
		viewList(dptList);
	}
	public void viewNameAndCEOList() {
		List<DeptDTO> dptList=null;
		System.out.print("검색할 거래처 이름 >> ");
		String d_name="";
		d_name=scanner.nextLine();
		if(d_name.trim().isEmpty()) {
			viewAllList();
		}
		
		System.out.print("검색할 대표자 명 >> ");
		String d_ceo="";
		d_ceo=scanner.nextLine();
		
		dptList=deptDao.findByNameAndCEO(d_name, d_ceo);
		if(dptList.size()<1 || dptList==null) {
			System.out.println("리스트가 없음!");
			return;
		}
		viewList(dptList);
	}
	protected void viewList(DeptDTO deptDTO) {
		System.out.println("거래처코드: "+deptDTO.getD_code());
		System.out.println("거래처 이름: "+deptDTO.getD_name());
		System.out.println("거래처 대표: "+deptDTO.getD_ceo());
		System.out.println("거래처 전화번호: "+deptDTO.getD_tel());
	}
	protected void viewList(List<DeptDTO> deptList) {
		for(DeptDTO dto: deptList) {
			System.out.println(dto.toString());
		}
	}
}
