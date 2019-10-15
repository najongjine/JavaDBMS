package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCServiceV3;

public class JdbcEx06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreJDBCServiceV3 sc=new ScoreJDBCServiceV3();
		List<ScoreVO> scoreList=sc.findById(30);
		for(ScoreVO sVO:scoreList) {
			System.out.println(sVO);
		}
	}

}
