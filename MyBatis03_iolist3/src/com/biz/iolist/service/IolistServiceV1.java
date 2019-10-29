package com.biz.iolist.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.persistence.IolistViewVO;
import com.biz.iolist.persistence.ProductDTO;
import com.biz.iolist.persistence.dao.DeptDao;
import com.biz.iolist.persistence.dao.IolistDao;
import com.biz.iolist.persistence.dao.IolistViewDao;
import com.biz.iolist.persistence.dao.ProductDao;

public class IolistServiceV1 {
	protected IolistDao iolistDao;
	protected DeptDao deptDao;
	protected ProductDao proDao;
	protected IolistViewDao viewDao;
	Scanner scanner;

	public IolistServiceV1() {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		iolistDao = sqlSession.getMapper(IolistDao.class);
		deptDao = sqlSession.getMapper(DeptDao.class);
		proDao = sqlSession.getMapper(ProductDao.class);
		viewDao = sqlSession.getMapper(IolistViewDao.class);
		scanner = new Scanner(System.in);

	}

	public void viewAllList() {
		List<IolistViewVO> ioList = viewDao.selectAll();
		for (IolistViewVO vo : ioList) {
			System.out.println(vo.toString());
		}
	}

	public void insertIolist() {
		DeptDTO deptDTO = null;
		ProductDTO proDTO = null;
		IolistDTO ioDTO = null;
		String d_code = "",io_dcode="";
		String p_code = "", io_pcode="";
		String io_inout = "";
		int io_qty = 0;
		int io_price = 0;
		int io_total = 0;
		Date date = new Date();// 현재 날자 가져옴. 날자는 골때리는 형태로 되어있음.
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");// 날자를 문자열 형태로 바꾸기 위한 객체.
		String io_date = sd.format(date);

		deptDao.selectAll();
		System.out.print("추가할 거래처 코드 입력 >> ");
		d_code = scanner.nextLine();
		deptDTO = deptDao.findById(d_code);
		if (deptDTO == null) {
			System.out.println("거래처 코드가 잘못되었습니다.");
			return;
		}
		io_dcode=deptDTO.getD_code();

		proDao.selectAll();
		System.out.print("추가할 상품 코드 입력 >> ");
		p_code = scanner.nextLine();
		proDTO = proDao.findById(p_code);
		if (proDTO == null) {
			System.out.println("상품 코드가 잘못되었습니다.");
			return;
		}
		io_pcode=proDTO.getP_code();

		while (true) {
			System.out.print("1.매입 2.매출? >> ");
			io_inout=scanner.nextLine();
			if(io_inout.equalsIgnoreCase("1")) {
				io_inout="매입";
				break;
			}
			else if(io_inout.equalsIgnoreCase("2")) {
				io_inout="매출";
				break;
			}
			System.out.println("1이나 2만 입력하세요 !!");
		}
		while(true) {
			System.out.print("수량 >> ");
			String strQty=scanner.nextLine();
			try {
				io_qty=Integer.valueOf(strQty);
			} catch (Exception e) {
				System.out.println("수량엔 숫자만 입력하세요!!");
				continue;
			}
			break;
		}
		if(io_inout.equals("1")) {
			io_price=proDTO.getP_iprice();
		}else if(io_inout.equals("2")) {
			io_price=proDTO.getP_oprice();
		}
		io_total=io_price*io_qty;
		
		ioDTO=IolistDTO.builder().io_date(io_date).io_dcode(io_dcode)
				.io_inout(io_inout).io_pcode(io_pcode).io_price(io_price)
				.io_qty(io_qty).io_total(io_total).build();
		int ret=iolistDao.insert(ioDTO);
		if(ret>0) System.out.println("iolist 추가 성공!!");
	}

}
