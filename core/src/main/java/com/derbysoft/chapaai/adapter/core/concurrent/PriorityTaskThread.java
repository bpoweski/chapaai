package com.derbysoft.chapaai.adapter.core.concurrent;

import com.derbysoft.chapaai.adapter.core.commons.log.LogRecorder;

public class PriorityTaskThread<T extends PriorityTask> extends Thread {
    private static final long TIMEOUT = 1;
    private boolean markToStop = false;
    private PriorityTaskQueue<T> taskBlockingQueue;
    private PriorityTaskExecutor<T> taskExecutor;

    public PriorityTaskThread(String name, PriorityTaskQueue<T> taskBlockingQueue, PriorityTaskExecutor<T> taskExecutor) {
        super(name);
        this.taskBlockingQueue = taskBlockingQueue;
        this.taskExecutor = taskExecutor;
        this.start();
    }

    public void markToStop() {
        markToStop = true;
    }

    public void run() {
        while (!markToStop) {
            execute();
        }
    }

    private void execute() {
        T task = null;
        try {
            task = taskBlockingQueue.hold(TIMEOUT);
            if (task != null) {
                taskExecutor.execute(task);
            }
        } catch (Exception e) {
            LogRecorder logRecorder = new LogRecorder("IntervalTimer run");
            logRecorder.setErrorMessage(e.getMessage());
            //hibernateRepository.persist(logRecorder);
        } finally {
            if (task != null) {
                taskBlockingQueue.release(task);
            }
        }
    }

}
