package com.freeman.j2ee.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Vector;


public interface BaseService extends Serializable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getEntityManager
	 * ()
	 */
	public abstract BaseEntityManagerImpl getEntityManager();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#setEntityManager
	 * (ca.canon.estore.contentmgmt.dao.EntityManager)
	 */
	public abstract void setEntityManager(BaseEntityManagerImpl entityManager);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getEntityById
	 * (java.lang.String, long)
	 */
	public abstract <E> E getEntityById(String className, long id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getEntitiesByIds
	 * (java.lang.String, long[])
	 */
	public abstract <E> Collection<E> getEntitiesByIds(String className,
			long[] ids);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#save(ca.canon
	 * .estore.model.IBaseEntity)
	 */
	public abstract <E> E save(IBaseEntity entity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#delete(ca.canon
	 * .estore.model.IBaseEntity)
	 */
	public abstract void delete(IBaseEntity entity);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getAllEntities
	 * (java.lang.String, boolean)
	 */
	public abstract <E> Vector<E> getAllEntities(String className,
			boolean hasNoOrder);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.estore.contentmgmt.service.EStoreContentService#getAllEntities
	 * (java.lang.String, java.lang.String, java.lang.String)
	 */
	public abstract <E> Vector<E> getAllEntities(String className,
			String orderClaus, String sort);

	public abstract void saveList(List<IBaseEntity> entities);

}