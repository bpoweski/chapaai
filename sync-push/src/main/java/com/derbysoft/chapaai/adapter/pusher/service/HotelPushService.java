package com.derbysoft.chapaai.adapter.pusher.service;

import com.derbysoft.synchronizer.dto.HotelKeysDTO;

public interface HotelPushService {

    void  syncPush(String providerCode,HotelKeysDTO hotelKeysDTO);
}
