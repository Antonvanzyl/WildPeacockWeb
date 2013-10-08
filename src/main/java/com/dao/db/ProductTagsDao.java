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

import com.entity.db.ProductTags;

/**
 * Home object for domain model class ProductTags.
 * 
 * @see com.entity.db.ProductTags
 * @author Hibernate Tools
 */
@Repository("ProductTags")
public class ProductTagsDao {

	private static final Log log = LogFactory.getLog(ProductTagsDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(ProductTags transientInstance) {
		log.debug("persisting ProductTags instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ProductTags instance) {
		log.debug("attaching dirty ProductTags instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ProductTags instance) {
		log.debug("attaching clean ProductTags instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ProductTags persistentInstance) {
		log.debug("deleting ProductTags instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ProductTags merge(ProductTags detachedInstance) {
		log.debug("merging ProductTags instance");
		try {
			ProductTags result = (ProductTags) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ProductTags findById(com.entity.db.ProductTagsId id) {
		log.debug("getting ProductTags instance with id: " + id);
		try {
			ProductTags instance = (ProductTags) sessionFactory.getCurrentSession().get("com.entity.db.ProductTags", id);
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

	public List<ProductTags> findByExample(ProductTags instance) {
		log.debug("finding ProductTags instance by example");
		try {
			List<ProductTags> results = (List<ProductTags>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.ProductTags")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
