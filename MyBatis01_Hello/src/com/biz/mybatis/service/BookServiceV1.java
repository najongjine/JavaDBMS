package com.biz.mybatis.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class BookServiceV1 {
	SqlSession sqlSession=null;
	Scanner scanner=new Scanner(System.in);

	public BookServiceV1() {
		sqlSession=DBConnection.getSqlSessionFactory().openSession(true);
	}
	
	public void SearchName() {
		while(true) {
			System.out.print("찾을 도서 입력 >> (-Q) ");
			String b_name=scanner.nextLine();
			if(b_name.equalsIgnoreCase("-Q")) break;
			
			BookDao dao=sqlSession.getMapper(BookDao.class);
			List<BookDTO> bookList=dao.findByName(b_name);
			for(BookDTO dto:bookList) {
				System.out.println(dto.toString());
			}
		}
	}
}
