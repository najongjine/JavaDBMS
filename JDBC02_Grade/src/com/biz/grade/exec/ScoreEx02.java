package com.biz.grade.exec;

import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV1;

public class ScoreEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		ScoreService sc=new ScoreServiceV1();
		
		while(true) {
			System.out.println("======================================");
			System.out.println("성적처리 v1");
			System.out.println("---------------------------------------");
			System.out.print("찾을 데이터 ID >>");
			String strID=scanner.nextLine();
			long s_id=Long.valueOf(strID);
			ScoreDTO scoreDTO=sc.findById(s_id);
			if(scoreDTO==null) {
				System.out.println("찾는 데이터가 없음!");
			}
			else {
				System.out.println(scoreDTO.toString());
			}
		}
	}
}
