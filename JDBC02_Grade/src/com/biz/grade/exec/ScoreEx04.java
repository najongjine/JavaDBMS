package com.biz.grade.exec;

import java.util.Random;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV1;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceV1;

public class ScoreEx04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentService st=new StudentServiceV1();
		ScoreService sc=new ScoreServiceV1();
		Scanner scanner=new Scanner(System.in);
		Random rnd=new Random();
		
		while(true) {
			System.out.println("=====================================");
			System.out.println("성적입력");
			System.out.println("--------------------------------------");
			System.out.print("학번 >> ");
			String strNum=scanner.nextLine();
			
			StudentDTO stDTO=st.findById(strNum);
			if(stDTO==null) {
				/*
				 * 참조무결성 검사.
				 * 학번이 학생정보 테이블에 있는지 검사.
				 */
				System.out.println("찾는 학번이 없음!");
				System.out.println("학생정보를 먼저 등록 해야 성적을 입력 할수 있습니다");
				continue;
			}
			
			/*
			 * 과목table에서 과목 코드를 조회한후 없으면 메시지를 보여주는 코드가 필요.
			 */
			System.out.print("과목코드 >> ");
			String strSubject=scanner.nextLine();
			
			System.out.print("점수 >> ");
			String strScore=scanner.nextLine();
			int intScore=Integer.valueOf(strScore);
			
			long s_id=(long)(Math.random()*100000);
			ScoreDTO scoreDTO=ScoreDTO.builder().s_id(s_id)
					.s_std(strNum)
					.s_score(intScore)
					.s_subject(strSubject)
					.build();
			int ret=sc.insert(scoreDTO);
			if(ret>0) System.out.println("데이터 추가 완료");
			else System.out.println("데이터 추가 실패!");
		}
	}

}
