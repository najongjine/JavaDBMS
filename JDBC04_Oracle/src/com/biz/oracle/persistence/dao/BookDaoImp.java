package com.biz.oracle.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.oracle.config.DBConnection;
import com.biz.oracle.config.DBContract;
import com.biz.oracle.persistence.BookDTO;

public class BookDaoImp extends BookDao {
	protected Connection dbConn=null;
	
	public BookDaoImp() {
		super();
		// TODO Auto-generated constructor stub
		dbConn=DBConnection.getDBConnection();
	}
	private BookDTO rstTO_DTO(ResultSet rst) throws SQLException {
		BookDTO bookDTO=BookDTO.builder().b_code(rst.getString("b_code"))
				.b_comp(rst.getString("b_comp"))
				.b_name(rst.getString("b_name"))
				.b_price(rst.getInt("b_price"))
				.b_writer(rst.getString("b_writer"))
				.build();
		return bookDTO;
	}
	@Override
	public List<BookDTO> selectALL() {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		BookDTO bookDTO=null;
		String sql=DBContract.SQL.SELECT_BOOKS;
		try {
			/*
			 * sql 문자열을 jdbc드라이버를 통해 dbms로 전송하기 위한
			 * 데이터형으로 변환하는 절차.
			 */
			pStr=dbConn.prepareStatement(sql);
			
			/*
			 * DBMS에게 지금보낸 sql을 실행하여 얻어진 결과를 달라.
			 */
			ResultSet rst=pStr.executeQuery();
			
			List<BookDTO> bookList=new ArrayList<BookDTO>();
			while(rst.next()) {
				//빌더써서 DTO셋팅해주고 DTO넘겨주는데, builder는 자동으로 new
				//키워드가 실행되서 새로운 객체로 만들어 지는거임
				bookDTO=rstTO_DTO(rst);
				bookList.add(bookDTO);
			}
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BookDTO findById(String b_code) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.SELECT_BOOKS;
		sql+=" where b_code=? ";
		
		BookDTO bookDTO=null;
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, b_code);
			ResultSet rst=pStr.executeQuery();
			if(rst.next()) {
				bookDTO=rstTO_DTO(rst);
			}
			rst.close();
			pStr.close();
			return bookDTO;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<BookDTO> findByName(String b_name) {
		// TODO Auto-generated method stub
		BookDTO bookDTO=null;
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.SELECT_BOOKS;
		sql+=" where b_name like '%' || ? || '%' "; // || == +
		//코드에선 문자열 결합 방식으로 해줘야함
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, b_name);
			ResultSet rst=pStr.executeQuery();
			
			List<BookDTO> bookList=new ArrayList<BookDTO>();
			while(rst.next()) {
				//rst는 각 실행마다 튜플 하나만 가져오니, 튜플 하나의 데이터만
				//DTO형식에 맞게 셋팅
				bookDTO=rstTO_DTO(rst);
				bookList.add(bookDTO);
			}
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BookDTO> findByComp(String b_comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int b_price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int sprice, int eprice) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=DBContract.SQL.SELECT_BOOKS;
		sql+= " where b_price between ? and ? ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setInt(1, sprice);
			pStr.setInt(2, eprice);
			ResultSet rst=pStr.executeQuery();
			List<BookDTO> bookList=new ArrayList<BookDTO>();
			while(rst.next()) {
				bookList.add(rstTO_DTO(rst));
			}
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=" insert into tbl_books( ";
		sql+= " B_CODE, ";
		sql+= " B_NAME, ";
		sql+= " B_COMP, ";
		sql+= " B_WRITER, ";
		sql+= " B_PRICE) ";
		sql+= " values( "
				// 채우기 방향을 <- 로 하겠단 뜻.
				// B0001 - > B0011 -> B0111 이렇게
				+ "'B'||LPAD(SEQ_BOOKS.NEXTVAL,4,'0'), "
				+ "?,?,?,?) ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, bookDTO.getB_name());
			pStr.setString(2, bookDTO.getB_name());
			pStr.setString(3, bookDTO.getB_writer());
			pStr.setInt(4, bookDTO.getB_price());
			
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
	public int update(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String b_code) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=" delete from tbl_books ";
		sql+=" where b_code=? ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, b_code);
			int ret=pStr.executeUpdate();
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
