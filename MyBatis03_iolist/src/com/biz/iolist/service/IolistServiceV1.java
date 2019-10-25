package com.biz.iolist.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.persistence.IolistViewVO;
import com.biz.iolist.persistence.dao.DeptDao;
import com.biz.iolist.persistence.dao.IolistDao;
import com.biz.iolist.persistence.dao.IolistViewDao;
import com.biz.iolist.persistence.dao.ProductDao;

public class IolistServiceV1 {
	protected IolistDao iolistDao;
	protected DeptDao deptDao;
	protected ProductDao proDao;
	protected IolistViewDao viewDao;

	public IolistServiceV1() {
		SqlSession sqlSession=DBConnection.getSqlSessionFactory().openSession(true);
		iolistDao = sqlSession.getMapper(IolistDao.class);
		deptDao = sqlSession.getMapper(DeptDao.class);
		proDao = sqlSession.getMapper(ProductDao.class);
		viewDao = sqlSession.getMapper(IolistViewDao.class);

	}
	
	public void viewAllList() {
		List<IolistViewVO> ioList=viewDao.selectAll();
		for(IolistViewVO vo:ioList) {
			System.out.println(vo.toString());
		}
	}
	
	
}
