package com.derbysoft.chapaai.adapter.pusher.application.service;

import com.derbysoft.synchronizer.dto.HotelKeysDTO;

public interface HotelPushService {

    void  syncPush(String providerCode,HotelKeysDTO hotelKeysDTO);
}
