package com.biz.rent.exec;

import java.util.List;

import com.biz.iolist.DBConnection.config.DBConnection;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.UserDTO;
import com.biz.rent.service.BookService;
import com.biz.rent.service.RentService;

public class Test {
	public static void main(String[] args) {
		RentService rs=new RentService();
		rs.menu();
	}
}
