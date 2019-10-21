package com.biz.grade.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.SubjectDao;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public class SubjectDaoImp extends SubjectDao{
	
	private SubjectDTO rstSubjToDTO(ResultSet rst) throws SQLException {
		SubjectDTO subDTO=SubjectDTO.builder().sb_code(rst.getString("SB_CODE"))
				.sb_name(rst.getString("SB_NAME"))
				.sb_pro(rst.getString("SB_PRO"))
				.build();
		return subDTO;
	}

	@Override
	public List<SubjectDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectDTO findById(String sb_code) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.SELECT_SUBJECT;
		sql+=" where sb_code=? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1,  sb_code.toUpperCase());
			ResultSet rst=pStr.executeQuery();
			
			SubjectDTO subDTO=null;
			if(rst.next()) {
				subDTO=rstSubjToDTO(rst);
			}
			rst.close();
			pStr.close();
			return subDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SubjectDTO> findByName(String sb_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubjectDTO> findByPro(String sb_pro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(SubjectDTO subjectDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SubjectDTO subjectDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String sb_code) {
		// TODO Auto-generated method stub
		return 0;
	}
}
