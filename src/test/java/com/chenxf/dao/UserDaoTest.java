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
	public void testQueryUser(){
		List<TUser> list = userDao.getAllUser();
		TUser user = userDao.getUserByUsername("admin");
		assertEquals("admin", list.get(0).getUserName());
		assertEquals("admin", user.getUserName());
	}
	
	//@Test
	public void testInsertUser(){
		TUser user = new TUser();
		user.setUserName("chen");
		user.setPasswd("xiaofeng");
		userDao.save(user);	//因为事务定义在Service层，若未设置事务默认操作为read-only，不可写入
							//此处测试不能通过
		TUser dbUser = userDao.getUserByUsername("chen");
		assertEquals("chen",dbUser.getUserName());
		assertEquals("xiaofeng", dbUser.getPasswd());
	}
}