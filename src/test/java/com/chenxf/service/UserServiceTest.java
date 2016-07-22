package com.chenxf.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.chenxf.domain.TUser;

public class UserServiceTest extends ServiceTestBase {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserService(){
		TUser u = userService.getUserByUserName("admin");
		//System.out.println(u.getUserName());
		Assert.assertEquals("admin",u.getUserName());
	}
	
	@Test
	public void testInsertUser(){
		TUser user = new TUser();
		user.setUserName("chen");
		user.setPasswd("xiaofeng");
		userService.insertUser(user);
		TUser dbUser = userService.getUserByUserName("chen");
		assertEquals("chen",dbUser.getUserName());
		assertEquals("xiaofeng", dbUser.getPasswd());
	}
}
