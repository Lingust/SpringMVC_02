package com.chenxf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxf.dao.UserDao;
import com.chenxf.domain.TUser;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据用户名执行查询
	 * @param username
	 * @return
	 */
	public TUser getUserByUserName(String username){
		return userDao.getUserByUsername(username);
	}
}
