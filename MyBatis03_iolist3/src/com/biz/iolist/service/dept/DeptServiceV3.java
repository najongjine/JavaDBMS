package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2 {
	public void deptInsert() {
		String _strMaxCode=deptDao.getMaxCode();
		int intMaxCode=Integer.valueOf(_strMaxCode.substring(1));
		intMaxCode++;
		String d_code=_strMaxCode.substring(0, 1)+
				String.format("%04d", intMaxCode) ;
		
		String d_name="";
		while(true) {
			System.out.print("추가할 거래처 이름(-Q) >> ");//
			d_name=scanner.nextLine();
			if(d_name.equals("-Q")) return;
			if(d_name.trim().isEmpty()) {
				System.out.println("거래처 이름은 필수입니다!!");
				continue;
			}
			break;
		}
		
		String d_ceo="";
		while(true) {
			System.out.print("추가할 거래처 CEO >> ");//
			d_ceo=scanner.nextLine();
			if(d_ceo.equals("-Q")) return;
			if(d_ceo.trim().isEmpty()) {
				System.out.println("CEO 입력은 필수입니다!!");
				continue;
			}
			break;
		}
		
		System.out.print("추가할 거래처 전화번호 >>");
		String d_tel=scanner.nextLine();
		if(d_tel.equals("-Q")) return;
		
		System.out.print("추가할 거래처 주소 >> ");
		String d_addr=scanner.nextLine();
		if(d_addr.equals("-Q")) return;
		
		DeptDTO deptDTO=DeptDTO.builder().d_addr(d_addr).d_ceo(d_ceo)
				.d_code(d_code).d_name(d_name).d_tel(d_tel).build();
		
		int ret=deptDao.insert(deptDTO);
		if(ret>0) System.out.println("정보 추가 성공");
		else System.out.println("정보추가 실패!!");
	}
}
