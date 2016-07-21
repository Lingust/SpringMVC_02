package com.chenxf.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chenxf.domain.TUser;

@Repository
public class UserDao extends BaseDao<TUser> {
	private final String GET_USER_BY_USERNAME="from TUser t where t.userName=:username";
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//查询并返回所有用户
	public List<TUser> getAllUser(){
		return (List<TUser>)loadAll();
	}
	
	//根据用户名查询用户
	@SuppressWarnings("unchecked")
	public TUser getUserByUsername(String name){
		List<TUser> users = (List<TUser>) getHibernateTemplate().findByNamedParam(GET_USER_BY_USERNAME, "username", name);
		if(users.isEmpty())
			return null;
		else
			return users.get(0);
	}
}
