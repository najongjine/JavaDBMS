package com.biz.iolist.mybatis.exec;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatCheck01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		try {
			sd.parse("2019-0101");
		} catch (ParseException e) {
			System.out.println("날짜 형식이 잘못 되었습니다.");
		}
	}

}
