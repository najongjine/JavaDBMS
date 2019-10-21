package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract.DBConn;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
/*
 * Dao(Data Access Object) class:= service 클래스의 연산기능 중에서
 * 순수하게 JDBC와 연동하여 직접 DB를 읽고(select),
 * DB를 update 수행하는 기능을 service로 부터 분리.
 * 
 * service 클래스는 business 로직만 담당하는 역활 수행.
 *  사용자로부터 어떤 데이터를 입력받고, 결과를 보여주는 용도.
 *  main()와 Dao클래스 사이에서 연산을 주도적으로 수행한다.
 *  main()입력된 데이터 -> service에서 가공, 검증
 *  ->Dao에서 update 수행
 * Dao에서 select한 data-> Service에서 다양한 방법으로 가공 view를 수행.
 */
public abstract class ScoreDao {
	protected Connection dbConn=null;
	
	public ScoreDao() {
		dbConn=DBConnection.getDBConnection();
	}
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	public abstract List<ScoreVO> findByStName(String stName);
	public abstract int insert(ScoreDTO scoreDTO);
	public abstract int update(ScoreDTO scoreDTO);
	public abstract int delete(long id);

	public abstract List<ScoreVO> findByStNum(String strStNum);

	public abstract List<ScoreVO> findBySubject(String strSubject);

}
