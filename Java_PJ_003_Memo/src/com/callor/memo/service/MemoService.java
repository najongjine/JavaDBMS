package com.callor.memo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.callor.memo.DBConnection.DBConnection;
import com.callor.memo.dao.MemoDao;
import com.callor.memo.persistence.MemoDTO;

public class MemoService {
	MemoDao memoDao = null;
	SqlSession sqlSession = null;
	Scanner scanner;
	List<MemoDTO> memoList;
	MemoDTO memoDTO;

	public MemoService() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		memoDao = sqlSession.getMapper(MemoDao.class);
		scanner = new Scanner(System.in);
	}
	
	public void memoMenu() {
		while(true) {
			System.out.print("1.전체조회 2.제목검색 3.내용검색 4.ID 검색 5.메모추가 6.메모수정 7.메모삭제 0.종료 >> ");
			String strMenu=scanner.nextLine();
			if(strMenu.equalsIgnoreCase("1")) {
				viewAll();
			}
			else if(strMenu.equalsIgnoreCase("2")) {
				findBySubj();
			}
			else if(strMenu.equalsIgnoreCase("3")) {
				findByText();
			}
			else if(strMenu.equalsIgnoreCase("4")) {
				findByID();
			}
			else if(strMenu.equalsIgnoreCase("5")) {
				insert();
			}
			else if(strMenu.equalsIgnoreCase("6")) {
				update();
			}
			else if(strMenu.equalsIgnoreCase("7")) {
				delete();
			}
			else if(strMenu.equalsIgnoreCase("0")) {
				return;
			}else {
				System.out.println("메뉴 선택을 잘못하셨습니다!!");
			}
		}
		
	}//end menu

	public void viewAll() {
		memoList = memoDao.selectAll();
		if(memoList.size()<1) {
			System.out.println("아직 추가된 메모가 하나도 없습니다.");
			return;
		}
		for (MemoDTO dto : memoList) {
			System.out.println(dto.toString());
		}
	}

	public void findByID() {
		memoDTO = null;
		long id = -1;
		while (true) {
			System.out.print("아이디를 입력하세요 (-Q) >> ");
			String strID = scanner.nextLine();
			if (strID.equalsIgnoreCase("-Q"))
				return;
			try {
				id = Long.valueOf(strID);
			} catch (Exception e) {
				System.out.println("아이디는 숫자만 입력하세요!");
				continue;
			}
			break;
		} // end while
		memoDTO = memoDao.findById(id);
		if (memoDTO == null) {
			System.out.println("찾으려는 memo의 ID가 없습니다!!");
			return;
		}
		System.out.println(memoDTO.toString());
	}// end

	public void findBySubj() {
		memoDTO = null;
		memoList = null;
		String m_subject = "";

		System.out.print("찾으려는 제목 입력 (-Q) >> ");
		m_subject = scanner.nextLine();
		if (m_subject.equalsIgnoreCase("-Q"))
			return;

		memoList = memoDao.findBySubj(m_subject);
		if (memoList.size()<1) {
			System.out.println("찾으려는 제목 없음!!");
			return;
		}
		showList(memoList);
	}// end

	public void findByText() {
		memoDTO = null;
		memoList = null;
		String m_text = "";

		System.out.print("찾으려는 내용 입력 (-Q) >> ");
		m_text = scanner.nextLine();
		if (m_text.equalsIgnoreCase("-Q"))
			return;

		memoList = memoDao.findBySubj(m_text);
		if (memoList.size()<1) {
			System.out.println("찾으려는 내용 없음!!");
			return;
		}
		showList(memoList);
	}// end

	protected void showList(List<MemoDTO> memoList) {
		for (MemoDTO dto : memoList) {
			System.out.println(dto.toString());
		}
	}//

	public void insert() {
		memoDTO = null;
		memoList = null;
		System.out.println("============================================================");
		System.out.println("메모 추가 시작");
		System.out.println("------------------------------------------------------------");
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String m_date = localDate.format(df);

		System.out.print("작성자(-Q) >> ");
		String m_auth = scanner.nextLine();
		if(m_auth.equalsIgnoreCase("-Q")) return;

		System.out.print("메모의 제목 >> ");
		String m_subject = scanner.nextLine();
		if(m_subject.equalsIgnoreCase("-Q")) return;

		System.out.print("내용 입력 >> ");
		String m_text = scanner.nextLine();
		if(m_text.equalsIgnoreCase("-Q")) return;

		memoDTO = MemoDTO.builder().m_auth(m_auth).m_date(m_date).m_subject(m_subject).m_text(m_text).build();
		int ret = memoDao.insert(memoDTO);
		if (ret > 0)
			System.out.println("메모 추가 성공");
		else
			System.out.println("메모 추가 실패!!");
	}//

	public void update() {
		memoDTO = null;
		memoList = null;
		long id=-1;
		
		findBySubj();
		while(true) {
			System.out.print("수정할 메모의 ID (-Q) >> ");
			String strID=scanner.nextLine();
			if(strID.equalsIgnoreCase("-Q")) return;
			try {
				id=Long.valueOf(strID);
			} catch (Exception e) {
				System.out.println("ID는 숫자만 입력!!");
				continue;
			}
			memoDTO=memoDao.findById(id);
			if(memoDTO==null) {
				System.out.println("찾으려는 MEMO의 아이디가 없습니다!!");
				continue;
			}
			break;
		}
		

		System.out.printf("수정할 메모의 제목(%s) >> ",memoDTO.getM_subject());
		String m_subject = scanner.nextLine();
		if(m_subject.equalsIgnoreCase("-Q")) return;
		if(!m_subject.trim().isEmpty()) {
			memoDTO.setM_subject(m_subject);
		}

		System.out.printf("수정할 내용 입력(%s) >> ",memoDTO.getM_text());
		String m_text = scanner.nextLine();
		if(m_text.equalsIgnoreCase("-Q")) return;
		if(!m_text.trim().isEmpty()) {
			memoDTO.setM_text(m_text);
		}

		int ret = memoDao.update(memoDTO);
		if (ret > 0)
			System.out.println("메모 수정 성공");
		else
			System.out.println("메모 수정 실패!!");
	}//end update
	
	public void delete() {
		memoDTO = null;
		memoList = null;
		long id=-1;
		
		findBySubj();
		while(true) {
			System.out.print("삭제할 메모의 ID (-Q) >> ");
			String strID=scanner.nextLine();
			if(strID.equalsIgnoreCase("-Q")) return;
			try {
				id=Long.valueOf(strID);
			} catch (Exception e) {
				System.out.println("ID는 숫자만 입력!!");
				continue;
			}
			memoDTO=memoDao.findById(id);
			if(memoDTO==null) {
				System.out.println("찾으려는 MEMO의 아이디가 없습니다!!");
				continue;
			}
			break;
		}//end while
		
		int ret=memoDao.delete(id);
		if (ret > 0)
			System.out.println("메모 삭제 성공");
		else
			System.out.println("메모 삭제 실패!!");
	}//end delete
}
