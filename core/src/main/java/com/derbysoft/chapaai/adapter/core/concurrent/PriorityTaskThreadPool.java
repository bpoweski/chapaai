package com.derbysoft.chapaai.adapter.core.concurrent;

import java.util.ArrayList;
import java.util.List;

public class PriorityTaskThreadPool<T extends PriorityTask> {
    private String name;
    private PriorityTaskQueue<T> taskBlockingQueue;
    private PriorityTaskExecutor<T> taskExecutor;
    private boolean markToStop = false;
    private final List<PriorityTaskThread<T>> taskThreads = new ArrayList<PriorityTaskThread<T>>();

    public PriorityTaskThreadPool(String name, int threadPoolSize, PriorityTaskQueue<T> taskBlockingQueue,
                                  PriorityTaskExecutor taskExecutor) {
        this.name = name;
        this.taskBlockingQueue = taskBlockingQueue;
        this.taskExecutor = taskExecutor;
        for (int i = 1; i <= threadPoolSize; i++) {
            addThread();
        }
    }

    public String getName() {
        return name;
    }

    private void addThread() {
        synchronized (taskThreads) {
            taskThreads.add(new PriorityTaskThread<T>(name + "-" + taskThreads.size() + 1, taskBlockingQueue, taskExecutor));
        }
    }

    public void changeThreadPoolSize(int newThreadPoolSize) {
        synchronized (taskExecutor) {
            if (markToStop) {
                return;
            }
            int difference = newThreadPoolSize - this.taskThreads.size();
            if (difference == 0) {
                return;
            }
            if (difference > 0) {
                for (int i = 1; i <= difference; i++) {
                    addThread();
                }
                return;
            } else {
                for (int i = difference; i <= 0; i++) {
                    removeThread();
                }
            }
        }
    }

    private void removeThread() {
        synchronized (taskThreads) {
            if (taskThreads.size() == 0) {
                return;
            }
            PriorityTaskThread<T> taskThread = taskThreads.get(taskThreads.size() - 1);
            taskThread.markToStop();
        }
    }

    public void stopRunning() {
        synchronized (taskThreads) {
            markToStop = true;
            for (PriorityTaskThread taskThread : taskThreads) {
                taskThread.markToStop();
            }
        }
    }
}
