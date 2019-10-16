package com.biz.grade.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

public class StudentServiceV1 extends StudentService {

	@Override
	public int insert(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO findById(String num) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.STUDENT_SELECT;
		sql+=" where st_num=? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, num);
			ResultSet rst=pStr.executeQuery();
			
			StudentDTO sDTO=null;
			if(rst.next()) {
				sDTO=this.rstTOdto(rst);
			}else {
				sDTO=null;
			}
		rst.close();
		dbConn.close();
		return sDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentDTO> findByName(String name) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.STUDENT_SELECT;
		sql+=" where st_name=? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst=pStr.executeQuery();
			
			List<StudentDTO> stdList=new ArrayList<StudentDTO>();
			while(rst.next()) {
				stdList.add(StudentDTO.builder().st_addr(rst.getString("ST_ADDR"))
						.st_dept(rst.getString("ST_DEPT"))
						.st_grade(rst.getInt("ST_GRADE"))
						.st_name(rst.getString("ST_NAME"))
						.st_num(rst.getString("ST_NUM"))
						.st_tel(rst.getString("ST_TEL")).build());
			}
			rst.close();
			dbConn.close();
			return stdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}//

	@Override
	public List<StudentDTO> findBySubject(String subject) {
		// TODO Auto-generated method stub
		this.dbConnection();
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.STUDENT_SELECT;
		sql+=" where st_subject=? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, subject);
			ResultSet rst=pStr.executeQuery();
			
			List<StudentDTO> stdList=new ArrayList<StudentDTO>();
			while(rst.next()) {
				stdList.add(StudentDTO.builder().st_addr(rst.getString("ST_ADDR"))
						.st_dept(rst.getString("ST_DEPT"))
						.st_grade(rst.getInt("ST_GRADE"))
						.st_name(rst.getString("ST_NAME"))
						.st_num(rst.getString("ST_NUM"))
						.st_tel(rst.getString("ST_TEL")).build());
			}
			rst.close();
			dbConn.close();
			return stdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	private StudentDTO rstTOdto(ResultSet rst) throws SQLException {
		return StudentDTO.builder().st_addr(rst.getString("ST_ADDR"))
				.st_dept(rst.getString("ST_DEPT"))
				.st_grade(rst.getInt("ST_GRADE"))
				.st_name(rst.getString("ST_NAME"))
				.st_num(rst.getString("ST_NUM"))
				.st_tel(rst.getString("ST_TEL")).build();
	
	}

}
