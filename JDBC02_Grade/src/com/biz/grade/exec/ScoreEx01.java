package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV1;

public class ScoreEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreService sc=new ScoreServiceV1();
		List<ScoreDTO> scoreList=sc.selectAll();
		if(scoreList==null) {
			System.out.println("성적 데이터를 읽을수 없다!");
			return;
		}
		for(ScoreDTO dto:scoreList) {
			System.out.println(dto.toString());
		}
	}

}
