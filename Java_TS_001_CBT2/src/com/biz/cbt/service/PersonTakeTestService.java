package com.biz.cbt.service;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.biz.cbt.persistence.PersonVO;
import com.biz.cbt.persistence.CbtDTO;

import lombok.Getter;

@Getter
public class PersonTakeTestService {
	private PersonVO personVO;
	private CbtService cbs;
	Scanner scanner = new Scanner(System.in);

	public PersonTakeTestService() {
		personVO = new PersonVO();
		cbs = new CbtService();
		personVO.setCbtList(cbs.getCbtList());
		init();
	}

	public void init() {
		personVO.setPoint(0);
		cbs.init();
		Collections.shuffle(personVO.getCbtList());
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
				init();
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

	public boolean checkAnswer(CbtDTO cbtDTO) {
		if (cbtDTO.getChosenAns().equalsIgnoreCase(cbtDTO.getCb_correct_answer())) {
			return true;
		}
		return false;
	}

	public void show5Problems(List<CbtDTO> cbtList) {
		if (personVO.getSolvedCount() % 5 != 0) {
			return;
		}
		System.out.println("=====================5문제 오답 리스트==================");
		int startIndex = personVO.getSolvedCount() - 5;
		int endIndex = personVO.getSolvedCount();
		for (int i = startIndex; i < endIndex; i++) {
			CbtDTO cbtDTO = cbtList.get(i);
			System.out.printf("%d: %s\n", cbtList.get(i).getCb_seq(), cbtList.get(i).getCb_question());
			if (checkAnswer(cbtDTO)) {
				System.out.printf("정답입니다: %s\n", cbtList.get(i).getCb_correct_answer());
			} else {
				System.out.printf("틀렸습니다!! 정답:%s\t고른답안:%s\n", cbtList.get(i).getCb_correct_answer(),
						cbtList.get(i).getChosenAns());
			}
		}
		System.out.println("===========================================================");
	}

	public void showAProblemAndAns(CbtDTO cbtDTO) {
		int tryCount=0;
		String[] ans = new String[4];
		int intMenu = 0;
		System.out.println();
		
		System.out.println();
		while (true) {
			System.out.println(cbtDTO.getCb_question());
			for (int i = 0; i < cbtDTO.getAnsList().size(); i++) {
				System.out.printf("%d: %s\t", i + 1, cbtDTO.getAnsList().get(i));
				ans[i] = cbtDTO.getAnsList().get(i);
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
			cbtDTO.setChosenAns(ans[intMenu - 1]);
			if(checkAnswer(cbtDTO)) {
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
		List<CbtDTO> cbtList = personVO.getCbtList();
		System.out.println("------------------문제풀이 시작----------------------");
		for (int i = 0; i < cbtList.size(); i++) {
			CbtDTO cbtDTO = cbtList.get(i);
			showAProblemAndAns(cbtList.get(i));
			personVO.setSolvedCount(i + 1);
			if (checkAnswer(cbtDTO)) {
				personVO.setPoint(personVO.getPoint() + 5);
			}
			show5Problems(cbtList);
		}
		showScore();
		init();
	}
}
