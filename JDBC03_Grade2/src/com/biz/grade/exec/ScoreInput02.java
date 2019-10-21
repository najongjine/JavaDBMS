package com.biz.grade.exec;

import java.util.Random;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.service.ScoreServiceV3;

public class ScoreInput02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV3 sc=new ScoreServiceV3();
		
		String strStNum=sc.inputStudent();
		if(strStNum==null) {
			System.out.println("성적입력 종료!!");
			return;
		}
		String strSubject=sc.inputSubject();
		if(strSubject==null) {
			System.out.println("성적입력 종료!!");
			return;
		}
		Integer intScore=sc.inputScore();
		if(intScore==null) {
			System.out.println("성적입력 종료!!");
			return;
		}
		Random rnd=new Random();
		ScoreDTO scoreDTO=ScoreDTO.builder().s_id(rnd.nextLong())
				.s_rem("")
				.s_score(Integer.valueOf(intScore))
				.s_std(strStNum.toUpperCase())
				.s_subject(strSubject.toUpperCase()).build();
		
		int ret=sc.insert(scoreDTO);
		if(ret>0) {
			System.out.println("데이터 추가 성공!");
		}else {
			System.out.println("데이터 추가 실패!");
		}
	}

}
