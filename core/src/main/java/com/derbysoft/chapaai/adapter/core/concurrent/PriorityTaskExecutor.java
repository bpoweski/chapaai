package com.derbysoft.chapaai.adapter.core.concurrent;

public interface PriorityTaskExecutor<T extends PriorityTask> {
    void execute(T task);
}
