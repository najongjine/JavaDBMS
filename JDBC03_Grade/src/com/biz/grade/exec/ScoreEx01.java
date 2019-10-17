package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV2 sc=new ScoreServiceV2Ext();
		
		List<ScoreVO> scoreList=sc.selectAll();
		if(scoreList==null || scoreList.size()<1) {
			System.out.println("데이터가 없습니다!");
			return;
		}
		for(ScoreVO vo:scoreList) {
			System.out.println(vo.toString());
		}
	}

}
