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

import com.entity.db.SystemAudit;

/**
 * Home object for domain model class SystemAudit.
 * 
 * @see com.entity.db.SystemAudit
 * @author Hibernate Tools
 */
@Repository("SystemAudit")
public class SystemAuditDao {

	private static final Log log = LogFactory.getLog(SystemAuditDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(SystemAudit transientInstance) {
		log.debug("persisting SystemAudit instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SystemAudit instance) {
		log.debug("attaching dirty SystemAudit instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SystemAudit instance) {
		log.debug("attaching clean SystemAudit instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SystemAudit persistentInstance) {
		log.debug("deleting SystemAudit instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SystemAudit merge(SystemAudit detachedInstance) {
		log.debug("merging SystemAudit instance");
		try {
			SystemAudit result = (SystemAudit) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SystemAudit findById(int id) {
		log.debug("getting SystemAudit instance with id: " + id);
		try {
			SystemAudit instance = (SystemAudit) sessionFactory.getCurrentSession().get("com.entity.db.SystemAudit", id);
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

	public List<SystemAudit> findByExample(SystemAudit instance) {
		log.debug("finding SystemAudit instance by example");
		try {
			List<SystemAudit> results = (List<SystemAudit>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.SystemAudit")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
