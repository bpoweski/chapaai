package com.derbysoft.chapaai.adapter.pusher.timer.timertask;

import com.derbysoft.chapaai.adapter.core.concurrent.PriorityTaskQueue;
import com.derbysoft.chapaai.adapter.pusher.task.PushTask;
import com.derbysoft.chapaai.adapter.pusher.task.generator.PushTaskGenerator;

import java.util.List;
import java.util.TimerTask;

public class ProviderPushTimerTask extends TimerTask {

    private PriorityTaskQueue<PushTask> pushTaskQueue;
    private PushTaskGenerator taskGenerator;
    private String            providerCode;


    @Override
    public void run() {
        addTasksToQueue(taskGenerator.pushGenerate(providerCode));
    }


    public void addTasksToQueue(List<PushTask> pushTasks) {
        for (PushTask pushTask : pushTasks) {
            boolean addTaskResult = pushTaskQueue.addTask(pushTask);
            //TODO add log to record
        }
    }

    public PriorityTaskQueue<PushTask> getPushTaskQueue() {
        return pushTaskQueue;
    }

    public void setPushTaskQueue(PriorityTaskQueue<PushTask> pushTaskQueue) {
        this.pushTaskQueue = pushTaskQueue;
    }

    public PushTaskGenerator getTaskGenerator() {
        return taskGenerator;
    }

    public void setTaskGenerator(PushTaskGenerator taskGenerator) {
        this.taskGenerator = taskGenerator;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }
}
