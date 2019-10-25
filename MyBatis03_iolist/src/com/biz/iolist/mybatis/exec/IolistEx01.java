package com.biz.iolist.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.persistence.IolistViewVO;
import com.biz.iolist.persistence.ProductDTO;
import com.biz.iolist.persistence.dao.ProductDao;

public class IolistEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession sqlSession=DBConnection.getSqlSessionFactory().openSession(true);
		ProductDao proDao=sqlSession.getMapper(ProductDao.class);
		List<ProductDTO> proList=proDao.selectAll();
		for(ProductDTO vo:proList) {
			System.out.println(vo.toString());
		}
	}

}
