package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class JdbcEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcDriver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userName="grade";
		String password="grade";
		Connection dbConn=null;
		PreparedStatement pStr=null;
		List<ScoreVO> scoreList=null;
		
		try {
			Class.forName(jdbcDriver);
			dbConn=DriverManager.getConnection(url,userName,password);
			String sql="select * from tbl_score";
			pStr=dbConn.prepareStatement(sql);
			ResultSet rst=pStr.executeQuery();
			scoreList=new ArrayList<ScoreVO>();
			while(rst.next()) {
				ScoreVO sVO=new ScoreVO();
				sVO.setS_id(rst.getInt(1));
				sVO.setS_std(rst.getString(2));
				sVO.setS_score(rst.getInt(3));
				scoreList.add(sVO);
			}
			rst.close();
			dbConn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("===============================");
		System.out.println("성적일람표");
		System.out.println("--------------------------------");
		System.out.println("ID\t학생\t점수\t비고");
		System.out.println("-----------------------------------");
		for(ScoreVO svo:scoreList) {
			System.out.printf("%d\t%s\t%d\t%s\n",svo.getS_id(),svo.getS_std(),
					svo.getS_score(),svo.getS_rem());
		}
	}

}
