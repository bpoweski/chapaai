package com.derbysoft.chapaai.adapter.pusher.timer;

import com.derbysoft.chapaai.adapter.pusher.timer.timertask.PushTimerTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.constant.SyncConstant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Timer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class PushTimerTest {


    public static final int INTERVAL_SECONDS = 5;
    private PushTimer pushTimer;


    @Test
    public void testStart(){
        Timer timer = pushTimer.getTimer();
        PushTimerTask timerTask = pushTimer.getPushTimerTask();

        pushTimer.start();


        verify(timerTask,times(1)).setIntervalSeconds(pushTimer.getIntervalSeconds());
        verify(timer,times(1)).schedule(timerTask,
                                        SyncConstant.NO_DELAY,
                                        INTERVAL_SECONDS*SyncConstant.INTERVAL);

    }


    @Test
    public void testStop(){
        Timer timer = pushTimer.getTimer();
        PushTimerTask timerTask = pushTimer.getPushTimerTask();

        pushTimer.stop();

        verify(timerTask,times(1)).stop();
        verify(timer,times(1)).cancel();
    }

    @Before
    public void setup() {
        pushTimer = Mockito.spy(new PushTimer());
        PushTimerTask pushTimerTask = mock(PushTimerTask.class);
        Timer timer = mock(Timer.class);

        pushTimer.setPushTimerTask(pushTimerTask);
        pushTimer.setTimer(timer);
        pushTimer.setIntervalSeconds(INTERVAL_SECONDS);



    }
}
