package com.derbysoft.chapaai.adapter.pusher.application.service;

import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.AbnormalPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.BlockPushEntry;
import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.LowFrequencyPushEntry;

import java.io.Serializable;
import java.util.List;

public interface AbnormalPushEntryService {

    public Serializable save(AbnormalPushEntry entry);

    public boolean remove(AbnormalPushEntry entry);

    public List<BlockPushEntry> listBlockEntry(String hotelCode);

    public List<LowFrequencyPushEntry> listLowFrequencyEntry(String hotelCode);
}
