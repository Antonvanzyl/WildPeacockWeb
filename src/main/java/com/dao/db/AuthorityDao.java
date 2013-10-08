package com.dao.db;

// Generated 03 Oct 2013 3:23:45 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.db.Authority;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Authority.
 * @see com.entity.db.Authority
 * @author Hibernate Tools
 */
@Repository("Authority")
public class AuthorityDao {

	private static final Log log = LogFactory.getLog(AuthorityDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Authority transientInstance) {
		log.debug("persisting Authority instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Authority instance) {
		log.debug("attaching dirty Authority instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Authority instance) {
		log.debug("attaching clean Authority instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Authority persistentInstance) {
		log.debug("deleting Authority instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Authority merge(Authority detachedInstance) {
		log.debug("merging Authority instance");
		try {
			Authority result = (Authority) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Authority findById(int id) {
		log.debug("getting Authority instance with id: " + id);
		try {
			Authority instance = (Authority) sessionFactory.getCurrentSession().get("com.entity.db.Authority", id);
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

	public List<Authority> findByExample(Authority instance) {
		log.debug("finding Authority instance by example");
		try {
			List<Authority> results = (List<Authority>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.Authority")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public Authority getRecordByUsername(String username) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Authority.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.setMaxResults(1);
		
		return (Authority)criteria.uniqueResult();
	}
}
