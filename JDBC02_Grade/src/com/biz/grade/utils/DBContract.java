package com.biz.grade.utils;

public class DBContract {
	public static class TABLE{
		public static final String SCORE=" tbl_score ";
		public static final String STUDENT=" tbl_student ";
		public static final String DEPT=" tbl_dept ";
	}
	public static class SQL{
		public static final String SCORE_SELECT=" select S_ID,S_STD,S_SUBJECT,S_SCORE,S_REM "
				+" from tbl_score ";
		public static final String STUDENT_SELECT=" select ST_NUM, " + 
				" ST_NAME, " + 
				" ST_TEL, " + 
				" ST_ADDR, " + 
				" ST_GRADE, " + 
				" ST_DEPT "
				+" from tbl_student";
	}
	public static class VIEW{
		public static final String SCORE=" view_score ";
		public static final String SCORE_PV=" view_score_pv ";
	}
	public static class DBCONN{
		public static final String JDBCDRIVER="oracle.jdbc.OracleDriver";
		public static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
		public static final String USER="grade";
		public static final String PASSWORD="grade";
	}
}
