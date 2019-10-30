package com.biz.iolist.service.iolist;

import java.text.ParseException;
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
import com.biz.iolist.service.dept.DeptServiceV3;
import com.biz.iolist.service.iolist.view.IolistViewServiceV1;
import com.biz.iolist.service.pro.ProductServiceV4;

public class IolistServiceV1 {
	protected IolistDao iolistDao;
	protected DeptDao deptDao;
	protected ProductDao proDao;
	protected IolistViewDao viewDao;
	protected IolistViewServiceV1 ioView;
	protected ProductServiceV4 proService;
	protected DeptServiceV3 deptService;
	Scanner scanner;

	public IolistServiceV1() {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		iolistDao = sqlSession.getMapper(IolistDao.class);
		deptDao = sqlSession.getMapper(DeptDao.class);
		proDao = sqlSession.getMapper(ProductDao.class);
		viewDao = sqlSession.getMapper(IolistViewDao.class);
		ioView=new IolistViewServiceV1();
		proService=new ProductServiceV4();
		deptService=new DeptServiceV3();
		scanner = new Scanner(System.in);

	}
	public void iolistMenu() {
		while(true) {
			System.out.println("===============================================");
			System.out.println("새나라 마트 매입매출 관리");
			System.out.println("------------------------------------------------");
			System.out.print("1.등록 2수정 3삭제 4.검색 0.종료 >> ");
			String strMenu=scanner.nextLine();
			int intMenu=-1;
			try {
				intMenu=Integer.valueOf(strMenu);
			} catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
			}
			if(intMenu==0) break;
			else if(intMenu==1) insert();
			else if(intMenu==2) update();
			else if(intMenu==3) delete();
			else if(intMenu==4) search();
		}
	}//
	public void search() {
		// TODO Auto-generated method stub
		
	}
	public void delete() {
		long io_seq=-1;
		String strSeq="";
		IolistDTO ioDTO=null;
		while(true) {
			System.out.print("삭제할 Iolist SEQ (-Q)>>");
			strSeq=scanner.nextLine();
			if(strSeq.equals("-Q")) return;
			try {
				io_seq=Long.valueOf(strSeq);
			} catch (Exception e) {
				System.out.println("SEQ 는 숫자로만 입력해야 합니다!!");
				continue;
			}
			ioDTO=iolistDao.findById(io_seq);
			if(ioDTO==null) {
				System.out.println("찾으려는 SEQ가 없습니다!!");
				continue;
			}
			System.out.println(ioDTO.toString());
			System.out.print("정말 삭제하겠습니까? (y/n) >> ");
			String yesNO=scanner.nextLine();
			if(yesNO.equalsIgnoreCase("y")) {
				int ret=iolistDao.delete(io_seq);
				if(ret>0) {
					System.out.println("데이터 삭제 성공");
					return;
				}else {
					System.out.println("데이터 삭제 실패!!");
					return;
				}
			}
			break;
		}
	}//end delete
	public void update() {
		// TODO Auto-generated method stub
		IolistDTO iolistDTO=null;
		
		System.out.print("거래처명 >> ");
		String strDName=scanner.nextLine();
		if(strDName.trim().isEmpty()) {
			ioView.viewAllList();
		}
		ioView.viewListByDName(strDName);
		
		System.out.print("수정할 SEQ 코드 입력(-Q) >> ");
		String strio_seq=scanner.nextLine();
		if(strio_seq.equals("-Q")) return;
		long io_seq=-1;
		try {
			io_seq=Long.valueOf(strio_seq);
		} catch (Exception e) {
			System.out.println("SEQ 코드가 잘못됬습니다!!");
		}
		iolistDTO=iolistDao.findById(io_seq);
		if(iolistDTO==null) {
			System.out.println("찾으려는 SEQ가 없습니다!!");
			return;
		}
		while(true) {
			System.out.printf("거래구분(%s) 입력 1.매입 2매출 (-1 종료)>> ",iolistDTO.getIo_inout());
			String strInout=scanner.nextLine();
			if(strInout.equals("-1")) return;
			if(!strInout.trim().isEmpty()) {
				try {
					int intInout=Integer.valueOf(strInout);
					if(intInout<0) break;
					
					if(intInout==1) {
						iolistDTO.setIo_inout("매입");
					}
					else if(intInout==2) {
						iolistDTO.setIo_inout("매출");
					}else {
						System.out.println("매입,매출 구분을 다시 선택하세요");
						continue;
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("매입매출 구분을 다시 입력해 주세요");
					continue;
				}
			}
			break;
		}// 매입매출 while
		//if(iolistDTO.getIo_inout().isEmpty()) return;
		
		while(true) {
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			String curDate=sd.format(date);
			
			System.out.printf("거래일자(%s)",curDate);
			String strDate=scanner.nextLine();
			if(strDate.trim().isEmpty()) {
				//iolistDTO.setIo_date(curDate);
			}else {
				try {
					sd.parse(strDate);
				} catch (ParseException e) {
					System.out.println("날짜 형식이 잘못되었습니다!");
					continue;
				}
				iolistDTO.setIo_date(strDate);
			}
			break;
		}//
		while(true) {
			System.out.print("거래처명 입력(-Q) >> ");
			String strDname=scanner.nextLine();
			if(strDname.equals("-Q")) return;
			List<DeptDTO> deptList=deptDao.findByName(strDname);
			for(DeptDTO dto:deptList) {
				System.out.println(dto.toString());
			}
			if(deptList!=null && deptList.size()>0) {
				System.out.print("거래처코드 입력 >> ");
				String strDCode=scanner.nextLine();
				if(strDCode.trim().isEmpty()) break;
				DeptDTO dptDTO=deptDao.findById(strDCode);
				if(dptDTO==null) {
					System.out.println("거래코드 없음 !!");
					continue;
				}else {
					iolistDTO.setIo_dcode(strDCode);
				}
			}else {
				continue;
			}
			break;
		}
		//if(iolistDTO.getIo_dcode().isEmpty()) return;
		while(true) {
			System.out.print("삼풍명 입력(-Q) >> ");
			String strPname=scanner.nextLine();
			if(strPname.equals("-Q")) return;
			List<ProductDTO> proList=proDao.findByPName(strPname);
			for(ProductDTO dto:proList) {
				System.out.println(dto.toString());
			}
			if(proList==null && proList.size()<1) {
				System.out.println("찾는 상품이 없음");
				continue;
			}else {
				System.out.println("상품코드 >> ");
				String strPCode=scanner.nextLine();
				if(strPCode.trim().isEmpty()) break;
				ProductDTO proDTO=proDao.findById(strPCode);
				if(proDTO==null) {
					System.out.println("상품코드를 확인하세요");
					continue;
				}else {
					iolistDTO.setIo_pcode(strPCode);
					int intPrice=iolistDTO.getIo_inout().equals("매입")?
							proDTO.getP_iprice():proDTO.getP_oprice();
					iolistDTO.setIo_price(intPrice);
				}
			}
			break;
		}//
		if(iolistDTO.getIo_pcode().isEmpty()) return;
		
		while(true) {
			System.out.printf("단가입력(%d) >> ",iolistDTO.getIo_price());
			String strPrice=scanner.nextLine();
			if(strPrice.equals("-Q")) return;
			if(strPrice.trim().isEmpty()) break;
			try {
				int price=Integer.valueOf(strPrice);
				iolistDTO.setIo_price(price);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		while(true) {
			System.out.println("수량입력 >> ");
			String strQty=scanner.nextLine();
			if(strQty.equals("-Q")) return;
			if(strQty.trim().isEmpty()) break;
			try {
				int intQty=Integer.valueOf(strQty);
				iolistDTO.setIo_qty(intQty);
			} catch (Exception e) {
				System.out.println("수량은 숫자로만 입력!!");
				continue;
			}
			break;
		}//
		int total=iolistDTO.getIo_price()*iolistDTO.getIo_qty();
		iolistDTO.setIo_total(total);
		int ret=iolistDao.update(iolistDTO);
		if(ret>0)System.out.println("데이터 수정 완료");
		else System.out.println("데이터 수정 실패");
		
	}//end update
	public void insert() {
		IolistDTO iolistDTO=new IolistDTO();
		while(true) {
			System.out.print("거래구분 입력 1.매입 2매출 (-1 종료)>> ");
			String strInout=scanner.nextLine();
			try {
				int intInout=Integer.valueOf(strInout);
				if(intInout<0) break;
				
				if(intInout==1) {
					iolistDTO.setIo_inout("매입");
				}
				else if(intInout==2) {
					iolistDTO.setIo_inout("매출");
				}else {
					System.out.println("매입,매출 구분을 다시 선택하세요");
					continue;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("매입매출 구분을 다시 입력해 주세요");
				continue;
			}
			break;
		}//
		if(iolistDTO.getIo_inout().isEmpty()) return;
		
		while(true) {
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			String curDate=sd.format(date);
			
			System.out.printf("거래일자(%s)",curDate);
			String strDate=scanner.nextLine();
			if(strDate.trim().isEmpty()) {
				iolistDTO.setIo_date(curDate);
			}else {
				try {
					sd.parse(strDate);
				} catch (ParseException e) {
					System.out.println("날짜 형식이 잘못되었습니다!");
					continue;
				}
				iolistDTO.setIo_date(strDate);
			}
			break;
		}//
		while(true) {
			System.out.print("거래처명 입력(-Q) >> ");
			String strDname=scanner.nextLine();
			if(strDname.equals("-Q")) break;
			List<DeptDTO> deptList=deptDao.findByName(strDname);
			for(DeptDTO dto:deptList) {
				System.out.println(dto.toString());
			}
			if(deptList!=null && deptList.size()>0) {
				System.out.print("거래처코드 입력 >> ");
				String strDCode=scanner.nextLine();
				DeptDTO dptDTO=deptDao.findById(strDCode);
				if(dptDTO==null) {
					System.out.println("거래코드 없음 !!");
					continue;
				}else {
					iolistDTO.setIo_dcode(strDCode);
				}
			}else {
				continue;
			}
			break;
		}
		if(iolistDTO.getIo_dcode().isEmpty()) return;
		while(true) {
			System.out.print("삼풍명 입력(-Q) >> ");
			String strPname=scanner.nextLine();
			if(strPname.equals("-Q")) break;
			List<ProductDTO> proList=proDao.findByPName(strPname);
			for(ProductDTO dto:proList) {
				System.out.println(dto.toString());
			}
			if(proList==null && proList.size()<1) {
				System.out.println("찾는 상품이 없음");
				continue;
			}else {
				System.out.println("상품코드 >> ");
				String strPCode=scanner.nextLine();
				ProductDTO proDTO=proDao.findById(strPCode);
				if(proDTO==null) {
					System.out.println("상품코드를 확인하세요");
					continue;
				}else {
					iolistDTO.setIo_pcode(strPCode);
					int intPrice=iolistDTO.getIo_inout().equals("매입")?
							proDTO.getP_iprice():proDTO.getP_oprice();
					iolistDTO.setIo_price(intPrice);
				}
			}
			break;
		}//
		if(iolistDTO.getIo_pcode().isEmpty()) return;
		
		while(true) {
			System.out.printf("단가입력(%d) >> ",iolistDTO.getIo_price());
			String strPrice=scanner.nextLine();
			try {
				int price=Integer.valueOf(strPrice);
				iolistDTO.setIo_price(price);
			} catch (Exception e) {
				// TODO: handle exception
			}
			break;
		}
		while(true) {
			System.out.println("수량입력 >> ");
			String strQty=scanner.nextLine();
			try {
				int intQty=Integer.valueOf(strQty);
				iolistDTO.setIo_qty(intQty);
			} catch (Exception e) {
				System.out.println("수량은 숫자로만 입력!!");
				continue;
			}
			break;
		}//
		int total=iolistDTO.getIo_price()*iolistDTO.getIo_qty();
		iolistDTO.setIo_total(total);
		int ret=iolistDao.update(iolistDTO);
		if(ret>0)System.out.println("데이터 추가 완료");
		else System.out.println("데이터 추가 실패");
		
	}//end insert
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

		List<DeptDTO> deptList=deptDao.selectAll();
		for(DeptDTO dto:deptList) {
			System.out.println(dto.toString());
		}
		System.out.print("추가할 거래처 코드 입력(-Q) >> ");
		d_code = scanner.nextLine();
		if(d_code.equals("-Q")) return;
		deptDTO = deptDao.findById(d_code);
		if (deptDTO == null) {
			System.out.println("거래처 코드가 잘못되었습니다.");
			return;
		}
		io_dcode=deptDTO.getD_code();

		List<ProductDTO> proList=proDao.selectAll();
		for(ProductDTO dto:proList) {
			System.out.println(dto.toString());
		}
		System.out.print("추가할 상품 코드 입력(-Q) >> ");
		p_code = scanner.nextLine();
		if(p_code.equals("-Q")) return;
		proDTO = proDao.findById(p_code);
		if (proDTO == null) {
			System.out.println("상품 코드가 잘못되었습니다.");
			return;
		}
		io_pcode=proDTO.getP_code();

		
		while (true) {
			System.out.print("1.매입 2.매출?(-Q) >> ");
			io_inout=scanner.nextLine();
			if(io_inout.equals("-Q")) return;
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
			System.out.print("수량(-Q) >> ");
			String strQty=scanner.nextLine();
			if(strQty.equals("-Q")) return;
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
