package com.planetsystems.monitoring.core.daos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class AbstractJpaDAO<T extends Serializable> {
	
	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(final String id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

	public void create(final T entity) {
		
		entityManager.persist(entity);
	}

	public T update(final T entity) {
		return entityManager.merge(entity);
	}

	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(final String entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}

	public List<T> findByAttributes(Map<String, Object> attributes) {
		List<T> results;
		// set up the Criteria query
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> foo = cq.from(clazz);

		List<Predicate> predicates = new ArrayList<Predicate>();
		for (String s : attributes.keySet()) {
			if (foo.get(s) != null) {
				predicates.add(cb.like((Expression)foo.get(s), "%" + attributes.get(s) + "%"));
			}
		}
		cq.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<T> q = entityManager.createQuery(cq);

		results = q.getResultList();
		return results;
	}

}
