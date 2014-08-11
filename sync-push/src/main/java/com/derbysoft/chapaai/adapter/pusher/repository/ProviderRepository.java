package com.derbysoft.chapaai.adapter.pusher.repository;

import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;

import java.util.List;

public interface ProviderRepository {

    Provider get(String providerCode);

    void save(Provider provider);

    void update(Provider provider);

    List<Provider> list();

    List<Provider> listEnable();
}
