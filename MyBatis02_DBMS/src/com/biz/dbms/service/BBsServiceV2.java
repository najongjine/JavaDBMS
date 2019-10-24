package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV2 {
	SqlSession sqlSession = null;
	Scanner scanner;

	public BBsServiceV2() {
		super();
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scanner = new Scanner(System.in);
	}

	public void bbsMenu() {
		int intMenu = 0;
		while (true) {
			System.out.print("내용보기(sq 입력) W.작성 U.수정 D.삭제 Q.종료 >> ");
			String strMenu = scanner.nextLine();
			if (strMenu.equalsIgnoreCase("Q"))
				return;
			else if (strMenu.equalsIgnoreCase("W")) {
				writeBBS();
				viewBBsList();
			} else if (strMenu.equalsIgnoreCase("U")) {
				updateBBS();
				viewBBsList();
			} else if (strMenu.equalsIgnoreCase("D")) {
				deleteBBS();
				viewBBsList();
			} else {
				try {
					int intSEQ=Integer.valueOf(strMenu);
					viewText(intSEQ);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
	public void viewText(long bs_id) {
		BBsDao bbsDao=sqlSession.getMapper(BBsDao.class);
		BBsDTO bbsDTO=bbsDao.findById(bs_id);
		if(bbsDTO==null) {
			System.out.println("내용이 업습니다");
			return;
		}else {
			System.out.println("======================================");
			System.out.println("제목: "+bbsDTO.getBs_subject());
			System.out.println("작성: "+bbsDTO.getBs_writer());
			System.out.println("작성일: "+bbsDTO.getBs_date());
			System.out.println("내용: "+bbsDTO.getBs_text());
			System.out.println("======================================");
		}
	}

	public void writeBBS() {
		while (true) {
			System.out.print("작성자 (-Q)>> ");
			String bs_writer = scanner.nextLine();
			if (bs_writer.equals("-Q"))
				break;
			if (bs_writer.trim().length() < 1) {
				System.out.println("작성자를 입력하세요");
				continue;
			}

			System.out.print("제목 (-Q)>> ");
			String bs_subject = scanner.nextLine();
			if (bs_subject.equals("-Q"))
				break;
			if (bs_subject.trim().length() < 1) {
				System.out.println("제목을 입력하세요");
				continue;
			}

			System.out.print("내용 (-Q)>> ");
			String bs_text = scanner.nextLine();
			if (bs_text.equals("-Q"))
				break;
			if (bs_text.trim().length() < 1) {
				System.out.println("내용을 입력하세요");
				continue;
			}

			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm:SS");

			String curDate = df.format(date);
			String curTime = tf.format(date);

			BBsDTO bbsDTO = BBsDTO.builder().bs_date(curDate).bs_subject(bs_subject).bs_text(bs_text).bs_time(curTime)
					.bs_writer(bs_writer).build();
			BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
			int ret = bbsDao.insert(bbsDTO);
			if (ret > 0) {
				System.out.println("게시판 작성 완료");
			} else {
				System.out.println("게시판 작성 실패!");
			}
			break;
		} //

	}

	public void viewBBsList() {
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = bbsDao.selectAll();

		System.out.println("=======================================");
		System.out.println("BBs V1");
		System.out.println("---------------------------------------");
		System.out.println("SQ\t작성일\t작성시간\t작성자\t제목");
		for (BBsDTO bbs : bbsList) {
			System.out.print(bbs.getBs_id() + "\t");
			System.out.print(bbs.getBs_date() + "\t");
			System.out.print(bbs.getBs_time() + "\t");
			System.out.print(bbs.getBs_writer() + "\t");
			System.out.print(bbs.getBs_subject() + "\t");
			System.out.println();
		}
		System.out.println("==============================================");

	}
	public void deleteBBS() {
		System.out.print("삭제할 SQ(-Q) >> ");
		String strID=scanner.nextLine();
		try {
			long bs_id=Long.valueOf(strID);
			viewText(bs_id);
			System.out.print("삭제할까요? (Yes,No) >> ");
			String yesNo=scanner.nextLine();
			if(yesNo.equals("Yes")) {
				BBsDao bbsDao=sqlSession.getMapper(BBsDao.class);
				bbsDao.delete(bs_id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void updateBBS() {
		viewBBsList();
		BBsDao bbsDao=sqlSession.getMapper(BBsDao.class);
		BBsDTO bbsDTO=null;
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tf = new SimpleDateFormat("HH:mm:SS");

		String curDate = df.format(date);
		String curTime = tf.format(date);
		
		long bs_id=0;
		while(true) {
			System.out.print("변경할 게시물의 ID(-Q): >> ");
			String strID=scanner.nextLine();
			if(strID.equals("-Q")) return;
			try {
				bs_id=Long.valueOf(strID);
				bbsDTO=bbsDao.findById(bs_id);
				if(bbsDTO==null) {
					System.out.println("찾는 아이디가 없습니다!");
					continue;
				}
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("id는 숫자로만 입력!");
				continue;
			}
		}//
		System.out.print("바뀔 제목 >> ");
		String bs_subject=scanner.nextLine();
		System.out.print("바뀔 내용 >> ");
		String bs_text=scanner.nextLine();
		
		bbsDTO.setBs_subject(bs_subject);
		bbsDTO.setBs_text(bs_text);
		bbsDTO.setBs_date(curDate);
		bbsDTO.setBs_time(curTime);
		
		bbsDao.update(bbsDTO);
	}

}
