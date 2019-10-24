package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV1 {
	SqlSession sqlSession = null;
	Scanner scanner;

	public BBsServiceV1() {
		super();
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scanner = new Scanner(System.in);
	}

	public void bbsMenu() {
		int intMenu = 0;
		while (true) {
			System.out.println("1. 내용보기 2.작성 3.수정 4.삭제 0.종료");
			String strMenu = scanner.nextLine();
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				System.out.println("0, 1~4 까지 선택");
				continue;
			}
			if (intMenu == 0)
				return;
			else if (intMenu == 1) {
				
			} else if (intMenu == 2) {

			} else if (intMenu == 3) {

			} else if (intMenu == 4) {

			}
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
			System.out.print("계속작성? (y/n) >> ");
			String yesNo = scanner.nextLine();
			if (yesNo.equalsIgnoreCase("n") || yesNo.equalsIgnoreCase("no"))
				break;
		} //

	}

	public void viewBBsList() {
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = bbsDao.selectAll();

		System.out.println("=======================================");
		System.out.println("BBs V1");
		System.out.println("---------------------------------------");
		System.out.println("작성일\t작성시간\t작성자\t제목");
		for (BBsDTO bbs : bbsList) {
			System.out.print(bbs.getBs_date() + "\t");
			System.out.print(bbs.getBs_time() + "\t");
			System.out.print(bbs.getBs_writer() + "\t");
			System.out.print(bbs.getBs_subject() + "\t");
			System.out.println();
		}

	}

}
