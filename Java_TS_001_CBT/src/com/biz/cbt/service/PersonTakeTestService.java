package com.biz.cbt.service;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.biz.cbt.persistence.PersonVO;
import com.biz.cbt.persistence.ProblemVO;

import lombok.Getter;

@Getter
public class PersonTakeTestService {
	private PersonVO personVO;
	private CbtService cbs;
	Scanner scanner = new Scanner(System.in);

	public PersonTakeTestService() {
		personVO = new PersonVO();
		cbs = new CbtService();
		personVO = cbs.getPersonVO();
		init();
	}

	public void init() {
		personVO.setPoint(0);
		cbs.init();
		Collections.shuffle(personVO.getProblemList());
	}

	public void takeTestMenu() {
		while (true) {
			System.out.println("======================================================");
			System.out.println("1.문제 입력\t2.문제풀이\t0.종료");
			System.out.print("선택 >> ");
			String strMenu=scanner.nextLine();
			if(strMenu.equalsIgnoreCase("1")) {
				cbs.cbtMenu();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				startTest();
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				System.out.println("프로그램 종료...");
				return;
			}
			else {
				System.out.println("메뉴선택을 잘못하셨습니다!");
			}
		}
	}

	public boolean checkAnswer(ProblemVO pVO) {
		if (pVO.getChosenAns().equalsIgnoreCase(pVO.getCorrectAns())) {
			return true;
		}
		return false;
	}

	public void show5Problems(List<ProblemVO> probList) {
		if (personVO.getSolvedCount() % 5 != 0) {
			return;
		}
		System.out.println("=====================5문제 오답 리스트==================");
		int startIndex = personVO.getSolvedCount() - 5;
		int endIndex = personVO.getSolvedCount();
		for (int i = startIndex; i < endIndex; i++) {
			ProblemVO pVO = probList.get(i);
			System.out.printf("%d: %s\n", probList.get(i).getCb_seq(), probList.get(i).getQuestion());
			if (checkAnswer(pVO)) {
				System.out.printf("정답입니다: %s\n", probList.get(i).getCorrectAns());
			} else {
				System.out.printf("틀렸습니다!! 정답:%s\t고른답안:%s\n", probList.get(i).getCorrectAns(),
						probList.get(i).getChosenAns());
			}
		}
		System.out.println("===========================================================");
	}

	public void showAProblemAndAns(ProblemVO probVO) {
		int tryCount=0;
		String[] ans = new String[4];
		int intMenu = 0;
		System.out.println();
		
		System.out.println();
		while (true) {
			System.out.println(probVO.getQuestion());
			for (int i = 0; i < probVO.getAnswers().size(); i++) {
				System.out.printf("%d: %s\t", i + 1, probVO.getAnswers().get(i));
				ans[i] = probVO.getAnswers().get(i);
			}
			System.out.print("\n답안선택(1~4까지) >> ");
			String strMenu = scanner.nextLine();
			try {
				intMenu = Integer.valueOf(strMenu);
				if (intMenu > 4 || intMenu < 1) {
					System.out.println("1~4 까지의 숫자만 선택하세요!!");
					continue;
				}
			} catch (Exception e) {
				System.out.println("1~4 까지의 숫자만 선택하세요!!");
				continue;
			}
			probVO.setChosenAns(ans[intMenu - 1]);
			if(checkAnswer(probVO)) {
				System.out.println("정답입니다 (enter=다음문제 풀기)");
				scanner.nextLine();
				return;
			}
			if(tryCount>0) return;
			System.out.print("틀렸습니다. 재도전을 하시겠습니까? (Y/N)>>");
			String yn=scanner.nextLine();
			if(yn.equalsIgnoreCase("Y")) {
				tryCount++;
				continue;
			}
			break;
		} // end while
		
		
	}//end showAProblemAndAns
	public void showScore() {
		System.out.println("점수: "+personVO.getPoint());
	}
	public void startTest() {
		List<ProblemVO> probList = personVO.getProblemList();
		System.out.println("------------------문제풀이 시작----------------------");
		for (int i = 0; i < probList.size(); i++) {
			ProblemVO pVO = probList.get(i);
			showAProblemAndAns(probList.get(i));
			personVO.setSolvedCount(i + 1);
			if (checkAnswer(pVO)) {
				personVO.setPoint(personVO.getPoint() + 5);
			}
			show5Problems(probList);
		}
		showScore();
		init();
	}
}
