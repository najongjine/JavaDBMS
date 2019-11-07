package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.biz.cbt.DBConnection.DBConnection;
import com.biz.cbt.Dao.CbtDao;
import com.biz.cbt.persistence.CbtDTO;
import com.biz.cbt.persistence.PersonVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CbtService {
	CbtDao cbtDao;
	List<CbtDTO> cbtList;
	//PersonVO cbtDTO;
	Scanner scanner;

	public CbtService() {
		cbtDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(CbtDao.class);
		scanner = new Scanner(System.in);
		cbtList = cbtDao.selectAll();
		for (CbtDTO dto : cbtList) {
			if(dto.getAnsList().size()>=4) break;
			dto.getAnsList().add(dto.getCb_answer1());
			dto.getAnsList().add(dto.getCb_answer2());
			dto.getAnsList().add(dto.getCb_answer3());
			dto.getAnsList().add(dto.getCb_answer4());
		}
		init();
	}


	public void init() {
		cbtList = cbtDao.selectAll();
		Collections.shuffle(cbtList);
		for (CbtDTO dto : cbtList) {
			Collections.shuffle(dto.getAnsList());
		}
	}

	public void cbtMenu() {
		while (true) {
			System.out.println("=====================================================");
			System.out.println("1.문제등록\t2.문제수정\t3.문제삭제\t0.종료");
			System.out.print("메뉴선택 >> ");
			String strMenu=scanner.nextLine();
			if(strMenu.equalsIgnoreCase("1")) {
				insertQuestion();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				updateQuestion();
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				deleteQuestion();
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				return;
			}else {
				System.out.println("메뉴선택을 잘못하셨습니다!");
			}
		}
	}

	public void viewAllList() {
		for (CbtDTO vo : cbtList) {
			System.out.println(vo.toString());
		}
	}

	public void insertQuestion() {
		System.out.print("문제입력 (-Q)>> ");
		String cb_question = scanner.nextLine();
		if (cb_question.equals("-Q"))
			return;

		System.out.print("답안1 입력(-Q) >> ");
		String cb_answer1 = scanner.nextLine();
		if (cb_answer1.equals("-Q"))
			return;

		System.out.print("답안2 입력(-Q) >> ");
		String cb_answer2 = scanner.nextLine();
		if (cb_answer2.equals("-Q"))
			return;

		System.out.print("답안3 입력(-Q) >> ");
		String cb_answer3 = scanner.nextLine();
		if (cb_answer3.equals("-Q"))
			return;

		System.out.print("답안4 입력(-Q) >> ");
		String cb_answer4 = scanner.nextLine();
		if (cb_answer4.equals("-Q"))
			return;

		System.out.print("정답 입력(-Q) >> ");
		String cb_correct_answer = scanner.nextLine();
		if (cb_correct_answer.equals("-Q"))
			return;

		CbtDTO cbtDTO = CbtDTO.builder().cb_answer1(cb_answer1).cb_answer2(cb_answer2).cb_answer3(cb_answer3)
				.cb_answer4(cb_answer4).cb_correct_answer(cb_correct_answer).cb_question(cb_question).build();

		int ret = cbtDao.insert(cbtDTO);
		if (ret > 0)
			System.out.println("데이터 추가 성공");
		else {
			System.out.println("데이터 추가 실패!!");
			return;
		}

		init();

		/*
		 * if(cbtListSize<=problemListSize) return; for(int
		 * i=problemListSize;i<=cbtListSize;i++) { cbtDTO=cbtList.get(i-1); CbtDTO
		 * pVO=new CbtDTO(); pVO.setQuestion(cbtDTO.getCb_question());
		 * pVO.getAnswers().add(cbtDTO.getCb_answer1());
		 * pVO.getAnswers().add(cbtDTO.getCb_answer2());
		 * pVO.getAnswers().add(cbtDTO.getCb_answer3());
		 * pVO.getAnswers().add(cbtDTO.getCb_answer4());
		 * Collections.shuffle(pVO.getAnswers());
		 * pVO.setCorrectAns(cbtDTO.getCb_correct_answer());
		 * cbtDTO.getProblemList().add(pVO); }
		 */

	}//

	public void updateQuestion() {
		CbtDTO cbtDTO = null;
		long cb_seq = -1;
		while (true) {
			System.out.print("수정할 문제의 SEQ(-Q) >> ");
			String strSeq = scanner.nextLine();
			if (strSeq.equalsIgnoreCase("-Q"))
				return;
			try {
				cb_seq = Long.valueOf(strSeq.trim());
			} catch (Exception e) {
				System.out.println("SEQ는 숫자로만 입력하세요!!");
				continue;
			}
			cbtDTO = cbtDao.findById(cb_seq);
			if (cbtDTO == null) {
				System.out.println("찾으려는 SEQ가 없습니다!!");
				continue;
			}
			break;
		} // end while

		System.out.print("문제입력(전에 내용은 사라짐) (-Q) >> ");
		String cb_question = scanner.nextLine();
		if (cb_question.equals("-Q"))
			return;
		if (!cb_question.trim().isEmpty()) {
			cbtDTO.setCb_question(cb_question);
		}

		System.out.print("답안1(전에 내용은 사라짐) (-Q) >> ");
		String cb_ans1 = scanner.nextLine();
		if (cb_ans1.equals("-Q"))
			return;
		if (!cb_ans1.trim().isEmpty()) {
			cbtDTO.setCb_answer1(cb_ans1);
			;
		}

		System.out.print("답안2(전에 내용은 사라짐) (-Q) >> ");
		String cb_ans2 = scanner.nextLine();
		if (cb_ans2.equals("-Q"))
			return;
		if (!cb_ans2.trim().isEmpty()) {
			cbtDTO.setCb_answer2(cb_ans2);
		}

		System.out.print("답안3(전에 내용은 사라짐) (-Q) >> ");
		String cb_ans3 = scanner.nextLine();
		if (cb_ans3.equals("-Q"))
			return;
		if (!cb_ans3.trim().isEmpty()) {
			cbtDTO.setCb_answer3(cb_ans3);
		}

		System.out.print("답안4(전에 내용은 사라짐) (-Q) >> ");
		String cb_ans4 = scanner.nextLine();
		if (cb_ans4.equals("-Q"))
			return;
		if (!cb_ans4.trim().isEmpty()) {
			cbtDTO.setCb_answer4(cb_ans4);
		}

		System.out.print("정답(전에 내용은 사라짐) (-Q) >> ");
		String cb_correctAns = scanner.nextLine();
		if (cb_correctAns.equals("-Q"))
			return;
		if (!cb_correctAns.trim().isEmpty()) {
			cbtDTO.setCb_correct_answer(cb_correctAns);
			;
		}

		System.out.println(cbtDTO.toString());
		int ret = cbtDao.update(cbtDTO);
		if (ret > 0) {
			System.out.println("업데이트 성공");
			init();
		} else {
			System.out.println("업데이트 실패!!");
		}
	}

	public void deleteQuestion() {
		CbtDTO cbtDTO = null;
		long cb_seq = -1;
		while (true) {
			System.out.print("삭제할 문제의 SEQ(-Q) >> ");
			String strSeq = scanner.nextLine();
			if (strSeq.equalsIgnoreCase("-Q"))
				return;
			try {
				cb_seq = Long.valueOf(strSeq.trim());
			} catch (Exception e) {
				System.out.println("SEQ는 숫자로만 입력하세요!!");
				continue;
			}
			cbtDTO = cbtDao.findById(cb_seq);
			if (cbtDTO == null) {
				System.out.println("찾으려는 SEQ가 없습니다!!");
				continue;
			}
			break;
		} // end while
		System.out.print("정말 삭제하시겠습니까?(Y=삭제) >> ");
		String yn = scanner.nextLine();
		if (!yn.equalsIgnoreCase("Y"))
			return;
		int ret = cbtDao.delete(cb_seq);
		if (ret > 0) {
			System.out.println("문제 삭제 성공");
			init();
		} else {
			System.out.println("문제 삭제 실패!!");
		}
	}
}
