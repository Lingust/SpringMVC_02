package com.chenxf.service;

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
}
