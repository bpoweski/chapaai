package com.derbysoft.chapaai.adapter.pusher.application.timer;


import com.derbysoft.chapaai.adapter.core.concurrent.PriorityTaskThreadPool;
import com.derbysoft.chapaai.adapter.pusher.application.task.PushTask;
import com.derbysoft.chapaai.adapter.pusher.application.task.executor.PushTaskExecutor;
import com.derbysoft.chapaai.adapter.pusher.application.timer.timertask.ProviderPushTimerTask;
import com.derbysoft.chapaai.adapter.pusher.domain.model.constant.SyncConstant;

import java.util.Timer;


public class ProviderPushTimer {

    private Timer timer;
    private PushTaskExecutor pushTaskExecutor;
    private PriorityTaskThreadPool<PushTask> pushTaskPriorityTaskThreadPool;
    private ProviderPushTimerTask  providerPushTimerTask;

    public ProviderPushTimer(){
    }

    public void start(final String providerCode, final long intervalSeconds,final int pushThreadPoolSize){
        providerPushTimerTask.setProviderCode(providerCode);
        pushTaskPriorityTaskThreadPool = new PriorityTaskThreadPool<PushTask>("ProviderPushExecutorThread:" + providerCode,
                pushThreadPoolSize, providerPushTimerTask.getPushTaskQueue(), pushTaskExecutor);
        timer.schedule(providerPushTimerTask, SyncConstant.NO_DELAY, intervalSeconds * SyncConstant.INTERVAL);
    }


    public void stop() {
        timer.cancel();
        pushTaskPriorityTaskThreadPool.stopRunning();
    }

    public void changeExecuteThreadPoolSize(int newSize) {
        pushTaskPriorityTaskThreadPool.changeThreadPoolSize(newSize);
    }


    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public PushTaskExecutor getPushTaskExecutor() {
        return pushTaskExecutor;
    }

    public void setPushTaskExecutor(PushTaskExecutor pushTaskExecutor) {
        this.pushTaskExecutor = pushTaskExecutor;
    }

    public PriorityTaskThreadPool<PushTask> getPushTaskPriorityTaskThreadPool() {
        return pushTaskPriorityTaskThreadPool;
    }

    public void setPushTaskPriorityTaskThreadPool(PriorityTaskThreadPool<PushTask> pushTaskPriorityTaskThreadPool) {
        this.pushTaskPriorityTaskThreadPool = pushTaskPriorityTaskThreadPool;
    }

    public ProviderPushTimerTask getProviderPushTimerTask() {
        return providerPushTimerTask;
    }

    public void setProviderPushTimerTask(ProviderPushTimerTask providerPushTimerTask) {
        this.providerPushTimerTask = providerPushTimerTask;
    }
}
