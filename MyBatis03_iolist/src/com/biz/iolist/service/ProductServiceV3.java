package com.biz.iolist.service;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2{
	public void menuProduct() {
		System.out.println("1.등록 2.수정 3.추가 0.종료");
		String strMenu=scanner.nextLine();
	}
	public void insertProduct() {
		System.out.print("추가할 상품 코드(-Q) >> ");
		String p_code=scanner.nextLine();
		if(p_code.trim().isEmpty() || p_code.equals("-Q")) {
			return;
		}
		
		System.out.print("추가할 상품 이름(-Q) >> ");
		String p_name=scanner.nextLine();
		if(p_code.trim().isEmpty() || p_code.equals("-Q")) {
			return;
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
}
