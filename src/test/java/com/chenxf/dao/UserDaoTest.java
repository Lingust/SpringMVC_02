package com.chenxf.dao;

import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chenxf.domain.TUser;

public class UserDaoTest extends JUnitDaoBase{
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testUserDao(){
		List<TUser> list = userDao.getAllUser();
		
		assertEquals("admin", list.get(0).getUserName());
	}
}
