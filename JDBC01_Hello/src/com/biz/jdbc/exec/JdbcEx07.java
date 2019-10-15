package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCServiceV3;

public class JdbcEx07 {
	public static void main(String[] args) {
		ScoreJDBCServiceV3 sc=new ScoreJDBCServiceV3();
		ScoreVO scoreVO =ScoreVO.builder().s_id(601).s_score(88).s_std("이몽룡")
				.s_rem("연습").build();
		int ret=sc.insert(scoreVO);
		System.out.println(ret);
		
		List<ScoreVO> scList=sc.findById(601);
		for(ScoreVO s:scList) {
			System.out.println(s);
		}
	}
}
