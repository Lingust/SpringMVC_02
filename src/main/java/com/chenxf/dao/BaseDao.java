package com.chenxf.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.util.Assert;

public abstract class BaseDao<T> {
	//一个待操作的数据表类的对象
	private Class<T> entityClass;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public Session getSession(){
		return getHibernateTemplate().getSessionFactory().openSession();
	}

	/**
	 * 通过反射获取继承此类的子类的泛型类T
	 */
	@SuppressWarnings("unchecked")
	public BaseDao(){
		Type genType = getClass().getGenericSuperclass();
		/*if(!(genType instanceof ParameterizedType))
			return Object.class;*/
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		//根据索引确定泛型类，此处只有一个，所以索引为0
		entityClass = (Class<T>) params[0];
	}
	
	/**
	 * 获取所有PO对象
	 * @return List
	 */
	public List<T> loadAll(){
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	/**
	 * 保存PO对象
	 * @param entity
	 */
	public void save(T entity){
		getHibernateTemplate().save(entity);
	}
	
	/**
	 * 删除PO对象
	 * @param entity
	 */
	public void remove(T entity){
		getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 修改PO对象
	 * @param entity
	 */
	public void update(T entity){
		getHibernateTemplate().update(entity);
	}
	
	/**
	 * 执行带参HQL查询
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<?> find(String hql, Object...params){
		return getHibernateTemplate().find(hql, params);
	}
	
	/**
	 * 延迟加载，具体用途不明
	 * @param entity
	 */
	public void initialize(Object entity){
		this.getHibernateTemplate().initialize(entity);
	}
	
	//分页查询，去除select, order by, 稍后补充
	
	/**
	 * 通过HQL语句创建自定义Query对象，包含查出
	 * 的对象
	 * @param hql
	 * @param values
	 * @return Query
	 */
	public Query createQuery(String hql, Object...values){
		Assert.hasText(hql);	//留意此处，不用if判断
		Query query = (Query) getSession().createQuery(hql);
		for(int i=0;i<values.length;i++){
			query.setParameter(i, values[i]);
		}
		return query;
	}
	
}
