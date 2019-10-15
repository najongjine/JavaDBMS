package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

import lombok.Getter;
@Getter
public class ScoreJDBCServiceV3 {
	protected Connection dbConn=null;
	protected PreparedStatement pStr=null;
	protected List<ScoreVO> scoreList=null;
	
	protected void dbConnection() {
		try {
			Class.forName(DBConstract.DB_INFO.JdbcDriver);
			dbConn=DriverManager.getConnection(DBConstract.DB_INFO.URL,
					DBConstract.DB_INFO.USER,DBConstract.DB_INFO.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//
	public List<ScoreVO > selectAll(){
		String sql="select * from tbl_score";
		dbConnection();
		try {
			pStr=dbConn.prepareStatement(sql);
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.scoreList;
	}
	public List<ScoreVO> findById(int s_id){
		String sql=" select * from tbl_score ";
		sql+=" where s_id= ? ";
		
		dbConnection();
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setInt(1, s_id);//setInt(? 위치, 타겟)
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.scoreList;
	}//
	public List<ScoreVO> findById(int s_id, int e_id){
		String sql=" select * from tbl_score ";
		sql+=" where s_id= between ? and ? ";
		
		dbConnection();
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setInt(1, s_id);//setInt(? 위치, 요거 붙여라)
			pStr.setInt(2, e_id);//setInt(? 위치, 요거 붙여라)
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.scoreList;
	}//
	public List<ScoreVO> findByName(String s_name){
		String sql=" select * from tbl_score ";
		sql+=" where s_std= ? ";
		dbConnection();
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, s_name);
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.scoreList;
	}
	
	//resultset에서 데이터를 추출하여 List로 변환
	public void setScoreList(PreparedStatement pStr) throws SQLException {
		scoreList=new ArrayList<ScoreVO>();
		ResultSet rst=pStr.executeQuery();
		while(rst.next()) {
			ScoreVO sVO=ScoreVO.builder().s_id(rst.getInt(DBConstract.SCORE.S_ID))
					.s_rem(rst.getString(DBConstract.SCORE.S_REM))
					.s_score(rst.getInt(DBConstract.SCORE.S_SCORE))
					.s_std(rst.getString(DBConstract.SCORE.S_STD))
					.build();
			scoreList.add(sVO);
		}
		rst.close();
	}//
	
	public int insert(ScoreVO scoreVO) {
		String sql=" insert into tbl_score ";
				sql+=" (s_id, s_std, s_subject, s_score, s_rem) ";
				sql+=" values(?,?,001,?,?) ";
		dbConnection();
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1, scoreVO.getS_id());
			pStr.setString(2, scoreVO.getS_std());
			pStr.setInt(3, scoreVO.getS_score());
			pStr.setString(4, scoreVO.getS_rem());
			int ret=pStr.executeUpdate();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
