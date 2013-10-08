package com.dao.db;

// Generated 03 Oct 2013 3:23:45 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.db.Statistics;

/**
 * Home object for domain model class Statistics.
 * 
 * @see com.entity.db.Statistics
 * @author Hibernate Tools
 */
@Repository("Statistics")
public class StatisticsDao {

	private static final Log log = LogFactory.getLog(StatisticsDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Statistics transientInstance) {
		log.debug("persisting Statistics instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Statistics instance) {
		log.debug("attaching dirty Statistics instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Statistics instance) {
		log.debug("attaching clean Statistics instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Statistics persistentInstance) {
		log.debug("deleting Statistics instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Statistics merge(Statistics detachedInstance) {
		log.debug("merging Statistics instance");
		try {
			Statistics result = (Statistics) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Statistics findById(int id) {
		log.debug("getting Statistics instance with id: " + id);
		try {
			Statistics instance = (Statistics) sessionFactory.getCurrentSession().get("com.entity.db.Statistics", id);
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

	public List<Statistics> findByExample(Statistics instance) {
		log.debug("finding Statistics instance by example");
		try {
			List<Statistics> results = (List<Statistics>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.Statistics")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
