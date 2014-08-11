package com.derbysoft.chapaai.adapter.core.domain.repository;

import com.derbysoft.chapaai.adapter.core.domain.model.ProviderConfig;

public interface ProviderConfigRepository {
    ProviderConfig get(String providerCode);
}
