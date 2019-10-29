package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1{
	public void deptMenu() {
		System.out.println("====================================");
		System.out.println("거래처 정보 관리");
		System.out.println("-------------------------------------");
		System.out.print("1. 등록 2.수정 3.삭제 4.검색 0.종료 >> ");
		String strMenu=scanner.nextLine();
		try {
			int intMenu=Integer.valueOf(strMenu);
			if(intMenu==0) return;
			else if(intMenu==1) {
				deptInsert();
			}else if(intMenu==2) {
				viewNameList();
				deptUpdate();
			}else if(intMenu==3) {
				viewNameList();
				deptDelete();
			}else if(intMenu==4) {
				viewNameAndCEOList();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
	}
	public void deptDelete() {
		while(true) {
			System.out.print("삭제할 거래처 코드(-Q) >> ");
			String d_code=scanner.nextLine();
			if(d_code.equals("-Q")) return;
			
			DeptDTO deptDTO=deptDao.findById(d_code);
			if(deptDTO==null) {
				System.out.println("삭제할 거래처 코드가 없음!");
				continue;
			}
			viewDetail(deptDTO);
			System.out.print("정말 삭제?? enter:삭제 >> ");
			String yesNo=scanner.nextLine();
			if(yesNo.trim().isEmpty()) {
				int ret=deptDao.delete(d_code);
				if(ret>0) {
					System.out.println("삭제완료");
					return;
				}
				else {
					System.out.println("삭제 실패!");
				}
			}
		}// end while
	}//end delete
	public void deptUpdate() {
		DeptDTO deptDTO=null;
		String d_code="";
		while(true) {
			System.out.print("수정할 거래처의 code(-Q) >> ");
			d_code=scanner.nextLine();
			if(d_code.equals("-Q")) return;
			
			deptDTO=deptDao.findById(d_code);
			if(deptDTO==null) {
				System.out.println("코드가 잘못됬거나 없습니다!");
				continue;
			}
			break;
		}
		
		System.out.printf("수정할 상호 이름(%s)(-Q) >> ",deptDTO.getD_name());
		String d_name=scanner.nextLine();
		if(d_name.equals("-Q")) return;
		if(d_name.trim().isEmpty()) {
			d_name=deptDTO.getD_name();
		}
		
		System.out.printf("수정할 상호 CEO(%s)(-Q) >> ",deptDTO.getD_ceo());
		String d_ceo=scanner.nextLine();
		if(d_ceo.equals("-Q")) return;
		if(d_ceo.trim().isEmpty()) {
			d_ceo=deptDTO.getD_ceo();
		}
		
		System.out.printf("수정할 상호 전화번호(%s)(-Q) >> ",deptDTO.getD_tel());
		String d_tel=scanner.nextLine();
		if(d_tel.equals("-Q")) return;
		if(d_tel.trim().isEmpty()) {
			d_tel=deptDTO.getD_tel();
		}
		
		System.out.printf("수정할 상호 주소(%s)(-Q) >> ",deptDTO.getD_addr());
		String d_addr=scanner.nextLine();
		if(d_addr.equals("-Q")) return;
		if(d_addr.trim().isEmpty()) {
			d_addr=deptDTO.getD_addr();
		}
		
		deptDTO.setD_addr(d_addr);
		deptDTO.setD_ceo(d_ceo);
		deptDTO.setD_name(d_name);
		deptDTO.setD_tel(d_tel);
		int ret=deptDao.update(deptDTO);
		System.out.println(ret);
		if(ret>0) {
			System.out.println("업데이트 성공");
			return;
		}
		else {
			System.out.println("업데이트 실패!");
		}
	}//end update
	public void deptInsert() {
		
	}
}
