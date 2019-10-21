package com.biz.grade.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.ScoreDao;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;

public class ScoreDaoImp extends ScoreDao{

	
	@Override
	public List<ScoreVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreVO findById(long id) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.SELECT_VIEW_SCORE;
		sql+=" where s_id=? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			
			ResultSet rst=pStr.executeQuery();
			ScoreVO scoreVO=null;
			if(rst.next()) {
				scoreVO=rstTOScoreVO(rst);
			}
			rst.close();
			pStr.close();
			return scoreVO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<ScoreVO> findByStName(String stName) {
		// TODO Auto-generated method stub
		return null;
	}
	private ScoreVO rstTOScoreVO(ResultSet rst) throws Exception {
		ScoreVO scoreVO=ScoreVO.builder().s_id(rst.getLong("S_ID"))
		.s_score(rst.getInt("S_SCORE"))
		.s_std(rst.getString("S_STD"))
		.s_subject(rst.getString("S_SUBJECT"))
		.sb_name(rst.getString("SB_NAME"))
		.st_dept(rst.getString("ST_DEPT"))
		.st_grade(rst.getInt("ST_GRADE"))
		.st_name(rst.getString("ST_NAME"))
		.d_name(rst.getString("D_NAME"))
		.d_tel(rst.getString("D_TEL"))
		.build();
		
		return scoreVO;
	}
	@Override
	public int insert(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=" insert into tbl_score( ";
		sql+=" S_ID, ";
		sql+=" S_SCORE, ";
		sql+=" S_REM, ";
		sql+=" S_SUBJECT, ";
		sql+=" S_STD) ";
		sql+=" values(?,?,?,?,?) ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1,scoreDTO.getS_id());
			pStr.setInt(2,scoreDTO.getS_score());
			pStr.setString(3,scoreDTO.getS_rem());
			pStr.setString(4,scoreDTO.getS_subject());
			pStr.setString(5,scoreDTO.getS_std());
			int ret=pStr.executeUpdate();
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(ScoreDTO scoreDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreVO> findByStNum(String strStNum) {
		// TODO Auto-generated method stub
			PreparedStatement pStr=null;
			String sql=DBContract.SQL.SELECT_VIEW_SCORE;
			sql+=" where s_std=? ";
			try {
				pStr=dbConn.prepareStatement(sql);
				pStr.setString(1, strStNum);
				List<ScoreVO> scList=new ArrayList<ScoreVO>();
				ResultSet rst=pStr.executeQuery();
				while(rst.next()) {
					scList.add(rstTOScoreVO(rst));
				}
				rst.close();
				pStr.close();
				return scList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<ScoreVO> findBySubject(String strSubject) {
		// TODO Auto-generated method stub
		return null;
	}

}
