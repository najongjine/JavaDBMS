package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreDeleteEx01 {
/*
 * 1.학생 이름을 입력 받아서 
 * 2.성적 리스트를 보여주고
 * 3.리스트를 보고 ID를 입력받아서
 * 4.해당 ID의 score값을 삭제
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		ScoreServiceV2 sc=new ScoreServiceV2Ext();
		String strStNum=null;
		List<ScoreVO> scoreList=null;
		
		while(true) { //학번
			System.out.print("학번 (-Q:quit) >> ");
			strStNum=scanner.nextLine();
			if(strStNum.equalsIgnoreCase("-Q")) {
				System.out.println("성적입력 종료!");
				break;
			}
			//학번조회
			scoreList=sc.findByStNum(strStNum);
			if(scoreList==null || scoreList.size()<1) {
				System.out.println("학생정보에 학번이 없습니다!");
				System.out.println("학생정보를 먼저 등록해야 합니다.");
				continue;
			}
			
			break;
		}
		if(strStNum.equalsIgnoreCase("-Q"))return;
		
		while(true) {
			System.out.println("=========================================");
			System.out.println("ID\t학생이름\t과목\t점수");
			System.out.println("------------------------------------------");
			for(ScoreVO vo:scoreList) {
				System.out.print(vo.getS_id()+"\t");
				System.out.print(vo.getSt_name()+"\t");
				System.out.print(vo.getSb_name()+"\t");
				System.out.print(vo.getS_score()+"\n");
			}
			System.out.print("삭제할 ID (-Q:quit)>> ");
			String strID= scanner.nextLine();
			
			if(strID.equalsIgnoreCase("-Q"))return;
			try {
				long longID=Long.valueOf(strID);
				int ret=sc.delete(longID);
				if(ret>0) {
					System.out.println("삭제완료");
					scoreList=sc.findByStNum(strStNum);
				}else {
					System.out.println("삭제실패!");
				}
				continue;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 가능!");
				continue;
			}
		}
	}

}
