package com.derbysoft.chapaai.adapter.core.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityTaskQueue<T extends PriorityTask> {
    private final Set<String> allTaskIDs = new HashSet<String>();
    private final Set<T> runningTasks = Collections.synchronizedSet(new HashSet<T>());
    private final PriorityBlockingQueue<T> waitTaskQueue = new PriorityBlockingQueue<T>();

    public Set<String> getAllTaskIDs() {
        return allTaskIDs;
    }

    public Set<T> getRunningTasks() {
        return runningTasks;
    }

    public boolean addTask(T task) {
        synchronized (allTaskIDs) {
            if (allTaskIDs.contains(task.getTaskId())) {
                return false;
            }
            if (waitTaskQueue.offer(task)) {
                allTaskIDs.add(task.getTaskId());
                return true;
            } else {
                throw new IllegalStateException("wait task queue is full!");
            }
        }
    }

    public T hold(long timeOut) {
        try {
            T t = waitTaskQueue.poll(timeOut, TimeUnit.SECONDS);
            if (t == null) {
                return null;
            } else {
                runningTasks.add(t);
                return t;
            }
        } catch (InterruptedException e) {
            return null;
        }
    }

    public void release(T task) {
        synchronized (allTaskIDs) {
            if (runningTasks.remove(task)) {
                allTaskIDs.remove(task.getTaskId());
            }
        }
    }

    public int getSize() {
        synchronized (allTaskIDs) {
            return allTaskIDs.size();
        }
    }
}
