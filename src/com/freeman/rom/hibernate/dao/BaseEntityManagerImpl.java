package com.freeman.rom.hibernate.dao;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.freeman.j2ee.service.IBaseEntity;
import com.freeman.utilities.ConvertUtil;


public class BaseEntityManagerImpl implements BaseEntityManager {

	private static Logger log = Logger.getLogger(BaseEntityManagerImpl.class);
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public <E> E findById(String className, long id) {
		try {
			Session session= this.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			E instance = (E) session.get(className, id);
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> findByHql(String hql) {
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		List<E> result = (List<E>) query.list();
		tx.commit();
		return result;
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> searchByHql(String hql, List<Object> params) {
		if (params == null || params.size() <= 0) {
			return this.findByHql(hql);
		}
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i=0; i < params.size(); i++){
			query.setParameter(i, params.get(i));
		}
		List<E> result = (List<E>) query.list();
		tx.commit();
		
		return result;
	}

	@SuppressWarnings("unchecked")
	public <E> Vector<E> searchByHql(String hql, Object[] params) {
		if (params == null || params.length <= 0) {
			return this.searchByHql(hql);
		}
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i=0; i < params.length; i++){
			query.setParameter(i, params[i]);
		}
		List<E> result = (List<E>) query.list();
		tx.commit();

		return ConvertUtil.list2Vector(result);
	}

	@SuppressWarnings("unchecked")
	public <E> Vector<E> searchByHql(String hql) {
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		List<E> result = (List<E>) query.list();
		tx.commit();
		return ConvertUtil.list2Vector(result);
	}

	public IBaseEntity create(IBaseEntity transientInstance) {
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(transientInstance);
		tx.commit();
		return transientInstance;
	}

	public void update(IBaseEntity transientInstance) {
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.merge(transientInstance);
		tx.commit();
	}

	public void delete(IBaseEntity transientInstance) {
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.delete(transientInstance);
		tx.commit();
	}

	public void deleteById(String className, Long id) {
		String hql = "delete from " + className + " p " + " where p.id = " + id;
		this.excuteHql(hql);
	}

	public void deleteByIds(String className, List<Long> ids) {
		if (ids.isEmpty()) return;
		String hql = "delete from " + className + " p " 
				+ " where p.id = " + ids.get(0);
		for (int i = 1 ; i <  ids.size() ; i++){
			hql = hql + " or id = " + ids.get(i);
		}		
		this.excuteHql(hql);
	}

	public void excuteHql(String hql) {
		Session session= this.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.executeUpdate();
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> excuteHql(String hql, String varListName,
			List<Object> variableList, List<Object> params) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}
		}
		if (varListName != null && varListName.length() > 0
				&& variableList != null) {
			query.setParameterList(varListName, variableList);
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> excuteHql(String hql, String[] varListNames,
			List<Object>[] variableLists, List<Object> params) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}
		}
		for (int i = 0; i < ((varListNames.length > variableLists.length) ? variableLists.length
				: varListNames.length); i++) {
			if (varListNames[i] != null && variableLists[i] != null) {
				query.setParameterList(varListNames[i], variableLists[i]);
			}
		}
		return query.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}




}
