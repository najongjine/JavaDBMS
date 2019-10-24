package com.biz.dbms.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	private static SqlSessionFactory sqlSessionFactory=null;
	static {
		//*-config.xml 파일을 읽어서 mybatis 초기 설정 값을 가져오기
		String configFile="com/biz/dbms/config/MyBatis-config.xml";
		InputStream inputStream=null;
		try {
			//configFile 읽기
			inputStream=Resources.getResourceAsStream(configFile);
			
			//sqlSession을 싱글톤으로 생성
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			if(sqlSessionFactory==null) {
				sqlSessionFactory=builder.build(inputStream);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//end static
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
