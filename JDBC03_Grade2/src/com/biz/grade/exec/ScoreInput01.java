package com.biz.grade.exec;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreInput01 {
/*
 * 1. 학번 입력받기.
 * 2.학번이 학생 테이블에 있으면 성적 입력으로 진행.
 *  학번이 없으면 학번 다시 입력
 * 3. 과목코드를 입력받고
 * 4. 과목코드가 과목테이블에 있으면 점수 입력으로 진행
 *  없으면 콰목코드 다시 입력
 * 5. 점수를 입력받고
 * 6. 점수가 0~100범위 내에 있으면 DB에 저장.
 *  그렇지 않으면 잠수 다시 입력
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV2 sc=new ScoreServiceV2Ext();
		String strStNum=null;
		String strSubject=null;
		String strScore=null;
		Scanner scanner=new Scanner(System.in);
		
		while(true) { //학번
			System.out.print("학번 (-Q:quit) >> ");
			strStNum=scanner.nextLine();
			if(strStNum.equalsIgnoreCase("-Q")) {
				break;
			}
			//학번조회
			List<ScoreVO> scoreList=sc.findByStNum(strStNum);
			if(scoreList==null || scoreList.size()<1) {
				System.out.println("학생정보에 학번이 없습니다!");
				System.out.println("학생정보를 먼저 등록해야 합니다.");
				continue;
			}
			for(ScoreVO vo:scoreList) {
				System.out.println(vo.toString());
			}
			break;
		}
		if(strStNum.equalsIgnoreCase("-Q"))return;
		
		while(true) {//과목코드
			System.out.print("과목코드 (-Q:quit) >> ");
			strSubject=scanner.nextLine();
			if(strSubject.equalsIgnoreCase("-Q")) {
				break;
			}
			List<ScoreVO> scList=sc.findBySubject(strSubject);
			if(scList==null || scList.size()<1) {
				System.out.println("과목코드가 없음!");
				System.out.println("과목정보를 먼저 등록!");
				continue;
			}
			for(ScoreVO vo:scList) {
				System.out.println(vo.toString());
			}
			break;
		}
		if(strSubject.equalsIgnoreCase("-Q")) return;
		
		while(true) {//점수
			System.out.print("점수 (-Q:quit) >> ");
			strScore=scanner.nextLine();
			if(strScore.equalsIgnoreCase("-Q")) {
				break;
			}
			try {
				int intScore=Integer.valueOf(strScore);
				if(intScore<0 || intScore>100) {
					System.out.println("점수는 0~100 까지만 입력!");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("성적은 숫자로만 입력!!");
				continue;
			}
			break;
		}
		if(strScore.equalsIgnoreCase("-Q"))return;
		
		Random rnd=new Random();
		ScoreDTO scoreDTO=ScoreDTO.builder().s_id(rnd.nextLong())
				.s_rem("")
				.s_score(Integer.valueOf(strScore))
				.s_std(strStNum)
				.s_subject(strSubject).build();
		
		int ret=sc.insert(scoreDTO);
		if(ret>0) {
			System.out.println("데이터 추가 성공!");
		}else {
			System.out.println("데이터 추가 실패!");
		}
	}

}
