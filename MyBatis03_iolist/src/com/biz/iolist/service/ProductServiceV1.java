package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.persistence.ProductDTO;
import com.biz.iolist.persistence.dao.ProductDao;

public class ProductServiceV1 {
	protected ProductDao proDao;
	protected Scanner scanner;

	public ProductServiceV1() {
		proDao = DBConnection.getSqlSessionFactory().openSession(true)
				.getMapper(ProductDao.class);
		scanner=new Scanner(System.in);
	}
	
	public void proUpdate() {
		/*List<ProductDTO> proList=proDao.selectAll();
		for(ProductDTO dto:proList) {
			System.out.println(dto.toString());
		}*/
		System.out.println("===============================================");
		System.out.print("수정할 상품코드 (-Q)>> ");
		String p_code=scanner.nextLine();
		p_code=p_code.toUpperCase();
		ProductDTO proDTO=proDao.findById(p_code);
		System.out.println("-------------------------------------------");
		System.out.printf("상품코드: %s \n",proDTO.getP_code());
		System.out.printf("상품이름: %s\n",proDTO.getP_name());
		System.out.printf("매입가격: %s\n",proDTO.getP_iprice());
		System.out.printf("매출가격: %s\n",proDTO.getP_oprice());
		
		String strVAT=proDTO.getP_vat().equals("1") ? "과세":"면세";
		System.out.printf("과세여부: %s\n",strVAT);
		System.out.println("------------------------------------------------");
		
		System.out.printf("상품명(%s) >> ",proDTO.getP_name());
		String p_name=scanner.nextLine();
		if(!p_name.trim().isEmpty()) {
			proDTO.setP_name(p_name);
		}
		
		System.out.printf("매입단가(%d) >> ",proDTO.getP_iprice());
		String strIprice=scanner.nextLine();
		try {
			proDTO.setP_iprice(Integer.valueOf(strIprice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.printf("매출단가(%d) >> ",proDTO.getP_oprice());
		String strOprice=scanner.nextLine();
		try {
			proDTO.setP_oprice(Integer.valueOf(strOprice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.printf("부가세여부(%s)(1 아님 0 입력) >> ",proDTO.getP_vat());
		String p_vat=scanner.nextLine();
		if(!p_vat.trim().isEmpty() && (p_vat.equals("1") || p_vat.equals("0"))) {
			proDTO.setP_vat(p_vat);
		}
		
		int ret=proDao.update(proDTO);
		if(ret>0) System.out.println("데이터 변경 완료!!");
		else System.out.println("데이터 변경 실패!!");
		
		System.out.println(proDao.findById(p_code).toString());
	}
}
