package com.derbysoft.chapaai.adapter.core.domain.repository.impl;


import com.derbysoft.chapaai.adapter.core.commons.framework.hibernate.HibernateRepository;
import com.derbysoft.chapaai.adapter.core.domain.model.ProviderConfig;
import com.derbysoft.chapaai.adapter.core.domain.repository.ProviderConfigRepository;

public class ProviderConfigRepositoryImpl implements ProviderConfigRepository {
    private HibernateRepository hibernateRepository;

    public void setHibernateRepository(HibernateRepository hibernateRepository) {
        this.hibernateRepository = hibernateRepository;
    }

    @Override
    public ProviderConfig get(String providerCode) {
        return hibernateRepository.get(ProviderConfig.class, providerCode);
    }
}
