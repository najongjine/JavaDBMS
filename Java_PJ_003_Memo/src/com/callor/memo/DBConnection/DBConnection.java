package com.callor.memo.DBConnection;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String configFile="com/callor/memo/mybatis/config/mybatis-config.xml";
		try {
			InputStream is=Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			if(sqlSessionFactory==null) {
				sqlSessionFactory=builder.build(is);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
