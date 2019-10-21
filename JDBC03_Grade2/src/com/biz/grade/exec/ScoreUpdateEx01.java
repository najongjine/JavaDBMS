package com.biz.grade.exec;

import java.util.List;
import java.util.Random;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV3;

public class ScoreUpdateEx01 {
/*
 * 1. 학번을 입력받고
 * 2. 학생정보 리스트를 보여주고
 * 3. 학번을 입력받고
 * 4. 학번에 해당하는 성적리스트 보여주고
 * 5. 리스트를 보고 ID를 입력하면
 * 6. 각 칼럼별로 값을 보여주고
 *  그냥 enter를 입력하면 원래 값을 유지
 *  새로운 값을 입력하고 enter를 입력하면 새로운 값으로 변경
 *  
 *  ID 100을 선택했다
 *  학번(T0020) >>  이 경우는 T0020으로 그냥 유지
 *  과목(S003) >> S004 이 경우는 S004로 변경
 *  점수(33) >> 90  이 경우도 90으로 변경
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV3 sc=new ScoreServiceV3();
		ScoreDTO scoreDTO=null;
		
		List<ScoreVO> scList=sc.updateStudent();
		if(scList==null || scList.size()<1) {
			System.out.println("성적입력 종료!!");
			return;
		}
		for(ScoreVO vo:scList) {
			System.out.println(vo.toString());
		}
		
		ScoreVO scoreVO=sc.selectScore();
		String strSubject=sc.inputSubject();
		if(strSubject==null) {
			System.out.println("성적입력 종료!!");
			return;
		}
		System.out.println(scoreVO.toString());
		
		sc.updateScore(scoreVO);
		
		Integer intScore=sc.inputScore();
		if(intScore==null) {
			System.out.println("성적입력 종료!!");
			return;
		}
		Random rnd=new Random();

	}

}
