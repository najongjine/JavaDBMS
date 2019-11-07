package com.biz.cbt.DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String configFile="com/biz/cbt/config/mybatis-config.xml";
		InputStream is;

		try {
			is=Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
			if(sqlSessionFactory==null)
				sqlSessionFactory=builder.build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
