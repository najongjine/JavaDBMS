package com.biz.iolist.service.pro;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2{
	public void menuProduct() {
		System.out.print("1.등록 2.수정 3.검색 0.종료 >> ");
		String strMenu=scanner.nextLine();
		int intMenu=Integer.valueOf(strMenu);
		if(intMenu==1) {
			insertProduct();
		}
		else if(intMenu==2) {
			proUpdate();
		}
		else if(intMenu==3) {
			searchPName();
		}
		else if(intMenu==0) {
			return;
		}
	}
	public void insertProduct() {
		String p_code="";
		while(true) {
			System.out.print("추가할 상품 코드(enter: auto make) (-Q) >> ");
			p_code=scanner.nextLine();
			ProductDTO checkProDTOCode=proDao.findById(p_code);
			if(p_code.equals("-Q")) return;
			if(p_code.trim().isEmpty()) {
				String strTMPCode=proDao.getMaxPCode();
				int intPCode=Integer.valueOf(strTMPCode.substring(1));
				intPCode++;
				p_code=strTMPCode.substring(0, 1);
				p_code+=String.format("%04d", intPCode);
				break;
			}
			else if(p_code.trim().isEmpty() ||  checkProDTOCode!=null
					||p_code.length()!=5 ||p_code.substring(0, 1)!="P") {
				System.out.println("상품코드가 잘못됬음!");
				continue;
			}
			try {
				Integer.valueOf(p_code.substring(1));
			} catch (Exception e) {
				System.out.println("두번째 부터는 숫자만 입력!");
			}
			break;
		}// pcode 입력 끝
		
		
		String p_name="";
		while(true) {
			System.out.print("추가할 상품 이름(-Q) >> ");
			p_name=scanner.nextLine();
			if(p_name.trim().isEmpty() ) {
				System.out.println("상품이름은 반드시 입력 해야함!");
				continue;
			}
			if(p_name.equals("-Q")) return;
			ProductDTO proDTO=proDao.findBySName(p_name);
			if(proDTO!=null) {
				System.out.println("같은이름의 상품 있음.");
				System.out.println(proDTO.toString());
				System.out.print("사용하시겠습니까? (enter:사용함, no:다시입력) >> ");
				String yesNO=scanner.nextLine();
				if(yesNO.trim().isEmpty()) break;
				continue;
			}
			break;
		}
		
		int p_iprice=0,p_oprice=0;
		while(true) {
			System.out.print("추가할 상품 매입 단가(-Q) >> ");
			String strIprice=scanner.nextLine();
			if(strIprice.equals("-Q")) {
				return;
			}
			try {
				p_iprice=Integer.valueOf(strIprice);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격엔 숫자만 입력!!");
				continue;
			}
		}
		
		while(true) {
			System.out.print("추가할 상품 판매 단가(-Q) >> ");
			String strOprice=scanner.nextLine();
			if(strOprice.equals("-Q")) {
				return;
			}
			try {
				p_oprice=Integer.valueOf(strOprice);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격엔 숫자만 입력!!");
				continue;
			}
		}
		
		System.out.print("부가세여부 (1 아님 0 입력) (-Q) >> ");
		String p_vat=scanner.nextLine();
		if(p_vat.equals("-Q")) {
			return;
		}
		ProductDTO proDTO=ProductDTO.builder().p_code(p_code)
				.p_iprice(p_iprice).p_name(p_name)
				.p_oprice(p_oprice).p_vat(p_vat).build();
		
		int ret=proDao.insert(proDTO);
		if(ret>0) System.out.println("데이터 추가 성공");
		else System.out.println("데이터 추가 실패!!");
		
	}
	
	
	public void deleteProduct() {
		System.out.print("삭제할 상품의 아이디 >> ");
		String p_code=scanner.nextLine();
		ProductDTO proDTO=viewPDetail(p_code);
		if(proDTO==null) {
			System.out.println("상품의 아이디가 잘못됬거나 없습니다!");
			return;
		}
		int ret=proDao.delete(p_code);
		if(ret>0) System.out.println("삭제 완료");
		else System.out.println("삭제 실패!");
	}
}
