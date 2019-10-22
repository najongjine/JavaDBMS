package com.biz.addr.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.SQL.SQL;
import com.biz.addr.persistence.AddrDTO;

public class AddrDaoImp extends AddrDao {

	private AddrDTO rstTO_DTO(ResultSet rst) throws SQLException {
		AddrDTO addrDTO=AddrDTO.builder().id(rst.getLong("id"))
				.name(rst.getString("name"))
				.tel(rst.getString("tel"))
				.chain("chain")
				.addr("addr")
				.build();
		return addrDTO;
	}//end
	@Override
	public AddrDTO findById(long id) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=SQL.SELECT_ADDR;
		sql+=" where id=? ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			ResultSet rst=pStr.executeQuery();
			
			AddrDTO addrDTO=null;
			while(rst.next()) {
				addrDTO=rstTO_DTO(rst);
			}
			rst.close();
			pStr.close();
			return addrDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}// end

	@Override
	public List<AddrDTO> findByName(String name) {
		PreparedStatement pStr=null;
		String sql=SQL.SELECT_ADDR;
		sql+=" where name=? ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst=pStr.executeQuery();
			
			List<AddrDTO> addrList=new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO addrDTO=rstTO_DTO(rst);
				addrList.add(addrDTO);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}//end

	@Override
	public List<AddrDTO> findByAddr(String addr) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=SQL.SELECT_ADDR;
		sql+=" where addr like '%' || ? || '%' ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, addr);
			ResultSet rst=pStr.executeQuery();
			
			List<AddrDTO> addrList=new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO addrDTO=rstTO_DTO(rst);
				addrList.add(addrDTO);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AddrDTO> findByTel(String tel) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=SQL.SELECT_ADDR;
		sql+=" where tel like '%' || ? || '%'  ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, tel);
			ResultSet rst=pStr.executeQuery();
			
			List<AddrDTO> addrList=new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO addrDTO=rstTO_DTO(rst);
				addrList.add(addrDTO);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AddrDTO> findByChain(String chain) {
		// TODO Auto-generated method stub
		PreparedStatement pStr=null;
		String sql=SQL.SELECT_ADDR;
		sql+=" where chain % || ? || % ";
		
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, chain);
			ResultSet rst=pStr.executeQuery();
			
			List<AddrDTO> addrList=new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO addrDTO=rstTO_DTO(rst);
				addrList.add(addrDTO);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insert(AddrDTO addrDTO) {
		String sql=" insert into tbl_addr( ";
		sql+="ID, ";
		sql+="NAME, ";
		sql+="TEL, ";
		sql+="ADDR, ";
		sql+="CHAIN) ";
		sql+=" values (SEQ_ADDR.nextval,?,?,?,?) ";
		PreparedStatement pStr=null;
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, addrDTO.getName());
			pStr.setString(2, addrDTO.getTel());
			pStr.setString(3, addrDTO.getAddr());
			pStr.setString(4, addrDTO.getChain());
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
	public int update(AddrDTO addrDTO) {
		String sql=" update tbl_addr set ";
		sql+=" name = ?, ";
		sql+=" tel = ?, ";
		sql+=" addr = ?, ";
		sql+=" chain = ? ";
		sql+=" where ID=? ";
		PreparedStatement pStr=null;
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setString(1, addrDTO.getName());
			pStr.setString(2, addrDTO.getTel());
			pStr.setString(3, addrDTO.getAddr());
			pStr.setString(4, addrDTO.getChain());
			pStr.setLong(5, addrDTO.getId());
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
	public int delete(long id) {
		String sql= " delete from tbl_addr ";
		sql+=" where id=? ";
		PreparedStatement pStr=null;
		try {
			pStr=dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			int ret=pStr.executeUpdate();
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}// end
	@Override
	public List<AddrDTO> selectALL() {
		PreparedStatement pStr=null;
		String sql=SQL.SELECT_ADDR;
		
		try {
			pStr=dbConn.prepareStatement(sql);
			ResultSet rst=pStr.executeQuery();
			
			List<AddrDTO> addrList=new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO addrDTO=rstTO_DTO(rst);
				addrList.add(addrDTO);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
