package com.dao.db;

// Generated 03 Oct 2013 3:23:45 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.db.CustomPages;

/**
 * Home object for domain model class CustomPages.
 * 
 * @see com.entity.db.CustomPages
 * @author Hibernate Tools
 */
@Repository("CustomPages")
public class CustomPageDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(CustomPages transientInstance) {
		log.debug("persisting CustomPages instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CustomPages instance) {
		log.debug("attaching dirty CustomPages instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CustomPages instance) {
		log.debug("attaching clean CustomPages instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CustomPages persistentInstance) {
		log.debug("deleting CustomPages instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CustomPages merge(CustomPages detachedInstance) {
		log.debug("merging CustomPages instance");
		try {
			CustomPages result = (CustomPages) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomPages findById(int id) {
		log.debug("getting CustomPages instance with id: " + id);
		try {
			CustomPages instance = (CustomPages) sessionFactory.getCurrentSession().get("com.entity.db.CustomPages", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CustomPages> findByExample(CustomPages instance) {
		log.debug("finding CustomPages instance by example");
		try {
			List<CustomPages> results = (List<CustomPages>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.CustomPages")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public CustomPages getRecordByUsername(String username) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CustomPages.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.setMaxResults(1);

		return (CustomPages) criteria.uniqueResult();
	}
}
