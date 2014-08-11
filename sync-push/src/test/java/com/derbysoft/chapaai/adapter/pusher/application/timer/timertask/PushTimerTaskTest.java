package com.derbysoft.chapaai.adapter.pusher.application.timer.timertask;


import com.derbysoft.chapaai.adapter.pusher.application.timer.ProviderPushTimer;
import com.derbysoft.chapaai.adapter.pusher.domain.model.Provider;
import com.derbysoft.chapaai.adapter.pusher.domain.repository.ProviderRepository;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PushTimerTaskTest {

    private PushTimerTask pushTimerTask;


    @Test
    public void testGetProviderList() {
        //prepareTestData
        List<Provider> providers_expect = mock(List.class);
        ProviderRepository providerRepository = pushTimerTask.getProviderRepository();
        when(providerRepository.listEnable())
                .thenReturn(providers_expect);

        //Execute
        List<Provider> providers_actual = pushTimerTask.getProviderList();

        //Assert
        Assert.assertEquals(providers_expect, providers_actual);

        Mockito.reset(providerRepository);
    }

    @Test
    public void startPullTimersSuccessFul() {
        //Parameter
        String providerCode = "provide1";
        int pullThreadPoolSize = 5;

        //Data prepare
        ProviderPushTimer providerPullTimer = mock(ProviderPushTimer.class);
        when(pushTimerTask.createProviderPushTimer())
                .thenReturn(providerPullTimer);
        Provider provider = createMockProvider(providerCode, pullThreadPoolSize);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);
        pushTimerTask.getProviderPullTimerMap().clear();

        //Execute
        pushTimerTask.startPushTimers(providers);

        //verify
        Mockito.verify(providerPullTimer)
                .start(providerCode, pushTimerTask.getIntervalSeconds(), pullThreadPoolSize);
        Assert.assertEquals(providerPullTimer, pushTimerTask.getProviderPullTimerMap().get(providerCode));

        Mockito.reset(pushTimerTask);
        pushTimerTask.getProviderPullTimerMap().clear();
    }

    @Test
    public void startPullTimersAlreadyInMap() {
        //Parameter
        String providerCode = "provide1";
        int pullThreadPoolSize = 5;

        //Data prepare
        ProviderPushTimer providerPullTimer = mock(ProviderPushTimer.class);
        when(pushTimerTask.createProviderPushTimer())
                .thenReturn(providerPullTimer);
        Provider provider = createMockProvider(providerCode, pullThreadPoolSize);

        List<Provider> providers = new ArrayList<Provider>();
        providers.add(provider);
        pushTimerTask.getProviderPullTimerMap().clear();
        ProviderPushTimer providerPushTimeInCache = mock(ProviderPushTimer.class);
        pushTimerTask.getProviderPullTimerMap().put(providerCode, providerPushTimeInCache);

        //Execute
        pushTimerTask.startPushTimers(providers);

        //verify
        Mockito.verify(providerPullTimer,never())
                .start(providerCode, pushTimerTask.getIntervalSeconds(), pullThreadPoolSize);
        Assert.assertEquals(providerPushTimeInCache, pushTimerTask.getProviderPullTimerMap().get(providerCode));

        Mockito.reset(pushTimerTask);
        pushTimerTask.getProviderPullTimerMap().clear();
    }


   @Test
   public void testStop(){
       Map<String, ProviderPushTimer> providerPullTimerMap = pushTimerTask.getProviderPullTimerMap();
       providerPullTimerMap.clear();

       for(int i = 0;i < 10; i++){
           providerPullTimerMap.put(String.valueOf(i),mock(ProviderPushTimer.class));
       }

       pushTimerTask.stop();

       for(Map.Entry<String, ProviderPushTimer> entry:providerPullTimerMap.entrySet()){
           verify(entry.getValue(),times(1)).stop();
       }

   }

    public Provider createMockProvider(String providerCode, int pushThreadPoolSize) {
        Provider result = mock(Provider.class);
        when(result.getProviderCode()).thenReturn(providerCode);
        when(result.getPushThreadPoolSize()).thenReturn(pushThreadPoolSize);

        return result;

    }

    @Before
    public void setup() {
        pushTimerTask = Mockito.spy(new PushTimerTaskTestImpl());
        ProviderRepository mockRepository = mock(ProviderRepository.class);
        pushTimerTask.setProviderRepository(mockRepository);
        pushTimerTask.setIntervalSeconds(5);

    }
}

class PushTimerTaskTestImpl extends  PushTimerTask{
    @Override
    public ProviderPushTimer createProviderPushTimer() {
        return new ProviderPushTimer();
    }
}