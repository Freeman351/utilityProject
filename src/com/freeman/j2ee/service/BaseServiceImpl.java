package com.freeman.j2ee.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseServiceImpl implements BaseService {
	
	// update cach every hour min*sec*(ms*1000)
	protected static long CACHE_TIME_OUT = 60*60*1000;
	
	protected BaseEntityManagerImpl entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getEntityManager
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#getEntityManager
	 * ()
	 */
	public BaseEntityManagerImpl getEntityManager() {
		return entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#setEntityManager
	 * (ca.canon.estore.contentmgmt.dao.EntityManager)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#setEntityManager
	 * (ca.canon.estore.contentmgmt.dao.EntityManager)
	 */
	public void setEntityManager(BaseEntityManagerImpl entityManager) {
		this.entityManager = entityManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getEntityById
	 * (java.lang.String, long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#getEntityById
	 * (java.lang.String, long)
	 */
	public <E> E getEntityById(String className, long id) {
		E entity = entityManager.findById(className, id);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.service.BaseService#getEntitiesByIds(java.lang.String,
	 * long[])
	 */
	public <E> Collection<E> getEntitiesByIds(String className, long[] ids) {
		Vector<E> entities = new Vector<E>();
		for (long id : ids) {
			E entity = entityManager.findById(className, id);
			if (entity != null)
				entities.add(entity);
		}
		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#save(ca.canon
	 * .estore.model.IBaseEntity)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#save(ca.
	 * canon.estore.model.IBaseEntity)
	 */
	@SuppressWarnings("unchecked")
	public <E> E save(IBaseEntity entity) {
		if (entity.isNew()) {
			// new
			entity = entityManager.create(entity);
		} else {
			// update
			entityManager.update(entity);
		}
		return (E) entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#delete(ca.canon
	 * .estore.model.IBaseEntity)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#delete(ca
	 * .canon.estore.model.IBaseEntity)
	 */
	public void delete(IBaseEntity entity) {
		entityManager.delete(entity);
		return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getAllEntities
	 * (java.lang.String, boolean)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#getAllEntities
	 * (java.lang.String, boolean)
	 */
	public <E> Vector<E> getAllEntities(String className, boolean hasNoOrder) {
		String hql = "select a from " + className + " a";
		if (!hasNoOrder) {
			hql += " order by a.ordinal";
		}
		Vector<E> allBaseEntity = entityManager.searchByHql(hql);
		return allBaseEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getAllEntities
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentProdService#getAllEntities
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public <E> Vector<E> getAllEntities(String className, String orderClaus,
			String sort) {
		String hql = "select a from " + className + " a";
		if (orderClaus != null && !orderClaus.equals("")) {
			hql += " order by a." + orderClaus;
			if (sort != null && !sort.equals("")) {
				hql += " " + sort;
			}
		}
		Vector<E> allBaseEntity = entityManager.searchByHql(hql);
		return allBaseEntity;
	}

	protected <E> Vector<E> convertObject(Vector<E> allBaseEntity) {
		Vector<Long> ids = new Vector<Long>();
		for (Iterator<E> iterator = allBaseEntity.iterator(); iterator
				.hasNext();) {
			IBaseEntity entity = (IBaseEntity) iterator.next();
			if (ids.contains(entity.getId())) {
				iterator.remove();
				continue;
			}
			ids.add(entity.getId());
		}
		return allBaseEntity;
	}


	private <E> Vector<E> filterDupObject(Vector<E> allBaseEntity) {
		List<Long> ids = new ArrayList<Long>();
		for (Iterator iterator = allBaseEntity.iterator(); iterator.hasNext();) {
			IBaseEntity entity = (IBaseEntity) iterator.next();
			if (ids.contains(entity.getId())) {
				iterator.remove();
				continue;
			}
			ids.add(entity.getId());
		}
		return allBaseEntity;
	}

	public void saveList(List<IBaseEntity> entities) {
		if (entities != null) {
			for (IBaseEntity entity : entities) {
				if (entity.isNew()) {
					// new
					entity = entityManager.create(entity);
				} else {
					// update
					entityManager.update(entity);
				}
			}
		}
	}

	public boolean isEmailValid(String email) {
		boolean isValid = false;

		/*
		 * Email format: A valid email address will have following format:
		 * [\\w\\.-]+: Begins with word characters, (may include periods and
		 * hypens).
		 * 
		 * @: It must have a '@' symbol after initial characters.
		 * ([\\w\\-]+\\.)+: '@' must follow by more alphanumeric characters (may
		 * include hypens.). This part must also have a "." to separate domain
		 * and subdomain names. [A-Z]{2,4}$ : Must end with two to four
		 * alaphabets. (This will allow domain names with 2, 3 and 4 characters
		 * e.g pa, com, net, wxyz)
		 * 
		 * Examples: Following email addresses will pass validation abc@xyz.net;
		 * ab.c@tx.gov
		 */

		// Initialize reg ex for email.
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		// Make the comparison case-insensitive.
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
}
