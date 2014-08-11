package com.derbysoft.chapaai.adapter.pusher.domain.repository.impl;

import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.BlockPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.AbnormalPushEntryRepository;
import com.derbysoft.ccs.core.MappingCache;

import java.util.List;

public class AbnormalPushEntryRepositoryImpl implements AbnormalPushEntryRepository {

    public static final String HOTEL_CODE = "hotelCode";
    private MappingCache<BlockPushEntry> mappingCache;

    @Override
    public List<BlockPushEntry> list(String hotelCode){
        return mappingCache.list(BlockPushEntry.CCS_HOTEL_CODE_NAME,hotelCode);
    }

    public MappingCache<BlockPushEntry> getMappingCache() {
        return mappingCache;
    }

    public void setMappingCache(MappingCache<BlockPushEntry> mappingCache) {
        this.mappingCache = mappingCache;
    }
}
