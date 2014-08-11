package com.derbysoft.chapaai.adapter.pusher.application.timer;

import com.derbysoft.chapaai.adapter.core.concurrent.PriorityTaskThreadPool;
import com.derbysoft.chapaai.adapter.pusher.application.task.PushTask;
import com.derbysoft.chapaai.adapter.pusher.application.task.executor.PushTaskExecutor;
import com.derbysoft.chapaai.adapter.pusher.application.timer.timertask.ProviderPushTimerTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.constant.SyncConstant;
import org.junit.Before;
import org.junit.Test;

import java.util.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ProviderPushTimerTest {

    ProviderPushTimer providerPushTimer;

    @Test
    public void testStart() {
        String providerCode = "ProviderCode";
        long intervalSeconds  = 100;
        int  poolSize         = 5;
        Timer timer = providerPushTimer.getTimer();
        ProviderPushTimerTask task = providerPushTimer.getProviderPushTimerTask();

        providerPushTimer.start(providerCode,intervalSeconds,poolSize);

        verify(task,times(1)).setProviderCode(providerCode);
        verify(timer,times(1)).schedule(task,SyncConstant.NO_DELAY, intervalSeconds * SyncConstant.INTERVAL);
    }

    @Test
    public void testStop() {
        Timer timer = providerPushTimer.getTimer();

        providerPushTimer.stop();

        verify(timer,times(1)).cancel();
    }

    @Before
    public void setup() {
        providerPushTimer = new ProviderPushTimer();
        Timer timer = mock(Timer.class);
        PushTaskExecutor executor = mock(PushTaskExecutor.class);
        ProviderPushTimerTask task = mock(ProviderPushTimerTask.class);
        PriorityTaskThreadPool<PushTask> pushTaskPriorityTaskThreadPool = mock(PriorityTaskThreadPool.class);

        providerPushTimer.setPushTaskPriorityTaskThreadPool(pushTaskPriorityTaskThreadPool);
        providerPushTimer.setProviderPushTimerTask(task);
        providerPushTimer.setPushTaskExecutor(executor);
        providerPushTimer.setTimer(timer);

    }
}
