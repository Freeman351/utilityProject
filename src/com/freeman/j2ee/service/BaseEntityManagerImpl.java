package com.freeman.j2ee.service;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.freeman.utilities.ConvertUtil;

public class BaseEntityManagerImpl extends HibernateDaoSupport implements
		BaseEntityManager {

	private static Logger log = Logger.getLogger(BaseEntityManagerImpl.class);

	@SuppressWarnings("unchecked")
	public <E> E findById(String className, long id) {
		try {
			E instance = (E) getHibernateTemplate().get(className, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public <E> List<E> findByHql(String hql) {
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public <E> Vector<E> getObjects(String classname, String whereClaus,
			String orderClaus) {
		List<E> objects = getHibernateTemplate().find(
				"from " + classname + " a " + whereClaus + " " + orderClaus);
		return ConvertUtil.list2Vector(objects);
	}

	public <E> Vector<E> getObjects(String query, Object obj) {
		List<E> objects = getHibernateTemplate().find(query, obj);
		return ConvertUtil.list2Vector(objects);
	}

	@SuppressWarnings("unchecked")
	public <E> Vector<E> searchByHql(String hql, Object[] params) {
		if (params == null || params.length <= 0) {
			return this.searchByHql(hql);
		}
		List<E> result = getHibernateTemplate().find(hql, params);
		return ConvertUtil.list2Vector(result);
	}

	@SuppressWarnings("unchecked")
	public <E> Vector<E> searchByHql(String hql) {
		List<E> result = getHibernateTemplate().find(hql);
		return ConvertUtil.list2Vector(result);
	}

	public long getCountByHql(String hql) {
		Query query = getSession().createQuery(hql);
		List<Long> result = (List<Long>) query.list();
		if(result.size()>0){
			Long value = result.get(0);
			return value.longValue();
		}
		return 0;
	}

	public <E> Vector<E> searchByHqlPaginanion(String hql, int start, int limit) {
		Query query = getSession().createQuery(hql);
		query = query.setFirstResult(start);
		query.setMaxResults(limit);
		List<E> result = query.list();
		return ConvertUtil.list2Vector(result);
	}
	
	public IBaseEntity create(IBaseEntity transientInstance) {
		getHibernateTemplate().saveOrUpdate(transientInstance);
		return transientInstance;
	}

	public void update(IBaseEntity transientInstance) {
		getHibernateTemplate().merge(transientInstance);
	}

	public void delete(IBaseEntity transientInstance) {
		getHibernateTemplate().delete(
				getHibernateTemplate().merge(transientInstance));
	}

	public void excuteHql(String hql) {
		this.getSession().createQuery(hql).executeUpdate();
	}

	public <E> List<E> excuteHql(String hql, String varListName,
			List<Object> variableList, List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}
		}
		if (varListName != null && varListName.length() >0 && variableList != null){
			query.setParameterList(varListName, variableList);
		}
		return query.list();
	}

	public <E> List<E> excuteHql(String hql, String[] varListNames,
			List<Object>[] variableLists, List<Object> params) {
		Query query = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			int i = 0;
			for (Object param : params) {
				query.setParameter(i++, param);
			}
		}
		for (int i = 0; i < ((varListNames.length > variableLists.length)? variableLists.length:varListNames.length); i++){
			if (varListNames[i] != null && variableLists[i] != null){
				query.setParameterList(varListNames[i], variableLists[i]);
			}
		}
		return query.list();
	}

}
