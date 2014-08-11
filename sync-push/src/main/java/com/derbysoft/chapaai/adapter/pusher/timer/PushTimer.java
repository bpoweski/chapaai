package com.derbysoft.chapaai.adapter.pusher.timer;

import com.derbysoft.chapaai.adapter.pusher.timer.timertask.PushTimerTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.constant.SyncConstant;

import java.util.Timer;

public class PushTimer {


    private long intervalSeconds;
    private Timer timer;
    private PushTimerTask pushTimerTask;

    public void start() {
        pushTimerTask.setIntervalSeconds(intervalSeconds);
        timer.schedule(pushTimerTask,
                SyncConstant.NO_DELAY,
                intervalSeconds * SyncConstant.INTERVAL);
    }

    public void stop() {
        timer.cancel();
        pushTimerTask.stop();
    }

    public long getIntervalSeconds() {
        return intervalSeconds;
    }

    public void setIntervalSeconds(long intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }


    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public PushTimerTask getPushTimerTask() {
        return pushTimerTask;
    }

    public void setPushTimerTask(PushTimerTask pushTimerTask) {
        this.pushTimerTask = pushTimerTask;
    }
}
