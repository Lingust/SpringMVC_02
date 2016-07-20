package com.chenxf.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chenxf.domain.TUser;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<TUser> getAllUser(){
		String hsql = "from TUser t";
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hsql);
		
		return query.list();
	}
}
