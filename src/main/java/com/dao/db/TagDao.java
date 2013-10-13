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

import com.entity.db.Tag;

/**
 * Home object for domain model class Tag.
 * 
 * @see com.entity.db.Tag
 * @author Hibernate Tools
 */
@Repository("Tag")
public class TagDao {

	private static final Log log = LogFactory.getLog(TagDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void persist(Tag transientInstance) {
		log.debug("persisting Tag instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tag instance) {
		log.debug("attaching dirty Tag instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tag instance) {
		log.debug("attaching clean Tag instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tag persistentInstance) {
		log.debug("deleting Tag instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tag merge(Tag detachedInstance) {
		log.debug("merging Tag instance");
		try {
			Tag result = (Tag) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tag findById(int id) {
		log.debug("getting Tag instance with id: " + id);
		try {
			Tag instance = (Tag) sessionFactory.getCurrentSession().get("com.entity.db.Tag", id);
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

	public List<Tag> findByExample(Tag instance) {
		log.debug("finding Tag instance by example");
		try {
			List<Tag> results = (List<Tag>) sessionFactory.getCurrentSession().createCriteria("com.entity.db.Tag").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<Tag> getAllMainTags() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
		criteria.add(Restrictions.isNull("tag.id"));
		return (List<Tag>)criteria.list();
	}

	public List<Tag> getAllTags() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class);
		return (List<Tag>)criteria.list();
	}
}
