package com.biz.iolist.service.iolist.view;

import java.util.List;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.persistence.IolistViewVO;
import com.biz.iolist.persistence.dao.IolistViewDao;

public class IolistViewServiceV1 {
	IolistViewDao ioViewDao;

	public IolistViewServiceV1() {
		super();
		ioViewDao=DBConnection.getSqlSessionFactory().openSession(true).getMapper(IolistViewDao.class);
	}
	protected void viewList(List<IolistViewVO> iolist) {
		System.out.println("================================================");
		System.out.println("매입매출 정보");
		System.out.println("--------------------------------------------------");
		System.out.println("거래일자\t구분\t거래처\t상품\t수량\t단가\t합계");
		for(IolistViewVO vo:iolist) {
			viewItem(vo);
		}
		System.out.println("================================================");
	}
	protected void viewItem(IolistViewVO vo) {
		System.out.println(vo.getIo_date()+"\t");
		System.out.println(vo.getIo_inout()+"\t");
		System.out.printf("(%s)%s\t",vo.getIo_dcode(),vo.getIo_dname());
		System.out.printf("(%s)%s\t",vo.getIo_pcode(),vo.getIo_pname());
		System.out.println(vo.getIo_qty()+"\t");
		System.out.println(vo.getIo_price()+"\t");
		System.out.println(vo.getIo_total()+"\n");
	}
	public void viewAllList() {
		List<IolistViewVO> iolist=ioViewDao.selectAll();
		if(iolist!=null && iolist.size()>0) {
			for(IolistViewVO vo: iolist) {
				System.out.println(vo.toString());
			}
		}
	}
	public void viewListByPCode(String pcode) {
		List<IolistViewVO> iolist=ioViewDao.findByPcode(pcode);
		if(iolist!=null && iolist.size()>0) {
		}
	}
	public void viewListByPName(String pname) {
		List<IolistViewVO> iolist=ioViewDao.findByPName(pname);
		if(iolist!=null && iolist.size()>0) {
		}
	}
	public void viewListByDCode(String dcode) {
		List<IolistViewVO> iolist=ioViewDao.findByDcode(dcode);
		if(iolist!=null && iolist.size()>0) {
		}
	}
	public void viewListByDName(String dname) {
		List<IolistViewVO> iolist=ioViewDao.findByDName(dname);
		if(iolist!=null && iolist.size()>0) {
			for(IolistViewVO vo:iolist) {
				System.out.println(vo.toString());
			}
		}else {
			System.out.println("찾으려는 거래처 이름이 없습니다!");
		}
	}
}
