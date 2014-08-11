package com.derbysoft.chapaai.adapter.pusher.repository.impl;

import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;
import com.derbysoft.chapaai.adapter.pusher.repository.ProviderRepository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ProviderRepositoryImpl implements ProviderRepository {
    public static final String DISABLE = "disabled";
    public static final boolean NOT_DISABLE = false;
    private SessionFactory sessionFactory;

    @Override
    public Provider get(String providerCode) {
        return (Provider)sessionFactory.getCurrentSession().get(Provider.class,providerCode);
    }

    @Override
    public void save(Provider provider) {
        sessionFactory.getCurrentSession().save(provider);
    }

    @Override
    public void update(Provider provider) {
        sessionFactory.getCurrentSession().update(provider);
    }

    @Override
    public List<Provider> list() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Provider.class);
        return criteria.list();
    }

    @Override
    public List<Provider> listEnable() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Provider.class);
        criteria.add(Restrictions.eq(DISABLE, NOT_DISABLE));
        return criteria.list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
