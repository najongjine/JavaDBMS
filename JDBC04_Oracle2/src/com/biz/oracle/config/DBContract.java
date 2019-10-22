package com.biz.oracle.config;

public class DBContract {
	public static class SQL{
		public static final String SELECT_BOOKS=" select "
				+ "B_CODE, " + 
				"B_NAME, " + 
				"B_COMP, " + 
				"B_WRITER, " + 
				"B_PRICE "+
				" from tbl_books ";
	}
}
