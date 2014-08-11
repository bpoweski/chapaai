package com.derbysoft.chapaai.adapter.pusher.application.service.impl;

import com.derbysoft.chapaai.adapter.pusher.application.service.AbnormalPushEntryService;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.AbnormalPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.BlockPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.LowFrequencyPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.AbnormalPushEntryRepository;

import java.io.Serializable;
import java.util.List;

public class AbnormalPushEntryServiceImpl implements AbnormalPushEntryService {

    private AbnormalPushEntryRepository abnormalPushEntryRepository;

    @Override
    public Serializable save(AbnormalPushEntry entry) {
        return null;
    }

    @Override
    public boolean remove(AbnormalPushEntry entry) {
        return false;
    }

    @Override
    public List<BlockPushEntry> listBlockEntry(String hotelCode) {
        return null;
    }

    @Override
    public List<LowFrequencyPushEntry> listLowFrequencyEntry(String hotelCode) {
        return null;
    }

    public AbnormalPushEntryRepository getAbnormalPushEntryRepository() {
        return abnormalPushEntryRepository;
    }

    public void setAbnormalPushEntryRepository(AbnormalPushEntryRepository abnormalPushEntryRepository) {
        this.abnormalPushEntryRepository = abnormalPushEntryRepository;
    }
}
