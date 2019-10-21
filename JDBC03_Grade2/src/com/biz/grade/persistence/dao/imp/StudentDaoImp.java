package com.biz.grade.persistence.dao.imp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.dao.StudentDao;
import com.biz.grade.persistence.domain.DeptDTO;
import com.biz.grade.persistence.domain.StudentDTO;
import com.biz.grade.persistence.domain.SubjectDTO;

public class StudentDaoImp extends StudentDao {

	@Override
	public List<StudentDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO findById(String st_num) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.SELECT_STUDENT;
		sql+=" where st_num=? ";
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, st_num.toUpperCase());
			ResultSet rst=pStr.executeQuery();
			
			StudentDTO stdDTO=null;
			if(rst.next()) {
				stdDTO=rstStdToDTO(rst);
			}
			rst.close();
			pStr.close();
			return stdDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private StudentDTO rstStdToDTO(ResultSet rst) throws SQLException {
		StudentDTO stdDTO=StudentDTO.builder().st_addr(rst.getString("ST_ADDR"))
				.st_dept(rst.getString("ST_DEPT"))
				.st_grade(rst.getInt("ST_GRADE"))
				.st_name(rst.getString("ST_NAME"))
				.st_num(rst.getString("ST_NUM"))
				.st_tel(rst.getString("ST_TEL"))
				.build();
		return stdDTO;
	}
	
	private DeptDTO rstDeptToDTO(ResultSet rst) throws SQLException {
		DeptDTO deptDTO=DeptDTO.builder().d_name(rst.getString("D_NAME"))
				.d_num(rst.getString("D_NUM"))
				.d_pro(rst.getString("D_PRO"))
				.d_tel(rst.getString("D_TEL"))
				.build();
		return deptDTO;
	}
	@Override
	public List<StudentDTO> findByName(String st_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDTO> findByGrade(int st_grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(StudentDTO studentDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String st_num) {
		// TODO Auto-generated method stub
		return 0;
	}

}
