package com.derbysoft.chapaai.adapter.pusher.service;

import com.derbysoft.synchronizer.dto.ChangesDTO;

public interface ChangePushService {

    /**
     * TODO Add a new parameter providerConfig here.make the service enable can be configuration
     * @param changes
     */
    void syncPush(ChangesDTO changes,String hotelCode);
}
