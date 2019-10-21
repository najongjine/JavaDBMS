package com.biz.grade.service;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.dao.ScoreDao;
import com.biz.grade.persistence.dao.StudentDao;
import com.biz.grade.persistence.dao.SubjectDao;
import com.biz.grade.persistence.dao.imp.ScoreDaoImp;
import com.biz.grade.persistence.dao.imp.StudentDaoImp;
import com.biz.grade.persistence.dao.imp.SubjectDaoImp;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public class ScoreServiceV3 {
	StudentDao stdDao=null;
	SubjectDao subDao=null;
	ScoreDao scoreDao=null;
	Scanner scanner;
	
	public ScoreServiceV3() {
		// TODO Auto-generated constructor stub
		scanner=new Scanner(System.in);
		stdDao=new StudentDaoImp();
		subDao=new SubjectDaoImp();
		scoreDao=new ScoreDaoImp();
	}
	
	public int insert(ScoreDTO scoreDTO) {
		return scoreDao.insert(scoreDTO);
	}
	public String inputStudent() {
		String strStNum=null;
		while(true) { //학번
			strStNum=null;//clear
			System.out.print("학번 (-Q:quit) >> ");
			strStNum=scanner.nextLine();
			if(strStNum.equalsIgnoreCase("-Q")) {
				System.out.println("성적입력 종료!");
				break;
			}
			//학번조회
			StudentDTO stdDTO=stdDao.findById(strStNum);
			if(stdDTO==null ) {
				System.out.println("학생정보에 학번이 없습니다!");
				System.out.println("학생정보를 먼저 등록해야 합니다.");
				continue;
			}
			System.out.println(stdDTO.toString());
			break;
		}
		if(strStNum.equalsIgnoreCase("-Q"))return null;
		return strStNum;
	
	}
	public String inputSubject() {
		String strSubject=null;
		
		while(true) {//과목코드
			System.out.print("과목코드 (-Q:quit) >> ");
			strSubject=scanner.nextLine();
			if(strSubject.equalsIgnoreCase("-Q")) {
				break;
			}
			SubjectDTO subDTO=subDao.findById(strSubject);
			if(subDTO==null ) {
				System.out.println("과목코드가 없음!");
				System.out.println("과목정보를 먼저 등록!");
				continue;
			}
			System.out.println(subDTO.toString());
			break;
		}
		if(strSubject.equalsIgnoreCase("-Q")) return null;
		return strSubject;
		
	}
	public Integer inputScore() {
		String strScore=null;
		Integer intScore=null;
		
		while(true) {//점수
			System.out.print("점수 (-Q:quit) >> ");
			strScore=scanner.nextLine();
			if(strScore.equalsIgnoreCase("-Q")) {
				break;
			}
			try {
				intScore=Integer.valueOf(strScore);
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
		if(strScore.equalsIgnoreCase("-Q"))return null;
		return intScore;
	}
	
	/*
	 * 학번을 입력하면 학번에 해당하는 성적리스트 return
	 */
	public List<ScoreVO> updateStudent() {
		String strStNum=null;
		List<ScoreVO> scList=null;
		
		while(true) {
			System.out.print("학번 (-Q:quit)>> ");
			strStNum=scanner.nextLine();
			if(strStNum.equalsIgnoreCase("-Q"))break;
			
			scList=scoreDao.findByStNum(strStNum);
			if(scList==null || scList.size()<1) {
				System.out.println("성적 데이터 없음!");
				continue;
			}
			break;
		}
		if(strStNum.equalsIgnoreCase("-Q")) return null;
		return scList;
	}
	
	/*
	 * score 테이블에서 수정할 데이터 선택
	 */
	public ScoreVO selectScore() {
		ScoreVO scoreVO=null;
		String strScore=null;
		while(true) {
			System.out.println("----------------------------------------");
			System.out.print("수정할 ID (-Q:quit)>> ");
			strScore=scanner.nextLine();
			if(strScore.equalsIgnoreCase("-Q"))break;
			
			try {
				scoreVO=
				scoreDao.findById(Integer.valueOf(strScore));
				if(scoreVO==null) {
					System.out.println("찾는 ID가 없습니다!");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 가능!");
				continue;
			}
			break;
		}
		if(strScore.equalsIgnoreCase("-Q")) return null;
		return scoreVO;
	}

	public ScoreVO updateScore(ScoreVO scoreVO) {
		// TODO Auto-generated method stub
		System.out.printf("학번 %s>> ",scoreVO.getS_std());
		String strStNum=scanner.nextLine();
		if((strStNum.length()>0)) {
			scoreVO.setS_std(strStNum);
		}
		System.out.printf("과목코드 %s>> ",scoreVO.getS_subject());
		String strSubject=scanner.nextLine();
		if(strSubject.length() >0) {
			scoreVO.setS_subject(strSubject);
		}
		System.out.printf("점수 %s>> ",scoreVO.getS_score());
		String strScore=scanner.nextLine();
		try {
			int intScore=Integer.valueOf(strScore);
			scoreVO.setS_score(intScore);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return scoreVO;
	}
}
