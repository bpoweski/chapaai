package com.derbysoft.chapaai.adapter.pusher.application.task.executor;

import com.derbysoft.chapaai.adapter.core.concurrent.PriorityTaskExecutor;
import com.derbysoft.chapaai.adapter.pusher.application.service.HotelPushService;
import com.derbysoft.chapaai.adapter.pusher.application.task.PushTask;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;

public class PushTaskExecutor implements PriorityTaskExecutor<PushTask> {

    private HotelPushService hotelPushService;

    @Override
    public void execute(PushTask pushTask) {
        String providerCode= pushTask.getProviderCode();
        HotelKeysDTO hotelKeysDTO = pushTask.getHotelKeys();
        try {
             hotelPushService.syncPush(providerCode,hotelKeysDTO);
        } catch (RuntimeException e) {
            //TODO Add log here
        }
    }

    public HotelPushService getHotelPushService() {
        return hotelPushService;
    }

    public void setHotelPushService(HotelPushService hotelPushService) {
        this.hotelPushService = hotelPushService;
    }
}
