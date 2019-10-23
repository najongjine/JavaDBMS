package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class MyBatisEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * JDBC의 다양한 클래스를 대신하여 java app + dbms간의 연결 connection을
		 * 대신 관리해줄 클래스, 객체선언
		 * 
		 * session:= network 환경에서 지점과 지점사이가 다양한 방법으로
		 * 연결되고 데이터를 주고받을 준비가 된 통로.
		 */
		SqlSession sqlSession=DBConnection.getSqlSessionFactory().openSession(true);
		
		BookDao bookDao= sqlSession.getMapper(BookDao.class);
		
		bookDao.delete("B0004");
	}

}
