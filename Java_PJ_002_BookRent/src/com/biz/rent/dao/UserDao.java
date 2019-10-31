package com.biz.rent.dao;

import java.util.List;

import com.biz.rent.persistence.UserDTO;

public interface UserDao {
	public List<UserDTO> selectAll();
	public UserDTO findById(String u_code);
	public List<UserDTO> findByUserName(String u_name);
	public List<UserDTO> findByUserTel(String u_tel);
	public String getMaxCode();
	public int insert(UserDTO userDTO);
	public int update(UserDTO userDTO);
	public int delete(String u_code);
}
