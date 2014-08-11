package com.derbysoft.chapaai.adapter.pusher.domain.repository.impl;

import com.derbysoft.chapaai.adapter.pusher.domain.model.RoomType;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.RoomTypeRepository;

public class RoomTypeRepositoryImpl extends ChapaaiEntryRepositoryImpl<RoomType>
                                   implements RoomTypeRepository {
    @Override
    public RoomType getRoomType(String roomTypeCode) {
        return this.getChapaaiMappingEntry(roomTypeCode);
    }
}
