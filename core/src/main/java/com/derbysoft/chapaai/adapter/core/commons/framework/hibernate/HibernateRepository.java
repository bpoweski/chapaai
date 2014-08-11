package com.derbysoft.chapaai.adapter.core.commons.framework.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class HibernateRepository {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T get(Class<T> entityType, Serializable id) {
        return id == null ? null : (T) getSession().get(entityType, id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T persist(T entity) {
        getSession().persist(entity);
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> void update(T entity) {
        getSession().update(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> void deleteById(Class<T> entityType, String idName, Serializable id) {
        if (id == null) {
            return;
        }
        executeUpdate("delete from " + entityType.getName() + " where " + idName + "=?", id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> void deleteById(Class<T> entityType, Serializable id) {
        if (id == null) {
            return;
        }
        executeUpdate("delete from " + entityType.getName() + " where id =?", id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> List<T> listAll(Class<T> entityType) {
        return findCustom("from " + entityType.getName());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> PagedData<T> find(Class<T> entityType, int pageNumber, int pageSize) {
        Criteria listCriteria = getSession().createCriteria(entityType);

        listCriteria.setFirstResult((pageNumber - 1) * pageSize);
        listCriteria.setMaxResults(pageSize);

        Criteria countCriteria = getSession().createCriteria(entityType);
        countCriteria.setProjection(Projections.count("*"));

        return new PagedData(listCriteria.list(), (Long) countCriteria.uniqueResult());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> List<T> findCustom(final String hql, final Object... values) {
        Query query = getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void executeUpdate(final String hql, final Object... values) {
        Query query = getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.executeUpdate();
    }
}
