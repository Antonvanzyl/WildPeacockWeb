package com.dao.db;

// Generated 03 Oct 2013 3:23:45 PM by Hibernate Tools 3.4.0.CR1

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.db.Publishing;
import com.types.PublishingSectionType;

/**
 * Home object for domain model class Publishing.
 * 
 * @see com.entity.db.Publishing
 * @author Hibernate Tools
 */
@Repository("Publishing")
public class PublishingDao {

	private static final Log log = LogFactory.getLog(PublishingDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Publishing transientInstance) {
		log.debug("persisting Publishing instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Publishing instance) {
		log.debug("attaching dirty Publishing instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Publishing instance) {
		log.debug("attaching clean Publishing instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Publishing persistentInstance) {
		log.debug("deleting Publishing instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Publishing merge(Publishing detachedInstance) {
		log.debug("merging Publishing instance");
		try {
			Publishing result = (Publishing) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Publishing findById(int id) {
		log.debug("getting Publishing instance with id: " + id);
		try {
			Publishing instance = (Publishing) sessionFactory.getCurrentSession().get("com.entity.db.Publishing", id);
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

	public List<Publishing> findByExample(Publishing instance) {
		log.debug("finding Publishing instance by example");
		try {
			List<Publishing> results = (List<Publishing>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.Publishing")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Publishing> getRecords(PublishingSectionType publishingSectionType, int startIndex, int count) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Publishing.class);
		criteria.add(Restrictions.eq("section", publishingSectionType));
		criteria.setMaxResults(count);
		criteria.setFirstResult(startIndex);

		return (List<Publishing>) criteria.list();
	}
}
