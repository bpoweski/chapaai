package com.derbysoft.chapaai.adapter.pusher.repository;

import com.derbysoft.chapaai.adapter.pusher.domain.model.abnormalpush.BlockPushEntry;

import java.util.List;

public interface AbnormalPushEntryRepository {

     List<BlockPushEntry> list(String hotelCode);

}
