package com.derbysoft.chapaai.adapter.pusher.application.task.executor;

import com.derbysoft.chapaai.adapter.pusher.application.service.HotelPushService;
import com.derbysoft.chapaai.adapter.pusher.application.task.PushTask;
import com.derbysoft.synchronizer.dto.HotelKeysDTO;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PushTaskExecutorTest {

    private PushTaskExecutor executor;

    @Test
    public void testExecute(){
        String providerCode = "provider";
        HotelKeysDTO hotelKeysDTO  = new HotelKeysDTO();

        PushTask task = mock(PushTask.class);
        when(task.getProviderCode()).thenReturn(providerCode);
        when(task.getHotelKeys()).thenReturn(hotelKeysDTO);


        executor.execute(task);

        verify(executor.getHotelPushService(),times(1)).syncPush(providerCode,hotelKeysDTO);
    }




    @Before
    public void setup(){
        executor = new PushTaskExecutor();
        HotelPushService hotelPushService = mock(HotelPushService.class);
        executor.setHotelPushService(hotelPushService);
    }
}
