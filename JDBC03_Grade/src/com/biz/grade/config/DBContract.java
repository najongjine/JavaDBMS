package com.biz.grade.config;

public class DBContract {
	public static class DBConn{
		public static final String JdbcDriver="oracle.jdbc.OracleDriver";
		public static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
		public static final String USER="grade";
		public static final String PASSWORD="grade";
	}
	public static class SQL{
		public static final String SELECT_SCORE= " select S_ID, " + 
				" S_SCORE, " + 
				" S_REM, " + 
				" S_SUBJECT, " + 
				" S_STD " +
				" from tbl_score ";
		public static final String SELECT_VIEW_SCORE= " select "
				+ " S_ID, " + 
				" S_STD, " + 
				" ST_NAME, " + 
				" ST_GRADE, " + 
				" ST_DEPT, " + 
				" D_NAME, " + 
				" D_TEL, " + 
				" S_SUBJECT, " + 
				" SB_NAME, " + 
				" S_SCORE "
				+" from view_score ";
		public static final String INSERT_SCORE=" insert into tbl_score( "
				+ "S_ID, " + 
				" S_STD, "+
				" S_SUBJECT, "+
				" S_SCORE, " + 
				" S_REM) " + 
				" values(?,?,?,?,?) ";
	}
}
