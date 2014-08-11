package com.derbysoft.chapaai.adapter.pusher.domain.repository.impl;

import com.derbysoft.ccs.core.MappingCache;
import com.derbysoft.chapaai.adapter.pusher.domain.model.ChapaaiMappingEntry;
import com.derbysoft.chapaai.adapter.pusher.utils.RepositoryUtils;

import java.util.List;

public class ChapaaiEntryRepositoryImpl<T extends ChapaaiMappingEntry>{

    private MappingCache<T> mappingCache;


    public List<T> listChapaaiMappingEntry(String field,String value, boolean disabled) {
        return RepositoryUtils.filterDisabled(mappingCache.list(field, value), disabled);
    }

    public T getChapaaiMappingEntry(String primaryKey) {
        return mappingCache.get(primaryKey);
    }

    public MappingCache<T> getMappingCache() {
        return mappingCache;
    }

    public void setMappingCache(MappingCache<T> mappingCache) {
        this.mappingCache = mappingCache;
    }
}
