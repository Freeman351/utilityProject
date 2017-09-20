package com.freeman.rom.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import org.hibernate.SessionFactory;

import com.freeman.j2ee.service.IBaseEntity;


public interface BaseEntityManager3 extends Serializable {
	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	public abstract IBaseEntity create(IBaseEntity transientInstance);

	public abstract void update(IBaseEntity transientInstance);

	public abstract void delete(IBaseEntity transientInstance);

	public abstract void deleteById(String className, Long id);

	public abstract void deleteByIds(String className, List<Long> ids);
	
	public abstract <E> E findById(String className, long id);

	public <E> List<E> findByHql(String hql);

	public <E> Vector<E> getObjects(String classname, String whereClaus,
			String orderClaus);

	public <E> Vector<E> getObjects(String query, Object obj);

	public <E> Vector<E> searchByHql(String hql, Object[] params);

	public <E> Vector<E> searchByHql(String hql);

	public <E> List<E> searchByHql(String hql, List<Object> params);

	public void excuteHql(String hql);

	public <E> List<E> excuteHql(String hql, String varListName,
			List<Object> variableList, List<Object> params);

	public <E> List<E> excuteHql(String hql, String[] varListNames,
			List<Object>[] variableLists, List<Object> params);
}
