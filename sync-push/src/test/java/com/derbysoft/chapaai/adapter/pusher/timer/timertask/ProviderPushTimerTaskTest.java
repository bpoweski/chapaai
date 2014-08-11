package com.derbysoft.chapaai.adapter.pusher.timer.timertask;

import com.derbysoft.chapaai.adapter.core.concurrent.PriorityTaskQueue;
import com.derbysoft.chapaai.adapter.pusher.task.PushTask;
import com.derbysoft.chapaai.adapter.pusher.task.generator.PushTaskGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProviderPushTimerTaskTest {

    private ProviderPushTimerTask timerTask;

    @Test
    public void testRun() {
        PushTaskGenerator taskGenerator = timerTask.getTaskGenerator();
        String providerCode = timerTask.getProviderCode();
        ProviderPushTimerTask spyTimerTask = spy(timerTask);

        List<PushTask> pushTasks = new ArrayList<PushTask>();
        when(taskGenerator.pushGenerate(eq(providerCode))).thenReturn(pushTasks);


        spyTimerTask.run();

        verify(taskGenerator,times(1)).pushGenerate(providerCode);
        verify(spyTimerTask,times(1)).addTasksToQueue(pushTasks);
    }


    @Test
    public void testAddTasksToQueue(){
        PriorityTaskQueue<PushTask> pushTaskQueue = timerTask.getPushTaskQueue();

        List<PushTask> input = new ArrayList<PushTask>();
        for(int i = 0;i< 10; i ++){
            input.add(mock(PushTask.class));
        }


        timerTask.addTasksToQueue(input);


        for(PushTask task:input){
            verify(pushTaskQueue,times(1)).addTask(task);
        }


    }

    @Before
    public void setup() {
        timerTask = new ProviderPushTimerTask();

        PriorityTaskQueue<PushTask> pushTaskQueue = mock(PriorityTaskQueue.class);
        PushTaskGenerator taskGenerator = mock(PushTaskGenerator.class);
        String providerCode = "providerCode";

        timerTask.setProviderCode(providerCode);
        timerTask.setPushTaskQueue(pushTaskQueue);
        timerTask.setTaskGenerator(taskGenerator);
    }


}
