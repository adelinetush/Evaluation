package com.planetsystems.monitoring.core.daos;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;
import com.planetsystems.monitoring.model.RecordStatus;

/**
 * @author Planet DevList.size()+1
 * 
 * @param <T>
 */

public interface BaseDao<T> extends GenericDAO<T, String> {

	/**
	 * delete entity by changing status to deleted
	 * 
	 * @param entity
	 */
	public void delete(T entity);
	

	
	/**
	 * @param type
	 * @param id
	 * @return
	 */
	<T1> T1 getEntityById(Class<T1> type, String id);

	/**
	 * @param entity
	 */
	@SuppressWarnings("hiding")
	<T> void mergeEntity(T entity);

	/**
	 * @see com.googlecode.genericdao.dao.jpa.GenericDAO#save(java.lang.Object)
	 *      this saves entity by adding defaults users, is and dates
	 */

	public T save(T entity);

	/**
	 * @param entity
	 * @return
	 */
	public T saveConsolidatedPlan(T entity);

	/**
	 * Searches by a given property
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	List<T> searchByPropertyEqual(String property, Object value);

	/**
	 * returns property based on the its status
	 * 
	 * @param property
	 * @param value
	 * @param recordStatus
	 * @return
	 */
	List<T> searchByPropertyEqual(String property, Object value,
			RecordStatus recordStatus);

	/**
	 * searches for a list of all entities of a given record status
	 * 
	 * @param recordStatus
	 * @return
	 */
	List<T> searchByRecordStatus(RecordStatus recordStatus);

	/**
	 * searches by a unique property like an id
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	T searchUniqueByPropertyEqual(String property, Object value);

	/**
	 * searches by a unique property like an id
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	T searchUniqueByPropertyEquals(String property, Object value);

	/**
	 * returns property based on the its status
	 * 
	 * @param property
	 * @param value
	 * @param recordStatus
	 * @return
	 */

	public T update(T entity);

	/**
	 * @param entity
	 */
	@SuppressWarnings("hiding")
	<T> void updateEntity(T entity);

}